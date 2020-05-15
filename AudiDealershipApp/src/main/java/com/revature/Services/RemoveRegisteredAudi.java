package com.revature.Services;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class RemoveRegisteredAudi {
	AuthenticateUser authUser = new AuthenticateUser();
	private static AudiCarDatabase audiCarDatabase = AudiCarDatabase.getAudiCarDatabase();
	
	/**
	 * @param carVin
	 */
	public void removeAudiCar(String carVin) {
		// Remove Audi's from the lot and thus, the database using its VIN
		audiCarDatabase.getLot().remove(carVin);

	}
}
