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
import javax.persistence.Table;

import com.nagarro.pos.constants.Constants;

@Entity
@Table(name = Constants.PRODUCTS)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2974194386590699266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID)
	private long id;

	@Column(name = Constants.NAME, nullable = false, length = 45)
	private String name;

	@Column(name = Constants.DESCRIPTION, nullable = false, length = 100)
	private String description;
	
	@Column(name = Constants.STOCK, nullable = false)
	private int stock;

	@Column(name = Constants.UNIT_PRICE, nullable = false)
	private double unitPrice;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.CART_DETAILS_IDENTITY_PRODUCT, cascade = CascadeType.ALL)
	private Set<CartDetails> cartDetails = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.ORDER_DETAILS_IDENTITY_PRODUCT, cascade = CascadeType.ALL)
	private Set<OrderDetails> orderDetails = new HashSet<>();

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", stock=" + stock
				+ ", unitPrice=" + unitPrice + ", cartDetails=" + cartDetails + ", orderDetails=" + orderDetails + "]";
	}

}
