package com.revature.Objects;

import java.io.Serializable;
import java.util.HashMap;

import com.revature.DAO.UserDAO;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class UserDatabase implements Serializable {

	private static HashMap<String, Customer> customers = new HashMap<>();
	private static HashMap<String, Employee> employees = new HashMap<>();

	// CUSTOMER METHODS
	
	/**
	 * @return
	 */
	public static HashMap<String, Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param username
	 * @return
	 */
	public static Customer getCustomer(String username) {
		return customers.get(username);
	}
	

	/**
	 * @param username
	 * @param newUser
	 */
	public static void addCustomer(String username, Customer newUser) {
		UserDatabase.customers.put(username, newUser);
	}
	
	/**
	 * @param customers
	 */
	public static void setCustomers(HashMap<String, Customer> customers) {
		UserDatabase.customers = customers;
	}

	
	// EMPLOYEE METHODS
	
	/**
	 * @return
	 */
	public static HashMap<String, Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param username
	 * @return
	 */
	public static Employee getEmployee(String username) {
		return employees.get(username);
	}

	/**
	 * @param username
	 * @param newUser
	 */
	public static void addEmployee(String username, Employee newUser) {
		UserDAO userDAO = new UserDAO();
		UserDatabase.employees.put(username, newUser);
		userDAO.createEmployeeDatabase(employees);

	}

	/**
	 * @param employees
	 */
	public static void setEmployees(HashMap<String, Employee> employees) {
		UserDatabase.employees = employees;
	}
}
