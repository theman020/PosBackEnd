package com.nagarro.pos.constants;

/**
 * This class Constants consists of all app regarding constants.
 * 
 * @author damanpreetbrar
 *
 */
public class Constants {

	public static final String ID = "id";

	/* Employee Model Constants */

	public static final String EMPLOYEES = "employees";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String MANAGER_NAME = "managerName";

	/* Customer Model Constants */

	public static final String CUSTOMERS = "customers";
	public static final String EMAIL = "email";
	public static final String NAME = "name";
	public static final String MOBILE_NUMBER = "mobileNumber";

	/* Product Model Constants */

	public static final String PRODUCTS = "products";
	public static final String DESCRIPTION = "description";
	public static final String STOCK = "stock";
	public static final String UNIT_PRICE = "unitPrice";
	public static final String IMAGE_PATH = "image_path";

	/* Cart Model Constants */

	public static final String CARTS = "carts";

	/* CartDetails Model Constants */

	public static final String CART_DETAILS = "cart_details";
	public static final String QUANTITY = "quantity";
	public static final int DEFAULT_QUANTITY = 1;

	/* CartDetails Identity Model Constants */

	public static final String PRODUCT_ID = "product_id";
	public static final String CART_ID = "cart_id";
	public static final String CART_DETAILS_IDENTITY_CART = "cartDetailsIdentity.cart";
	public static final String CART_DETAILS_IDENTITY_PRODUCT = "cartDetailsIdentity.product";

	/* Order Model Constants */

	public static final String ORDERS = "orders";
	public static final String ORDER_ID = "order_id";
	public static final String MODE_OF_PAYMENT = "mode_of_payment";

	/* OrderDetails Model Constants */

	public static final String ORDER_DETAILS = "order_details";
	public static final String ORDER_DETAILS_IDENTITY_ORDER = "orderDetailsIdentity.order";
	public static final String ORDER_DETAILS_IDENTITY_PRODUCT = "orderDetailsIdentity.product";

	/* CashDrawer Model Constants */

	public static final String CASH_DRAWER_DETAILS = "cash_drawer_details";
	public static final String STARTING_AMOUNT = "starting_amount";
	public static final String ENDING_AMOUNT = "ending_amount";
	public static final String LOGIN_TIME = "login_time";
	public static final String LOGOUT_TIME = "logout_time";
	public static final String STATUS = "status";

}
