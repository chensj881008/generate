package ${packageName};

<#list importData as value>
${value}
</#list>
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
* @author ${author}
* @title ${title}
* @email ${email}
* @package ${packageName}
* @date ${date}
*/
public interface ${className} {
    /**
     * ${domainName}服务接口 数据新增/修改
     * @param ${paramT}
     * @return int
     */
    ${domainName} save${domainName}(${domainName} ${paramT});
   <#-- /**
     * ${domainName}服务接口 数据删除
     * @param ${paramT}
     * @return int
     */
    void delete${domainName}ById(${domainName} ${paramT});-->
    /**
     * ${domainName}服务接口 单条数据查询
     * @param ${paramT}
     * @return int
     */
    ${domainName} get${domainName}ById(${domainName} ${paramT});
    /**
     * ${domainName}服务接口 分页查询
     * @param ${paramT}
     * @return ${domainName}List
     */
    Page<${domainName}> get${domainName}PageList(${domainName} ${paramT}, ExampleMatcher matcher, Pageable pageable);
}
