package ua.edu.lp.reliability.dao.user;

import java.util.List;

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
}
