package com.nagarro.pos.daosimpl;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.daos.CartDAO;
import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.CartDetails;
import com.nagarro.pos.models.Customer;

@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public CartDetails getCartProductById(long cartId, long productId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return (CartDetails) session.createQuery("from CartDetails where cart_id=:cartId and product_id=:productId")
					.setParameter("cartId", cartId).setParameter("productId", productId).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

	@Override
	public Cart createCustomerCart(Cart cart, long customerId) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.load(Customer.class, customerId);
		cart.setCustomer(customer);
		session.save(cart);
		return cart;
	}

	@Override
	public CartDetails addProductToCart(CartDetails cartDetails, long cartId) {
		Session session = sessionFactory.getCurrentSession();
		Cart cart = (Cart) session.load(Cart.class, cartId);
		cartDetails.getCartDetailsIdentity().setCart(cart);
		
		session.save(cartDetails);
		return cartDetails;
		
	}

	

	@Override
	public int updateProductQuantity(long cartId, long productId, CartDetails cartDetails) {
		Session session = sessionFactory.getCurrentSession();
		int updatedRows= session.createQuery("update CartDetails set quantity =:quantity  where cart_id=:cartId and product_id=:productId")
				.setParameter("quantity",cartDetails.getQuantity() )
				.setParameter("cartId", cartId)
				.setParameter("productId", productId)
				.executeUpdate();
		
		return updatedRows;
	}

	@Override
	public int deleteCartProductsByCartId(long cartId) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("delete from CartDetails where cart_id=:cartId")
			.setParameter("cartId", cartId)
			.executeUpdate();
		
	}

	@Override
	public int deleteProductFromCart(long cartId, long productId) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("delete from CartDetails where cart_id=:cartId and product_id=:productId")
			.setParameter("cartId", cartId)
			.setParameter("productId", productId)
			.executeUpdate();
	}

	

}
