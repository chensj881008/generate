package cn.com.ss.generate.convert;

import cn.com.ss.generate.convert.dbinfo.DBTableInfo;
import cn.com.ss.generate.convert.dbinfo.GenerateTableInfo;
import cn.com.ss.generate.convert.type.JavaTypeResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenshijie
 * @title 转换Table到Domain
 * @email chensj@winning.com.cm
 * @package cn.com.ss.generate.convert
 * @date 2018-05-22 21:04
 */
public class ConvertTableToDomain {

    private DatabaseMetaData databaseMetaData;

    private JavaTypeResolver javaTypeResolver;

    private Log logger;

    public ConvertTableToDomain(DatabaseMetaData databaseMetaData, JavaTypeResolver javaTypeResolver, Log logger) {
        this.databaseMetaData = databaseMetaData;
        this.javaTypeResolver = javaTypeResolver;
        this.logger = LogFactory.getLog(getClass());
    }

    private void calculatePrimaryKey(DBTableInfo table,
                                     GenerateTableInfo introspectedTable) {
        ResultSet rs = null;

        try {
            rs = databaseMetaData.getPrimaryKeys(
                    table.getCatalog(), table.getSchema(), table.getTableName());
        } catch (SQLException e) {
            closeResultSet(rs);
            System.out.println(e.getMessage());
            return;
        }

        try {
            // keep primary columns in key sequence order
            Map<Short, String> keyColumns = new TreeMap<Short, String>();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME"); //$NON-NLS-1$
                short keySeq = rs.getShort("KEY_SEQ"); //$NON-NLS-1$
                keyColumns.put(keySeq, columnName);
            }

            for (String columnName : keyColumns.values()) {
                introspectedTable.addPrimaryKeyColumn(columnName);
            }
        } catch (SQLException e) {
            // ignore the primary key if there's any error
        } finally {
            closeResultSet(rs);
        }
    }

    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }
}
