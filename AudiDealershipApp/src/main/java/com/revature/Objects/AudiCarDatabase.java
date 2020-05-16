package com.revature.Objects;

import java.io.Serializable;
import java.util.HashMap;

import com.revature.DAO.AudiCarDAO;
//import com.revature.DAO.UserDAO;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class AudiCarDatabase implements Serializable {
	private static AudiCarDatabase audiCarDatabase;
	private HashMap<String, AudiCar> lot = new HashMap<String, AudiCar>();

	public static AudiCarDatabase getAudiCarDatabase() {
		if(audiCarDatabase == null) {
			audiCarDatabase = new AudiCarDatabase();
		}
		return audiCarDatabase;
	}
	
	/**
	 * @return
	 */
	public HashMap<String, AudiCar> getLot() {
		AudiCarDAO audiCarDAO = new AudiCarDAO();
		audiCarDatabase.lot = audiCarDAO.readAudiCarDatabase();
		return lot;
	}

	/**
	 * @param carVin
	 * @return
	 */
	public AudiCar getAudiCar(String carVin) {
		return lot.get(carVin);
	}

	/**
	 * @param vin
	 * @param car
	 */
	public void addCar(String vin, AudiCar car) {
		AudiCarDAO carDAO = new AudiCarDAO();
		lot.put(vin, car);
		//setLot(lot);
		carDAO.createAudiCarDatabase(lot);

	}

	/**
	 * @param lot
	 */
	public void setLot(HashMap<String, AudiCar> lot) {
		audiCarDatabase.lot = lot;
	}
	public void removeCar(String vin, AudiCar car) {
		AudiCarDAO carDAO = new AudiCarDAO();
		lot.remove(vin);
		carDAO.createAudiCarDatabase(lot);
	}
}
