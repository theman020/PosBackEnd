package com.nagarro.pos.daosimpl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.daos.CustomerDAO;
import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Cart getCustomerCart(long customerId) {
		Session session=sessionFactory.getCurrentSession();
		try {
		Cart cart=session.createQuery("from Cart where customer_id=:customerId",Cart.class)
				.setParameter("customerId", customerId)
				.getSingleResult();
			return cart;
		}
		catch(NoResultException exception) {
			return null;
		}
	}



	@Override
	public List<Customer> searchCustomers(String searchParameter) {
		Session session=sessionFactory.getCurrentSession();
		TypedQuery<Customer> query = session.createQuery("from Customer where name like :name or email like :email or mobileNumber like :mobileNumber", Customer.class)
				.setParameter("name", '%'+searchParameter+'%')
				.setParameter("email", '%'+searchParameter+'%')
				.setParameter("mobileNumber", '%'+searchParameter+'%');
		List<Customer> customersList = query.getResultList();
		
		return customersList;
	}




	
}
