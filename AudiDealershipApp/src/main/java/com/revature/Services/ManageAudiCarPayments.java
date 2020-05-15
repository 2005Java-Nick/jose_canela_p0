package com.revature.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class ManageAudiCarPayments {
	private static Logger log = Logger.getRootLogger();
	
	private static UserDatabase userDatabase = UserDatabase.getUserDatabase();
	private static AudiCarDatabase audiCarDatabase = AudiCarDatabase.getAudiCarDatabase();
	
	public void removeDropOffers(String carVin, String employee, String password) {
		// Grabs the offer on a car that is in the lot and deletes the customer entry
		audiCarDatabase.getLot().get(carVin).getOffers().clear();
		
		log.info("ManageAudiCarPayments:removeDropOffers:Employee("+employee+") removed all unaccepted offers");
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
		log.info("ManageAudiCarPayments:calculateMonthlyPayment:Customer("+customerUsername+") Monthly Payment calculated");
		return offer / 24;
		}

	/**
	 * @param customer
	 */
	public static void viewAudiCarsAndPaymentInfo(String customer) {
		Customer user = userDatabase.getCustomer(customer);
		System.out.println("Vehicles Owned by: " + customer + "\t Total Balance Due: $" + user.getMonthlyPayment());
		for (AudiCar car : user.getCarsOwned()) {
			System.out.println("|-Vehicle: " + "VIN: " + car.getVinNumber() + ", Year: " + car.getYear() + ", Model: " + car.getModel() + " \n" + "|-Original Price: "
					+ car.getPrice() + " Monthly Installments: $" + car.getPrice() / 24 + " Remaining payments:"
					+ car.getRemainingPayments() + "\n");
			
			log.info("ManageAudiCarPayments:viewAudiCarsAndPaymentInfo:Customer("+customer+") viewed Owned Audi's and Payment Info");
		}
	}

	/**
	 * @param customer
	 */
	public void makePayment(String customer) {

		try {
			TimeUnit.SECONDS.sleep(1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Customer user = userDatabase.getCustomer(customer);
			
			// Update Balance
			user.setTotalBalance(user.getTotalBalance() - user.getMonthlyPayment());
			log.info("ManageAudiCarPayments:makePayment:Customer Balance updated");
			
			user.addPayment(formatter.format(now), user.getMonthlyPayment());
			for (int i = 0; i < user.getCarsOwned().size(); i++) {
				user.getCarsOwned().get(i).setRemainingPayments(user.getCarsOwned().get(i).getRemainingPayments() - 1);
				
				log.info("ManageAudiCarPayments:makePayment:Customer("+customer+") made monthly payment on each car");
			}

		} catch (InterruptedException e) {
			log.error("ManageAudiCarPayments:makePayment:InterruptedException - waiting or sleeping thread was\ninterrupted by another thread");
		}

	}

	/**
	 * Users can see their payment history
	 * @param customer
	 */
	public void customerPaymentHistory(String customer) {
		
		Customer user = userDatabase.getCustomer(customer);
		Iterator<Entry<String, Double>> iterator = user.getPaymentHistory().entrySet().iterator();
		System.out.println("Payment History:");
		while (iterator.hasNext()) {
			Map.Entry<String, Double> pair = (Map.Entry<String, Double>) iterator.next();
			System.out.println(pair);
			
		}
		log.error("ManageAudiCarPayments:customerPaymentHistory:Customer("+customer+") viewed their Payment History");
	}

	/**
	 * Employees can see all payments made
	 */
	public void employeePaymentView() {
		
		HashMap<String, Customer> payments = userDatabase.getCustomers();

		Iterator<Entry<String, Customer>> iterator = payments.entrySet().iterator();
		System.out.println("Customer Payment History:");
		while (iterator.hasNext()) {
			Map.Entry<String, Customer> pair = (Map.Entry<String, Customer>) iterator.next();
			System.out.println(pair.getValue().getUsername());
			System.out.println(pair.getValue().getPaymentHistory());

		}
		log.error("ManageAudiCarPayments:employeePaymentView:Employee viewed All Customer Payments");
	}
}
