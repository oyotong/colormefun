package shop.company.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shop.company.entity.AuthUser;

public interface AuthUserService {

	public void saveAuthUser(AuthUser o);

	public void updateAuthUser(AuthUser o);

	public void deleteAuthUser(AuthUser o);

	public void deleteAuthUserByIds(String[] ids);

	public AuthUser getAuthUserById(Serializable id);

	public List<AuthUser> findAllAuthUser();

	public List<AuthUser> findAllAuthUserWithPage();

	public List<AuthUser> findAllAuthUserBySearchWithPage(AuthUser o);

	public Map findAllAuthUserByNameWithIdAndName(String name);

	public List<AuthUser> findAllAuthUserByIds(String[] cids);

	public void updatePassword(AuthUser currentUser, String newUserPassword,
                               String newUserPassword2, String oldUserPassword);

	public void updatePassword2(AuthUser currentUser, String newUserPassword,
                                String newUserPassword2, String oldUserPassword);

}
