package com.nagarro.pos.daosimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractJpaDAO<T extends Serializable> {

	private Class<T> clazz;

	@Autowired
	SessionFactory sessionFactory;

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;

	}

	public T findById(final long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	
	public List<T> findAll() {

		return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
		
	}

	public T create(T entity) {

		getCurrentSession().persist(entity);
		return entity;
	}

	public T update(T entity) {
		getCurrentSession().saveOrUpdate(entity);
		return entity;

	}

	public void delete(T entity) {

		getCurrentSession().delete(entity);

	}

	public void deleteById(final long entityId) {

		T entity = this.findById(entityId);
		if (entity != null)
			this.delete(entity);

	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
