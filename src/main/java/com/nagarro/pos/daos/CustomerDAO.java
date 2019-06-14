package com.nagarro.pos.daos;

import java.util.List;

import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.Customer;

/**
 * this is a CustomerDAO interface for Customer related operation
 * 
 * @author damanpreetbrar
 *
 */
public interface CustomerDAO {

	Cart getCustomerCart(long customerId);

	List<Customer> searchCustomers(String searchParameter);

}
