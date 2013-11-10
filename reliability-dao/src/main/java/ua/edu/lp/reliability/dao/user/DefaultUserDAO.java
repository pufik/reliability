package ua.edu.lp.reliability.dao.user;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ua.edu.lp.reliability.dao.AbstractBaseDAO;
import ua.edu.lp.reliability.model.user.User;

@Repository("userDao")
public class DefaultUserDAO extends AbstractBaseDAO<User, Long> implements UserDAO {

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
		return executeNamedQuerySingleResult("User.getByLogin", parameters);
	}
}
