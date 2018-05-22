package cn.com.ss.generate.convert.dbinfo;

/**
 * @author chenshijie
 * @title 数据库表信息
 * @email chensj@winning.com.cm
 * @package cn.com.ss.generate.convert
 * @date 2018-05-22 21:02
 */
public class DBTableInfo {

    private String  catalog;

    private String schema;
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
     * 对象别名
     */
    private String alias;

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
}
