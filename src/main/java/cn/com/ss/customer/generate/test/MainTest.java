package cn.com.ss.customer.generate.test;

import cn.com.ss.customer.generate.Constant;
import cn.com.ss.customer.generate.code.java.JavaFileGenerator;
import cn.com.ss.customer.generate.domain.TableInfo;
import cn.com.ss.customer.generate.util.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    public static void main(String[] args) {
        logger.info("开始准备导出数据");
        Connection connection = null;
        try {
            logger.info("连接数据库");
            connection = ConnectionUtil.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            logger.info("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库连接失败，错误原因[{}]", e.getMessage());
            return;
        }
        try {
            TableInfo info = DatabaseUtils.getTableInfo("SYS_REPORT_INFO", connection);
            JavaFileGenerator generator = new JavaFileGenerator();
            generator.setTableInfo(info);
            Map<String, Object> domainData = generator.generateDomainData();
            Map<String, Object> clientData = generator.generateJavaClientData();
            Map<String, Object> sqlMapData = generator.generateSqlMapData();
            Map<String, Object> serviceData = generator.generateJavaServiceData();
            Map<String, Object>  serviceImplData = generator.generateJavaServiceImplData();
//            generateDomainFile(domainData, info);
//            generateClientFile(clientData,info);
//            generateSqlMapFile(sqlMapData,info);
//            generateMyBatisConfigFile();
           // generateServiceFile(serviceData,info);
            //generateServiceImplFile(serviceImplData,info);

            List<String> tableList = new ArrayList<>();
            tableList.add("ET_USER_INFO");
            tableList.add("SYS_REPORT_INFO");
            GenerateFileUtils.generateFacdeFile(tableList);
            GenerateFileUtils.generateFacdeImplFile(tableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void generateServiceImplFile(Map<String, Object> data, TableInfo info) {
        Configuration config = new Configuration();
        Writer writer = null;
        try {
            File templateFile = new File(MainTest.class.getClassLoader().getResource("template").getPath());
            config.setDirectoryForTemplateLoading(templateFile);
            Template template = config.getTemplate("servicesImpl.ftl");
            String path = info.getDomainPath();
            String pack = Constant.SERVICEIMPL_PACKAGE;
            String targePath = FileUtils.createABSPath(path, pack);
            File file = new File(targePath + File.separator + info.getDomainName() + "ServiceImpl.java");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            if(writer != null ){
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void generateDomainFile(Map<String, Object> data, TableInfo info) {
        Configuration config = new Configuration();
        Writer writer = null;
        try {
            File templateFile = new File(MainTest.class.getClassLoader().getResource("template").getPath());
            config.setDirectoryForTemplateLoading(templateFile);
            Template template = config.getTemplate("domain.ftl");
            String path = info.getDomainPath();
            String pack = info.getDomainPackage();
            String targePath = FileUtils.createABSPath(path, pack);
            File file = new File(targePath + File.separator + info.getDomainName() + ".java");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            if(writer != null ){
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void generateClientFile(Map<String, Object> data, TableInfo info) {
        Configuration config = new Configuration();
        Writer writer = null;
        try {
            File templateFile = new File(MainTest.class.getClassLoader().getResource("template").getPath());
            config.setDirectoryForTemplateLoading(templateFile);
            Template template = config.getTemplate("client.ftl");
            String path = info.getDomainPath();
            String pack = Constant.DAO_PACKAGE;
            String targePath = FileUtils.createABSPath(path, pack);
            File file = new File(targePath + File.separator + info.getDomainName() + "Dao.java");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            if(writer != null ){
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void generateMyBatisConfigFile() {
         Map<String,Object> data = new HashMap<>();
        data.put("domainPackage",Constant.DOMAIN_PACKAGE);
        Configuration config = new Configuration();
        Writer writer = null;
        try {
            File templateFile = new File(MainTest.class.getClassLoader().getResource("template").getPath());
            config.setDirectoryForTemplateLoading(templateFile);
            Template template = config.getTemplate("mybatis.ftl");
            String path = Constant.PATH;
            String pack = Constant.MAPPER_PACKAGE;
            String targePath = FileUtils.createABSPath(path, pack);
            File file = new File(targePath + File.separator + "mybatis-config.xml");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            if(writer != null ){
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void generateSqlMapFile(Map<String, Object> data, TableInfo info) {
        Configuration config = new Configuration();
        Writer writer = null;
        try {
            File templateFile = new File(MainTest.class.getClassLoader().getResource("template").getPath());
            config.setDirectoryForTemplateLoading(templateFile);
            Template template = config.getTemplate("sqlmap.ftl");
            String path = info.getDomainPath();
            String pack = Constant.MAPPER_PACKAGE+".mapper";
            String targePath = FileUtils.createABSPath(path, pack);
            File file = new File(targePath + File.separator + info.getTableName() + "_SqlMap.xml");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            if(writer != null ){
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void generateServiceFile(Map<String, Object> data, TableInfo info) {
        Configuration config = new Configuration();
        Writer writer = null;
        try {
            File templateFile = new File(MainTest.class.getClassLoader().getResource("template").getPath());
            config.setDirectoryForTemplateLoading(templateFile);
            Template template = config.getTemplate("services.ftl");
            String path = info.getDomainPath();
            String pack = Constant.SERVICE_PACKAGE;
            String targePath = FileUtils.createABSPath(path, pack);
            File file = new File(targePath + File.separator + info.getDomainName() + "Service.java");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            if(writer != null ){
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
