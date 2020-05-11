package com.revature.Objects;

import java.io.Serializable;
import java.util.HashMap;

import com.revature.DAO.AudiCarDAO;

public class AudiCarDatabase implements Serializable {
	private static HashMap<String, AudiCar> lot = new HashMap<String, AudiCar>();

	public static HashMap<String, AudiCar> getLot() {
		return lot;
	}

	public static AudiCar getAudiCar(String carVin) {
		return lot.get(carVin);
	}

	public static void addCar(String vin, AudiCar car) {
		AudiCarDAO carDAO = new AudiCarDAO();
		lot.put(vin, car);
		carDAO.createAudiCarDatabase(lot);
		
	}

	public static void setLot(HashMap<String, AudiCar> lot) {
		AudiCarDatabase.lot = lot;
	}
}
