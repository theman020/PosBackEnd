package com.nagarro.pos.services;

import java.util.List;

import com.nagarro.pos.dtos.LogoutDTO;
import com.nagarro.pos.models.CashDrawerDetails;

/**
 * This is a CashDrawerDetailsService interface for implementing CashDrawer
 * related services
 * 
 * @author damanpreetbrar
 *
 */
public interface CashDrawerDetailsService {

	List<CashDrawerDetails> getAllCashDrawerDetails();

	List<CashDrawerDetails> getEmployeeCashDrawer(long employeeId);

	void updateEndingAmount(LogoutDTO logoutDTO);
}
