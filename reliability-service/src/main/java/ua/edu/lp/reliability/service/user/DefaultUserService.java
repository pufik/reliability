package ua.edu.lp.reliability.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.user.UserDAO;
import ua.edu.lp.reliability.model.user.User;

@Service("userService")
public class DefaultUserService implements UserService {

	@Resource(name = "userDao")
	private UserDAO userDao;

	@Override
	public User getUserById(Long userId) {
		return userDao.getById(userId);
	}

	@Override
	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

}
