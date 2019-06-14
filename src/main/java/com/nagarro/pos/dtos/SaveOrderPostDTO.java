package com.nagarro.pos.dtos;

import java.util.HashSet;
import java.util.Set;

import com.nagarro.pos.enums.ModeOfPayment;
import com.nagarro.pos.enums.Status;
import com.nagarro.pos.models.Customer;
import com.nagarro.pos.models.Employee;
import com.nagarro.pos.models.Order;
import com.nagarro.pos.models.OrderDetails;

/**
 * Save Order data is transferred using SaveOrderPostDTO object
 * 
 * @author damanpreetbrar
 *
 */
public class SaveOrderPostDTO {

	private long cartId;
	private Customer customer;
	private Employee employee;
	private ModeOfPayment modeOfPayment;
	private Status status;
	private double amount;
	private Set<OrderDetails> orderDetails = new HashSet<>();

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the modeOfPayment
	 */
	public ModeOfPayment getModeOfPayment() {
		return modeOfPayment;
	}

	/**
	 * @param modeOfPayment the modeOfPayment to set
	 */
	public void setModeOfPayment(ModeOfPayment modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the orderDetails
	 */
	public Set<OrderDetails> getOrderDetails() {

		return orderDetails;
	}

	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * @return the cartId
	 */
	public long getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Order createOrderObject() {
		Order order = new Order();
		order.setCustomer(this.customer);
		order.setEmployee(this.employee);
		order.setAmount(this.amount);
		order.setModeOfPayment(this.modeOfPayment);
		order.setStatus(this.status);

		return order;
	}

	public Set<OrderDetails> createOrderDetails(Order savedOrder) {
		for (OrderDetails orderDetails : this.orderDetails) {
			orderDetails.getOrderDetailsIdentity().setOrder(savedOrder);
		}

		return this.orderDetails;
	}

}
