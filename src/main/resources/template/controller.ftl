package ${packageName}

import ${modelPackage}
import ${rowPackage}
import ${basePackage}
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
* @author ${author}
* @title ${title}
* @email ${email}
* @package ${packageName}
* @date ${date}
*/
@RestController
@RequestMapping(value = "${domainName?uncap_first}")
@CrossOrigin
public class ${domainName}Controller  extends BaseController{
    /**
     * 数据分页查询
     * @param ${domainName?uncap_first} 实体类
     * @param row 分页参数
     * @return map
     */
    @GetMapping(value = "list")
    public Map<String, Object> list(${domainName} ${domainName?uncap_first}, Row row) {
        ${domainName?uncap_first}.setRow(row);
        Map<String, Object> resultMap = new HashMap<String, Object>(6);
        resultMap.put("total", getFacade().get${domainName}Service().get${domainName}Count(${domainName?uncap_first}));
        resultMap.put("rows", getFacade().get${domainName}Service().get${domainName}PageList(${domainName?uncap_first}));
        return resultMap;
    }
    /**
     * 数据新增
     * @param ${domainName?uncap_first} 实体类
     * @return map
     */
    @PostMapping(value="add")
    public Map<String, Object> add(${domainName} ${domainName?uncap_first}){
        Map<String, Object> resultMap = new HashMap<String, Object>(6);
        resultMap.put("data", getFacade().get${domainName}Service().create${domainName}(${domainName?uncap_first}));
        return resultMap;
    }
    /**
     * 数据更新
     * @param ${domainName?uncap_first} 实体类
     * @return map
     */
    @PostMapping(value="update")
    public Map<String, Object> update(${domainName} ${domainName?uncap_first}){
        Map<String, Object> resultMap = new HashMap<String, Object>(6);
        resultMap.put("data", getFacade().get${domainName}Service().modify${domainName}(${domainName?uncap_first}));
        return resultMap;
    }
    /**
     * 数据获取
     * @param ${domainName?uncap_first} 实体类
     * @return map
     */
    @PostMapping(value="get")
    public Map<String, Object> get(${domainName} ${domainName?uncap_first}){
        Map<String, Object> resultMap = new HashMap<String, Object>(6);
        resultMap.put("data", getFacade().get${domainName}Service().get${domainName}(${domainName?uncap_first}));
        return resultMap;
    }
    /**
     * 数据删除
     * @param ${domainName?uncap_first} 实体类
     * @return map
     */
    @PostMapping(value="delete")
    public Map<String, Object> delete(${domainName} ${domainName?uncap_first}){
        Map<String, Object> resultMap = new HashMap<String, Object>(6);
        resultMap.put("data", getFacade().get${domainName}Service().remove${domainName}(${domainName?uncap_first}));
        return resultMap;
    }
}
