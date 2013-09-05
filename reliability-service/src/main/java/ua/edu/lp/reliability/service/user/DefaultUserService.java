package ua.edu.lp.reliability.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.user.UserDAO;
import ua.edu.lp.reliability.model.user.User;

@Service("userService")
public class DefaultUserService implements UserService {

	@Resource(name = "userDao")
	private UserDAO userDAo;

	@Override
	public User getUserById(Long userId) {
		return userDAo.getById(userId);
	}

}
