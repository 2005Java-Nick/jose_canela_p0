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

		try (FileOutputStream fos = new FileOutputStream("AudiCarLot.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(lot);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @return
	 */
	public HashMap<String, AudiCar> readAudiCarDatabase() {
		HashMap<String, AudiCar> lot = null;
		try (FileInputStream fis = new FileInputStream("AudiCarLot.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			lot = (HashMap<String, AudiCar>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		return lot;
	}
}
