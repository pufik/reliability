package ua.edu.lp.reliability.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ua.edu.lp.reliability.dao.UserDAO;
import ua.edu.lp.reliability.model.user.User;

@Repository
public class UserDAOImpl extends AbstractBaseDAO<User, Long> implements UserDAO {

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
