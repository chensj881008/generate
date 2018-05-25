package cn.com.ss.customer.generate.util;

import java.sql.*;

/**
 * @author chenshijie
 * @title 获取数据库连接信息
 * @email chensj@winning.com.cm
 * @package cn.com.ss.generate.util
 * @date 2018-05-21 21:57
 */
public class ConnectionUtil {

    /**
     * @return Connection
     */
    public static synchronized Connection getConnection() throws Exception {
        // 读出配置信息
        String driverClassName = PropertiesLoader.getProperty("db.driverClassName");
        String url = PropertiesLoader.getProperty("db.url");
        String username = PropertiesLoader.getProperty("db.username");
        String password = PropertiesLoader.getProperty("db.password");

        Connection conn = null;
        try {
            // 加载数据库驱动程序
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return conn;
    }

    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet( ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private ConnectionUtil() {
    }
}
