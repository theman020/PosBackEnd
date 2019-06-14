package com.nagarro.pos.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.pos.daos.EmployeeDAO;
import com.nagarro.pos.daos.GenericDAO;
import com.nagarro.pos.dtos.LoginPostDTO;
import com.nagarro.pos.models.CashDrawerDetails;
import com.nagarro.pos.models.Employee;
import com.nagarro.pos.services.EmployeeService;

@Service
@Transactional(readOnly=true)
public class EmployeeServiceImpl implements EmployeeService {

	
	GenericDAO<Employee> genericDAO;
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	 @Autowired
	   public void setDao( GenericDAO< Employee > dao ){
	      this.genericDAO = dao;
	      genericDAO.setClazz( Employee.class );
	   }
	
	

	@Override
	public List<Employee> getAllEmployees() {
		return genericDAO.findAll();
	}

	@Override
	@Transactional
	public Employee addEmployee(Employee employee) {
		return genericDAO.create(employee);
	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		return genericDAO.findById(employeeId);
	}

	@Override
	public Employee authenticateEmployee(LoginPostDTO loginPostDTO) {
		Employee authenticatedEmployee=employeeDAO.authenticateEmployee(loginPostDTO);
		return authenticatedEmployee;
	}



	
	@Override
	@Transactional
	public CashDrawerDetails addCashDrawerDetails(Employee authenticatedEmployee, LoginPostDTO loginPostDTO) {
		return employeeDAO.addCashDrawerDetails(authenticatedEmployee,loginPostDTO);
		
	}



	@Override
	public Employee getEmployeeByUsername(String username) {
		return employeeDAO.getEmployeeByUsername(username);
	}

}
