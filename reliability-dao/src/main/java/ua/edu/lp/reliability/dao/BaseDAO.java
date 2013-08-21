package ua.edu.lp.reliability.dao;

import java.util.List;

public interface BaseDAO<T, I> {

	T getById(I id);

	void create(T entity);

	void update(T entity);

	void remove(T entity);

	List<T> getAll();
}
