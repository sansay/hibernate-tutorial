package com.any.tutorial.hibernate.beans;

import java.util.Set;

public class CertifiedEmployee extends Employee {

	private Set certificates;

	public CertifiedEmployee() {
	}

	public CertifiedEmployee(String firstName, String lastName, int sallary) {
		super(firstName, lastName, sallary);
	}

	public Set getCertificates() {
		return certificates;
	}

	public void setCertificates(Set certificates) {
		this.certificates = certificates;
	}

}
