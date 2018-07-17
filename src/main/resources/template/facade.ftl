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
    ${value}Service get${value}Service();

</#list>
}