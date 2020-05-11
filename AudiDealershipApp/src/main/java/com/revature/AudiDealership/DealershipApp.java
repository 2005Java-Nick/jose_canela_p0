package com.revature.AudiDealership;

import java.util.*;

import com.revature.DAO.*;
import com.revature.Objects.*;
import com.revature.Services.*;

/**
 * @author josecanela
 *
 */

public class DealershipApp {
	static User user = new User(); //Instantiate a user

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegisterUser regUser = new RegisterUser();
		RegisterAudi regAudi = new RegisterAudi();
		
		UserDAO userDAO = new UserDAO();
		UserDatabase.setEmployees(userDAO.readEmployees());
		UserDatabase.setCustomers(userDAO.readCustomer());
		AudiCarDAO audiCarDAO = new AudiCarDAO();
		AudiCarDatabase.setLot(audiCarDAO.readAudiCarDatabase());
		mainMenu();
		
	}

//Main Menu
	/**
	 * 
	 */
	public static void mainMenu() {
		AuthenticateUser authUser = new AuthenticateUser();
		Boolean run = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome!");
		do {
			String userInput;
			System.out.println("Please select one of our options:");

			System.out.println("|-1. Login \n|-2. Create Account \n|-3. Exit\n");

			userInput = scan.nextLine();
			userInput = userInput.toUpperCase();

			switch (userInput.toUpperCase()) {
			case "1":
			case "LOGIN":
				signInMenu();
				break;

			case "2":
				registrationMenu();
				break;
			case "CREATE":
				break;
			case "3":

			case "EXIT":
				run = false;
				break;
			default:
				System.out.println("Invalid choice, try again...");
				break;
			}

		} while (run);

		scan.close();
		System.out.println("Goodbye!");
	}

//SignIn Menu
	/**
	 * 
	 */
	public static void signInMenu() {
		AuthenticateUser authUser = new AuthenticateUser();
		String username;
		String password;
		String userInput;
		Scanner scan = new Scanner(System.in);

		// Prompt
		System.out.println("LOGIN MENU:");
		System.out.println("1. Customer Login: \n2. Employee Login: ");
		userInput = scan.nextLine();

		switch (userInput) {
		case "1":
			// CUSTOMER LOGIN
			System.out.println("Username: ");
			userInput = scan.nextLine();
			username = userInput;

			System.out.println("Password : ");
			userInput = scan.nextLine();
			password = userInput;
			System.out.println(authUser.authenticateCustomer(username, password));
			if (authUser.authenticateCustomer(username, password)) {
				customerMenu(username);
			}
			else {
				signInMenu();
			}

			break;

		case "2":
			// EMPLOYEE LOGIN
			System.out.println("Username: ");
			userInput = scan.nextLine();
			username = userInput;

			System.out.println("Password : ");
			userInput = scan.nextLine();
			password = userInput;
			if (authUser.authenticateEmployee(username, password)) {
				employeeMenu(username);
			}else {
				signInMenu();
			}

			break;

		default:
			System.out.println("Invalid choice, try again...");
			break;
		}

	}

//Registration Menu
	/**
	 * 
	 */
	public static void registrationMenu() {
		RegisterUser regUser = new RegisterUser();
		String userInput;
		String username;
		String password;
		String systemPassword;
		Scanner scan = new Scanner(System.in);

		System.out.println("\nREGISTRATION MENU");
		System.out.println("|-1. Customer Registration: \n|-2. Employee Registration: ");
		userInput = scan.nextLine();

		switch (userInput) {
		case "1":
			System.out.println("Username: ");
			userInput = scan.nextLine();
			username = userInput;

			System.out.println("Password : ");
			userInput = scan.nextLine();
			password = userInput;

			regUser.registerCustomer(username, password);
			signInMenu();
			break;

		case "2":
			System.out.println("Username: ");
			userInput = scan.nextLine();
			username = userInput;

			System.out.println("Password : ");
			userInput = scan.nextLine();
			password = userInput;

			System.out.println("Root password : ");
			userInput = scan.nextLine();
			systemPassword = userInput;

			regUser.registerEmployee(username, password, systemPassword);
			signInMenu();
			break;

		default:
			System.out.println("Invalid choice, try again...");
			break;
		}
	}

