package com.any.tutorial.hibernate.beans;

import java.util.Set;

public class CertifiedEmployee extends Employee {

	private Set<Certificate> certificates;

	public CertifiedEmployee() {
	}

	public CertifiedEmployee(String firstName, String lastName, int sallary) {
		super(firstName, lastName, sallary);
	}

	public Set<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

}
