package com.revature.Services;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.revature.DAO.AudiCarDAO;
import com.revature.DAO.UserDAO;
import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class BidOnAudiCar {
	private static Logger log = Logger.getRootLogger();
	
	private static UserDatabase userDatabase = UserDatabase.getUserDatabase();
	private static AudiCarDatabase audiCarDatabase = AudiCarDatabase.getAudiCarDatabase();
	AudiCarDAO audiCarDAO = new AudiCarDAO();
	UserDAO userDAO = new UserDAO();
	
	ManageAudiCarPayments mngAudiCarPay = new ManageAudiCarPayments();
	RemoveRegisteredAudi rmvRegAudi = new RemoveRegisteredAudi();

	Customer user = new Customer(); 
	/**
	 * User doesn't need to authenticate because they're already past the login screen
	 * @param vinNumber
	 * @param customer
	 * @param offer
	 */
	public void addOffer(String vinNumber, String customer, Double offer) {
		if (audiCarDatabase.getLot().containsKey(vinNumber)) {
			AudiCar car = audiCarDatabase.getLot().get(vinNumber);
			//car.setOffer(true);
			car.setOffers(customer, offer);
			car.setOffer(true);
			audiCarDatabase.addCar(vinNumber, car);
			
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
			log.info("removeOffer:Offer for car with VIN(" + vinNumber + ") removed for customer (" + customer + ")");
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
			log.info("acceptOffer:Employee checked current offers on car with VIN ("+ vinNumber +")");
		} else {
			System.out.println("Car not found.");
			log.info("getCurrentOffers:Car not found");
			System.out.println("---------------------------------------");
		}
	}

	/**
	 * Removes a car from the lot, and assign it to a user
	 * @param customer
	 * @param carVin
	 */
	public void acceptOffer(String customer, String carVin) {

		if (audiCarDatabase.getAudiCar(carVin) != null) {
			
			AudiCar car = audiCarDatabase.getAudiCar(carVin);
			
			user = userDatabase.getCusts().get(customer); 
			Customer cust = userDatabase.getCustomers().get(customer);
			
			car.setPrice(car.getOffers().get(customer));
			
			cust.addCars(car);
			
			cust.setTotalBalance(car.getPrice());
			cust.setMonthlyPayment(cust.getMonthlyPayment() + mngAudiCarPay.calculateMonthlyPayment(customer, carVin));
			
			user = cust;
			user.addCars(car);
			
			//userDatabase.setCustomers(userDAO.readCustomer()); 
			userDAO.createCustomerDatabase(userDatabase.getCustomers()); 
			//userDAO.createCustomerDatabase(userDatabase.getCusts());
			audiCarDatabase.removeCar(carVin, car);
			
			System.out.println("---------------------------------------");
			log.info("acceptOffer:Employee accepted offer - car with VIN ("+ carVin +") removed from lot and given to customer("+ customer +")");
		} else {
			System.out.println("Sorry. This Audi cannot be found in the car lot. Please try again.");
			log.info("acceptOffer:Car not found");
			System.out.println("---------------------------------------");
		}

	}
}
