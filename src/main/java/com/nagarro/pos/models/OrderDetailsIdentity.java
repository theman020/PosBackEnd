package com.nagarro.pos.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class OrderDetailsIdentity implements Serializable {

	private static final long serialVersionUID = 2590392756238686955L;

	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	Order order;

	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	Product product;

	/**
	 * @return the order
	 */
	@JsonIgnore
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDetailsIdentity [order=" + order + ", product=" + product + "]";
	}
	
	
}
