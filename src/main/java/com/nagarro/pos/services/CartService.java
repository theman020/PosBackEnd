package com.nagarro.pos.services;

import java.util.List;

import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.CartDetails;
import com.nagarro.pos.models.Product;

/**
 * This is a CartService interface for implementing cart related services
 * 
 * @author damanpreetbrar
 *
 */
public interface CartService {

	List<Cart> getAllCarts();

	Cart getCartById(long cartId);

	CartDetails addProductToCart(long cartId, Product product);

	CartDetails getCartProductById(long cartId, long poductId);

	Cart addCart(Cart cart, long customerId);

	int updateProductQuantity(long cartId, long productId, CartDetails cartDetails);

	long deleteCartById(long cartId);

	int deleteCartProductsByCartId(long cartId);

	int deleteProductFromCart(long cartId, long productId);

	Cart updateCart(Cart cart);

	Cart reloadCustomerOrder(Cart cart, long orderId);
}
