package cn.com.ss.customer.generate.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshijie
 * @title 表信息 数据库中表信息
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate
 * @date 2018-05-22 22:05
 */
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 对象名称
     */
    private String domainName;
    /**
     * 对象所在包
     */
    private String domainPackage;
    /**
     * 文件放置目录
     */
    private String domainPath;
    /**
     * 对象别名
     */
    private String alias;
    /**
     * 字段信息
     */
    private List<TableColumnInfo> tableColumnInfos;

    private List<String> primaryKeys;

    private ActualTableName actualTableName;

    private String catalog;
    private String schema;
    private String remark;

    public TableInfo() {
        this.tableColumnInfos = new ArrayList<>();
        this.primaryKeys = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainPackage() {
        return domainPackage;
    }

    public void setDomainPackage(String domainPackage) {
        this.domainPackage = domainPackage;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<TableColumnInfo> getTableColumnInfos() {
        return tableColumnInfos;
    }

    public void setTableColumnInfos(List<TableColumnInfo> tableColumnInfos) {
        this.tableColumnInfos = tableColumnInfos;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public void addPrimaryKeyColumn(String columnName) {
        this.primaryKeys.add(columnName);
    }

    public ActualTableName getActualTableName() {
        return actualTableName;
    }

    public void setActualTableName(ActualTableName actualTableName) {
        this.actualTableName = actualTableName;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", domainName='" + domainName + '\'' +
                ", domainPackage='" + domainPackage + '\'' +
                ", alias='" + alias + '\'' +
                ", tableColumnInfos=" + tableColumnInfos +
                ", primaryKeys=" + primaryKeys +
                ", actualTableName=" + actualTableName +
                ", catalog='" + catalog + '\'' +
                ", schema='" + schema + '\'' +
                '}';
    }
}
