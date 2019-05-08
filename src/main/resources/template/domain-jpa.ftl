package ${packageName};

<#list importData as value>
${value}
</#list>
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
/**
 * @author ${author}
 * @title ${title}
 * @email ${email}
 * @package ${packageName}
 * @date ${date}
 */
@Entity
@Table(name = "${tableName}")
public class ${className} implements Serializable {

    private static final long serialVersionUID = -1L;
    <#list propNumPks as prop >
     /**
     * 字段名：${prop.actualColumnName}
     * 备注: ${(prop.remarks)!'无'}
     * 默认值：${(prop.defaultValue)!'无'}
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "${prop.actualColumnName}")
    private ${prop.typeName} ${prop.domainColumnName};
    </#list>
    <#list propStrPks as prop >
     /**
     * 字段名：${prop.actualColumnName}
     * 备注: ${(prop.remarks)!'无'}
     * 默认值：${(prop.defaultValue)!'无'}
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "${prop.actualColumnName}")
    private ${prop.typeName} ${prop.domainColumnName};
    </#list>
    <#list props as prop >
     /**
     * 字段名：${prop.actualColumnName}
     * 备注: ${(prop.remarks)!'无'}
     * 默认值：${(prop.defaultValue)!'无'}
     */
    @Column(name = "${prop.actualColumnName}")
    private ${prop.typeName} ${prop.domainColumnName};
    </#list>

    public ${className} (){

    }
<#list propNumPks as prop >
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
<#list propStrPks as prop >
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
