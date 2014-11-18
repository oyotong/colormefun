package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.AuthUser;
import shop.company.entity.AuthUserShortcutmenu;

public interface AuthUserShortcutmenuService {
	
	public void saveAuthUserShortcutmenu(AuthUserShortcutmenu o);
	
	public void updateAuthUserShortcutmenu(AuthUserShortcutmenu o);
	
	public void deleteAuthUserShortcutmenu(AuthUserShortcutmenu o);
	
	public void deleteAuthUserShortcutmenuByIds(Integer[] ids);
	
	public AuthUserShortcutmenu getAuthUserShortcutmenuById(Serializable id);
	
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenu();
	
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenuWithPage();
	
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenuBySearchWithPage(AuthUserShortcutmenu o);
	
	public Map findAllAuthUserShortcutmenuByNameWithIdAndName(String name);
	
	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenuByIds(Integer[] cids);

	public List<AuthUserShortcutmenu> findAllAuthUserShortcutmenuByUser(AuthUser user);
	
}
