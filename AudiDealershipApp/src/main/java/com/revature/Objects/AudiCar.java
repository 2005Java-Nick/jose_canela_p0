package com.revature.Objects;

import java.util.HashMap;

public class AudiCar {
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
	
	
	public AudiCar() {
		// TODO Auto-generated constructor stub
	}
	
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

	public String getVinNumber() {
		return vinNumber;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public boolean isThereOffers() {
		return offer;
	}

	public void setOffer(boolean offer) {
		this.offer = offer;
	}

	public HashMap<String, Double> getOffers() {
		return this.offers;
	}
	
	public void setOffers(String username, Double offer) {
		this.offers.put(username, offer);
	}

	public Integer getRemainingPayments() {
		return remainingPayments;
	}

	public void setRemainingPayments(Integer remainingPayments) {
		this.remainingPayments = remainingPayments;
	}
	
	@Override
	public String toString() {
		return "VIN: " + this.vinNumber + " Model: " + this.model + " Year: " + this.year;
	}
	
	public String getCarRecord() {
		return "| " + this.vinNumber + "     \t| " + this.model + "      \t| " + this.year
				+ "     \t| " + this.offer + "  \t|";
	}
	
	

}
