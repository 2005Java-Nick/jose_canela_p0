package com.revature.Services;

import com.revature.Objects.*;

public class RemoveRegisteredAudi {
	AuthenticateUser authUser = new AuthenticateUser();

	public void removeAudiCar(String carVin) {
		//Remove Audi's from the lot and thus, the database using its VIN
		AudiCarDatabase.getLot().remove(carVin);

	}
}
