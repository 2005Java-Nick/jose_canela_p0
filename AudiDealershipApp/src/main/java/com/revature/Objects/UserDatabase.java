package com.revature.Objects;

import java.io.Serializable;
import java.util.HashMap;

import com.revature.DAO.UserDAO;

public class UserDatabase implements Serializable {
	private static HashMap<String, Customer> customers = new HashMap<>();
	private static HashMap<String, Employee> employees = new HashMap<>();
	
	//Customer methods
		public static HashMap<String, Customer> getCustomers() {
			return customers;
		}
		
		public static Customer getCustomer(String username) {
			return customers.get(username);
		}

		public static void addCustomer(String username, Customer newUser) {
			UserDatabase.customers.put(username, newUser);
		}

		//Employee Methods
		public static HashMap<String, Employee> getEmployees() {
			return employees;
		}
		
		public static Employee getEmployee(String username) {
			return employees.get(username);
		}


		public static void addEmployee(String username, Employee newUser) {
			UserDAO userDAO = new UserDAO();
			UserDatabase.employees.put(username, newUser);
			userDAO.createEmployeeDatabase(employees);
			
		}

		public static void setCustomers(HashMap<String, Customer> customers) {
			UserDatabase.customers = customers;
		}

		public static void setEmployees(HashMap<String, Employee> employees) {
			UserDatabase.employees = employees;
		}
}
