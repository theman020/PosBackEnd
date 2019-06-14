package com.nagarro.pos.servicesimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.daos.CashDrawerDetailsDAO;
import com.nagarro.pos.daos.GenericDAO;
import com.nagarro.pos.dtos.LogoutDTO;
import com.nagarro.pos.models.CashDrawerDetails;
import com.nagarro.pos.services.CashDrawerDetailsService;

@Service
@Transactional
public class CashDrawerDetailsServiceImpl implements CashDrawerDetailsService {

	
	@Autowired
	CashDrawerDetailsDAO cashDrawerDetailsDAO;
	
	GenericDAO<CashDrawerDetails> genericCashDrawerDetailsDAO;
	
	
	
	@Autowired
	public void setCartDao(GenericDAO<CashDrawerDetails> dao) {
		this.genericCashDrawerDetailsDAO = dao;
		genericCashDrawerDetailsDAO.setClazz(CashDrawerDetails.class);
	}
	
	@Override
	public List<CashDrawerDetails> getAllCashDrawerDetails() {
		return genericCashDrawerDetailsDAO.findAll();
	}

	@Override
	public List<CashDrawerDetails> getEmployeeCashDrawer(long employeeId) {
		return cashDrawerDetailsDAO.getEmployeeCashDrawerDetails(employeeId);
	}

	@Override
	public void updateEndingAmount(LogoutDTO logoutDTO) {
		cashDrawerDetailsDAO.updateEndingAmount(logoutDTO);
		
	}

}
