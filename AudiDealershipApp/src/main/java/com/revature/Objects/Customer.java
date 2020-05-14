package com.revature.Objects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class Customer extends User {
	private Double totalBalance;
	private Double monthlyPayment;
	private ArrayList<AudiCar> carsOwned = new ArrayList<AudiCar>();
	private HashMap<String, Double> paymentHistory = new HashMap<String, Double>();

	/**
	 * 
	 */
	public Customer() {
		// TODO Auto-generated constructor stub
		super();
		this.totalBalance = 0D;
		this.monthlyPayment = 0D;
	}

	/**
	 * @return
	 */
	public Double getTotalBalance() {
		return totalBalance;
	}

	/**
	 * @param totalBalance
	 */
	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

	/**
	 * @return
	 */
	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	/**
	 * @param monthlyPayment
	 */
	public void setMonthlyPayment(Double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	/**
	 * @return
	 */
	public ArrayList<AudiCar> getCarsOwned() {
		return carsOwned;
	}

	/**
	 * @param car
	 */
	public void addCars(AudiCar car) {
		this.carsOwned.add(car);
	}

	/**
	 * @return
	 */
	public HashMap<String, Double> getPaymentHistory() {
		return paymentHistory;
	}

	/**
	 * @param date
	 * @param payment
	 */
	public void addPayment(String date, Double payment) {
		this.paymentHistory.put(date, payment);
	}

}
