package com.nagarro.pos.daos;

import java.io.Serializable;
import java.util.List;

/**
 * this is a GenericDAO interface for simplification of the Data Access Layer by
 * providing a single, reusable implementation of a generic DAO.
 * 
 * @author damanpreetbrar
 *
 * @param <T>
 */
public interface GenericDAO<T extends Serializable> {

	T findById(final long id);

	List<T> findAll();

	T create(final T entity);

	T update(final T entity);

	void delete(final T entity);

	void deleteById(final long entityId);

	void setClazz(Class<T> classToSeT);

}
