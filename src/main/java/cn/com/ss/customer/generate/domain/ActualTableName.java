package cn.com.ss.customer.generate.domain;

import static cn.com.ss.customer.generate.util.StringUtils.composeFullyQualifiedTableName;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.domain
 * @date 2018-05-22 22:59
 */
public class ActualTableName {

    private String tableName;
    private String catalog;
    private String schema;

    private String fullName;

    public ActualTableName(String catalog, String schema, String tableName) {
        this.catalog = catalog;
        this.schema = schema;
        this.tableName = tableName;
        fullName = composeFullyQualifiedTableName(catalog,
                schema, tableName, '.');
    }

    public String getCatalog() {
        return catalog;
    }

    public String getSchema() {
        return schema;
    }

    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
