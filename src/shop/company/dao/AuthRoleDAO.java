package shop.company.dao;

import java.util.List;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.AuthRole;

public interface AuthRoleDAO extends BaseDAO<AuthRole>{

	List<AuthRole> findAllWithoutPurview();
	
}