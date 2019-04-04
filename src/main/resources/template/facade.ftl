package ${packageName}

/**
* @author ${author}
* @title ${title}
* @email ${email}
* @package ${packageName}
* @date ${date}
*/
public interface ${className} {

<#list tableNameList as value>
   /**
     * ${value}Service服务接口获取
     * @return ${value}Service
     */
    ${value}Service get${value}Service();

</#list>
}