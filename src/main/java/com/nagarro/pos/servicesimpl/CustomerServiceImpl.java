package com.nagarro.pos.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.pos.daos.CustomerDAO;
import com.nagarro.pos.daos.GenericDAO;
import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.Customer;
import com.nagarro.pos.services.CartService;
import com.nagarro.pos.services.CustomerService;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	GenericDAO<Customer> genericCustomerDAO;

	@Autowired
	public void setDao(GenericDAO<Customer> dao) {
		this.genericCustomerDAO = dao;
		genericCustomerDAO.setClazz(Customer.class);
	}

	@Autowired
	CartService cartService;
	
	
	@Autowired
	CustomerDAO customerDAO;
	
	
	@Override
	public List<Customer> getAllCustomers() {
		return genericCustomerDAO.findAll();
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		
		return genericCustomerDAO.create(customer);

	}

	@Override
	public Customer getCustomerById(long customerId) {
		Customer customer=genericCustomerDAO.findById(customerId);
		return customer;
	}

	@Override
	@Transactional
	public Cart createCustomerCart(long customerId) {
		Cart cart=new Cart();
		return this.cartService.addCart(cart,customerId);
	}

	@Override
	public List<Customer> searchCustomer(String searchParameter) {
			return customerDAO.searchCustomers(searchParameter);	
	}

	@Override
	@Transactional
	public Cart getCustomerCart(long customerId) {
		Cart cart= customerDAO.getCustomerCart(customerId);
		if(cart==null) {
			return createCustomerCart(customerId);
		}
		return cart;
	}

}
