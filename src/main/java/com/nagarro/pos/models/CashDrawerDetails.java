package com.nagarro.pos.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nagarro.pos.constants.Constants;

@Entity
@Table(name=Constants.CASH_DRAWER_DETAILS)
public class CashDrawerDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5266657142275076507L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=Constants.ID)
	private long id;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee employee;
	
	@Column(name=Constants.STARTING_AMOUNT)
	private double startingAmount;
	
	@Column(name=Constants.ENDING_AMOUNT)
	private double endingAmount;
	
	
	
	@Column(name=Constants.LOGIN_TIME)
	@CreationTimestamp
	private LocalDateTime loginTime;

	@Column(name=Constants.LOGOUT_TIME)
	@UpdateTimestamp
	private LocalDateTime logoutTime;
	
	


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
	 * @return the employee
	 */
	@JsonIgnore
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
	 * @return the startingAmount
	 */
	public double getStartingAmount() {
		return startingAmount;
	}

	/**
	 * @param startingAmount the startingAmount to set
	 */
	public void setStartingAmount(double startingAmount) {
		this.startingAmount = startingAmount;
	}

	/**
	 * @return the endingAmount
	 */
	public double getEndingAmount() {
		return endingAmount;
	}

	/**
	 * @param endingAmount the endingAmount to set
	 */
	public void setEndingAmount(double endingAmount) {
		this.endingAmount = endingAmount;
	}

	


	

	/**
	 * @return the loginTime
	 */
	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the logoutTime
	 */
	public LocalDateTime getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @param logoutTime the logoutTime to set
	 */
	public void setLogoutTime(LocalDateTime logoutTime) {
		this.logoutTime = logoutTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CashDrawerDetails [id=" + id + ", employee=" + employee + ", startingAmount=" + startingAmount
				+ ", endingAmount=" + endingAmount + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + "]";
	}
	

}
