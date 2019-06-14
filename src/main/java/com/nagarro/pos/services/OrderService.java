package com.nagarro.pos.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.nagarro.pos.enums.Status;
import com.nagarro.pos.models.Order;
import com.nagarro.pos.models.OrderDetails;

/**
 * This is a OrderService interface for implementing Order related services
 * 
 * @author damanpreetbrar
 *
 */
public interface OrderService {

	Order create(Order order);

	Order getOrderById(long orderId);

	void saveOrderDetails(Set<OrderDetails> orderDetails);

	Order updateOrder(Order order);

	List<Order> getEmployeeSavedOrPlacedOrders(long employeeId, Status orderStatus);

	Double sumTodaysCash(long employeeId);

	List<Order> getEmployeeAllOrders(long employeeId);

	List<Order> getEmployeeAllOrders(long employeeId, Date fromCreateDate, Date toCreateDate);

	Double sumTodaysCash(long employeeId, long cashDrawerId);

}
