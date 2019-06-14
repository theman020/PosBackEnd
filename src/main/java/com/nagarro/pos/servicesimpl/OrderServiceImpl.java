package com.nagarro.pos.servicesimpl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.pos.daos.GenericDAO;
import com.nagarro.pos.daos.OrderDAO;
import com.nagarro.pos.enums.Status;
import com.nagarro.pos.models.CashDrawerDetails;
import com.nagarro.pos.models.Order;
import com.nagarro.pos.models.OrderDetails;
import com.nagarro.pos.services.OrderService;

@Service
@Transactional(readOnly=true)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDAO;

	GenericDAO<Order> genericOrderDAO;
	
	GenericDAO<CashDrawerDetails> genericCashDrawerDAO;
	
	 @Autowired
	   public void setOrderDao( GenericDAO< Order > dao ){
	      this.genericOrderDAO = dao;
	      genericOrderDAO.setClazz( Order.class );
	 }
	 
	 GenericDAO<OrderDetails> genericOrderDetailDAO;
		
	 @Autowired
	   public void setOrderDetailsDao( GenericDAO< OrderDetails > dao ){
	      this.genericOrderDetailDAO = dao;
	      genericOrderDetailDAO.setClazz( OrderDetails.class );
	      
	 }

	 
	 @Autowired
	   public void setCashDrawerDao( GenericDAO< CashDrawerDetails > dao ){
	      this.genericCashDrawerDAO = dao;
	      genericCashDrawerDAO.setClazz( CashDrawerDetails.class );
	 }
	 
	@Override
	@Transactional
	public Order create(Order order) {
		return genericOrderDAO.create(order);
	}

	@Override
	public Order getOrderById(long orderId) {
		return genericOrderDAO.findById(orderId);
	}



	@Override
	@Transactional
	public Order updateOrder(Order order) {
		return this.genericOrderDAO.update(order);
	}

	@Override
	@Transactional
	public void saveOrderDetails(Set<OrderDetails> orderDetails) {
		for(OrderDetails orderDetail : orderDetails)
			 this.genericOrderDetailDAO.create(orderDetail);
	}

	@Override
	public List<Order> getEmployeeSavedOrPlacedOrders(long employeeId, Status orderStatus) {
		return orderDAO.getEmployeeSavedOrPlacedOrders(employeeId,orderStatus);
	}

	@Override
	public Double sumTodaysCash(long employeeId) {
		
		return orderDAO.sumTodaysCash(employeeId);
				
	}
	
	@Override
	public List<Order> getEmployeeAllOrders(long employeeId){
		return orderDAO.getEmployeeAllOrders(employeeId);
	}

	@Override
	public List<Order> getEmployeeAllOrders(long employeeId, Date fromDate,Date toDate) {
		return orderDAO.getEmployeeAllOrders(employeeId,fromDate,toDate);
	}


	@Override
	public Double sumTodaysCash(long employeeId, long cashDrawerId) {
		CashDrawerDetails cashDrawerDetails=genericCashDrawerDAO.findById(cashDrawerId);
		return orderDAO.sumTodaysCash(employeeId,cashDrawerDetails.getStartingAmount(),cashDrawerDetails.getLoginTime());
		
	}
	 
	 
}
