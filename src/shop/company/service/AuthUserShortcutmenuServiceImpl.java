package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.company.dao.AuthUserShortcutmenuDAO;
import shop.company.entity.AuthUser;
import shop.company.entity.AuthUserShortcutmenu;

@Service
@Transactional
public class AuthUserShortcutmenuServiceImpl implements AuthUserShortcutmenuService {

	@Autowired()
	private AuthUserShortcutmenuDAO authUserShortcutmenuDAO;

	public AuthUserShortcutmenuDAO getAuthUserShortcutmenuDAO() {
		return authUserShortcutmenuDAO;
	}

	public void setAuthUserShortcutmenuDAO(AuthUserShortcutmenuDAO authUserShortcutmenuDAO) {
		this.authUserShortcutmenuDAO = authUserShortcutmenuDAO;
	}

	@Override
	public void deleteAuthUserShortcutmenu(AuthUserShortcutmenu o) {
		authUserShortcutmenuDAO.remove(o);
	}
	
	@Override
	public void deleteAuthUserShortcutmenuByIds(Integer[] ids){
		authUserShortcutmenuDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenu() {
		return authUserShortcutmenuDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenuWithPage(){
		return authUserShortcutmenuDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenuBySearchWithPage(AuthUserShortcutmenu o){
		return authUserShortcutmenuDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public AuthUserShortcutmenu getAuthUserShortcutmenuById(Serializable id) {
		return authUserShortcutmenuDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllAuthUserShortcutmenuByNameWithIdAndName(String name) {
		return authUserShortcutmenuDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveAuthUserShortcutmenu(AuthUserShortcutmenu o) {
		authUserShortcutmenuDAO.persist(o);
	}

	@Override
	public void updateAuthUserShortcutmenu(AuthUserShortcutmenu o) {
		authUserShortcutmenuDAO.merge(o);
	}
	
	@Override
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenuByIds(Integer[] cids) {
		return authUserShortcutmenuDAO.findAllByIds(cids);
	}

	@Override
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenuByUser(AuthUser user) {
		return authUserShortcutmenuDAO.findAllByUser(user);
	}

}
