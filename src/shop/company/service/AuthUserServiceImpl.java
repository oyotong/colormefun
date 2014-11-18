package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.common.exception.ApplicationException;
import shop.common.util.StringUtils;
import shop.company.dao.AuthUserDAO;
import shop.company.entity.AuthUser;

@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired()
	private AuthUserDAO authUserDAO;

	public AuthUserDAO getAuthUserDAO() {
		return authUserDAO;
	}

	public void setAuthUserDAO(AuthUserDAO authUserDAO) {
		this.authUserDAO = authUserDAO;
	}

	@Override
	public void deleteAuthUser(AuthUser o) {
		authUserDAO.remove(o);
	}
	
	@Override
	public void deleteAuthUserByIds(String[] ids){
		authUserDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuthUser> findAllAuthUser() {
		return authUserDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AuthUser> findAllAuthUserWithPage(){
		return authUserDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AuthUser> findAllAuthUserBySearchWithPage(AuthUser o){
		return authUserDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public AuthUser getAuthUserById(Serializable id) {
		return authUserDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllAuthUserByNameWithIdAndName(String name) {
		return authUserDAO.findAllByNameWithIdAndName(name);
	}
	
	@Override
	public void updatePassword(AuthUser currentUser, String newUserPassword,
			String newUserPassword2, String oldUserPassword) {
		
		if(!StringUtils.isNotNull(newUserPassword )){
			return;
		}
		
		if(!currentUser.getUserPassword().equals(oldUserPassword)){
			throw new ApplicationException("旧密码错误。");
		}
		
		if(!newUserPassword.equals(newUserPassword2)){
			throw new ApplicationException("两次密码不一致。");
		}
		
		currentUser.setUserPassword(newUserPassword);
		this.authUserDAO.merge(currentUser);
	}

	@Override
	public void saveAuthUser(AuthUser o) {
		authUserDAO.persist(o);
	}

	@Override
	public void updateAuthUser(AuthUser o) {
		authUserDAO.merge(o);
	}
	
	@Override
	public List<AuthUser> findAllAuthUserByIds(String[] cids) {
		return authUserDAO.findAllByIds(cids);
	}

	@Override
	public void updatePassword2(AuthUser currentUser, String newUserPassword,
			String newUserPassword2, String oldUserPassword) {
		if(!StringUtils.isNotNull(newUserPassword )){
			return;
		}
		if(currentUser.getUserPassword2() != null && !currentUser.getUserPassword2().equals(oldUserPassword)){
			throw new ApplicationException("旧财务密码错误。");
		}
		
		if(!newUserPassword.equals(newUserPassword2)){
			throw new ApplicationException("两次财务密码不一致。");
		}
		
		currentUser.setUserPassword2(newUserPassword);
		this.authUserDAO.merge(currentUser);
	}

}
