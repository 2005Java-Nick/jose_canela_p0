package com.revature.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.revature.Objects.Customer;
import com.revature.Objects.Employee;

/**
 * @author josecanela
 *
 */
@SuppressWarnings("serial")
public class UserDAO implements Serializable {
	private static Logger log = Logger.getRootLogger();
	
	/**
	 * 
	 * Each customer balance is kept in this data object
	 * 
	 * @param employees
	 * @return
	 */
	public boolean createEmployeeDatabase(HashMap<String, Employee> employees) {
		try (FileOutputStream fos = new FileOutputStream("EmployeeDatabase.dat", false);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(employees);
			return true;
		} catch (IOException e) {
			log.error("UserDAO:createEmployeeDatabase:IoException");
			return false;
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Employee> readEmployees() {
		HashMap<String, Employee> employees = new HashMap<String, Employee>();
		try (FileInputStream fis = new FileInputStream("EmployeeDatabase.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			employees = (HashMap<String, Employee>) ois.readObject();
		} catch (IOException e) {
			log.error("UserDAO:readEmployees:IoException");
			return employees;
		} catch (ClassNotFoundException e) {
			log.error("UserDAO:readEmployees:Class was not found!");
			return employees;
		}
		return employees;
	}

	/**
	 * @param customers
	 * @return
	 */
	public boolean createCustomerDatabase(HashMap<String, Customer> customers) {
		try (FileOutputStream fos = new FileOutputStream("CustomerDatabase.dat", false);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(customers);
			return true;
		} catch (IOException e) {
			log.error("UserDAO:createCustomerDatabase:IoException");
			return false;
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Customer> readCustomer() {
		HashMap<String, Customer> customers = new HashMap<String, Customer>();
		try (FileInputStream fis = new FileInputStream("CustomerDatabase.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			customers = (HashMap<String, Customer>) ois.readObject();
		} catch (IOException e) {
			log.error("UserDAO:readCustomer:IoException");
			return customers;
		} catch (ClassNotFoundException e) {
			log.error("UserDAO:readCustomer:Class was not found!");
			return customers;
		}
		return customers;
	}
}
