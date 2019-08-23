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
    Page<${domainName}> get${domainName}PageList(${domainName} ${paramT}, Row row);
    /**
     * ${domainName}服务接口 统计总数
     * @param ${paramT}
     * @return long
     */
    Long countBy${domainName}(${domainName} ${paramT});
    /**
     * ${domainName}服务接口 获取list
     * @param ${paramT}
     * @return ${domainName}List
     */
    List<${domainName}> findAllBy${domainName}(${domainName} ${paramT});
}
