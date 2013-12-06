package ua.edu.lp.reliability.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ua.edu.lp.reliability.dao.exception.ModelNotFoundException;

public abstract class AbstractBaseDAO<T, I> implements BaseDAO<T, I> {

	@PersistenceContext
	private EntityManager entityManager;

	protected abstract Class<T> getEntityClass();

	@Override
	public T getById(I id) {
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public void create(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
		entityManager.flush();
	}

	@Override
	public void remove(T entity) {
		entityManager.remove(entity);
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	protected List<T> executeNamedQuery(String queryName, Map<String, Object> parameters) {
		Query query = entityManager.createNamedQuery(queryName);

		for (String key : parameters.keySet()) {
			query.setParameter(key, parameters.get(key));
		}

		return (List<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected T executeNamedQuerySingleResult(String queryName, Map<String, Object> parameters) {

		T entity = null;
		try {
			Query query = entityManager.createNamedQuery(queryName);

			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}

			entity = (T) query.getSingleResult();

		} catch (NoResultException ex) {
			throw new ModelNotFoundException(ex);
		}

		return entity;
	}
}
