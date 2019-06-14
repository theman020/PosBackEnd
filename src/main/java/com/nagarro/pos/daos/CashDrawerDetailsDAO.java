package com.nagarro.pos.daos;

import java.util.List;

import com.nagarro.pos.dtos.LogoutDTO;
import com.nagarro.pos.models.CashDrawerDetails;

/**
 * this is a CashDrawerDetailsDAO interface for Cash Drawer related operation
 * 
 * @author damanpreetbrar
 *
 */
public interface CashDrawerDetailsDAO {

	List<CashDrawerDetails> getEmployeeCashDrawerDetails(long employeeId);

	void updateEndingAmount(LogoutDTO logoutDTO);
}
