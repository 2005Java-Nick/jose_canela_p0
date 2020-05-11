package com.revature.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import com.revature.Objects.AudiCar;

public class AudiCarDAO {
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
