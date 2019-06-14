package com.nagarro.pos.services;

import java.util.List;

import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.Customer;

/**
 * This is a CustomerService interface for implementing Customer related
 * services
 * 
 * @author damanpreetbrar
 *
 */
public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer addCustomer(Customer customer);

	Customer getCustomerById(long customerId);

	Cart createCustomerCart(long customerId);

	List<Customer> searchCustomer(String searchParameter);

	Cart getCustomerCart(long customerId);

}
