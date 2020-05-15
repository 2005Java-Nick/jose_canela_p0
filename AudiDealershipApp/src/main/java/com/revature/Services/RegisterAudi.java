package com.revature.Services;

import org.apache.log4j.Logger;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class RegisterAudi {
	private static Logger log = Logger.getRootLogger();
	
	private static AudiCarDatabase audiCarDatabase = AudiCarDatabase.getAudiCarDatabase();
	
	/**
	 * Works as a log as for the employee
	 * @param vinNumber
	 * @param model
	 * @param year
	 * @param price
	 */
	public void addAudiCar(String vinNumber, String model, String year, Double price) {
		AudiCar newAudiCar = new AudiCar();
		if (!audiCarDatabase.getLot().containsKey(vinNumber)) {
			newAudiCar.setVinNumber(vinNumber);
			newAudiCar.setModel(model);
			newAudiCar.setYear(year);
			newAudiCar.setPrice(price);
			audiCarDatabase.addCar(vinNumber, newAudiCar);
			
			log.info("RegisterAudi:addAudiCar:Car created/registered");
		} else {
			log.info("RegisterAudi:addAudiCar:Car already exists in lot");
			System.out.println("Car ("+ vinNumber+ ", "+ model+ ", "+ year +", "+ price +") already exists in the car lot.");
		}

	}
}
