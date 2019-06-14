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
@Table(name=Constants.ORDER_DETAILS)
@AssociationOverrides({
	@AssociationOverride(name = Constants.ORDER_DETAILS_IDENTITY_ORDER, 
		joinColumns = @JoinColumn(name = Constants.ORDER_ID)),
	@AssociationOverride(name = Constants.ORDER_DETAILS_IDENTITY_PRODUCT, 
		joinColumns = @JoinColumn(name = Constants.PRODUCT_ID)) })
public class OrderDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5593096039358379555L;

	@EmbeddedId
	private OrderDetailsIdentity orderDetailsIdentity;
	
	@Column(name=Constants.QUANTITY,columnDefinition="int default 1")
	private int quantity;
	
	@Column(name=Constants.UNIT_PRICE)
	private double unitPrice;

	/**
	 * @return the orderDetailsIdentity
	 */
	public OrderDetailsIdentity getOrderDetailsIdentity() {
		return orderDetailsIdentity;
	}

	/**
	 * @param orderDetailsIdentity the orderDetailsIdentity to set
	 */
	public void setOrderDetailsIdentity(OrderDetailsIdentity orderDetailsIdentity) {
		this.orderDetailsIdentity = orderDetailsIdentity;
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
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDetails [orderDetailsIdentity=" + orderDetailsIdentity + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + "]";
	}

	
	
	
	
	
}
