package com.nagarro.pos.models;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import com.nagarro.pos.constants.Constants;

@Entity
@Table(name=Constants.CART_DETAILS)
@AssociationOverrides({
	@AssociationOverride(name = Constants.CART_DETAILS_IDENTITY_CART, 
		joinColumns = @JoinColumn(name = Constants.CART_ID)),
	@AssociationOverride(name = Constants.CART_DETAILS_IDENTITY_PRODUCT, 
		joinColumns = @JoinColumn(name = Constants.PRODUCT_ID)) })
public class CartDetails implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2318355200804761134L;


	@EmbeddedId
	private CartDetailsIdentity cartDetailsIdentity;
		
	
	@Column(name=Constants.QUANTITY,columnDefinition="int default 1")
	private int quantity;
	
	
	public CartDetails() {
	}
	

	public CartDetails(CartDetailsIdentity cartDetailsIdentity, int quantity) {
		super();
		this.cartDetailsIdentity = cartDetailsIdentity;
		this.quantity = quantity;
	}

	/**
	 * @return the cartDetailsIdentity
	 */
	public CartDetailsIdentity getCartDetailsIdentity() {
		return cartDetailsIdentity;
	}

	/**
	 * @param cartDetailsIdentity the cartDetailsIdentity to set
	 */
	public void setCartDetailsIdentity(CartDetailsIdentity cartDetailsIdentity) {
		this.cartDetailsIdentity = cartDetailsIdentity;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return cartDetailsIdentity.hashCode();
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return cartDetailsIdentity.equals(obj);
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return cartDetailsIdentity.toString();
	}
	
	
	
	public void incrementQuantity() {
		this.quantity++;
	}
	
	public void decrementQuantity() {
		this.quantity--;
	}
	
}
