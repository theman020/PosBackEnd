package com.nagarro.pos.daosimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.daos.CashDrawerDetailsDAO;
import com.nagarro.pos.dtos.LogoutDTO;
import com.nagarro.pos.models.CashDrawerDetails;


@Repository
public class CashDrawerDetailsDAOImpl implements CashDrawerDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<CashDrawerDetails> getEmployeeCashDrawerDetails(long employeeId) {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from CashDrawerDetails where employee_id=:employeeId order by login_time desc",CashDrawerDetails.class)
		.setParameter("employeeId", employeeId)
		.list();
	}

	@Override
	public void updateEndingAmount(LogoutDTO logoutDTO) {
		Session session=sessionFactory.getCurrentSession();
		LocalDateTime logoutTime=LocalDateTime.now();
		 session.createQuery("update CashDrawerDetails set ending_amount =:endingAmount , logout_time=:logoutTime where id=:cashDrawerId")
		.setParameter("endingAmount", logoutDTO.getEndingAmount())
		.setParameter("cashDrawerId",logoutDTO.getCashDrawerId())
		.setParameter("logoutTime", logoutTime)
		.executeUpdate();
	}

}
