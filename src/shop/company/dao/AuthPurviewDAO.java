package shop.company.dao;

import java.util.List;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.AuthPurview;
import shop.company.entity.AuthUser;

public interface AuthPurviewDAO extends BaseDAO<AuthPurview> {

	List<AuthPurview> findAllParentsAndKids();

	List<AuthPurview> findAllParentsWithKids();

	List<AuthPurview> findAllParentAndKidsByUser(AuthUser u);

}