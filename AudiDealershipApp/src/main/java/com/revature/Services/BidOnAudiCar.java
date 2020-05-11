package com.revature.Services;

import java.util.Iterator;

import com.revature.Objects.*;

public class BidOnAudiCar {
	ManageAudiCarPayments mngAudiCarPay = new ManageAudiCarPayments();
	RemoveRegisteredAudi rmvRegAudi = new RemoveRegisteredAudi();

	// User doesn't need to authenticate because they're already past the login screen
	public void addOffer(String vinNumber, String customer, Double offer) {
		if (AudiCarDatabase.getLot().containsKey(vinNumber)) {
			AudiCar car = AudiCarDatabase.getLot().get(vinNumber);
			car.setOffers(customer, offer);
			car.setOffer(true);
			System.out.println("Offer Placed on : " + vinNumber);
		}
	}

	// Only available in the employee menu
	public void removeOffer(String customer, String vinNumber) {
		if (AudiCarDatabase.getLot().containsKey(vinNumber)) {
			AudiCar car = AudiCarDatabase.getLot().get(vinNumber);
			car.getOffers().remove(customer);
		}
	}

	// Grabs the offers associated with a car and a customer
	public Double getCarOffer(String carVin, String customer) {
		return AudiCarDatabase.getAudiCar(carVin).getOffers().get(customer);
	}

	// Allows the Employee to see the offers made before accepting any
	public void getCurrentOffers(String vinNumber) {
		if (AudiCarDatabase.getAudiCar(vinNumber) != null) {
			Iterator iterator = AudiCarDatabase.getLot().get(vinNumber).getOffers().entrySet().iterator();
			AudiCar car = AudiCarDatabase.getLot().get(vinNumber);
			System.out.println(
					"Offers for: " + car.getYear() + ", " + car.getModel() + ", " + vinNumber);
			while (iterator.hasNext()) {
				System.out.println("|--User: " + iterator.next());
			}
			System.out.println("---------------------------------------");
		} else {
			System.out.println("Car not found");
		}
	}

	public void acceptOffer(String customer, String carVin) {
		// TODO: Remove car from the lot, and assign it to a user
		if (AudiCarDatabase.getAudiCar(carVin) != null) {
			ManageAudiCarPayments mngAudiCarPay = new ManageAudiCarPayments();
			AudiCar car = AudiCarDatabase.getAudiCar(carVin);
			Customer user = UserDatabase.getCustomer(customer);
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