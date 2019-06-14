package com.nagarro.pos.daos;

import java.util.List;

import com.nagarro.pos.models.Product;

/**
 * this is a ProductDAO interface for Product related operation
 * 
 * @author damanpreetbrar
 *
 */
public interface ProductDAO {

	List<Product> searchProductsByNameOrDescription(String searchParameter);

	List<Product> searchProductById(long productId);

	void updateProductStock(long productId, int quantity);
}
