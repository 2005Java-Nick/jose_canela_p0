package com.revature.Services;

import org.apache.log4j.Logger;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class RemoveUser {
	private static Logger log = Logger.getRootLogger();
	
	private static UserDatabase userDatabase = UserDatabase.getUserDatabase();
	AuthenticateUser authUser = new AuthenticateUser();

	/**
	 * @param username
	 * @param password
	 * @param customer
	 */
	public void removeUser(String username, String password, String customer) {
	
		if (authUser.authenticateEmployee(username, password)) {
			userDatabase.getCustomers().remove(customer);
			
			log.info("removeUser:User ("+ username +", "+ password +", "+ customer +") removed");
		}

	}
	
	public void viewCustomers() {
		System.out.println(userDatabase.getCustomers().keySet()); 
	}
}
