package com.revature.Objects;

import java.io.Serializable;
import java.util.HashMap;

import com.revature.DAO.AudiCarDAO;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class AudiCarDatabase implements Serializable {
	private static HashMap<String, AudiCar> lot = new HashMap<String, AudiCar>();

	/**
	 * @return
	 */
	public static HashMap<String, AudiCar> getLot() {
		return lot;
	}

	/**
	 * @param carVin
	 * @return
	 */
	public static AudiCar getAudiCar(String carVin) {
		return lot.get(carVin);
	}

	/**
	 * @param vin
	 * @param car
	 */
	public static void addCar(String vin, AudiCar car) {
		AudiCarDAO carDAO = new AudiCarDAO();
		lot.put(vin, car);
		carDAO.createAudiCarDatabase(lot);

	}

	/**
	 * @param lot
	 */
	public static void setLot(HashMap<String, AudiCar> lot) {
		AudiCarDatabase.lot = lot;
	}
}
