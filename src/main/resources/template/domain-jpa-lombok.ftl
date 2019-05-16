package ${packageName};

<#list importData as value>
${value}
</#list>
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author ${author}
 * @title ${title}
 * @email ${email}
 * @package ${packageName}
 * @date ${date}
 */
@Data
@Entity
@Table(name = "${tableName}")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ${className} implements Serializable {

    private static final long serialVersionUID = -1L;
    <#-- 自增长主键-->
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
    <#--UUID主键-->
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

}
