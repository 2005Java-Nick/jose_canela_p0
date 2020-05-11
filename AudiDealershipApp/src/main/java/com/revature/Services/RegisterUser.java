package com.revature.Services;

import com.revature.DAO.*;

import com.revature.Objects.*;

public class RegisterUser {
	public void registerCustomer(String username, String password) {
		if (UserDatabase.getCustomers().containsKey(username)) {
			System.out.println("User " + username + " is already in the database. Try another username.");
		} else {
			Customer newUser = new Customer();
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setUserPolicy(UserPolicy.CUSTOMER);
			UserDatabase.addCustomer(username, newUser);
			// TODO: Log user transactions
			System.out.println("Created");
		}
	}

	public void registerEmployee(String username, String password, String systemPassword) {
		if(UserDatabase.getEmployees().containsKey(username)) {
			System.out.println("User " + username + " is already in the database. Try another username.");
		}
		else if (systemPassword.contains(DealershipDAO.getAppPassword())) {
			Employee newUser = new Employee();
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setUserPolicy(UserPolicy.EMPLOYEE);
			UserDatabase.addEmployee(username, newUser);
			// TODO: Log user transactions
			System.out.println("Created");
		}
	}
}
