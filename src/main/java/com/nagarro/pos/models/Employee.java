package com.nagarro.pos.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.nagarro.pos.constants.Constants;

@Entity
@Table(name=Constants.EMPLOYEES)
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9029145216080312501L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=Constants.ID)
	private long id;
	
	@Column(name=Constants.USERNAME,nullable=false,length=20,unique=true)
	private String username;
	
	@Column(name=Constants.PASSWORD,nullable=false)
	private String password;
	
	@Column(name=Constants.MANAGER_NAME,nullable=false,length=45)
	private String managerName;

	@Transient
	private long cashDrawerId;
	
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return managerName;
	}

	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * @return the cashDrawerId
	 */
	public long getCashDrawerId() {
		return cashDrawerId;
	}

	/**
	 * @param cashDrawerId the cashDrawerId to set
	 */
	public void setCashDrawerId(long cashDrawerId) {
		this.cashDrawerId = cashDrawerId;
	}
	
	
	
}
