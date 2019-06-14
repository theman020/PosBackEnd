package com.nagarro.pos.services;

import java.util.List;

import com.nagarro.pos.models.Product;

/**
 * This is a ProductService interface for implementing Product related services
 * 
 * @author damanpreetbrar
 *
 */
public interface ProductService {

	List<Product> getAllProducts();

	Product addProduct(Product product);

	Product getProductById(long productId);

	List<Product> searchProducts(String searchParameter);

	void updateProduct(Product product);

	void updateProductStock(long productId, int quantity);
}
