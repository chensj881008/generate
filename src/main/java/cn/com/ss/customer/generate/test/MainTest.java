package cn.com.ss.customer.generate.test;

import cn.com.ss.customer.generate.domain.TableInfo;
import cn.com.ss.customer.generate.util.ConnectionUtil;
import cn.com.ss.customer.generate.util.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.test
 * @date 2018-05-25 15:17
 */
public class MainTest {

    private static Logger logger = LoggerFactory.getLogger(MainTest.class);


    public static void main(String[] args){
        logger.info("开始准备导出数据");
        Connection connection = null;
        try {
            logger.info("连接数据库");
            connection = ConnectionUtil.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            logger.info("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库连接失败，错误原因[{}]",e.getMessage());
            return;
        }
        try {
            TableInfo info = DatabaseUtils.getTableInfo("SYS_USER_INFO",connection);
            System.out.println(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
