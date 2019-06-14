package com.nagarro.pos.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nagarro.pos.constants.Constants;

@Entity
@Table(name = Constants.CARTS)
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -932011675760880455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID)
	private long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Customer customer;

	@Column
	@OneToMany(fetch = FetchType.EAGER, mappedBy = Constants.CART_DETAILS_IDENTITY_CART, cascade = CascadeType.ALL)
	Set<CartDetails> cartDetails = new HashSet<>();

	public Cart() {
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the customer
	 */
	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the cartDetails
	 */
	public Set<CartDetails> getCartDetails() {
		return cartDetails;
	}

	/**
	 * @param cartDetails
	 *            the cartDetails to set
	 */
	public void setCartDetails(Set<CartDetails> cartDetails) {
		this.cartDetails = cartDetails;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer=" + customer + ", cartDetails=" + cartDetails + "]";
	}

}
