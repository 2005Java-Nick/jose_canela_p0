package com.revature.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import com.revature.Objects.AudiCar;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class AudiCarDAO implements Serializable {

	/**
	 * @param lot
	 * @return
	 */
	public boolean createAudiCarDatabase(HashMap<String, AudiCar> lot) {

		try (FileOutputStream fos = new FileOutputStream("AudiCarLot.dat", false);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(lot);
			return true;
		} catch (IOException e) {
			// log.error(e.printStackTrace());
			return false;
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, AudiCar> readAudiCarDatabase() {
		HashMap<String, AudiCar> lot = new HashMap<String, AudiCar>();
		try (FileInputStream fis = new FileInputStream("AudiCarLot.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			lot = (HashMap<String, AudiCar>) ois.readObject();
		} catch (IOException e) {
			// log.error(e.printStackTrace());
			return lot;
		} catch (ClassNotFoundException e) {
			// log.error(e.printStackTrace());
			return lot;
		}

		return lot;
	}
}
