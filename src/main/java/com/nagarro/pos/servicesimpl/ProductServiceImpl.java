package com.nagarro.pos.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.pos.daos.GenericDAO;
import com.nagarro.pos.daos.ProductDAO;
import com.nagarro.pos.models.Product;
import com.nagarro.pos.services.ProductService;


@Service
@Transactional(readOnly=true)
public class ProductServiceImpl implements ProductService {

	GenericDAO<Product> genericDAO;
	
	 @Autowired
	   public void setDao( GenericDAO< Product > dao ){
	      this.genericDAO = dao;
	      genericDAO.setClazz( Product.class );
	   }
	 
	 @Autowired
	 ProductDAO productDAO;

	@Override
	public List<Product> getAllProducts() {
		return genericDAO.findAll();
	}

	@Override
	@Transactional
	public Product addProduct(Product product) {
		return genericDAO.create(product);
	}

	@Override
	public Product getProductById(long productId) {
		return genericDAO.findById(productId);
	}

	@Override
	public List<Product> searchProducts(String searchParameter) {

		try
		{
			long productId=Long.parseLong(searchParameter);
			return productDAO.searchProductById(productId);
		}
		catch(NumberFormatException ex) {
			return productDAO.searchProductsByNameOrDescription(searchParameter);
		}
		
	
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		genericDAO.update(product);
		
	}

	@Override
	@Transactional
	public void updateProductStock(long productId, int quantity) {
		productDAO.updateProductStock(productId,quantity);		
	}

}
