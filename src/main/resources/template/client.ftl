package ${packageName};

<#list importData as value>
${value}
</#list>
import org.springframework.stereotype.Repository;
/**
* @author ${author}
* @title ${title}
* @email ${email}
* @package ${packageName}
* @date ${date}
*/
@Repository
public interface ${className} {

    public int insert${domainName}(${domainName} ${paramT}) throws DataAccessException;

    public int update${domainName}(${domainName} ${paramT}) throws DataAccessException;

    public int delete${domainName}(${domainName} ${paramT}) throws DataAccessException;

    public ${domainName} select${domainName}(${domainName} ${paramT}) throws DataAccessException;

    public Object select${domainName}Count(${domainName} ${paramT}) throws DataAccessException;

    public List<${domainName}> select${domainName}List(${domainName} ${paramT}) throws DataAccessException;

    public List<${domainName}> select${domainName}PageList(${domainName} ${paramT}) throws DataAccessException;
}