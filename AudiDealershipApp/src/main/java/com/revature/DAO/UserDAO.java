package com.revature.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import com.revature.Objects.Customer;
import com.revature.Objects.Employee;

public class UserDAO {
	// Each customer balance is kept in this data object
		public void createEmployeeDatabase(HashMap<String, Employee> employees) {
			try (FileOutputStream fos = new FileOutputStream("UserDatabase.dat");
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(employees);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public HashMap<String, Employee> readEmployees() {
			HashMap<String, Employee> employees = null;
			try (FileInputStream fis = new FileInputStream("UserDatabase.dat");
					ObjectInputStream ois = new ObjectInputStream(fis);) {
				employees = (HashMap<String, Employee>) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return employees;
		}

		public void createCustomerDatabase(HashMap<String, Employee> customers) {
			try (FileOutputStream fos = new FileOutputStream("UserDatabase.dat");
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(customers);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public HashMap<String, Customer> readCustomer() {
			HashMap<String, Customer> customers = null;
			try (FileInputStream fis = new FileInputStream("UserDatabase.dat");
					ObjectInputStream ois = new ObjectInputStream(fis);) {
				customers = (HashMap<String, Customer>) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return customers;
		}
}
