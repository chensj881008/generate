package ${packageName};

<#list importData as value>
${value}
</#list>
import lombok.Data;

/**
 * @author ${author}
 * @title ${title}
 * @email ${email}
 * @package ${packageName}
 * @date ${date}
 */
@Alias("${alias}")
@Data
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

}