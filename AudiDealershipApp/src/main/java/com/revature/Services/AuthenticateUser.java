package com.revature.Services;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class AuthenticateUser {
	private static UserDatabase userDatabase = UserDatabase.getUserDatabase();
	// CUSTOMER LOGIN
	
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticateCustomer(String username, String password) {
		//TODO: LOG ---System.out Info---  System.out.println(userDatabase.getCustomers());
		if (userDatabase.getCustomers().containsKey(username)
				&& userDatabase.getCustomer(username).getPassword().equals(password)) {
			System.out.println("You're now Logged in");
			// TODO: Log success
			return true;

		} else if (!userDatabase.getCustomers().containsKey(username)) {
			// TODO: Login User not found
			System.out.println("Your login attempt has failed.\nMake sure the username and password are correct.\n");
			return false;
		} else {
			System.out.println("Failed to login");
			// TODO: Log login failure
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
			System.out.println("You're now Logged in");
			// TODO: Log success
			return true;
		} else {
			System.out.println("Your login attempt has failed.\nMake sure the username and password are correct.\n");
			// TODO: Log login failure
			return false;
		}
	}

}
