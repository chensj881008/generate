package ${packageName};

<#list importData as value>
${value}
</#list>
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ${author}
 * @title ${title}
 * @email ${email}
 * @package ${packageName}
 * @date ${date}
 */
@Repository
public interface ${className}Repository extends JpaRepository<${className}, ${idStr}> {

}