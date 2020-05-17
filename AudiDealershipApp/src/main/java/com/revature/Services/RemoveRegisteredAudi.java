package com.revature.Services;

import org.apache.log4j.Logger;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class RemoveRegisteredAudi {
	private static Logger log = Logger.getRootLogger();
	
	AuthenticateUser authUser = new AuthenticateUser();
	private static AudiCarDatabase audiCarDatabase = AudiCarDatabase.getAudiCarDatabase();
	
	/**
	 * @param carVin
	 */
	public void removeAudiCar(String carVin) {
		// Remove Audi's from the lot and thus, the database using its VIN
		AudiCar car = audiCarDatabase.getAudiCar(carVin);
		
		audiCarDatabase.removeCar(carVin, car);
		
		log.info("removeAudiCar:Car with VIN ("+ carVin +") removed");
	}
	
}
