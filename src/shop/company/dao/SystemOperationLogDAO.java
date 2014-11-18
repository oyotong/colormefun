package shop.company.dao;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.SystemOperationLog;

public interface SystemOperationLogDAO extends BaseDAO<SystemOperationLog>{
	Double getTotleBySearchField(SystemOperationLog o, String fieldName,
                                 Object... addArgs);

}