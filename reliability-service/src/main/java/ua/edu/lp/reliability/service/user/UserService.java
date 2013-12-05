package ua.edu.lp.reliability.service.user;

import ua.edu.lp.reliability.model.user.User;

public interface UserService {
	
	User getUserById(Long userId);
	
	User getUserByLogin(String login);
	
	User getUserByLoginAndPassword(String login, String password);
}
