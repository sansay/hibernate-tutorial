package com.any.tutorial.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {

	private Integer value;

	public int value1() {
		return value;
	}

	public Integer value2() {
		return value;
	}

	public static void main(String[] args) {
		SessionFactory sf = null;
		try {

			sf = new Configuration().configure().buildSessionFactory();
		} catch (Throwable t) {
			System.err.println("Cannot create session factory " + t);
			throw new ExceptionInInitializerError(t);
		}
		ManageEmployee me = new ManageEmployee(sf);
		
		me.deleteAll();

		Integer emp1 = me.addEmployee("Zara", "Ali", 1000);
		Integer emp2 = me.addEmployee("Daysi", "Das", 5000);
		Integer emp3 = me.addEmployee("John", "Paul", 10000);

		me.listEmployees();

		me.updateEmployee(emp1, 5500);

		me.deleteEmployee(emp2);

		me.listEmployees();

	}
}
