package com.nagarro.pos.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constants.ApiConstants;
import com.nagarro.pos.dtos.LoginPostDTO;
import com.nagarro.pos.dtos.LogoutDTO;
import com.nagarro.pos.dtos.ReportDTO;
import com.nagarro.pos.enums.Status;
import com.nagarro.pos.models.CashDrawerDetails;
import com.nagarro.pos.models.Employee;
import com.nagarro.pos.models.ErrorMessage;
import com.nagarro.pos.models.Order;
import com.nagarro.pos.services.CashDrawerDetailsService;
import com.nagarro.pos.services.EmployeeService;
import com.nagarro.pos.services.OrderService;
import com.nagarro.pos.utils.ErrorPayload;
import com.nagarro.pos.utils.ReportBuilder;

/**
 * EmployeeController class will handle the requests related to employee
 * resource
 * 
 * @author damanpreetbrar
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ApiConstants.EMPLOYEES_ROUTE)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CashDrawerDetailsService cashDrawerDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 
	 * @param employee
	 * @return Employee
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		return employeeService.addEmployee(employee);
	}

	/**
	 * getEmployeeByUsername method fetches a particular order whose username is
	 * passed in the path
	 * 
	 * @param username
	 * @return
	 */

	@GetMapping(value = ApiConstants.GET_EMPLOYEE_BY_USERNAME_ROUTE)
	public Employee getEmployeeByUsername(@PathVariable String username) {

		return employeeService.getEmployeeByUsername(username);

	}

	/**
	 * authenticateAndAddEmployeeCashDrawerDetails method authenticates employee and
	 * also add details of cash drawer
	 * 
	 * @param loginPostDTO
	 * @return
	 */

	@PostMapping(value = ApiConstants.EMPLOYEE_LOGIN_ROUTE)
	public ResponseEntity<?> authenticateAndAddEmployeeCashDrawerDetails(@RequestBody LoginPostDTO loginPostDTO) {

		Employee employee = employeeService.getEmployeeByUsername(loginPostDTO.getUsername());
		if (employee == null) {
			ErrorMessage usernameDoesNotExistError = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					"Username doesnot exists");
			return ResponseEntity.badRequest().body(usernameDoesNotExistError);
		}
		if (passwordEncoder.matches(loginPostDTO.getPassword(), employee.getPassword())) {
//		if(loginPostDTO.getPassword().equals(employee.getPassword())) {

			CashDrawerDetails cashDrawer = employeeService.addCashDrawerDetails(employee, loginPostDTO);
			employee.setCashDrawerId(cashDrawer.getId());
			return ResponseEntity.ok().body(employee);
		}

		ErrorMessage passwordIncorrectError = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
				"Password incorrect");
		return ResponseEntity.badRequest().body(passwordIncorrectError);
	}

	/**
	 * getSavedOrPlacedOrder method searches for the orders that are either placed
	 * or saved by the employee
	 * 
	 * @param employeeId
	 * @param status
	 * @return
	 */

	@GetMapping(value = ApiConstants.GET_EMPLOYEE_SAVED_OR_PLACED_ORDERS_ROUTE)
	public ResponseEntity<?> getSavedOrPlacedOrder(@PathVariable long employeeId,
			@RequestParam(required = false) String status) {

		if (employeeId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}

		Status orderStatus = Status.valueOf(status);
		List<Order> orders = orderService.getEmployeeSavedOrPlacedOrders(employeeId, orderStatus);

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	/**
	 * generateOrdersReport method first get all orders of the employee and then
	 * convert hem to ReportDTO objects and finally return it
	 * 
	 * @param orderId
	 * @throws ParseException
	 */
	@GetMapping(value = ApiConstants.GENERATE_ORDER_REPORT_ROUTE)
	public ResponseEntity<?> generateOrdersReport(@PathVariable long employeeId,
			@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate)
			throws ParseException {

		if (fromDate != null || toDate != null) {
			Date fromCreateDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			Date toCreateDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);

			List<Order> orderList = orderService.getEmployeeAllOrders(employeeId, fromCreateDate, toCreateDate);

			if (orderList.size() == 0) {

				ErrorMessage noResultFound = ErrorPayload.createErrorPayload(HttpStatus.NO_CONTENT,
						ApiConstants.NO_RESULT_FOUND);

				return new ResponseEntity<ErrorMessage>(noResultFound, HttpStatus.BAD_REQUEST);

			}
			List<ReportDTO> reportDTO = ReportBuilder.generateReportDTO(orderList);

			return ResponseEntity.ok().body(reportDTO);
		}

		ErrorMessage incorrectQueryParam = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
				ApiConstants.INCORRECT_QUERY_PARAM);

		return new ResponseEntity<ErrorMessage>(incorrectQueryParam, HttpStatus.BAD_REQUEST);

	}

	/**
	 * sumTodaysCash method will return sum of all the cash transaction amount
	 * 
	 * @param employeeId
	 * @return
	 */

	@GetMapping(value = ApiConstants.GET_SUM_OF_TODAYS_EMPLOYEE_CASH_ORDERS)
	public ResponseEntity<?> sumTodaysCash(@PathVariable long employeeId, @PathVariable long cashDrawerId) {
		if (employeeId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);
			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}

		Double totalCashSum = orderService.sumTodaysCash(employeeId, cashDrawerId);

		if (totalCashSum == null) {
			ErrorMessage noCashTransaction = ErrorPayload.createErrorPayload(HttpStatus.OK,
					ApiConstants.NO_CASH_TRANSACTION_FOR_TODAY);
			return new ResponseEntity<ErrorMessage>(noCashTransaction, HttpStatus.OK);
		}

		return ResponseEntity.ok().body(totalCashSum);
	}

	/**
	 * getEmployeeCashDrawer method will return the cash drawer details of the
	 * employee
	 * 
	 * @param employeeId
	 * @return
	 */
	@GetMapping(ApiConstants.EMPLOYEE_CASH_DRAWER)
	public ResponseEntity<?> getEmployeeCashDrawer(@PathVariable long employeeId) {
		if (employeeId <= 0) {
			ErrorMessage errorPayload = ErrorPayload.createErrorPayload(HttpStatus.BAD_REQUEST,
					ApiConstants.INVALID_ID_ERROR);

			return new ResponseEntity<ErrorMessage>(errorPayload, HttpStatus.BAD_REQUEST);
		}

		List<CashDrawerDetails> cashDrawer = cashDrawerDetailsService.getEmployeeCashDrawer(employeeId);

		return new ResponseEntity<List<CashDrawerDetails>>(cashDrawer, HttpStatus.OK);
	}

	/**
	 * logout method logged out the employee with id passed in the path
	 * 
	 * @return
	 */
	@PostMapping(value = ApiConstants.EMPLOYEE_LOGOUT_ROUTE)
	public ResponseEntity<?> logout(@RequestBody LogoutDTO logoutDTO) {
		cashDrawerDetailsService.updateEndingAmount(logoutDTO);

		return ResponseEntity.ok().build();
	}

}
