package cn.com.ss.customer.generate.code.java;

import cn.com.ss.customer.generate.code.AbstractGenerator;
import cn.com.ss.customer.generate.domain.TableColumnInfo;
import cn.com.ss.customer.generate.domain.TableInfo;
import cn.com.ss.customer.generate.util.DatabaseNameUtils;
import cn.com.ss.customer.generate.util.DatabaseUtils;
import cn.com.ss.customer.generate.util.DateUtils;
import cn.com.ss.customer.generate.util.FullyQualifiedJavaType;

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
public class JavaDomainGenerator extends AbstractGenerator {

    public JavaDomainGenerator() {
    }

    public Map<String,Object>  generateHeader(){
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
        dataMap.put("author","chensj");
        dataMap.put("title",t.getTableName());
        dataMap.put("email","demo@demo.com");
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
}
