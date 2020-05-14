package com.revature.Objects;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class AudiCar implements Serializable {
	private String vinNumber;
	private String model;
	private String year;
	private Double price;
	private Double payment;
	// Displays if there are any current offers
	private boolean offer;
	// Users can make offers for the car, so each car has a HashSet of offers
	// <"Username","Offer Amount">
	// It is persisted by the LotDAO, which contains all car objects
	private HashMap<String, Double> offers = new HashMap<String, Double>();
	private Integer remainingPayments;

	/**
	 * 
	 */
	public AudiCar() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param vinNumber
	 * @param model
	 * @param year
	 * @param price
	 * @param offer
	 */
	public AudiCar(String vinNumber, String model, String year, Double price, boolean offer) {
		super();
		this.vinNumber = vinNumber;
		this.model = model;
		this.year = year;
		this.price = price;
		this.offer = offer;
		this.payment = 0D;
		this.remainingPayments = 0;
	}

	/**
	 * @return
	 */
	public String getVinNumber() {
		return vinNumber;
	}

	/**
	 * @param vinNumber
	 */
	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	/**
	 * @return
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return
	 */
	public Double getPayment() {
		return payment;
	}

	/**
	 * @param payment
	 */
	public void setPayment(Double payment) {
		this.payment = payment;
	}

	/**
	 * @return
	 */
	public boolean isThereOffers() {
		return offer;
	}

	/**
	 * @param offer
	 */
	public void setOffer(boolean offer) {
		this.offer = offer;
	}

	/**
	 * @return
	 */
	public HashMap<String, Double> getOffers() {
		return this.offers;
	}

	/**
	 * @param username
	 * @param offer
	 */
	public void setOffers(String username, Double offer) {
		this.offers.put(username, offer);
	}

	/**
	 * @return
	 */
	public Integer getRemainingPayments() {
		return remainingPayments;
	}

	/**
	 * @param remainingPayments
	 */
	public void setRemainingPayments(Integer remainingPayments) {
		this.remainingPayments = remainingPayments;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "VIN: " + this.vinNumber + " Model: " + this.model + " Year: " + this.year;
	}

	/**
	 * @return
	 */
	public String getCarRecord() {
		return "| " + this.vinNumber + "     \t| " + this.model + "      \t| " + this.year + "     \t| " + this.offer
				+ "  \t|";
	}

}
