package cn.com.ss.customer.generate.code.java;

import cn.com.ss.customer.generate.Constant;
import cn.com.ss.customer.generate.code.AbstractGenerator;
import cn.com.ss.customer.generate.domain.TableColumnInfo;
import cn.com.ss.customer.generate.domain.TableInfo;
import cn.com.ss.customer.generate.util.DatabaseNameUtils;
import cn.com.ss.customer.generate.util.DateUtils;
import cn.com.ss.customer.generate.util.FullyQualifiedJavaType;
import cn.com.ss.customer.generate.util.PropertiesLoader;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chensj
 * @title spring data jpa domain  Repository 等生成
 * @project generator
 * @package cn.com.ss.customer.generate.code.java
 * @date: 2019-05-07 22:59
 */
public class JavaJpaFileGenerator extends AbstractGenerator {

    /**
     * 生成实体类文件数据
     * @return map
     */
    public Map<String,Object> generateDomainData(){
        TableInfo t = this.getTableInfo();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packageName",t.getDomainPackage());
        // 设置引入类信息
        Set<String> importData = new HashSet<>();
        List<TableColumnInfo> props = t.getTableColumnInfos();
        for (TableColumnInfo prop : props) {
            if (!prop.getFullyQualifiedJavaType().toString().startsWith(FullyQualifiedJavaType.JAVA_LANG)
                    && !prop.getFullyQualifiedJavaType().isArray()){
                importData.add("import "+ prop.getFullyQualifiedJavaType() +"; ");
            }
        }
        importData.add("import java.io.Serializable; \n");
        dataMap.put("importData",importData);
        dataMap.put("author", PropertiesLoader.getProperty("config.author"));
        dataMap.put("title",t.getRemark() == null ? t.getTableName() : t.getRemark());
        dataMap.put("email", PropertiesLoader.getProperty("config.email"));
        dataMap.put("date", DateUtils.getCurrentDate());
        dataMap.put("alias",t.getAlias());
        dataMap.put("className",t.getDomainName());
        dataMap.put("tableName",t.getTableName());
        List<TableColumnInfo> propData = new ArrayList<>();
        List<TableColumnInfo> propPkData = new ArrayList<>();
        List<TableColumnInfo> propNumPkData = new ArrayList<>();
        for (TableColumnInfo prop : props) {
            String typeName =  prop.getFullyQualifiedJavaType().toString();
            String type = typeName.substring(typeName.lastIndexOf(".")+1);
            prop.setTypeName(type);
            if(prop.isSequenceColumn() && "Long".equals(type)){
                propNumPkData.add(prop);
            } else if(prop.isSequenceColumn() && "String".equals(type)){
                propPkData.add(prop);
            }else{
                propData.add(prop);
            }
        }
        dataMap.put("props",propData);
        dataMap.put("propNumPks",propNumPkData);
        dataMap.put("propStrPks",propPkData);
        List<String> methodData = new ArrayList<>();

        dataMap.put("methodData",methodData);
        return  dataMap;
    }

    /**
     * 生成Repository文件数据
     * @return
     */
    public Map<String,Object> generateRepositoryData(){
        TableInfo t = this.getTableInfo();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packageName", Constant.DAO_PACKAGE);
        // 设置引入类信息
        Set<String> importData = new HashSet<>();
        String idStr = "";
        List<TableColumnInfo> props = t.getTableColumnInfos();
        List<TableColumnInfo> pks = props.stream().filter(item -> item.isSequenceColumn()).collect(Collectors.toList());
        for (TableColumnInfo prop : pks) {
            if(prop.isSequenceColumn()){
                String typeName =  prop.getFullyQualifiedJavaType().toString();
                String type = typeName.substring(typeName.lastIndexOf(".")+1);
                idStr = type;
            }
        }
        importData.add("import "+t.getDomainPackage()+"."+t.getDomainName()+";");
        dataMap.put("idStr",idStr);
        dataMap.put("importData",importData);
        dataMap.put("author", PropertiesLoader.getProperty("config.author"));
        dataMap.put("title",t.getRemark() == null ? t.getTableName() : t.getRemark());
        dataMap.put("email", PropertiesLoader.getProperty("config.email"));
        dataMap.put("date", DateUtils.getCurrentDate());
        dataMap.put("alias",t.getAlias());
        dataMap.put("className",t.getDomainName());
        dataMap.put("tableName",t.getTableName());
        return  dataMap;
    }
    /**
     * 生成Service层文件数据
     * @return map
     */
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
        dataMap.put("paramT", DatabaseNameUtils.convertFromDBToJava(t.getTableName(),1));
        return  dataMap;
    }

    /**
     * 生成Service 实现层文件数据
     * @return map
     */
    public Map<String,Object>  generateJavaServiceImplData(){
        TableInfo t = this.getTableInfo();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packageName", Constant.SERVICEIMPL_PACKAGE);
        List<String> importData = new ArrayList<>();
        importData.add("import java.util.List;  \n");
        importData.add("import "+t.getDomainPackage()+"."+t.getDomainName()+";  \n");
        importData.add("import "+ Constant.DAO_PACKAGE+"."+t.getDomainName()+"Repository;  \n");
        importData.add("import "+ Constant.SERVICE_PACKAGE+"."+t.getDomainName()+"Service;  \n");
        dataMap.put("importData",importData);
        dataMap.put("author",PropertiesLoader.getProperty("config.author"));
        dataMap.put("title",t.getRemark() == null ? t.getTableName() :"".equals(t.getRemark()) ? t.getTableName() :t.getRemark()  +"服务接口");
        dataMap.put("email", PropertiesLoader.getProperty("config.email"));
        dataMap.put("date",DateUtils.getCurrentDate());
        dataMap.put("className",t.getDomainName()+"ServiceImpl");
        dataMap.put("pClassName",t.getDomainName()+"Service");
        dataMap.put("daoClassName",t.getDomainName()+"Repository");
        dataMap.put("daoClassNameT",DatabaseNameUtils.convertFromDBToJava(t.getTableName(),1) +"Repository");
        dataMap.put("domainName",t.getDomainName());
        dataMap.put("paramT",DatabaseNameUtils.convertFromDBToJava(t.getTableName(),1));
        return  dataMap;
    }


}
