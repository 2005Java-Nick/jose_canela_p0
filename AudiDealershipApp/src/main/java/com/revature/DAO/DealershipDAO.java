package com.revature.DAO;

import java.io.Serializable;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public final class DealershipDAO implements Serializable {
	private static String appPassword = "root";

	/**
	 * 
	 */
	public DealershipDAO() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @return
	 */
	public static String getAppPassword() {
		return appPassword;
	}

	/**
	 * @param appPassword
	 */
	public static void setAppPassword(String appPassword) {
		DealershipDAO.appPassword = appPassword;
	}

}
