package com.nagarro.pos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constants.ApiConstants;
import com.nagarro.pos.models.ErrorMessage;
import com.nagarro.pos.models.Product;
import com.nagarro.pos.services.ProductService;
import com.nagarro.pos.utils.ErrorPayload;

/**
 * ProductController class will handle the requests related to product resource
 * 
 * @author damanpreetbrar
 *
 */
@RestController
@RequestMapping(value = ApiConstants.PRODUCTS_ROUTE)
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * searchProducts method will search the products depending upon the passed
	 * query parameter named searchParameter If it is not present then it will
	 * return all the products
	 * 
	 * @param searchParameter
	 * @return List of products
	 */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Product> searchProducts(@RequestParam(required = false) String searchParameter) {
		if (searchParameter != null) {
			return productService.searchProducts(searchParameter);
		}
		return productService.getAllProducts();
	}

	/**
	 * addProduct method create a product
	 * 
	 * @param product
	 * @return Product
	 */

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	/**
	 * getProductById method find the product with id which is passed in the path
	 * 
	 * @param productId
	 * @return
	 */
	@GetMapping(value = ApiConstants.PRODUCT_ID)
	public ResponseEntity<?> getProductById(@PathVariable long productId) {

		if (productId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}
		Product product = productService.getProductById(productId);

		return ResponseEntity.ok().body(product);
	}
}
