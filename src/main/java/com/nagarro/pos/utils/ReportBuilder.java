package com.nagarro.pos.utils;

import java.util.ArrayList;
import java.util.List;

import com.nagarro.pos.dtos.ReportDTO;
import com.nagarro.pos.models.Order;

/**
 * Report Builder util class will convert the order object into report object
 * 
 * @author damanpreetbrar
 *
 */
public class ReportBuilder {

	public static List<ReportDTO> generateReportDTO(List<Order> orders) {
		List<ReportDTO> reportElements = new ArrayList<>();

		for (Order order : orders) {
			long orderId = order.getId();
			String customerEmail = order.getCustomer().getEmail();
			String modeOfPayment = order.getModeOfPayment().getValue();
			String status = order.getStatus().getValue();
			String orderDate = order.getCreateOrderDateTime().toString();
			double tax = 10.00;
			double subtotal = order.getAmount() - tax;
			double totalAmount = order.getAmount();

			ReportDTO reportElement = new ReportDTO(orderId, customerEmail, modeOfPayment, status, orderDate, tax,
					subtotal, totalAmount);

			reportElements.add(reportElement);

		}

		return reportElements;
	}
}
