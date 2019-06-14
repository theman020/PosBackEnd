package com.nagarro.pos.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constants.ApiConstants;
import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.CartDetails;
import com.nagarro.pos.models.CartDetailsIdentity;
import com.nagarro.pos.models.Customer;
import com.nagarro.pos.models.ErrorMessage;
import com.nagarro.pos.models.OrderDetails;
import com.nagarro.pos.models.Product;
import com.nagarro.pos.services.CartService;
import com.nagarro.pos.services.CustomerService;
import com.nagarro.pos.services.ProductService;
import com.nagarro.pos.utils.ErrorPayload;

/**
 * CustomerController class will handle the requests related to customer
 * resource
 * 
 * @author damanpreetbrar
 *
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ApiConstants.CUSTOMERS_ROUTE)
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	/**
	 * searchCustomers method searches for the customers depending upon the query
	 * parameter named searchParameter
	 * 
	 * @param searchParameter
	 * @return
	 */

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Customer> searchCustomers(@RequestParam(required = false) String searchParameter) {
		if (searchParameter != null) {
			return customerService.searchCustomer(searchParameter);
		}
		return customerService.getAllCustomers();
	}

	/**
	 * addCustomer method adds the customer
	 * 
	 * @param customer
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.addCustomer(customer);
		if (newCustomer == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
	}

	/**
	 * getCustomerById method fetches a particular customer whose id is passed in
	 * the path
	 * 
	 * @param username
	 * @return
	 */

	@GetMapping(value = ApiConstants.CUTOMER_ID)
	public ResponseEntity<?> getCustomerById(@PathVariable long customerId) {

		if (customerId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}
		Customer customer = customerService.getCustomerById(customerId);
		return ResponseEntity.ok().body(customer);
	}

	/**
	 * createCustomerCart method returns
	 * 
	 * @param customerId
	 * @param cart
	 * @return
	 */

	@PostMapping(value = ApiConstants.CUSTOMER_CART_ROUTE)
	public ResponseEntity<?> createCustomerCart(@PathVariable long customerId, @RequestBody Cart cart) {
		if (customerId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}

		Cart customerCart = customerService.createCustomerCart(customerId);

		if (customerCart == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return new ResponseEntity<Cart>(customerCart, HttpStatus.CREATED);
	}

	/**
	 * getCustomerCart method return the cart of the customer
	 * 
	 * @param customerId
	 * @return
	 */
	@GetMapping(value = ApiConstants.CUSTOMER_CART_ROUTE)
	public ResponseEntity<?> getCustomerCart(@PathVariable long customerId) {
		if (customerId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}

		Cart customerCart = customerService.getCustomerCart(customerId);

		if (customerCart == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return new ResponseEntity<Cart>(customerCart, HttpStatus.CREATED);
	}

	/**
	 * reloadCustomerOrder method reload the customer order to the cart
	 * 
	 * @param customerId
	 * @param orderId
	 * @param orderDetailsList
	 * @return
	 */

	@PostMapping(value = ApiConstants.RELOAD_CUSTOMER_ORDER)
	public Cart reloadCustomerOrder(@PathVariable long customerId, @PathVariable long orderId,
			@RequestBody Set<OrderDetails> orderDetailsList) {
		Cart cart = customerService.getCustomerCart(customerId);

		Set<CartDetails> cartDetailsList = getCartDetailsFromOrderDetails(orderDetailsList);
		for (CartDetails cartDetails : cartDetailsList) {
			cartDetails.getCartDetailsIdentity().setCart(cart);
		}

		cart.setCartDetails(cartDetailsList);

		return cartService.reloadCustomerOrder(cart, orderId);

	}

	/**
	 * getCartDetailsFromOrderDetails method will create the CartDetails from the
	 * OrderDetails
	 * 
	 * @param orderDetailsList
	 * @return
	 */

	public Set<CartDetails> getCartDetailsFromOrderDetails(Set<OrderDetails> orderDetailsList) {
		Set<CartDetails> cartDetailsList = new HashSet<>();
		for (OrderDetails orderDetails : orderDetailsList) {

			long productId = orderDetails.getOrderDetailsIdentity().getProduct().getId();

			Product product = productService.getProductById(productId);

			if (orderDetails.getQuantity() > product.getStock())
				orderDetails.setQuantity(product.getStock());

			orderDetails.getOrderDetailsIdentity().setProduct(product);

			CartDetails cartDetails = new CartDetails();
			cartDetails.setQuantity(orderDetails.getQuantity());

			CartDetailsIdentity cartDetailsIdentity = new CartDetailsIdentity();
			cartDetailsIdentity.setProduct(orderDetails.getOrderDetailsIdentity().getProduct());
			cartDetails.setCartDetailsIdentity(cartDetailsIdentity);

			cartDetailsList.add(cartDetails);
		}

		return cartDetailsList;

	}

}
