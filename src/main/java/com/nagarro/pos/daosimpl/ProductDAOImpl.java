package com.nagarro.pos.daosimpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.daos.ProductDAO;
import com.nagarro.pos.models.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Product> searchProductsByNameOrDescription(String searchParameter) {
		Session session=sessionFactory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery("from Product where  name like :name or description like :description ", Product.class)
				.setParameter("name", '%'+searchParameter+'%')
				.setParameter("description", '%'+searchParameter+'%');
		List<Product> productsList = query.getResultList();
		
		return productsList;
	}
	
	
	@Override
	public List<Product> searchProductById(long productId) {
		Session session=sessionFactory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery("from Product where  id=:id ", Product.class)
				.setParameter("id", productId);
		List<Product> productsList = query.getResultList();
		
		return productsList;
	}


	@Override
	public void updateProductStock(long productId, int quantity) {
		
		Session session=sessionFactory.getCurrentSession();
		session.createQuery("update  Product set stock=stock - :quantity where  id=:id ")
				.setParameter("id", productId)
				.setParameter("quantity",quantity)
				.executeUpdate();
	}

}
