package cn.com.ss.customer.generate.domain;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.domain
 * @date 2018-05-22 22:06
 */
public class TableColumnInfo {
    /**
     * 字段名
     */
    protected String actualColumnName;
    /**
     * 字段类型
     */
    protected int jdbcType;
    /**
     * 字段类型名称
     */
    protected String jdbcTypeName;
    /**
     * 是否可以为null
     */
    protected boolean nullable;
    /**
     * 长度
     */
    protected int length;
    /**
     * 是否主键
     */
    protected boolean isSequenceColumn;

    /**
     *  字段备注信息
     */
    protected String remarks;
    /**
     * 默认值
     */
    protected String defaultValue;
    /**
     *  对象中属性名称
     */
    protected String domainColumnName;
    /**
     * 自增长
     */
    protected boolean autoIncrement;
    /**
     * 规模
     */
    protected int scale;
    /**
     * 工具生成字段
     */
    protected boolean generatedColumn;

    public String getActualColumnName() {
        return actualColumnName;
    }

    public void setActualColumnName(String actualColumnName) {
        this.actualColumnName = actualColumnName;
    }

    public int getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isSequenceColumn() {
        return isSequenceColumn;
    }

    public void setSequenceColumn(boolean sequenceColumn) {
        isSequenceColumn = sequenceColumn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDomainColumnName() {
        return domainColumnName;
    }

    public void setDomainColumnName(String domainColumnName) {
        this.domainColumnName = domainColumnName;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
   }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }

    public void setGeneratedColumn(boolean generatedColumn) {
        this.generatedColumn = generatedColumn;
    }

    public boolean isGeneratedColumn() {
        return generatedColumn;
    }

    @Override
    public String toString() {
        return "TableColumnInfo{" +
                "actualColumnName='" + actualColumnName + '\'' +
                ", jdbcType=" + jdbcType +
                ", jdbcTypeName='" + jdbcTypeName + '\'' +
                ", nullable=" + nullable +
                ", length=" + length +
                ", isSequenceColumn=" + isSequenceColumn +
                ", remarks='" + remarks + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", domainColumnName='" + domainColumnName + '\'' +
                ", autoIncrement=" + autoIncrement +
                ", scale=" + scale +
                ", generatedColumn=" + generatedColumn +
                '}';
    }
}
