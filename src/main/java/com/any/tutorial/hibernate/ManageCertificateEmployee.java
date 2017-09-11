package com.any.tutorial.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;

import com.any.tutorial.hibernate.beans.Certificate;
import com.any.tutorial.hibernate.beans.CertifiedEmployee;
import com.any.tutorial.hibernate.beans.Employee;

public class ManageCertificateEmployee extends ManageEmployee {
	
	public ManageCertificateEmployee(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Integer addEmployee(String firstName, String lastName, Integer sallary, Set cert) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer empId = null;
		try {
			tx = session.beginTransaction();
			CertifiedEmployee employee = new CertifiedEmployee(firstName, lastName, Optional.of(sallary).orElse(0));
			employee.setCertificates(cert);
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

	@Override
	public void listEmployees() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<CertifiedEmployee> empList = session.createQuery("FROM Employee", CertifiedEmployee.class).list();
			System.out.println("ALL EMPLOYEES:");
			for (Iterator<CertifiedEmployee> it = empList.iterator(); it.hasNext();) {
				CertifiedEmployee emp = it.next();
				System.out.println("Employee [First Name]: " + emp.getFirstName());
				System.out.println("Employee [Last Name]: " + emp.getLastName());
				System.out.println("Employee [Sallary]: " + emp.getSallary() + "\n");
				for (Iterator<Certificate> it2 = emp.getCertificates().iterator(); it2.hasNext();) {
					Certificate cert = it2.next();
					System.out.println("\tEmployee [Certificate]:" + cert.getName());
				}
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
			Employee emp = session.get(CertifiedEmployee.class, empId);
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
			Employee emp = session.get(CertifiedEmployee.class, empId);
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
