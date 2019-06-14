package com.nagarro.pos.services;

import java.util.List;

import com.nagarro.pos.dtos.LoginPostDTO;
import com.nagarro.pos.models.CashDrawerDetails;
import com.nagarro.pos.models.Employee;

/**
 * This is a EmployeeService interface for implementing Employee related
 * services
 * 
 * @author damanpreetbrar
 *
 */
public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee addEmployee(Employee employee);

	Employee getEmployeeById(long employeeId);

	Employee authenticateEmployee(LoginPostDTO loginPostDTO);

	CashDrawerDetails addCashDrawerDetails(Employee authenticatedEmployee, LoginPostDTO loginPostDTO);

	Employee getEmployeeByUsername(String username);

}
