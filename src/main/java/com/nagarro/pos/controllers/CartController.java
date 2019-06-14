package com.nagarro.pos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constants.ApiConstants;
import com.nagarro.pos.models.Cart;
import com.nagarro.pos.models.CartDetails;
import com.nagarro.pos.models.ErrorMessage;
import com.nagarro.pos.models.Product;
import com.nagarro.pos.services.CartService;
import com.nagarro.pos.services.ProductService;
import com.nagarro.pos.utils.ErrorPayload;

/**
 * CartController class will handle the requests related to cart resource
 * 
 * @author damanpreetbrar
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ApiConstants.CARTS_ROUTE)
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	/**
	 * getAllCarts method returns all carts
	 * 
	 * @return List of carts
	 */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Cart> getAllCarts() {
		return cartService.getAllCarts();
	}

	/**
	 * getCartById method find the cart with id which is passed in the path
	 * 
	 * @param cartId
	 * @return
	 */
	@GetMapping(value = ApiConstants.CART_ID)
	public ResponseEntity<?> getCartById(@PathVariable long cartId) {

		if (cartId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}

		Cart cart = cartService.getCartById(cartId);

		return ResponseEntity.ok().body(cart);
	}

	/**
	 * deleteCartProductsByCartId delete the entries of products from the cart
	 * 
	 * @param cartId
	 * @return
	 */

	@DeleteMapping(value = ApiConstants.CART_ID)
	public ResponseEntity<?> deleteCartProductsByCartId(@PathVariable long cartId) {

		if (cartId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}

		int deletedRows = cartService.deleteCartProductsByCartId(cartId);
		if (deletedRows > 0)
			return ResponseEntity.ok().build();
		else {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.INTERNAL_SERVER_ERROR,
					ApiConstants.ERROR_IN_DELETION);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * addProductToCart method adds a product in the cart
	 * 
	 * @param cartId
	 * @param product
	 * @return
	 */

	@PostMapping(value = ApiConstants.ADD_PRODUCT_TO_CART_ROUTE)
	public ResponseEntity<?> addProductToCart(@PathVariable long cartId, @RequestBody Product product) {
		Product productTobeadded = productService.getProductById(product.getId());
		if (productTobeadded.getStock() > 0) {
			cartService.addProductToCart(cartId, product);
			return getCartById(cartId);
		}

		else {

			ErrorMessage outOfStockError = ErrorPayload.createErrorPayload(HttpStatus.INTERNAL_SERVER_ERROR,
					ApiConstants.OUT_OF_STOCK_ERROR);
			return ResponseEntity.badRequest().body(outOfStockError);
		}

	}

	/**
	 * updateProductQuantity method increments or decrements product quantity in the
	 * cart
	 * 
	 * @param cartId
	 * @param productId
	 * @param cartDetails
	 * @return
	 */
	@PutMapping(value = ApiConstants.UPDATE_CART_PRODUCT_QUANTITY_ROUTE)
	public ResponseEntity<?> updateProductQuantity(@PathVariable long cartId, @PathVariable long productId,
			@RequestBody CartDetails cartDetails) {

		Product productTobeadded = productService.getProductById(productId);
		if (productTobeadded.getStock() < cartDetails.getQuantity()) {
			ErrorMessage outOfStockError = ErrorPayload.createErrorPayload(HttpStatus.INTERNAL_SERVER_ERROR,
					ApiConstants.OUT_OF_STOCK_ERROR);
			return ResponseEntity.badRequest().body(outOfStockError);
		}

		int upatedRows = cartService.updateProductQuantity(cartId, productId, cartDetails);
		if (upatedRows != 1)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok().body(cartDetails);

	}

	/**
	 * deleteProductFromCart method delete a particular product from the cart
	 * 
	 * @param cartId
	 * @param productId
	 * @return
	 */

	@DeleteMapping(value = ApiConstants.DELETE_PRODUCT_FROM_CART_ROUTE)
	public ResponseEntity<?> deleteProductFromCart(@PathVariable long cartId, @PathVariable long productId) {

		if (cartId <= 0 || productId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}
		int deletedRows = cartService.deleteProductFromCart(cartId, productId);
		if (deletedRows > 0)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.badRequest().build();
	}

}
