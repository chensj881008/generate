package ${packageName}

import ${modelPackage}
import ${facadeName}
import org.springframework.beans.factory.annotation.Autowired;
/**
* @author ${author}
* @title ${title}
* @email ${email}
* @package ${packageName}
* @date ${date}
*/
public class ${className} {
    @Autowired
    private ${facade} ${facade?uncap_first};

    public ${facade} get${facade}() {
        return ${facade?uncap_first};
    }
}