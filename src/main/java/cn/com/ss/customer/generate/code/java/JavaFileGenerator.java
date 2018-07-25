package cn.com.ss.customer.generate.code.java;

import cn.com.ss.customer.generate.Constant;
import cn.com.ss.customer.generate.code.AbstractGenerator;
import cn.com.ss.customer.generate.domain.TableColumnInfo;
import cn.com.ss.customer.generate.domain.TableInfo;
import cn.com.ss.customer.generate.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.code.java
 * @date 2018-05-25 22:27
 */
public class JavaFileGenerator extends AbstractGenerator {

    public JavaFileGenerator() {
    }

    public Map<String,Object>  generateDomainData(){
        TableInfo t = this.getTableInfo();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packageName",t.getDomainPackage());
        List<String> importData = new ArrayList<>();
        List<TableColumnInfo> props = t.getTableColumnInfos();
        for (TableColumnInfo prop : props) {
            if (!prop.getFullyQualifiedJavaType().toString().startsWith(FullyQualifiedJavaType.JAVA_LANG)){
                importData.add("import "+ prop.getFullyQualifiedJavaType() +"; ");
            }
        }
        importData.add("import java.io.Serializable; \n");
        importData.add("import org.apache.ibatis.type.Alias; \n");
        importData.add("import "+ t.getDomainPackage() +".BaseDomain;\n");
        dataMap.put("importData",importData);
        dataMap.put("author",PropertiesLoader.getProperty("config.author"));
        dataMap.put("title",t.getRemark() == null ? t.getTableName() : t.getRemark());
        dataMap.put("email", PropertiesLoader.getProperty("config.email"));
        dataMap.put("date",DateUtils.getCurrentDate());
        dataMap.put("alias",t.getAlias());
        dataMap.put("className",t.getDomainName());
        List<TableColumnInfo> propData = new ArrayList<>();
        for (TableColumnInfo prop : props) {
            String typeName =  prop.getFullyQualifiedJavaType().toString();
            String type = typeName.substring(typeName.lastIndexOf(".")+1);
            prop.setTypeName(type);
            propData.add(prop);
        }
        dataMap.put("props",propData);
        List<String> methodData = new ArrayList<>();

        dataMap.put("methodData",methodData);
        return  dataMap;
    }

    public Map<String,Object>  generateJavaClientData(){
        TableInfo t = this.getTableInfo();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packageName", Constant.DAO_PACKAGE);
        List<String> importData = new ArrayList<>();
        importData.add("\n");
        importData.add("import java.util.List;  \n");
        importData.add("import org.springframework.dao.DataAccessException;  \n");
        importData.add("import "+t.getDomainPackage()+"."+t.getDomainName()+";  \n");
        importData.add("\n");
        dataMap.put("importData",importData);
        dataMap.put("author",PropertiesLoader.getProperty("config.author"));
        dataMap.put("title",t.getRemark() == null ? t.getTableName() : t.getRemark()+"DAO接口");
        dataMap.put("email", PropertiesLoader.getProperty("config.email"));
        dataMap.put("date",DateUtils.getCurrentDate());
        dataMap.put("className",t.getDomainName()+"Dao");
        dataMap.put("domainName",t.getDomainName());
        dataMap.put("paramT",DatabaseNameUtils.convertFromDBToJava(t.getTableName(),1));
        return  dataMap;
    }

