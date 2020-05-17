package com.revature.Services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class ViewAudi {
	private static Logger log = Logger.getRootLogger();
	
	private static AudiCarDatabase audiCarDatabase = AudiCarDatabase.getAudiCarDatabase();
	
	HashMap<String, AudiCar> cars = audiCarDatabase.getLot();

	/**
	 * 
	 */
	public void viewAudis() {
		Iterator<Entry<String, AudiCar>> iterator = cars.entrySet().iterator();
		System.out.println("--------------Vin Number-------------Model-----------Year------------Price------------Offers-----");
		while (iterator.hasNext()) {
			Map.Entry<String, AudiCar> pair = (Map.Entry<String, AudiCar>) iterator.next();
			System.out.println(pair.getValue().getCarRecord());
			
			log.info("viewAudis:The available Audi's on the car lot were viewed");
		}
	}
}
