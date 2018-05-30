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

    public int create${domainName}(${domainName} ${paramT});

    public int modify${domainName}(${domainName} ${paramT});

    public int remove${domainName}(${domainName} ${paramT});

    public ${domainName} get${domainName}(${domainName} ${paramT});

    public int get${domainName}Count(${domainName} ${paramT});

    public List<${domainName}> get${domainName}List(${domainName} ${paramT});

    public List<${domainName}> get${domainName}PageList(${domainName} ${paramT});
}