package com.revature.Services;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.revature.DAO.*;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class RegisterUser implements Serializable {
	private static Logger log = Logger.getRootLogger();
	
	private static UserDatabase userDatabase = UserDatabase.getUserDatabase();
	
	/**
	 * @param username
	 * @param password
	 */
	public void registerCustomer(String username, String password) {
		if (userDatabase.getCustomers().containsKey(username)) {
			System.out.println("User " + username + " is already in the database. Try another username.");
		} else {
			Customer newUser = new Customer();
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setUserPolicy(UserPolicy.CUSTOMER);
			userDatabase.addCustomer(username, newUser);
			
			log.info("RegisterUser:registerCustomer:Customer("+username+") created/registered");
		}
	}

	/**
	 * @param username
	 * @param password
	 * @param systemPassword
	 */
	public void registerEmployee(String username, String password, String systemPassword) {
		if (userDatabase.getEmployees().containsKey(username)) {
			System.out.println("User " + username + " is already in the database. Try another username.");
		} else if (systemPassword.contains(DealershipDAO.getAppPassword())) {
			Employee newUser = new Employee();
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setUserPolicy(UserPolicy.EMPLOYEE);
			userDatabase.addEmployee(username, newUser);
			
			log.info("RegisterUser:registerEmployee:Employee("+username+") created/registered");
		}
	}
}
