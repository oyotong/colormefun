package shop.company.dao;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.SystemLoginLog;

public interface SystemLoginLogDAO extends BaseDAO<SystemLoginLog>{
	Double getTotleBySearchField(SystemLoginLog o, String fieldName,
                                 Object... addArgs);
}