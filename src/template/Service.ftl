package ${servicePackage};

import java.io.Serializable;
import java.util.*;

import ${entityPackage}.${pojo.ClassName};

public interface ${pojo.ClassName}Service {
	
	public void save${pojo.ClassName}(${pojo.ClassName} o);
	
	public void update${pojo.ClassName}(${pojo.ClassName} o);
	
	public void delete${pojo.ClassName}(${pojo.ClassName} o);
	
	public void delete${pojo.ClassName}ByIds(${pojo.IdClassName}[] ids);
	
	public ${pojo.ClassName} get${pojo.ClassName}ById(Serializable id);
	
	public List<${pojo.ClassName}> findAll${pojo.ClassName}();
	
	public List<${pojo.ClassName}> findAll${pojo.ClassName}WithPage();
	
	public List<${pojo.ClassName}> findAll${pojo.ClassName}BySearchWithPage(${pojo.ClassName} o);
	
	public Map findAll${pojo.ClassName}ByNameWithIdAndName(String name);
	
	public List<${pojo.ClassName}> findAll${pojo.ClassName}ByIds(${pojo.IdClassName}[] cids);
	
}
