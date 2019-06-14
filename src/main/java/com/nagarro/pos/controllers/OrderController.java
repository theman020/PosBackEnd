package com.nagarro.pos.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constants.ApiConstants;
import com.nagarro.pos.dtos.SaveOrderPostDTO;
import com.nagarro.pos.models.ErrorMessage;
import com.nagarro.pos.models.Order;
import com.nagarro.pos.models.OrderDetails;
import com.nagarro.pos.models.Product;
import com.nagarro.pos.services.CartService;
import com.nagarro.pos.services.OrderService;
import com.nagarro.pos.services.ProductService;
import com.nagarro.pos.utils.ErrorPayload;

/**
 * OrderController class will handle the requests related to order resource
 * 
 * @author damanpreetbrar
 *
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ApiConstants.ORDERS_ROUTE)
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	CartService cartService;

	@Autowired
	ProductService productService;

	/**
	 * placeOrder method adds the order of a customer
	 * 
	 * @param orderDTO
	 * @return
	 */

	@PostMapping
	public ResponseEntity<?> placeOrder(@RequestBody SaveOrderPostDTO orderDTO) {

		/*
		 * This loop will check whether order's products are in stock or not and return
		 * an error pay load if it is not
		 */
		for (OrderDetails orderDetails : orderDTO.getOrderDetails()) {

			long productId = orderDetails.getOrderDetailsIdentity().getProduct().getId();

			Product product = productService.getProductById(productId);

			if (orderDetails.getQuantity() > product.getStock()) {
				ErrorMessage outOfStock = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
						"Product with id " + productId + " is out of stock");

				return new ResponseEntity<ErrorMessage>(outOfStock, HttpStatus.BAD_REQUEST);
			}

			orderDetails.getOrderDetailsIdentity().setProduct(product);
		}

		/* This loop will update the stock of the product */
		for (OrderDetails orderDetails : orderDTO.getOrderDetails()) {
			long productId = orderDetails.getOrderDetailsIdentity().getProduct().getId();
			productService.updateProductStock(productId, orderDetails.getQuantity());

		}

		Order order = orderDTO.createOrderObject();
		Order savedOrder = orderService.create(order);
		Set<OrderDetails> orderDetailsList = orderDTO.createOrderDetails(savedOrder);

		orderService.saveOrderDetails(orderDetailsList);
		cartService.deleteCartProductsByCartId(orderDTO.getCartId());
		return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
	}

	/**
	 * saveOrder method saves the order and its details
	 * 
	 * @param orderDTO
	 * @return Order
	 */
	@PostMapping(value = ApiConstants.SAVE_ORDER)
	public Order saveOrder(@RequestBody SaveOrderPostDTO orderDTO) {
		Order order = orderDTO.createOrderObject();
		Order savedOrder = orderService.create(order);
		Set<OrderDetails> orderDetailsList = orderDTO.createOrderDetails(savedOrder);

		orderService.saveOrderDetails(orderDetailsList);
		cartService.deleteCartProductsByCartId(orderDTO.getCartId());
		return savedOrder;
	}

	/**
	 * updateOrder method updates the order
	 * 
	 * @param order
	 * @return Order
	 */

	@PutMapping(value = ApiConstants.ORDERS_ROUTE)
	public Order updateOrder(Order order) {
		return orderService.updateOrder(order);
	}

	/**
	 * getOrderById method fetches a particular order whose id is passed in the path
	 * 
	 * @param orderId
	 * @return
	 */
	@GetMapping(value = ApiConstants.ORDER_ID)
	public ResponseEntity<?> getOrderById(@PathVariable long orderId) {
		if (orderId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}

		Order order = orderService.getOrderById(orderId);

		return ResponseEntity.ok().body(order);
	}

}
