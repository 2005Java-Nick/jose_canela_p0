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
	private static UserDatabase userDatabase;
	
	private HashMap<String, Customer> customers = new HashMap<>();
	private HashMap<String, Employee> employees = new HashMap<>();
	
	// CUSTOMER METHODS
	
	/**
	 * @return
	 */
	public static UserDatabase getUserDatabase() {
		if(userDatabase == null) {
			userDatabase = new UserDatabase();
		}
		return userDatabase;
	}
	
	/**
	 * @return
	 */
	public HashMap<String, Customer> getCustomers(){
		UserDAO userDAO = new UserDAO();
		//userDatabase.customers = userDAO.readCustomer();
		
		userDAO.createCustomerDatabase(customers);
		userDatabase.customers = userDAO.readCustomer();
		//userDAO.createCustomerDatabase(customers);
		return customers;
	}
	/**
	 * @param username
	 * @return
	 */
	public Customer getCustomer(String username) {
		return customers.get(username);
	}
	

	/**
	 * @param username
	 * @param newUser
	 */
	
	public void addCustomer(String username, Customer newUser) {
		UserDAO userDAO = new UserDAO();
		userDatabase.customers.put(username, newUser);
		userDAO.createCustomerDatabase(customers);
	}
	
	/**
	 * @param customers
	 */
	public void setCustomers(HashMap<String, Customer> customers) {
		userDatabase.customers = customers;
	}

	
	// EMPLOYEE METHODS
	
	/**
	 * @return
	 */
	public HashMap<String, Employee> getEmployees() {
		UserDAO userDAO = new UserDAO();
		userDatabase.employees = userDAO.readEmployees();
		
		return employees;
	}

	/**
	 * @param username
	 * @return
	 */
	public Employee getEmployee(String username) {
		return employees.get(username);
	}

	/**
	 * @param username
	 * @param newUser
	 */
	public void addEmployee(String username, Employee newUser) {
		UserDAO userDAO = new UserDAO();
		userDatabase.employees.put(username, newUser);
		userDAO.createEmployeeDatabase(employees);

	}

	/**
	 * @param employees
	 */
	public void setEmployees(HashMap<String, Employee> employees) {
		userDatabase.employees = employees;
	}

}
