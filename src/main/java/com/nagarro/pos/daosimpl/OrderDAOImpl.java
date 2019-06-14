package com.nagarro.pos.daosimpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.daos.OrderDAO;
import com.nagarro.pos.enums.Status;
import com.nagarro.pos.models.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getEmployeeSavedOrPlacedOrders(long employeeId, Status orderStatus) {
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery(
				"select new map(order.id as id,order.customer.name as customerName,order.amount as amount,order.status as status,order.createOrderDateTime as createOrderDateTime) from Order order where status=:status and employee_id=:employeeId order by createOrderDateTime desc")
				.setParameter("status", orderStatus).setParameter("employeeId", employeeId).list();
	}

	@Override
	public void changeStatusToReopen(long orderId) {
		Session session = sessionFactory.getCurrentSession();

		session.createQuery("update Order set status=:status where id=:orderId").setParameter("status", Status.REOPEN)
				.setParameter("orderId", orderId).executeUpdate();

	}

	@Override
	public Double sumTodaysCash(long employeeId) {
		Session session = sessionFactory.getCurrentSession();
		Object ob = session.createQuery(
				"select sum(amount) as totalCash from Order where employee_id=:employeeId and mode_of_payment=:modeOfPayment and date(createOrderDateTime) = CURDATE()")
				.setParameter("employeeId", employeeId).setParameter("modeOfPayment", 0).list().get(0);

		return (Double) ob;
	}

	@Override
	public List<Order> getEmployeeAllOrders(long employeeId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Order where employee_id=:employeeId", Order.class)
				.setParameter("employeeId", employeeId).list();
	}

	@Override
	public List<Order> getEmployeeAllOrders(long employeeId, Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(
				"from Order where employee_id=:employeeId and date(createOrderDateTime) between :fromDate and :toDate",
				Order.class).setParameter("employeeId", employeeId).setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).list();
	}

	@Override
	public Double sumTodaysCash(long employeeId, double startingAmount, LocalDateTime loginTime) {

		LocalDateTime logoutTime = LocalDateTime.now();
		Session session = sessionFactory.getCurrentSession();
		Object ob = session.createQuery(
				"select sum(amount) as totalCash from Order where employee_id=:employeeId and mode_of_payment=:modeOfPayment and status=:status and createOrderDateTime between :login and :logout ")
				.setParameter("employeeId", employeeId).setParameter("modeOfPayment", 0)
				.setParameter("login", loginTime).setParameter("status", Status.COMPLETED)
				.setParameter("logout", logoutTime).list().get(0);
		System.out.println("+++++++++++++++>>>>>>>>>>>>>>object = " + ob);
		return (Double) ob;
	}

}
