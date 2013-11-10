package ua.edu.lp.reliability.dao.user;

import ua.edu.lp.reliability.dao.BaseDAO;
import ua.edu.lp.reliability.model.user.User;

public interface UserDAO extends BaseDAO<User, Long> {
	
	User getUserByLogin(String login);
	
}
