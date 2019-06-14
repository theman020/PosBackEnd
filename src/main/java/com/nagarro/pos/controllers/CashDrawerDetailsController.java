package com.nagarro.pos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constants.ApiConstants;
import com.nagarro.pos.models.CashDrawerDetails;
import com.nagarro.pos.services.CashDrawerDetailsService;

/**
 * CashDrawerDetailsController class will handle the requests related to
 * CashDrawerDetails resource
 * 
 * @author damanpreetbrar
 *
 */
@CrossOrigin(origins = "*")
@RestController(value = ApiConstants.CASH_DRAWER_DETAILS_ROUTES)
public class CashDrawerDetailsController {

	@Autowired
	private CashDrawerDetailsService cashDrawerDetailsService;

	/**
	 * getAllCashDrawers method returns all cash drawers details
	 * 
	 * @return List of CashDrawerDetails
	 */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CashDrawerDetails> getAllCashDrawers() {
		return cashDrawerDetailsService.getAllCashDrawerDetails();
	}

}