//CustomerMenu
	/**
	 * @param username
	 */
	public static void customerMenu(String username) {
		String userInput, vinNumber;
		Double offer;
		Scanner scan = new Scanner(System.in);
		ViewAudi viewAudi = new ViewAudi();
		BidOnAudiCar bidOnAudi = new BidOnAudiCar();

		System.out.println("CUSTOMER MENU: \n Hello " + username);
		System.out.println("|-1. Explore Cars: \n|-2. Make Offer: \n|-3. View Cars: \n|-4. View Remaining Balance: \n|-5. ");
		userInput = scan.nextLine();

		switch (userInput) {
		case "1":
			viewAudi.viewAudis();
			customerMenu(username);
			break;

		case "2":
			System.out.println("--MAKE OFFER--");
			System.out.println("Enter Vehicle Identification Number(VIN): ");
			vinNumber = scan.nextLine();
			//Clear?
			System.out.println("Enter Amount: ");
			offer = scan.nextDouble();
			bidOnAudi.addOffer(vinNumber, username, offer);
			customerMenu(username);
			break;
		case "3":
			System.out.println("--VIEW CARS--");
			System.out.println("Enter Vehicle Identification Number(VIN): ");
			vinNumber = scan.nextLine();
			//Clear?
			System.out.println("Enter Amount: ");
			offer = scan.nextDouble();
			bidOnAudi.addOffer(vinNumber, username, offer);
			customerMenu(username);
			break;
		case "4":
			System.out.println("--VIEW REMAINING BALANCE--");
			System.out.println("Enter Vehicle Identification Number(VIN): ");
			vinNumber = scan.nextLine();
			// Clear the scanner
			scan.hasNextLine();
			System.out.println("Enter Amount: ");
			offer = scan.nextDouble();
			bidOnAudi.addOffer(vinNumber, username, offer);
			customerMenu(username);
			break;
		case "5":
			System.out.println("--SIGNOUT--");
			mainMenu();
			break;
		default:
			System.out.println("Invalid choice, try again...");
			break;
		}
	}

//EmployeeMenu
	/**
	 * @param username
	 */
	public static void employeeMenu(String username) {
		ViewAudi viewAudi = new ViewAudi();
		BidOnAudiCar bidOnAudi = new BidOnAudiCar();
		RegisterAudi regAudi = new RegisterAudi();
		RemoveRegisteredAudi rmvRegAudi = new RemoveRegisteredAudi();
		ManageAudiCarPayments mngAudiCarPay = new ManageAudiCarPayments();
		String userInput, vinNumber, model, year, customer;
		Double price;
		Scanner scan = new Scanner(System.in);

		System.out.println("\nEMPLOYEE MENU: \n Hello " + username);
		System.out.println("|-1. Add Cars: \n|-2. Accept Offers: \n|-3. Remove Car From Lot: \n|-4. View All Payments: \n|-5. Signout");
		userInput = scan.nextLine();

		switch (userInput) {
		case "1":
			String exit = "Y";
			do {
				System.out.println("--ADD CARS--");
				System.out.println("Enter VIN:");
				vinNumber = scan.nextLine();
				System.out.println("Enter Model:");
				model = scan.nextLine();
				System.out.println("Enter Year:");
				year = scan.nextLine();
				scan.nextLine();
				System.out.println("Enter price:");
				price = scan.nextDouble();
				regAudi.addAudiCar(vinNumber, model, year, price);
				vinNumber = "";
				model = "";
				year = "";
				price = 0D;
				employeeMenu(username);
				System.out.println("Add Another Audi? (Y)");
				exit = scan.nextLine();
				
			} while (exit.equals("Y"));
			employeeMenu(username);
			break;
		case "2":
			System.out.println("\n--ACCEPT OFFER--");
			
			System.out.println("Enter VIN: ");
			vinNumber = scan.nextLine();
			bidOnAudi.getCurrentOffers(vinNumber);
			System.out.println("Do you want to accept any offers?");
			switch (userInput){
				case "Y":
					System.out.println("Enter Customer: ");
					customer = scan.nextLine();
					bidOnAudi.acceptOffer(customer, vinNumber);
					customer = "";
					vinNumber = "";
					break;
				case "N":
					employeeMenu(username);
				default:
					System.out.println("Invalid Entry");
			}
			
			break;
		case "3":
			System.out.println("--REMOVE CARS--");
			viewAudi.viewAudis();
			System.out.println("Enter Car VIN:");
			vinNumber = scan.nextLine();
			rmvRegAudi.removeAudiCar(vinNumber);
			vinNumber = "";
			employeeMenu(username);
			break;
		case "4":
			System.out.println("--VIEW ALL PAYMENTS--");
			mngAudiCarPay.employeePaymentView();
			employeeMenu(username);
			break;
		case "5":
			System.out.println("--SIGNOUT--");
			signInMenu();
			break;
		default:
			System.out.println("Invalid choice, try again...");
			break;
		}
	}

}
