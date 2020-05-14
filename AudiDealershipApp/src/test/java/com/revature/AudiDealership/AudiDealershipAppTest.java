package com.revature.AudiDealership;

import com.revature.Objects.*;
import com.revature.AudiDealership.*;
import com.revature.DAO.*;
import com.revature.Services.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AudiDealershipAppTest {
	UserPolicy userPolicy;
	UserDAO userDAO;
	AudiCarDAO audiDAO;

	Employee Jim = (Employee) new User("jim123", "Pigs123", userPolicy);
	Customer Kim = (Customer) new User("kim123", "Dog123", userPolicy);

	Integer cost = 33300;
	Double price = new Double(cost);

	AudiCar audi = new AudiCar("2G1WL52M4W9218917", "A3", "2020", price, true);
	ArrayList<AudiCar> carsOwned = new ArrayList<AudiCar>();

	/*
	 * @Test - marks the public void method as a test case.
	 * 
	 * @Before - Annotating a method with @Before causes that method to be run
	 * before each Test method.
	 * 
	 * @After - Annotating a method with @After causes that method to be run after
	 * the Test method.
	 * 
	 * @BeforeClass - Annotating a method with @BeforeClass causes it to be run once
	 * before any of the test methods in the class.
	 * 
	 * @AfterClass - call the method after all tests have finished.
	 * 
	 * @Ignore - marks to ignore the test and that test will not be executed.
	 */

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// UserDatabase userDB = new UserDatabase();
		// Employee Jim = new Employee("jim123", "Pigs123", userPolicy);
		// userDB.

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitializingUser() {
		User user = new User("Bob1", "ChickenFeet123", userPolicy);
		assertEquals("Bob1", user.getUsername());
		assertEquals("ChickenFeet123", user.getPassword());
		assertEquals(userPolicy, user.getUserPolicy());
	}

	@Test
	public void testUserDatabase() {
		// addCustomer(username, newUser);

		// getCustomer(username)
		// addEmployee(username, newUser);
		// getEmployee(username);

	}

	@Test
	public void testUserDAO() {
		HashMap<String, Employee> employees = new HashMap<String, Employee>();
		employees.put(Jim.getUsername(), Jim);
		HashMap<String, Customer> customers = new HashMap<String, Customer>();
		customers.put(Kim.getUsername(), Kim);

		assertTrue(userDAO.createEmployeeDatabase(employees));
		assertNotNull(userDAO.readEmployees());
		assertTrue(userDAO.createCustomerDatabase(customers));
		assertNotNull(userDAO.readCustomer());

	}

	@Test
	public void testUserRegistration() {
		// registerCustomer(username, password);
		// registerEmployee(username, password);

	}

	@Test
	public void testUserAuthentication() {
		// authenticateCustomer(username, password);
		// authenticateEmployee(username, password);

	}

	@Test
	public void testRemovingUser() {
		// removeUser(username, password, customer);

	}

	@Test
	public void testCustomer() {
		assertEquals("kim123", Kim.getUsername());
		assertEquals("Dog123", Kim.getPassword());
		assertEquals(userPolicy, Kim.getUserPolicy());

	}

	@Test
	public void testCreatingAudiCars() {
		assertEquals("2G1WL52M4W9218917", audi.getVinNumber());
		assertEquals("A3", audi.getModel());
		assertEquals("2020", audi.getYear());
		assertEquals(price, audi.getPrice());
		assertEquals(true, audi.isThereOffers());

		assertEquals("VIN: " + "2G1WL52M4W9218917" + " Model: " + "A3" + " Year: " + "2020", audi.toString());

		assertEquals(
				"| " + "2G1WL52M4W9218917" + "     \t| " + "A3" + "      \t| " + "2020" + "     \t| " + true + "  \t|",
				audi.getCarRecord());

	}

	@Test
	public void testAudiCarDatabase() {
		// addCar(vin, car);
		// getAudiCar(carVin);
		// getLot();

	}

	@Test
	public void testAudiCarDAO() {
		HashMap<String, AudiCar> lot = new HashMap<String, AudiCar>();
		lot.put(audi.getVinNumber(), audi);

		assertTrue(audiDAO.createAudiCarDatabase(lot));
		assertNotNull(audiDAO.readAudiCarDatabase());

	}

	@Test
	public void testAudiRegistration() {
		// addAudiCar(vinNumber, model, year, price);

	}

	@Test
	public void testViewAudi() {
		// viewAudis();

	}

	@Test
	public void testRemoveRegisteredAudi() {
		// removeAudiCar(carVin);

	}

	@Test
	public void testBiddingOnAudiCar() {
		// addOffer(vinNumber, customer, offer);
		// removeOffer(customer, vinNumber);
		// getCarOffer(carVin, customer);
		// getCurrentOffers(vinNumber);
		// acceptOffer(customer, vinNumber);
	}

	@Test
	public void testManagingAudiCarPayments() {
		// calculateMonthlyPayment(custUsername, carVin);
		// viewAudiCarAndPaymentInfo(customer);
		// makePayment(customer);
		// customerPaymentHistory(customer);
		// employeePaymentView();
	}

}
