package cn.com.ss.generate.convert.type;

import cn.com.ss.generate.convert.dbinfo.DBColumnInfo;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;

/**
 * @author chenshijie
 * @title 数据库类型到Java类型转换
 * @email chensj@winning.com.cm
 * @package cn.com.ss.generate.convert.type
 * @date 2018-05-22 21:03
 */
public class JavaTypeResolver {
    protected List<String> warnings;

    protected Properties properties;

    protected boolean forceBigDecimals;

    protected Map<Integer, JavaTypeResolver.JdbcTypeInformation> typeMap;

    public JavaTypeResolver() {
        super();
        properties = new Properties();
        typeMap = new HashMap<Integer, JavaTypeResolver.JdbcTypeInformation>();

        typeMap.put(Types.ARRAY, new JavaTypeResolver.JdbcTypeInformation("ARRAY", //$NON-NLS-1$
                new FullyJavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.BIGINT, new JavaTypeResolver.JdbcTypeInformation("BIGINT", //$NON-NLS-1$
                new FullyJavaTypeInfo(Long.class.getName())));
        typeMap.put(Types.BINARY, new JavaTypeResolver.JdbcTypeInformation("BINARY", //$NON-NLS-1$
                new FullyJavaTypeInfo("byte[]"))); //$NON-NLS-1$
        typeMap.put(Types.BIT, new JavaTypeResolver.JdbcTypeInformation("BIT", //$NON-NLS-1$
                new FullyJavaTypeInfo(Boolean.class.getName())));
        typeMap.put(Types.BLOB, new JavaTypeResolver.JdbcTypeInformation("BLOB", //$NON-NLS-1$
                new FullyJavaTypeInfo("byte[]"))); //$NON-NLS-1$
        typeMap.put(Types.BOOLEAN, new JavaTypeResolver.JdbcTypeInformation("BOOLEAN", //$NON-NLS-1$
                new FullyJavaTypeInfo(Boolean.class.getName())));
        typeMap.put(Types.CHAR, new JavaTypeResolver.JdbcTypeInformation("CHAR", //$NON-NLS-1$
                new FullyJavaTypeInfo(String.class.getName())));
        typeMap.put(Types.CLOB, new JavaTypeResolver.JdbcTypeInformation("CLOB", //$NON-NLS-1$
                new FullyJavaTypeInfo(String.class.getName())));
        typeMap.put(Types.DATALINK, new JavaTypeResolver.JdbcTypeInformation("DATALINK", //$NON-NLS-1$
                new FullyJavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.DATE, new JavaTypeResolver.JdbcTypeInformation("DATE", //$NON-NLS-1$
                new FullyJavaTypeInfo(Date.class.getName())));
        typeMap.put(Types.DECIMAL, new JavaTypeResolver.JdbcTypeInformation("DECIMAL", //$NON-NLS-1$
                new FullyJavaTypeInfo(BigDecimal.class.getName())));
        typeMap.put(Types.DISTINCT, new JavaTypeResolver.JdbcTypeInformation("DISTINCT", //$NON-NLS-1$
                new FullyJavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.DOUBLE, new JavaTypeResolver.JdbcTypeInformation("DOUBLE", //$NON-NLS-1$
                new FullyJavaTypeInfo(Double.class.getName())));
        typeMap.put(Types.FLOAT, new JavaTypeResolver.JdbcTypeInformation("FLOAT", //$NON-NLS-1$
                new FullyJavaTypeInfo(Double.class.getName())));
        typeMap.put(Types.INTEGER, new JavaTypeResolver.JdbcTypeInformation("INTEGER", //$NON-NLS-1$
                new FullyJavaTypeInfo(Integer.class.getName())));
        typeMap.put(Types.JAVA_OBJECT, new JavaTypeResolver.JdbcTypeInformation("JAVA_OBJECT", //$NON-NLS-1$
                new FullyJavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.LONGNVARCHAR, new JavaTypeResolver.JdbcTypeInformation("LONGNVARCHAR", //$NON-NLS-1$
                new FullyJavaTypeInfo(String.class.getName())));
        typeMap.put(Types.LONGVARBINARY, new JavaTypeResolver.JdbcTypeInformation(
                "LONGVARBINARY", //$NON-NLS-1$
                new FullyJavaTypeInfo("byte[]"))); //$NON-NLS-1$
        typeMap.put(Types.LONGVARCHAR, new JavaTypeResolver.JdbcTypeInformation("LONGVARCHAR", //$NON-NLS-1$
                new FullyJavaTypeInfo(String.class.getName())));
        typeMap.put(Types.NCHAR, new JavaTypeResolver.JdbcTypeInformation("NCHAR", //$NON-NLS-1$
                new FullyJavaTypeInfo(String.class.getName())));
        typeMap.put(Types.NCLOB, new JavaTypeResolver.JdbcTypeInformation("NCLOB", //$NON-NLS-1$
                new FullyJavaTypeInfo(String.class.getName())));
        typeMap.put(Types.NVARCHAR, new JavaTypeResolver.JdbcTypeInformation("NVARCHAR", //$NON-NLS-1$
                new FullyJavaTypeInfo(String.class.getName())));
        typeMap.put(Types.NULL, new JavaTypeResolver.JdbcTypeInformation("NULL", //$NON-NLS-1$
                new FullyJavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.NUMERIC, new JavaTypeResolver.JdbcTypeInformation("NUMERIC", //$NON-NLS-1$
                new FullyJavaTypeInfo(BigDecimal.class.getName())));
        typeMap.put(Types.OTHER, new JavaTypeResolver.JdbcTypeInformation("OTHER", //$NON-NLS-1$
                new FullyJavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.REAL, new JavaTypeResolver.JdbcTypeInformation("REAL", //$NON-NLS-1$
                new FullyJavaTypeInfo(Float.class.getName())));
        typeMap.put(Types.REF, new JavaTypeResolver.JdbcTypeInformation("REF", //$NON-NLS-1$
                new FullyJavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.SMALLINT, new JavaTypeResolver.JdbcTypeInformation("SMALLINT", //$NON-NLS-1$
                new FullyJavaTypeInfo(Short.class.getName())));
        typeMap.put(Types.STRUCT, new JavaTypeResolver.JdbcTypeInformation("STRUCT", //$NON-NLS-1$
                new FullyJavaTypeInfo(Object.class.getName())));
        typeMap.put(Types.TIME, new JavaTypeResolver.JdbcTypeInformation("TIME", //$NON-NLS-1$
                new FullyJavaTypeInfo(Date.class.getName())));
        typeMap.put(Types.TIMESTAMP, new JavaTypeResolver.JdbcTypeInformation("TIMESTAMP", //$NON-NLS-1$
                new FullyJavaTypeInfo(Date.class.getName())));
        typeMap.put(Types.TINYINT, new JavaTypeResolver.JdbcTypeInformation("TINYINT", //$NON-NLS-1$
                new FullyJavaTypeInfo(Byte.class.getName())));
        typeMap.put(Types.VARBINARY, new JavaTypeResolver.JdbcTypeInformation("VARBINARY", //$NON-NLS-1$
                new FullyJavaTypeInfo("byte[]"))); //$NON-NLS-1$
        typeMap.put(Types.VARCHAR, new JavaTypeResolver.JdbcTypeInformation("VARCHAR", //$NON-NLS-1$
                new FullyJavaTypeInfo(String.class.getName())));
    }


    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        forceBigDecimals = false;
    }


    public FullyJavaTypeInfo calculateJavaType(
            DBColumnInfo columnInfo) {
        FullyJavaTypeInfo answer = null;
        JavaTypeResolver.JdbcTypeInformation jdbcTypeInformation = typeMap
                .get(columnInfo.getJdbcType());

        if (jdbcTypeInformation != null) {
            answer = jdbcTypeInformation.getFullyJavaTypeInfo();
            answer = overrideDefaultType(columnInfo, answer);
        }

        return answer;
    }

    protected FullyJavaTypeInfo overrideDefaultType(DBColumnInfo column, FullyJavaTypeInfo defaultType) {
        FullyJavaTypeInfo answer = defaultType;

        switch (column.getJdbcType()) {
            case Types.BIT:
                answer = calculateBitReplacement(column, defaultType);
                break;
            case Types.DECIMAL:
            case Types.NUMERIC:
                answer = calculateBigDecimalReplacement(column, defaultType);
                break;
            default:
                break;
        }

        return answer;
    }

    protected FullyJavaTypeInfo calculateBitReplacement(DBColumnInfo column, FullyJavaTypeInfo defaultType) {
        FullyJavaTypeInfo answer;

        if (column.getLength() > 1) {
            answer = new FullyJavaTypeInfo("byte[]"); //$NON-NLS-1$
        } else {
            answer = defaultType;
        }

        return answer;
    }

    protected FullyJavaTypeInfo calculateBigDecimalReplacement(DBColumnInfo column, FullyJavaTypeInfo defaultType) {
        FullyJavaTypeInfo answer;

        if (column.getScale() > 0 || column.getLength() > 18 || forceBigDecimals) {
            answer = defaultType;
        } else if (column.getLength() > 9) {
            answer = new FullyJavaTypeInfo(Long.class.getName());
        } else if (column.getLength() > 4) {
            answer = new FullyJavaTypeInfo(Integer.class.getName());
        } else {
            answer = new FullyJavaTypeInfo(Short.class.getName());
        }

        return answer;
    }

    public String calculateJdbcTypeName(DBColumnInfo introspectedColumn) {
        String answer = null;
        JavaTypeResolver.JdbcTypeInformation jdbcTypeInformation = typeMap
                .get(introspectedColumn.getJdbcType());

        if (jdbcTypeInformation != null) {
            answer = jdbcTypeInformation.getJdbcTypeName();
        }

        return answer;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public static class JdbcTypeInformation {
        private String jdbcTypeName;

        private FullyJavaTypeInfo fullyJavaTypeInfo;

        public JdbcTypeInformation(String jdbcTypeName,
                                   FullyJavaTypeInfo fullyQualifiedJavaType) {
            this.jdbcTypeName = jdbcTypeName;
            this.fullyJavaTypeInfo = fullyQualifiedJavaType;
        }

        public String getJdbcTypeName() {
            return jdbcTypeName;
        }

        public FullyJavaTypeInfo getFullyJavaTypeInfo() {
            return fullyJavaTypeInfo;
        }
    }
}