    public Map<String,Object>  generateSqlMapData(){
        TableInfo t = this.getTableInfo();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("daoName", Constant.DAO_PACKAGE+"."+t.getDomainName()+"Dao");
        // spring mybatis 使用
        dataMap.put("param",DatabaseNameUtils.convertFromDBToJava(t.getTableName(),1));
        //spring-boot 需要
        dataMap.put("paramType",Constant.DOMAIN_PACKAGE+"."+DatabaseNameUtils.convertFromDBToJava(t.getTableName(),0));
        dataMap.put("cols", t.getTableColumnInfos());
        dataMap.put("tableName",t.getTableName());

        StringBuilder pks = new StringBuilder();
        StringBuilder pkString = new StringBuilder();
        StringBuilder pkListStr = new StringBuilder();
        List<String> pk = t.getPrimaryKeys();
        for(int i=0 ; i< pk.size() ; i++){
            String prop = DatabaseNameUtils.convertFromDBToJava(pk.get(i),1);
            if(i == pk.size() - 1){
                pks.append("t."+pk.get(i));
                pkString.append(pk.get(i));
                pkListStr.append("("+prop+" !=null or " +prop+ "!='')" );
            }else {
                pks.append("t."+pk.get(i)+",");
                pkString.append(pk.get(i)+",");
                pkListStr.append("("+prop+" !=null or " +prop+ "!='') and " );
            }

        }
        dataMap.put("pks",pks.toString());
        dataMap.put("pkString",pkString.toString());
        dataMap.put("pkListStr",pkListStr.toString());
        List<TableColumnInfo> columnInfos = t.getTableColumnInfos();
        List<TableColumnInfo> pkList = new ArrayList<>();
        for(int i=0 ; i< pk.size() ; i++){
            for (TableColumnInfo info : columnInfos) {
                if(pk.get(i).equals(info.getActualColumnName())){
                    pkList.add(info);
                }
            }

        }
        dataMap.put("pkList",pkList);
        List<String> importData = new ArrayList<>();
        importData.add("\n");
        importData.add("import java.util.List;  \n");
        importData.add("import org.springframework.dao.DataAccessException;  \n");
        importData.add("import "+t.getDomainPackage()+"."+t.getDomainName()+";  \n");
        importData.add("\n");
        dataMap.put("importData",importData);
        dataMap.put("author",PropertiesLoader.getProperty("config.author"));
        dataMap.put("title",t.getTableName()+"_DAO");
        dataMap.put("email", PropertiesLoader.getProperty("config.email"));
        dataMap.put("date",DateUtils.getCurrentDate());
        dataMap.put("className",t.getDomainName()+"Dao");
        dataMap.put("domainName",t.getDomainName());

        return  dataMap;
    }

    public Map<String,Object>  generateJavaServiceData(){
        TableInfo t = this.getTableInfo();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packageName", Constant.SERVICE_PACKAGE);
        List<String> importData = new ArrayList<>();
        importData.add("import java.util.List;  \n");
        importData.add("import "+t.getDomainPackage()+"."+t.getDomainName()+";  \n");
        dataMap.put("importData",importData);
        dataMap.put("author",PropertiesLoader.getProperty("config.author"));
        dataMap.put("title",t.getRemark() == null ? t.getTableName() :"".equals(t.getRemark()) ? t.getTableName() :t.getRemark()  +"服务接口");
        dataMap.put("email", PropertiesLoader.getProperty("config.email"));
        dataMap.put("date",DateUtils.getCurrentDate());
        dataMap.put("className",t.getDomainName()+"Service");
        dataMap.put("domainName",t.getDomainName());
        dataMap.put("paramT",DatabaseNameUtils.convertFromDBToJava(t.getTableName(),1));
        return  dataMap;
    }

    public Map<String,Object>  generateJavaServiceImplData(){
        TableInfo t = this.getTableInfo();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packageName", Constant.SERVICEIMPL_PACKAGE);
        List<String> importData = new ArrayList<>();
        importData.add("import java.util.List;  \n");
        importData.add("import "+t.getDomainPackage()+"."+t.getDomainName()+";  \n");
        importData.add("import "+ Constant.DAO_PACKAGE+"."+t.getDomainName()+"Dao;  \n");
        importData.add("import "+ Constant.SERVICE_PACKAGE+"."+t.getDomainName()+"Service;  \n");
        dataMap.put("importData",importData);
        dataMap.put("author",PropertiesLoader.getProperty("config.author"));
        dataMap.put("title",t.getRemark() == null ? t.getTableName() :"".equals(t.getRemark()) ? t.getTableName() :t.getRemark()  +"服务接口");
        dataMap.put("email", PropertiesLoader.getProperty("config.email"));
        dataMap.put("date",DateUtils.getCurrentDate());
        dataMap.put("className",t.getDomainName()+"ServiceImpl");
        dataMap.put("pClassName",t.getDomainName()+"Service");
        dataMap.put("daoClassName",t.getDomainName()+"Dao");
        dataMap.put("daoClassNameT",DatabaseNameUtils.convertFromDBToJava(t.getTableName(),1) +"Dao");
        dataMap.put("domainName",t.getDomainName());
        dataMap.put("paramT",DatabaseNameUtils.convertFromDBToJava(t.getTableName(),1));
        return  dataMap;
    }
}
