package com.revature.Objects;

import java.io.Serializable;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable {
	private String username;
	private String password;
	private UserPolicy userPolicy;

	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param userPolicy
	 */
	public User(String username, String password, UserPolicy userPolicy) {
		super();
		this.username = username;
		this.password = password;
		this.userPolicy = userPolicy;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public UserPolicy getUserPolicy() {
		return userPolicy;
	}

	/**
	 * @param userPolicy
	 */
	public void setUserPolicy(UserPolicy userPolicy) {
		this.userPolicy = userPolicy;
	}

}
