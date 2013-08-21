package ua.edu.lp.reliability.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.dao.UserDAO;
import ua.edu.lp.reliability.model.user.User;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private UserDAO userDAo;
	
	@Override
	public User getUserById(Long userId) {
		return userDAo.getById(userId);
	}
	
}
