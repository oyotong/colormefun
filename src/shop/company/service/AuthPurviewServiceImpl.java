package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.AuthPurviewDAO;
import shop.company.entity.AuthPurview;
import shop.company.entity.AuthUser;

@Service
@Transactional
public class AuthPurviewServiceImpl implements AuthPurviewService {

	@Autowired()
	private AuthPurviewDAO authPurviewDAO;

	public AuthPurviewDAO getAuthPurviewDAO() {
		return authPurviewDAO;
	}

	public void setAuthPurviewDAO(AuthPurviewDAO authPurviewDAO) {
		this.authPurviewDAO = authPurviewDAO;
	}

	@Override
	public void deleteAuthPurview(AuthPurview o) {

		authPurviewDAO.remove(o);
	}

	@Override
	public void deleteAuthPurviewByIds(String[] ids) {
		authPurviewDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuthPurview> findAllAuthPurview() {
		return authPurviewDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuthPurview> findAllAuthPurviewWithPage() {
		return authPurviewDAO.findAllWithPage();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuthPurview> findAllAuthPurviewBySearchWithPage(AuthPurview o) {
		return authPurviewDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public AuthPurview getAuthPurviewById(Serializable id) {
		return authPurviewDAO.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Map findAllAuthPurviewByNameWithIdAndName(String name) {
		return authPurviewDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveAuthPurview(AuthPurview o) {
		authPurviewDAO.persist(o);
	}

	@Override
	public void updateAuthPurview(AuthPurview o) {
		authPurviewDAO.merge(o);
	}

	@Override
	public List<AuthPurview> findAllAuthPurviewByIds(String[] cids) {
		return authPurviewDAO.findAllByIds(cids);
	}

	@Override
	public List<AuthPurview> findAllAuthPurviewWithKids() {
		return authPurviewDAO.findAllParentsWithKids();
	}

	@Override
	public List<AuthPurview> findAllAuthPurviewAndKids() {
		return authPurviewDAO.findAllParentsAndKids();
	}

	@Override
	public List<AuthPurview> findAllAuthPurviewAndKidsByUser(AuthUser u) {
		return authPurviewDAO.findAllParentAndKidsByUser(u);
	}

}
