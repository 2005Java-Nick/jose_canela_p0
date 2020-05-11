package com.revature.DAO;

public final class DealershipDAO {
	private static String appPassword = "root";
	
	DealershipDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public static String getAppPassword() {
		return appPassword;
	}

	public static void setAppPassword(String appPassword) {
		DealershipDAO.appPassword = appPassword;
	}

}
