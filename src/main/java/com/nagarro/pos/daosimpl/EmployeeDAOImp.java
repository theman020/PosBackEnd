package com.nagarro.pos.daosimpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.daos.EmployeeDAO;
import com.nagarro.pos.dtos.LoginPostDTO;
import com.nagarro.pos.models.CashDrawerDetails;
import com.nagarro.pos.models.Employee;

@Repository
public class EmployeeDAOImp implements EmployeeDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public Employee getEmployeeByUsername(String username) {
		Session session=sessionFactory.getCurrentSession();
		try {
		return (Employee) session.createQuery("from Employee where username=:username")
			.setParameter("username", username).getSingleResult();
		}
		catch(NoResultException exception) {
			return null;
		}
	}




	@Override
	@SuppressWarnings("unchecked")
	public Employee authenticateEmployee(LoginPostDTO loginPostDTO) {
		Session session=sessionFactory.getCurrentSession();
		
		List<Employee> employeeList= session.createQuery("from Employee where username=:username and password=:password")
				.setParameter("username", loginPostDTO.getUsername())
				.setParameter("password", loginPostDTO.getPassword())
				.list();
		
		if(employeeList.size()==0)
			return null;
		
		return employeeList.get(0);
		
	}


	@Override
	public CashDrawerDetails addCashDrawerDetails(Employee authenticatedEmployee, LoginPostDTO loginPostDTO) {
		CashDrawerDetails cashDrawerDetails=new CashDrawerDetails();
		cashDrawerDetails.setStartingAmount(loginPostDTO.getStartingAmount());
		cashDrawerDetails.setEmployee(authenticatedEmployee);
		
		sessionFactory.getCurrentSession().save(cashDrawerDetails);
		 
		return cashDrawerDetails;
	}


	
}
