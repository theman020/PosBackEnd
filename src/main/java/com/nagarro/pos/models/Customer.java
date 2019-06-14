package com.nagarro.pos.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nagarro.pos.constants.Constants;

@Entity
@Table(name=Constants.CUSTOMERS)
public class Customer implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 2553276531488178247L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=Constants.ID)
	private long id;
	
	@Column(name=Constants.NAME)
	private String name;
	
	
	@Column(name=Constants.EMAIL,nullable=false)
	private String email;

	@Column(name=Constants.MOBILE_NUMBER,nullable=false)
	private String mobileNumber;
	
	
	@OneToOne(mappedBy="customer",fetch=FetchType.LAZY)
	private Cart cart;
	

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	

	/**
	 * @return the cart
	 */
	@JsonIgnore
	public Cart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + "]";
	}
	
}
