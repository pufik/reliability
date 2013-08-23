package ua.edu.lp.reliability.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import ua.edu.lp.reliability.dao.AbstractBaseDAO;
import ua.edu.lp.reliability.model.user.User;

@Repository
public class DefaultUserDAO extends AbstractBaseDAO<User, Long> implements UserDAO {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
