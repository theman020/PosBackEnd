package com.nagarro.pos.daos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.nagarro.pos.enums.Status;
import com.nagarro.pos.models.Order;

/**
 * this is a OrderDAO interface for Order related operation
 * 
 * @author damanpreetbrar
 *
 */
public interface OrderDAO {

	List<Order> getEmployeeSavedOrPlacedOrders(long employeeId, Status orderStatus);

	void changeStatusToReopen(long orderId);

	Double sumTodaysCash(long employeeId);

	List<Order> getEmployeeAllOrders(long employeeId);

	List<Order> getEmployeeAllOrders(long employeeId, Date fromDate, Date toDate);

	Double sumTodaysCash(long employeeId, double startingAmount, LocalDateTime loginTime);

}
