package com.nagarro.pos.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.nagarro.pos.constants.Constants;
import com.nagarro.pos.enums.ModeOfPayment;
import com.nagarro.pos.enums.Status;

@Entity
@Table(name=Constants.ORDERS)
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1140831812325685045L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=Constants.ID)
	private long id;
	
	@OneToOne
	private Customer customer;
	
	@OneToOne
	private Employee employee;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name=Constants.MODE_OF_PAYMENT)
	private ModeOfPayment modeOfPayment;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name=Constants.STATUS)
	private Status status;

	@Column
	private double amount;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createOrderDateTime;

	@Column
	@UpdateTimestamp
	private LocalDateTime updateOrderDateTime;
	
	@Column
	@OneToMany(fetch = FetchType.EAGER, mappedBy = Constants.ORDER_DETAILS_IDENTITY_ORDER, cascade=CascadeType.ALL)
	Set<OrderDetails> orderDetails=new HashSet<>();
	

	public Order() {}
	
	public Order(long orderId) {
		this.id=orderId;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	

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
	 * @return the createOrderDateTime
	 */
	public LocalDateTime getCreateOrderDateTime() {
		return createOrderDateTime;
	}

	/**
	 * @param createOrderDateTime the createOrderDateTime to set
	 */
	public void setCreateOrderDateTime(LocalDateTime createOrderDateTime) {
		this.createOrderDateTime = createOrderDateTime;
	}

	/**
	 * @return the updateOrderDateTime
	 */
	public LocalDateTime getUpdateOrderDateTime() {
		return updateOrderDateTime;
	}

	/**
	 * @param updateOrderDateTime the updateOrderDateTime to set
	 */
	public void setUpdateOrderDateTime(LocalDateTime updateOrderDateTime) {
		this.updateOrderDateTime = updateOrderDateTime;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + " customer=" + customer + ", employee=" + employee
				+ ", modeOfPayment=" + modeOfPayment + ", amount=" + amount + ", createDateTime=" + createOrderDateTime
				+ ", updateDateTime=" + updateOrderDateTime + ", orderDetails=" + orderDetails + "]";
	}

	
	
	
	
}
