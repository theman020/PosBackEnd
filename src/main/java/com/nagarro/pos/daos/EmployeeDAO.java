package com.nagarro.pos.daos;

import com.nagarro.pos.dtos.LoginPostDTO;
import com.nagarro.pos.models.CashDrawerDetails;
import com.nagarro.pos.models.Employee;

/**
 * this is a Employee interface for Employee related operation
 * 
 * @author damanpreetbrar
 *
 */
public interface EmployeeDAO {

	Employee getEmployeeByUsername(String username);

	Employee authenticateEmployee(LoginPostDTO loginPostDTO);

	CashDrawerDetails addCashDrawerDetails(Employee authenticatedEmployee, LoginPostDTO loginPostDTO);

}
