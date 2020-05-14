package com.revature.Services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class ViewAudi {
	HashMap<String, AudiCar> cars = AudiCarDatabase.getLot();

	/**
	 * 
	 */
	public void viewAudis() {
		Iterator iterator = cars.entrySet().iterator();
		System.out.println("--------------Vin Number-------------Model-----------Year------------Offers--------");
		while (iterator.hasNext()) {
			Map.Entry<String, AudiCar> pair = (Map.Entry<String, AudiCar>) iterator.next();
			System.out.println(pair.getValue().getCarRecord());

		}
	}
}
