package ${packageName}

import ${pPackage}.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
* @author ${author}
* @title ${title}
* @email ${email}
* @package ${packageName}
* @date ${date}
*/
@Component
public class ${className} implements ${pname}{

<#list tableNameList as value>
    @Autowired
    ${value}Service ${value?uncap_first}Service;

</#list>

<#list tableNameList as value>
    public ${value}Service get${value}Service(){
        return ${value?uncap_first}Service;
    }

</#list>
}