package com.revature.Services;

import org.apache.log4j.Logger;

import com.revature.DAO.UserDAO;
import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class AuthenticateUser {
	private static Logger log = Logger.getRootLogger();
	private static UserDatabase userDatabase = UserDatabase.getUserDatabase();
	UserDAO userDAO = new UserDAO();
	// CUSTOMER LOGIN
	
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticateCustomer(String username, String password) {
		log.info("Current Customers who have registered: "+ userDatabase.getCustomers());
		
		if (userDatabase.getCustomers().containsKey(username)
				&& userDatabase.getCustomer(username).getPassword().equals(password)) {
			
			log.info("authenticateCustomer:Customer("+ username +") Login successful!");
			//System.out.println(userDatabase.getCustomers());
			return true;

		}
		 else {
			log.info("authenticateCustomer:Customer failed to login - invalid username or password");
			
			System.out.println("Your login attempt has failed.\nMake sure the username and password are correct.\n");
			return false;
		}
	}

	// EMPLOYEE LOGIN
	
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticateEmployee(String username, String password) {
		if (userDatabase.getEmployees().containsKey(username)
				&& userDatabase.getEmployee(username).getPassword().equals(password)) {
			
			log.info("authenticateEmployee:Employee("+ username +") Login successful!");
			return true;
		} else {
			log.info("authenticateEmployee:Employee failed to login - invalid username or password");
			
			System.out.println("Your login attempt has failed.\nMake sure the username and password are correct.\n");
		
			return false;
		}
	}

}
