package ${packageName};

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
@Service
public class ${className} implements  ${pClassName} {

    @Autowired
    private ${daoClassName} ${daoClassNameT};

    public int create${domainName}(${domainName} ${paramT}){
        return this.${daoClassNameT}.insert${domainName}(${paramT});
    }

    public int modify${domainName}(${domainName} ${paramT}){
        return this.${daoClassNameT}.update${domainName}(${paramT});
    }

    public int remove${domainName}(${domainName} ${paramT}){
        return this.${daoClassNameT}.delete${domainName}(${paramT});
    }

    public ${domainName} get${domainName}(${domainName} ${paramT}){
        return this.${daoClassNameT}.select${domainName}(${paramT});
    }

    public int get${domainName}Count(${domainName} ${paramT}){
        return (Integer)this.${daoClassNameT}.select${domainName}Count(${paramT});
    }

    public List<${domainName}> get${domainName}List(${domainName} ${paramT}){
        return this.${daoClassNameT}.select${domainName}List(${paramT});
    }

    public List<${domainName}> get${domainName}PageList(${domainName} ${paramT}){
        return this.${daoClassNameT}.select${domainName}PageList(${paramT});
    }
}