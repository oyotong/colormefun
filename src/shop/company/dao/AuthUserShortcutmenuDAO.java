package shop.company.dao;

import java.util.List;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.AuthUser;
import shop.company.entity.AuthUserShortcutmenu;

public interface AuthUserShortcutmenuDAO extends BaseDAO<AuthUserShortcutmenu>{

	List<AuthUserShortcutmenu> findAllByUser(AuthUser user);
	
}