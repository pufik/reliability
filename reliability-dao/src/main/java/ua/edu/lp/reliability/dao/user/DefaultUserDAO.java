package ua.edu.lp.reliability.dao.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ua.edu.lp.reliability.dao.AbstractBaseDAO;
import ua.edu.lp.reliability.model.user.User;

@Repository("userDao")
public class DefaultUserDAO extends AbstractBaseDAO<User, Long> implements UserDAO {

	private static final String USER_GET_BY_LOGIN_AND_PASSWORD = "User.getByLoginAndPassword";
	private static final String USER_GET_BY_LOGIN = "User.getByLogin";

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public List<User> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public User getUserByLogin(String login) {
		Map<String, Object> parameters = Collections.<String, Object> singletonMap("login", login);
		
		return executeNamedQuerySingleResult(USER_GET_BY_LOGIN, parameters);
	}

	@Override
	public User getByLoginAndPassword(String login, String password) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);
		parameters.put("password", password);

		return executeNamedQuerySingleResult(USER_GET_BY_LOGIN_AND_PASSWORD, parameters);
	}
}
