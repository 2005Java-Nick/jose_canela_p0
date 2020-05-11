package com.revature.Objects;

public class User {
	private String username;
	private String password;
	private UserPolicy userPolicy;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, UserPolicy userPolicy) {
		super();
		this.username = username;
		this.password = password;
		this.userPolicy = userPolicy; 
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserPolicy getUserPolicy() {
		return userPolicy;
	}

	public void setUserPolicy(UserPolicy userPolicy) {
		this.userPolicy = userPolicy;
	}
	
	

}
