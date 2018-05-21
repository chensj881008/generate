package cn.com.ss.generate.config;

import cn.com.ss.generate.util.ConnectionUtil;
import cn.com.ss.generate.util.PropertiesLoader;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.generate.config
 * @date 2018-05-21 21:47
 */
public class Configuration {

    private Connection connection;
    private List<String> tableNameList;
    private boolean comment;
    private String packageName;
    private String pathName;

    public Configuration() {
        this.connection = ConnectionUtil.getConnection();
        this.tableNameList = PropertiesLoader.getTableNameList();
        this.comment = Boolean.valueOf(PropertiesLoader.getProperty("config.comment"));
        this.packageName = PropertiesLoader.getProperty("config.package");
        this.pathName = PropertiesLoader.getProperty("config.path");
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<String> getTableNameList() {
        return tableNameList;
    }

    public void setTableNameList(List<String> tableNameList) {
        this.tableNameList = tableNameList;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}
