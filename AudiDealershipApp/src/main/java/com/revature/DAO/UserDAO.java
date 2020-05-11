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
		public boolean createEmployeeDatabase(HashMap<String, Employee> employees) {
			try (FileOutputStream fos = new FileOutputStream("UserDatabase.dat");
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(employees);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		public HashMap<String, Employee> readEmployees() {
			HashMap<String, Employee> employees = null;
			try (FileInputStream fis = new FileInputStream("UserDatabase.dat");
					ObjectInputStream ois = new ObjectInputStream(fis);) {
				employees = (HashMap<String, Employee>) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return employees;
		}

		public boolean createCustomerDatabase(HashMap<String, Customer> customers) {
			try (FileOutputStream fos = new FileOutputStream("UserDatabase.dat");
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(customers);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		public HashMap<String, Customer> readCustomer() {
			HashMap<String, Customer> customers = null;
			try (FileInputStream fis = new FileInputStream("UserDatabase.dat");
					ObjectInputStream ois = new ObjectInputStream(fis);) {
				customers = (HashMap<String, Customer>) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return customers;
		}
}
