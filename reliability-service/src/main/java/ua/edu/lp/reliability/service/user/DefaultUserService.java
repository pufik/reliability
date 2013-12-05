package ua.edu.lp.reliability.service.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.user.UserDAO;
import ua.edu.lp.reliability.model.user.User;

@Service("userService")
public class DefaultUserService implements UserService {

	@Resource(name = "userDao")
	private UserDAO userDao;

	private Map<String, User> users;

	@PostConstruct
	private void init() {
		// TODO: Should be removed
		users = new HashMap<String, User>();
		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("1111");

		users.put(admin.getLogin(), admin);
	}

	@Override
	public User getUserById(Long userId) {
		return userDao.getById(userId);
	}

	@Override
	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

	@Override
	public User getUserByLoginAndPassword(String login, String password) {
		// TODO: Should be changed to checking on DB
		return users.get(login);
	}
}
