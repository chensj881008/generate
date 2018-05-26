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
public interface ${className} {

    int insert${domainName}(${domainName} ${paramT}) throws DataAccessException;

    int update${domainName}(${domainName} ${paramT}) throws DataAccessException;

    int delete${domainName}(${domainName} ${paramT}) throws DataAccessException;

    ${domainName} select${domainName}(${domainName} ${paramT}) throws DataAccessException;

    Object select${domainName}Count(${domainName} ${paramT}) throws DataAccessException;

    List<${domainName}> select${domainName}List(${domainName} ${paramT}) throws DataAccessException;

    List<${domainName}> select${domainName}PageList(${domainName} ${paramT}) throws DataAccessException;
}