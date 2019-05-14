package ${packageName};

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public ${domainName} save${domainName}(${domainName} ${paramT}) {
        return ${daoClassNameT}.save(${paramT});
    }

   <#-- @Override
    public void delete${domainName}ById(${domainName} ${paramT}) {
        ${paramT}.setIsDel(1);
        ${daoClassNameT}.save(${paramT});
    }-->

    @Override
    public ${domainName} get${domainName}ById(${domainName} ${paramT}) {
        return ${daoClassNameT}.findById(${paramT}.getId()).orElse(null);
    }

    @Override
    public Page<${domainName}> get${domainName}PageList(${domainName} ${paramT}, ExampleMatcher matcher, Pageable pageable) {
    Example<${domainName}> example = Example.of(${paramT},matcher);
        return ${daoClassNameT}.findAll(example,pageable);
    }
}
