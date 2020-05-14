package com.revature.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.revature.Objects.*;

/**
 * @author josecanela
 *
 */
public class ManageAudiCarPayments {
	public void removeDropOffers(String carVin, String employee, String password) {
		// Grabs the offer on a car that is in the lot and deletes the customer entry
		AudiCarDatabase.getLot().get(carVin).getOffers().clear();

	}

	/**
	 * @param customerUsername
	 * @param carVin
	 * @return
	 */
	public Double calculateMonthlyPayment(String customerUsername, String carVin) {
		AudiCar car = AudiCarDatabase.getAudiCar(carVin);
		car.setRemainingPayments(24);
		BidOnAudiCar bidOnAudiCar = new BidOnAudiCar();
		// Customer customer = UserDB.getCustomer(customerUsername);
		Double offer = bidOnAudiCar.getCarOffer(carVin, customerUsername);

		// Returns a 2 year loan on the vehicle
		return offer / 24;

	}

	/**
	 * @param customer
	 */
	public void viewAudiCarsAndPaymentInfo(String customer) {
		Customer user = UserDatabase.getCustomer(customer);
		System.out.println("Vehicles Owned by: " + customer + "\t Total Balance Due: $" + user.getMonthlyPayment());
		for (AudiCar car : user.getCarsOwned()) {
			System.out.println("|-Vehicle: " + car.getYear() + ", " + car.getModel() + ": \n" + "|-Original Price: "
					+ car.getPrice() + "Monthly Installments: $" + car.getPrice() / 24 + "Remaining payments:"
					+ car.getRemainingPayments() + "\n");
		}
	}

	/**
	 * @param customer
	 */
	public void makePayment(String customer) {
//	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//	   LocalDateTime now = LocalDateTime.now();  
//	   System.out.println(dtf.format(now));
		try {
			boolean commitPayment = false;
			TimeUnit.SECONDS.sleep(1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Customer user = UserDatabase.getCustomer(customer);
			// Update Balance
//			if (user.getCarsOwned().size() < 2) {
//				user.setTotalBalance(user.getTotalBalance() - user.getMonthlyPayment());
//				user.getCarsOwned().get(0).setRemainingPayments(user.getCarsOwned().get(0).getRemainingPayments() - 1);
//			} else if(user.getTotalBalance() == payment) {
//				user.setTotalBalance(user.getTotalBalance() - user.getMonthlyPayment());
//			}

			user.setTotalBalance(user.getTotalBalance() - user.getMonthlyPayment());
			user.addPayment(formatter.format(now), user.getMonthlyPayment());
			for (int i = 0; i < user.getCarsOwned().size(); i++) {
				user.getCarsOwned().get(i).setRemainingPayments(user.getCarsOwned().get(i).getRemainingPayments() - 1);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param customer
	 */
	public void customerPaymentHistory(String customer) {
		// TODO: Users should be able to see their payments
		Customer user = UserDatabase.getCustomer(customer);
		Iterator iterator = user.getPaymentHistory().entrySet().iterator();
		System.out.println("Payment History:");
		while (iterator.hasNext()) {
			Map.Entry<String, Double> pair = (Map.Entry<String, Double>) iterator.next();
			System.out.println(pair);

		}
	}

	/**
	 * 
	 */
	public void employeePaymentView() {
		// TODO: Employees should be able to see all of the payments
		HashMap<String, Customer> payments = UserDatabase.getCustomers();

		Iterator iterator = payments.entrySet().iterator();
		System.out.println("User Payment History:");
		while (iterator.hasNext()) {
			Map.Entry<String, Customer> pair = (Map.Entry<String, Customer>) iterator.next();
			System.out.println(pair.getValue().getUsername());
			System.out.println(pair.getValue().getPaymentHistory());

		}

	}
}
