package com.any.tutorial.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;

import com.any.tutorial.hibernate.beans.Employee;

public class ManageEmployee {

	private SessionFactory sessionFactory;

	public ManageEmployee(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Integer addEmployee(String firstName, String lastName, Integer sallary) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer empId = null;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(firstName, lastName, Optional.of(sallary).orElse(0));
			empId = (Integer) session.save(employee);
			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();

		} finally {
			session.close();
		}

		return empId;
	}

	public void listEmployees() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Employee> empList = session.createQuery("FROM Employee", Employee.class).list();
			System.out.println("ALL EMPLOYEES:");
			for (Iterator<Employee> it = empList.iterator(); it.hasNext();) {
				Employee emp = it.next();
				System.out.println("Employee [First Name]: " + emp.getFirstName());
				System.out.println("Employee [Last Name]: " + emp.getLastName());
				System.out.println("Employee [Sallary]: " + emp.getSallary() + "\n");
			}
			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();

		} finally {
			session.close();
		}
	}

	public void deleteEmployee(Integer empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, empId);
			session.delete(emp);
			tx.commit();

		} catch (HibernateException he) {

		} finally {
			session.close();
		}
	}
	
	public void updateEmployee(Integer empId, Integer sallary) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, empId);
			emp.setSallary(Optional.of(sallary).orElse(0));
			session.update(emp);
			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();

		} finally {
			session.close();
		}
	}
	
	public void deleteAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			int count = session.createQuery("delete from Employee").executeUpdate();
			System.out.println(String.format("\n%d records deletted...\n", count));
			tx.commit();
			
		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

}
