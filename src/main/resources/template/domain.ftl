package ${packageName};

<#list importData as value>
${value}
</#list>


/**
 * @author ${author}
 * @title ${title}
 * @email ${email}
 * @package ${packageName}
 * @date ${date}
 */
@Alias("${alias}")
public class ${className} extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    <#list props as prop >
    /**
     * 字段名：${prop.actualColumnName}
     * 备注: ${(prop.remarks)!'无'}
     * 默认值：${(prop.defaultValue)!'无'}
     */
    private ${prop.typeName} ${prop.domainColumnName};
    </#list>

    public ${className} (){

    }

   <#list props as prop >
   /**
   * 字段名：${prop.actualColumnName}
   * get方法
   * 备注: ${(prop.remarks)!'无'}
   */
   public ${prop.typeName} get${prop.domainColumnName?cap_first}(){

        return ${prop.domainColumnName};
   }

   /**
   * 字段名：${prop.actualColumnName}
   * set方法
   * 备注: ${(prop.remarks)!'无'}
   */
   public void set${prop.domainColumnName?cap_first}(${prop.typeName} ${prop.domainColumnName}){
        this.${prop.domainColumnName} = ${prop.domainColumnName};
   }
   </#list>

}