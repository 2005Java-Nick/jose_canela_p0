package com.revature.AudiDealership;

import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

// import com.revature.DAO.*;
import com.revature.Objects.*;
import com.revature.Services.*;

/**
 * @author josecanela
 *
 */

public class DealershipApp {
	private static Logger log = Logger.getLogger(DealershipApp.class);
	
	static User user = new User(); // Instantiate a user

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		// UserDAO userDAO = new UserDAO();
		// AudiCarDAO audiCarDAO = new AudiCarDAO();
		// DealershipDAO dealershipDAO = new DealershipDAO();
		
		// UserDatabase userDatabase = new UserDatabase();
		// AudiCarDatabase audiCarDatabase = new AudiCarDatabase();
		log.info("mainMenu:Running Main Menu");
		mainMenu();
		

	}

//Main Menu
	/**
	 * 
	 */
	public static void mainMenu() {
		//AuthenticateUser authUser = new AuthenticateUser();
		Boolean run = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to The Audi Dealership App!");
		do {
			String userInput;
			System.out.println("Please select one of our options:");

			System.out.println("|-1. Login \n|-2. Create Account \n|-3. Close Application\n");

			userInput = scan.nextLine();
			userInput = userInput.toUpperCase();

			switch (userInput.toUpperCase()) {
			case "1":
				signInMenu();
				log.info("signInMenu:Running SignIn Menu");
				break;

			case "2":
				registrationMenu();
				log.info("registrationMenu:Running Registration Menu");
				break;

			case "3":
				run = false;
				log.info("mainMenu:Exited Main Menu");
				break;
			default:
				System.out.println("Invalid choice, try again...");
				log.info("mainMenu:Invalid Main Menu Choice Given");
				break;
			}

		} while (run == true);

		scan.close();
		System.out.println("Goodbye! Thank you for using The Audi Dealership App!");
		log.info("mainMenu:Application Closed.");

		System.exit(0);
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
		Scanner sc = new Scanner(System.in);

		// Prompt
		System.out.println("LOGIN MENU:");
		System.out.println("1. Customer Login: \n2. Employee Login: \n3. Exit");
		userInput = sc.nextLine();

		switch (userInput) {
		case "1":
			// CUSTOMER LOGIN
			System.out.println("Username: ");
			userInput = sc.nextLine();
			username = userInput;

			System.out.println("Password : ");
			userInput = sc.nextLine();
			password = userInput;

			if (authUser.authenticateCustomer(username, password)) {
				customerMenu(username);
				log.info("signInMenu:Running Customer Menu.");
			} else {
				log.info("signInMenu:Customer failed to login - invalid username or password");
				
				System.out.println("Your login attempt has failed.\nMake sure the username and password are correct.\n");
				signInMenu();
				log.info("signInMenu:Customer back to SignIn Menu");
				
			}

			break;

		case "2":
			// EMPLOYEE LOGIN
			System.out.println("Username: ");
			userInput = sc.nextLine();
			username = userInput;

			System.out.println("Password : ");
			userInput = sc.nextLine();
			password = userInput;
			if (authUser.authenticateEmployee(username, password)) {
				employeeMenu(username);
				log.info("signInMenu:Running Employee Menu.");
			} else {
				log.info("signInMenu:Employee failed to login - invalid username or password");
				
				// System.out.println("Your login attempt has failed.\nMake sure the username and password are correct.\n");
				signInMenu();
				log.info("signInMenu:Employee back to SignIn Menu");
			}

			break;

		case "3":
			mainMenu();
			log.info("signInMenu:User back to Main Menu");
			break;

		default:
			System.out.println("Invalid choice, try again...");
			log.info("signInMenu:Invalid SignIn Menu Choice Given");
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
		String rootPassword;
		Scanner s = new Scanner(System.in);

		System.out.println("\nREGISTRATION MENU");
		System.out.println("|-1. Customer Registration: \n|-2. Employee Registration: \n|-3. Exit\n");
		userInput = s.nextLine();

		switch (userInput) {
		case "1":
			System.out.println("Username: ");
			userInput = s.nextLine();
			username = userInput;

			System.out.println("Password : ");
			userInput = s.nextLine();
			password = userInput;

			regUser.registerCustomer(username, password);
			signInMenu();
			log.info("registrationMenu:Customer("+ username +") Registered and sent to SignIn Menu");
			break;

		case "2":
			System.out.println("Username: ");
			userInput = s.nextLine();
			username = userInput;

			System.out.println("Password : ");
			userInput = s.nextLine();
			password = userInput;

			System.out.println("Root password : ");
			userInput = s.nextLine();
			rootPassword = userInput;

			regUser.registerEmployee(username, password, rootPassword);
			signInMenu();
			log.info("registrationMenu:Employee("+ username +") Registered and sent to SignIn Menu");
			break;

		case "3":
			mainMenu();
			log.info("registrationMenu:User back to Main Menu");
			break;

		default:
			System.out.println("Invalid choice, try again...");
			log.info("registrationMenu:Invalid Registration Menu Choice Given");
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
		Scanner sca = new Scanner(System.in);
		ViewAudi viewAudi = new ViewAudi();
		BidOnAudiCar bidOnAudi = new BidOnAudiCar();
		ManageAudiCarPayments mngAudiCarPay = new ManageAudiCarPayments();

		System.out.println("CUSTOMER MENU: \n Hello " + username);
		System.out.println(
				"|-1. Explore Cars: \n|-2. Make Offer: \n|-3. View Cars: \n|-4. View Remaining Balance: \n|-5. Make Monthly Payment: \n|-6. Sign Out");
		userInput = sca.nextLine();

		switch (userInput) {
		case "1":
			viewAudi.viewAudis();
			customerMenu(username);
			log.info("customerMenu: Customer("+ username +") viewed the Audi's in the car lot and was given Customer Menu Options");
			break;

		case "2":
			System.out.println("--MAKE OFFER--");
			viewAudi.viewAudis();
			System.out.println("Enter Vehicle Identification Number(VIN): ");
			vinNumber = sca.nextLine();
			sca.nextLine();
			System.out.println("Enter Amount: ");
			offer = sca.nextDouble();
			bidOnAudi.addOffer(vinNumber, username, offer);
			customerMenu(username);
			
			log.info("customerMenu: Customer("+ username +") made an offer ($" + offer + ") on car with VIN (" + vinNumber +") and given Customer Menu Options");
			break;
		case "3":
			System.out.println("--VIEW CARS--");	
			mngAudiCarPay.viewAudiCarsAndPaymentInfo(username);
			customerMenu(username);
			
			log.info("customerMenu: Customer("+ username +") given Customer Menu Options");
			break;
		case "4":
			System.out.println("--VIEW REMAINING BALANCE--");
			mngAudiCarPay.customerPaymentHistory(username);
			customerMenu(username);
			
			log.info("customerMenu: Customer("+ username +") was given Customer Menu Options");
			break;
		case "5":
			System.out.println("--MAKE MONTHLY PAYMENTS--");
			mngAudiCarPay.makePayment(username);
			customerMenu(username);
			
			log.info("customerMenu: Customer("+ username +") was given Customer Menu Options");
		case "6":
			System.out.println("--SIGNED OUT--");
			signInMenu();
			
			log.info("customerMenu: Customer("+ username +") Signed Out and sent back to SignIn Menu");
			break;
		default:
			System.out.println("Invalid choice, try again...");
			log.info("customerMenu:Invalid Customer Menu Choice Given");
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
		Scanner scann = new Scanner(System.in);

		System.out.println("\nEMPLOYEE MENU: \n Hello " + username);
		System.out.println(
				"|-1. Explore Cars: \n|-2. Add Cars: \n|-3. Accept Offers: \n|-4. Remove Car From Lot: \n|-5. View All Customer Payments: \n|-6. Sign Out");
		userInput = scann.nextLine();

		switch (userInput) {
		case "1":
			viewAudi.viewAudis();
			employeeMenu(username);
			log.info("customerMenu: Employee("+ username +") Viewed Audi's in car lot and given Employee Menu Options");
			break;
			
		case "2":
			String exit = "Y";
			do {
				System.out.println("--ADD CARS--");
				System.out.println("Enter VIN:");
				vinNumber = scann.nextLine();
				System.out.println("Enter Model:");
				model = scann.nextLine();
				System.out.println("Enter Year:");
				year = scann.nextLine();
				scann.nextLine();
				System.out.println("Enter price:");
				price = scann.nextDouble();
				regAudi.addAudiCar(vinNumber, model, year, price);
				vinNumber = "";
				model = "";
				year = "";
				price = 0D;
				employeeMenu(username);
				
				
				System.out.println("Add Another Audi? (Y) or (N)");
				exit = scann.nextLine();

			} while (exit.equals("Y"));
			employeeMenu(username);
			
			log.info("employeeMenu: Employee("+ username +") stopped adding Audi's and given Employee Menu Options");
			break;
		case "3":
			System.out.println("\n--ACCEPT OFFER--");
			viewAudi.viewAudis();
			System.out.println("Enter VIN: ");
			vinNumber = scann.nextLine();
			bidOnAudi.getCurrentOffers(vinNumber);
			System.out.println("Do you want to accept any offers? (Y) or (N)");
			exit = scann.nextLine();
			switch (exit) {
			case "Y":
				System.out.println("Enter Customer: ");
				customer = scann.nextLine();
				bidOnAudi.acceptOffer(customer, vinNumber);
				customer = "";
				vinNumber = "";
				employeeMenu(username);
				
				log.info("employeeMenu: Employee("+ username +") given Employee Menu Options");
				break;
			case "N":
				employeeMenu(username);
				log.info("employeeMenu: Employee("+ username +") stopped accepting offers and given Employee Menu Options");
			default:
				System.out.println("Invalid Entry\n");
				log.info("employeeMenu:Invalid Employee Entry. Employee("+ username +") should enter Y or N");
			}

			break;
		case "4":
			System.out.println("--REMOVE CARS--");
			viewAudi.viewAudis();
			System.out.println("Enter Car VIN:");
			vinNumber = scann.nextLine();
			rmvRegAudi.removeAudiCar(vinNumber);
			vinNumber = "";
			employeeMenu(username);
			
			log.info("employeeMenu: Employee("+ username +") given Employee Menu Options");
			break;
		case "5":
			System.out.println("--VIEW ALL PAYMENTS--");
			mngAudiCarPay.employeePaymentView();
			employeeMenu(username);
			
			log.info("employeeMenu: Employee("+ username +") given Employee Menu Options");
			break;
		case "6":
			System.out.println("--SIGNED OUT--\n");
			signInMenu();
			
			log.info("employeeMenu: Employee("+ username +") Signed Out and sent back to SignIn Menu");
			break;
		default:
			System.out.println("Invalid choice, try again...");
			log.info("employeeMenu:Invalid Employee Menu Choice Given");
			break;
		}
	}

}
