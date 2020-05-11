package com.revature.Services;

import com.revature.Objects.*;

public class RemoveUser {
	AuthenticateUser authUser = new AuthenticateUser();

	public void removeUser(String username, String password, String customer) {
		if (authUser.authenticateEmployee(username, password)) {
			UserDatabase.getCustomers().remove(customer);
			// TODO: Log user deletion
			System.out.println("User Deleted");
		}

	}
}
