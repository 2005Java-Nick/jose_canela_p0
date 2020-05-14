package com.revature.Services;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class RegisterAudi {
	
	/**
	 * Works as a log as for the employee
	 * @param vinNumber
	 * @param model
	 * @param year
	 * @param price
	 */
	public void addAudiCar(String vinNumber, String model, String year, Double price) {
		AudiCar newAudiCar = new AudiCar();
		if (!AudiCarDatabase.getLot().containsKey(vinNumber)) {
			newAudiCar.setVinNumber(vinNumber);
			newAudiCar.setModel(model);
			newAudiCar.setYear(year);
			newAudiCar.setPrice(price);
			AudiCarDatabase.addCar(vinNumber, newAudiCar);
			// TODO: Log car created
			System.out.println("Car created");
		} else {
			// TODO: Log car already exists
			System.out.println("Car already exists");
		}

	}
}
