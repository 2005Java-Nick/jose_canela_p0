package com.revature.Services;

import java.util.Iterator;
import java.util.Map.Entry;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class BidOnAudiCar {
	
	private static UserDatabase userDatabase = UserDatabase.getUserDatabase();
	private static AudiCarDatabase audiCarDatabase = AudiCarDatabase.getAudiCarDatabase();
	
	ManageAudiCarPayments mngAudiCarPay = new ManageAudiCarPayments();
	RemoveRegisteredAudi rmvRegAudi = new RemoveRegisteredAudi();

	
	/**
	 * User doesn't need to authenticate because they're already past the login screen
	 * @param vinNumber
	 * @param customer
	 * @param offer
	 */
	public void addOffer(String vinNumber, String customer, Double offer) {
		if (audiCarDatabase.getLot().containsKey(vinNumber)) {
			AudiCar car = audiCarDatabase.getLot().get(vinNumber);
			car.setOffers(customer, offer);
			car.setOffer(true);
			System.out.println("Offer Placed on : " + vinNumber);
		}
	}

	/**
	 * Only available in the employee menu
	 * @param customer
	 * @param vinNumber
	 */
	public void removeOffer(String customer, String vinNumber) {
		if (audiCarDatabase.getLot().containsKey(vinNumber)) {
			AudiCar car = audiCarDatabase.getLot().get(vinNumber);
			car.getOffers().remove(customer);
		}
	}


	/**
	 * Grabs the offers associated with a car and a customer
	 * @param carVin
	 * @param customer
	 * @return
	 */
	public Double getCarOffer(String carVin, String customer) {
		return audiCarDatabase.getAudiCar(carVin).getOffers().get(customer);
	}

	
	/**
	 * Allows the Employee to see the offers made before accepting any of the offers
	 * @param vinNumber
	 */
	public void getCurrentOffers(String vinNumber) {
		if (audiCarDatabase.getAudiCar(vinNumber) != null) {
			Iterator<Entry<String, Double>> iterator = audiCarDatabase.getLot().get(vinNumber).getOffers().entrySet().iterator();
			AudiCar car = audiCarDatabase.getLot().get(vinNumber);
			System.out.println("Offers for: " + car.getYear() + ", " + car.getModel() + ", " + vinNumber);
			while (iterator.hasNext()) {
				System.out.println("|--User: " + iterator.next());
			}
			System.out.println("---------------------------------------");
		} else {
			System.out.println("Car not found");
		}
	}

	/**
	 * Removes a car from the lot, and assign it to a user
	 * @param customer
	 * @param carVin
	 */
	public void acceptOffer(String customer, String carVin) {

		if (audiCarDatabase.getAudiCar(carVin) != null) {
			ManageAudiCarPayments mngAudiCarPay = new ManageAudiCarPayments();
			AudiCar car = audiCarDatabase.getAudiCar(carVin);
			Customer user = userDatabase.getCustomer(customer);
			car.setPrice(car.getOffers().get(customer));
			user.addCars(car);
			user.setTotalBalance(car.getPrice());
			user.setMonthlyPayment(user.getMonthlyPayment() + mngAudiCarPay.calculateMonthlyPayment(customer, carVin));
			rmvRegAudi.removeAudiCar(carVin);
			System.out.println(user.getCarsOwned());
		} else {
			System.out.println("Car not found");
		}

	}
}
