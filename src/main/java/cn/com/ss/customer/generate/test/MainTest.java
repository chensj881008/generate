package cn.com.ss.customer.generate.test;

import cn.com.ss.customer.generate.code.java.JavaDomainGenerator;
import cn.com.ss.customer.generate.domain.TableInfo;
import cn.com.ss.customer.generate.util.ConnectionUtil;
import cn.com.ss.customer.generate.util.DatabaseUtils;
import cn.com.ss.customer.generate.util.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Map;

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
            TableInfo info = DatabaseUtils.getTableInfo("SYS_REPORT_INFO",connection);
            JavaDomainGenerator generator = new JavaDomainGenerator();
            generator.setTableInfo(info);
            Map<String,Object>  data = generator.generateHeader();
            Configuration config = new Configuration();
            Writer writer = null;
            File templateFile = new File(MainTest.class.getClassLoader().getResource("template").getPath());
            config.setDirectoryForTemplateLoading(templateFile);
            Template template = config.getTemplate("domain.ftl");
            String path = info.getDomainPath();
            String pack = info.getDomainPackage();
            String targePath = FileUtils.createABSPath(path,pack);
            File file = new File(targePath+File.separator+info.getDomainName()+".java");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(data,writer);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
