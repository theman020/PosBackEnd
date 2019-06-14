package com.nagarro.pos.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.pos.constants.Constants;
import com.nagarro.pos.daos.CartDAO;
import com.nagarro.pos.daos.GenericDAO;
import com.nagarro.pos.daos.OrderDAO;
import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.CartDetails;
import com.nagarro.pos.models.CartDetailsIdentity;
import com.nagarro.pos.models.Product;
import com.nagarro.pos.services.CartService;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

	GenericDAO<CartDetails> genericCartDetailsDAO;

	GenericDAO<Cart> genericCartDAO;

	GenericDAO<Product> genericProductDAO;

	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	OrderDAO orderDAO;

	@Autowired
	public void setCartDao(GenericDAO<Cart> dao) {
		this.genericCartDAO = dao;
		genericCartDAO.setClazz(Cart.class);
	}

	@Autowired
	public void setProductDao(GenericDAO<Product> dao) {
		this.genericProductDAO = dao;
		genericProductDAO.setClazz(Product.class);
	}

	@Autowired
	public void setCartDetailsDao(GenericDAO<CartDetails> dao) {
		this.genericCartDetailsDAO = dao;
		genericCartDetailsDAO.setClazz(CartDetails.class);
	}

	@Override
	public List<Cart> getAllCarts() {
		return genericCartDAO.findAll();
	}

	@Override
	@Transactional
	public Cart addCart(Cart cart,long customerId) {

		return cartDAO.createCustomerCart(cart,customerId);

	}

	@Override
	public Cart getCartById(long cartId) {
		return this.genericCartDAO.findById(cartId);
	}

	@Override
	@Transactional
	public CartDetails addProductToCart(long cartId, Product product) {
		CartDetails cartDetails = getCartProductById(cartId, product.getId());
		if (cartDetails == null) {

			CartDetailsIdentity cartDetailsIdentity = new CartDetailsIdentity();
			cartDetailsIdentity.setProduct(product);
			CartDetails newCartDetails = new CartDetails(cartDetailsIdentity, Constants.DEFAULT_QUANTITY);
		
			return cartDAO.addProductToCart(newCartDetails,cartId);
		}

		cartDetails.incrementQuantity();
		return genericCartDetailsDAO.update(cartDetails);
	}

	@Override
	public CartDetails getCartProductById(long cartId, long productId) {
		return cartDAO.getCartProductById(cartId, productId);
	}

	@Override
	@Transactional
	public int updateProductQuantity(long cartId, long productId, CartDetails cartDetails) {
		return cartDAO.updateProductQuantity(cartId, productId,cartDetails);
	}

	@Override
	public long deleteCartById(long cartId) {
		 genericCartDAO.deleteById(cartId);
		 return cartId;
	}

	@Override
	@Transactional
	public int deleteCartProductsByCartId(long cartId) {
		return cartDAO.deleteCartProductsByCartId(cartId);
		
	}

	@Override
	@Transactional
	public int deleteProductFromCart(long cartId, long productId) {
		return cartDAO.deleteProductFromCart(cartId,productId);
	}

	@Override
	public Cart updateCart(Cart cart) {
		return genericCartDAO.update(cart);
	}

	@Override
	@Transactional
	public Cart reloadCustomerOrder(Cart cart, long orderId) {
		Cart updatedCart=genericCartDAO.update(cart);
		orderDAO.changeStatusToReopen(orderId);
		return updatedCart;
	}

}
