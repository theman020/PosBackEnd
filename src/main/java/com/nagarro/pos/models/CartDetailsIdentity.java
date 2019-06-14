package com.nagarro.pos.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Embeddable
public class CartDetailsIdentity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4539276299991331403L;

	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	Cart cart;

	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	Product product;
	
	public CartDetailsIdentity() { }
		

	public CartDetailsIdentity(Cart cart, Product product) {
		super();
		this.cart = cart;
		this.product = product;
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

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
		
}
