package com.nagarro.pos.daos;

import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.CartDetails;

/**
 * this is a CartDAO interface for cart related operation
 * 
 * @author damanpreetbrar
 *
 */
public interface CartDAO {

	CartDetails getCartProductById(long cartId, long poductId);

	Cart createCustomerCart(Cart cart, long customerId);

	CartDetails addProductToCart(CartDetails newCartDetails, long cartId);

	int updateProductQuantity(long cartId, long productId, CartDetails cartDetails);

	int deleteCartProductsByCartId(long cartId);

	int deleteProductFromCart(long cartId, long productId);

}
