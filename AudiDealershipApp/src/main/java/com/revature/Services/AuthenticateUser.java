package com.revature.Services;

import com.revature.Objects.*;

public class AuthenticateUser {
	// Customer Login
		public boolean authenticateCustomer(String username, String password) {
			if (UserDatabase.getCustomers().containsKey(username) && UserDatabase.getCustomer(username).getPassword().equals(password)) {
				System.out.println("You're now Logged in");
				// TODO: Log success
				return true;

			} else if (!UserDatabase.getCustomers().containsKey(username)) {
				// TODO: Login User not found
				System.out.println("User does not exisits");
				return false;
			} else {
				System.out.println("Failed to login");
				// TODO: Log login failure
				return false;
			}
		}

		// Employee Login
		public boolean authenticateEmployee(String username, String password) {
			if (UserDatabase.getEmployees().containsKey(username) && UserDatabase.getEmployee(username).getPassword().equals(password)) {
				System.out.println("You're now Logged in");
				// TODO: Log success
				return true;
			} else {
				System.out.println("Failed to login");
				// TODO: Log login failure
				return false;
			}
		}

}
