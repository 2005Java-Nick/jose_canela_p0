package com.revature.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.revature.DAO.AudiCarDAO;
//import com.revature.DAO.UserDAO;
import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class ManageAudiCarPayments {
	private static Logger log = Logger.getRootLogger();
	
	private static UserDatabase userDatabase = UserDatabase.getUserDatabase();
	private static AudiCarDatabase audiCarDatabase = AudiCarDatabase.getAudiCarDatabase();
	AudiCarDAO audiCarDAO = new AudiCarDAO();
	AudiCar car = new AudiCar();
	HashMap<AudiCar, String> hm = new HashMap<AudiCar, String>();
	
	public void removeDropOffers(String carVin, String employee, String password) {
		// Grabs the offer on a car that is in the lot and deletes the customer entry
		audiCarDatabase.getLot().get(carVin).getOffers().clear();
		
		log.info("removeDropOffers:Employee("+employee+") removed all unaccepted offers");
	}

	/**
	 * @param customerUsername
	 * @param carVin
	 * @return
	 */
	public Double calculateMonthlyPayment(String customerUsername, String carVin) {
		AudiCar car = audiCarDatabase.getAudiCar(carVin);
		car.setRemainingPayments(24);
		BidOnAudiCar bidOnAudiCar = new BidOnAudiCar();
		Double offer = bidOnAudiCar.getCarOffer(carVin, customerUsername);

		// Returns a 2 year loan on the vehicle
		log.info("calculateMonthlyPayment:Customer("+customerUsername+") Monthly Payment calculated");
		return offer / 24;
		}

	/**
	 * @param customer
	 */
	public void viewAudiCarsAndPaymentInfo(String customer) {
		//Customer user = userDatabase.getCusts().get(customer);
		System.out.println("Vehicles Owned by: " + customer);
		//System.out.println(user.getCarsOwned());
		System.out.println("--------------------------------------------------------------------------------------------------------");
						   //user.getCarsOwned()
		for (AudiCar car : hm.keySet()) {
			System.out.println("|-Vehicle: " + "VIN: " + car.getVinNumber() + ", Year: " + car.getYear() + ", Model: " + car.getModel() + " \n" + "|-Original Price: "
					+ car.getPrice() + ", Monthly Installments: $" + car.getPrice() / 24 + ", Remaining payments:"
					+ car.getRemainingPayments());
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			log.info("viewAudiCarsAndPaymentInfo:Customer("+customer+") viewed Owned Audi's and Payment Info");
		}
	}

	/**
	 * @param customer
	 */
	public void makePayment(String customer) {
			System.out.println(customer);
		try {
			TimeUnit.SECONDS.sleep(1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Customer user = userDatabase.getCusts().get(customer);
			
			// Update Balance
			user.setTotalBalance(user.getTotalBalance() - user.getMonthlyPayment());
			log.info("makePayment:Customer Balance updated");
			
			user.addPayment(formatter.format(now), user.getMonthlyPayment());
			for (int i = 0; i < user.getCarsOwned().size(); i++) {
				user.getCarsOwned().get(i).setRemainingPayments(user.getCarsOwned().get(i).getRemainingPayments() - 1);
				
				hm.put(user.getCarsOwned().get(i), customer);
		
				//audiCarDAO.createAudiCarDatabase(hm);
				
				//System.out.println(user.getCarsOwned().get(i).getRemainingPayments());
				log.info("makePayment:Customer("+customer+") made monthly payment on each car");
			}

		} catch (InterruptedException e) {
			log.error("makePayment:InterruptedException - waiting or sleeping thread was\ninterrupted by another thread");
		}

	}

	/**
	 * Users can see their payment history
	 * @param customer
	 */
	public void customerPaymentHistory(String customer) {
		
		Customer user = userDatabase.getCust(customer);
		Iterator<Entry<String, Double>> iterator = user.getPaymentHistory().entrySet().iterator();
		System.out.println("Payment History:");
		System.out.println("-------------------------------------------------------");
		while (iterator.hasNext()) {
			Map.Entry<String, Double> pair = (Map.Entry<String, Double>) iterator.next();
			System.out.println(pair);
			System.out.println("-------------------------------------------------------");
		}
		log.error("customerPaymentHistory:Customer("+customer+") viewed their Payment History");
	}

	/**
	 * Employees can see all payments made
	 */
	public void employeePaymentView() {
		
		HashMap<String, Customer> payments = userDatabase.getCusts();

		Iterator<Entry<String, Customer>> iterator = payments.entrySet().iterator();
		System.out.println("Customer Payment History:");
		System.out.println("-------------------------------------------------------");
		while (iterator.hasNext()) {
			Map.Entry<String, Customer> pair = (Map.Entry<String, Customer>) iterator.next();
			System.out.println(pair.getValue().getUsername());
			System.out.println(pair.getValue().getPaymentHistory());
			System.out.println("-------------------------------------------------------");
			
		}
		log.error("employeePaymentView:Employee viewed All Customer Payments");
	}
}
