package cn.com.ss.customer.generate.util;

import cn.com.ss.customer.generate.domain.TableColumnInfo;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author chenshijie
 * @title java类型与数据库类型转换
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.util
 * @date 2018-05-25 21:26
 */
public class JavaTypeResolverUtils {

    protected boolean forceBigDecimals;

    protected static Map<Integer, JdbcTypeInformation> typeMap;

    static {
        typeMap = new HashMap<Integer, JdbcTypeInformation>();

        typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", //$NON-NLS-1$
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Long.class.getName())));
        typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", //$NON-NLS-1$
                new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
        typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Boolean.class.getName())));
        typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", //$NON-NLS-1$
                new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
        typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", //$NON-NLS-1$
                new FullyQualifiedJavaType(Boolean.class.getName())));
        typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", //$NON-NLS-1$
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", //$NON-NLS-1$
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", //$NON-NLS-1$
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", //$NON-NLS-1$
                new FullyQualifiedJavaType(Date.class.getName())));
        typeMap.put(Types.DECIMAL, new JdbcTypeInformation("DECIMAL", //$NON-NLS-1$
                new FullyQualifiedJavaType(BigDecimal.class.getName())));
        typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", //$NON-NLS-1$
                new FullyQualifiedJavaType(Double.class.getName())));
        typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Double.class.getName())));
        typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", //$NON-NLS-1$
                new FullyQualifiedJavaType(Integer.class.getName())));
        typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR", //$NON-NLS-1$
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation(
                "LONGVARBINARY", //$NON-NLS-1$
                new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
        typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", //$NON-NLS-1$
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.NCHAR, new JdbcTypeInformation("NCHAR", //$NON-NLS-1$
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.NCLOB, new JdbcTypeInformation("NCLOB", //$NON-NLS-1$
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR", //$NON-NLS-1$
                new FullyQualifiedJavaType(String.class.getName())));
        typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", //$NON-NLS-1$
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.NUMERIC, new JdbcTypeInformation("NUMERIC", //$NON-NLS-1$
                new FullyQualifiedJavaType(BigDecimal.class.getName())));
        typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", //$NON-NLS-1$
                new FullyQualifiedJavaType(Float.class.getName())));
        typeMap.put(Types.REF, new JdbcTypeInformation("REF", //$NON-NLS-1$
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Short.class.getName())));
        typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Object.class.getName())));
        typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", //$NON-NLS-1$
                new FullyQualifiedJavaType(Date.class.getName())));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", //$NON-NLS-1$
                new FullyQualifiedJavaType(Date.class.getName())));
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Byte.class.getName())));
        typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", //$NON-NLS-1$
                new FullyQualifiedJavaType("byte[]"))); //$NON-NLS-1$
        typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", //$NON-NLS-1$
                new FullyQualifiedJavaType(String.class.getName())));
    }

    private JavaTypeResolverUtils() {
        super();
    }


    public static FullyQualifiedJavaType generateJavaType(TableColumnInfo tableColumnInfo) {
        FullyQualifiedJavaType answer = null;
        JdbcTypeInformation jdbcTypeInformation = typeMap.get(tableColumnInfo.getJdbcType());

        if (jdbcTypeInformation != null) {
            answer = jdbcTypeInformation.getFullyQualifiedJavaType();
            answer = overrideDefaultType(tableColumnInfo, answer);
        }

        return answer;
    }

    protected static FullyQualifiedJavaType overrideDefaultType(TableColumnInfo tableColumnInfo, FullyQualifiedJavaType defaultType) {
        FullyQualifiedJavaType answer = defaultType;

        switch (tableColumnInfo.getJdbcType()) {
            case Types.BIT:
                answer = generateBitReplacement(tableColumnInfo, defaultType);
                break;
            case Types.DECIMAL:
            case Types.NUMERIC:
                answer = generateBigDecimalReplacement(tableColumnInfo, defaultType);
                break;
            default:
                break;
        }

        return answer;
    }

    /**
     * 字节数据转换
     * @param tableColumnInfo
     * @param defaultType
     * @return
     */
    protected static FullyQualifiedJavaType generateBitReplacement(TableColumnInfo tableColumnInfo, FullyQualifiedJavaType defaultType) {
        FullyQualifiedJavaType answer;

        if (tableColumnInfo.getLength() > 1) {
            answer = new FullyQualifiedJavaType("byte[]");
        } else {
            answer = defaultType;
        }

        return answer;
    }

    /**
     * BigDecimal类型转换
     * @param column
     * @param defaultType
     * @return
     */
    protected static FullyQualifiedJavaType generateBigDecimalReplacement(TableColumnInfo column, FullyQualifiedJavaType defaultType) {
        FullyQualifiedJavaType answer;

        if (column.getScale() > 0 || column.getLength() > 18) {
            answer = defaultType;
        } else if (column.getLength() > 9) {
            answer = new FullyQualifiedJavaType(Long.class.getName());
        } else if (column.getLength() > 4) {
            answer = new FullyQualifiedJavaType(Integer.class.getName());
        } else {
            answer = new FullyQualifiedJavaType(Short.class.getName());
        }

        return answer;
    }

    /**
     * 获取JDBC类型名称
     * @param column
     * @return
     */
    public static String generateJdbcTypeName(TableColumnInfo column) {
        String answer = null;
        JdbcTypeInformation jdbcTypeInformation = typeMap
                .get(column.getJdbcType());

        if (jdbcTypeInformation != null) {
            answer = jdbcTypeInformation.getJdbcTypeName();
        }

        return answer;
    }


    public static class JdbcTypeInformation {
        private String jdbcTypeName;

        private FullyQualifiedJavaType fullyQualifiedJavaType;

        public JdbcTypeInformation(String jdbcTypeName,
                                   FullyQualifiedJavaType fullyQualifiedJavaType) {
            this.jdbcTypeName = jdbcTypeName;
            this.fullyQualifiedJavaType = fullyQualifiedJavaType;
        }

        public String getJdbcTypeName() {
            return jdbcTypeName;
        }

        public FullyQualifiedJavaType getFullyQualifiedJavaType() {
            return fullyQualifiedJavaType;
        }
    }

    public boolean isForceBigDecimals() {
        return forceBigDecimals;
    }

    public void setForceBigDecimals(boolean forceBigDecimals) {
        this.forceBigDecimals = forceBigDecimals;
    }

}
