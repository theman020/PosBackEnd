package com.nagarro.pos.constants;

/**
 * This class ApiConstants consists of all api regarding constants.
 * 
 * @author damanpreetbrar
 *
 */
public class ApiConstants {

	/* Customer Resource routes constants */

	public static final String CUSTOMERS_ROUTE = "/api/customers";
	public static final String CUTOMER_ID = "/{customerId}";
	public static final String CUSTOMER_CART_ROUTE = "/{customerId}/carts";

	/* Product Resource routes constants */

	public static final String PRODUCTS_ROUTE = "/api/products";
	public static final String PRODUCT_ID = "/{productId}";

	/* Employee Resource routes constants */

	public static final String EMPLOYEES_ROUTE = "/api/employees";
	public static final String EMPLOYEE_ID = "/{employeeId}";
	public static final String EMPLOYEE_LOGIN_ROUTE = "/login";
	public static final String EMPLOYEE_LOGOUT_ROUTE = "/logout";
	public static final String GET_EMPLOYEE_BY_USERNAME_ROUTE = "/{username}";
	public static final String GET_EMPLOYEE_SAVED_OR_PLACED_ORDERS_ROUTE = "/{employeeId}/orders";
	public static final String GET_SUM_OF_TODAYS_EMPLOYEE_CASH_ORDERS = "/{employeeId}/cashdrawers/{cashDrawerId}/orders/sum";
	public static final String EMPLOYEE_CASH_DRAWER = "/{employeeId}/cashdrawerdetails";
	public static final String GENERATE_ORDER_REPORT_ROUTE = "/{employeeId}/orders/report";

	/* Cart Resource routes constants */

	public static final String CARTS_ROUTE = "/api/carts";
	public static final String CART_ID = "/{cartId}";
	public static final String ADD_PRODUCT_TO_CART_ROUTE = "/{cartId}/products";
	public static final String GET_CART_PRODUCTS_ROUTE = "/{cartId}/products";
	public static final String UPDATE_CART_PRODUCT_QUANTITY_ROUTE = "/{cartId}/products/{productId}";
	public static final String DELETE_PRODUCT_FROM_CART_ROUTE = "/{cartId}/products/{productId}";

	/* Order Resource routes constants */

	public static final String ORDERS_ROUTE = "/api/orders";
	public static final String ORDER_ID = "/{orderId}";
	public static final String SAVE_ORDER = "/save";
	public static final String RELOAD_CUSTOMER_ORDER = "/{customerId}/orders/{orderId}/reload";

	/* CashDrawerDetails Resource routes constants */

	public static final String CASH_DRAWER_DETAILS_ROUTES = "/api/cashdrawers";

	/* Error Messages */

	public static final String INVALID_ID_ERROR = "Invalid id";
	public static final String ERROR_IN_DELETION = "Error while deleting objects from the database";
	public static final String NO_CASH_TRANSACTION_FOR_TODAY = "No Cash Transaction for today";
	public static final String OUT_OF_STOCK_ERROR = "Out of stock";
	public static final String NO_RESULT_FOUND = "No Result Found";
	public static final String INCORRECT_QUERY_PARAM = "No Dates selected";
	public static final String SERVER_ERROR_MESSAGE = "Something went wrong";

}
