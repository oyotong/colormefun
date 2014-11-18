package ${daoPackage};

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import ${entityPackage}.${pojo.ClassName};

public interface ${pojo.ClassName}DAO extends BaseDAO<${pojo.ClassName}>{
	Double getTotleBySearchField(${pojo.ClassName} o, String fieldName,
			Object... addArgs);
}