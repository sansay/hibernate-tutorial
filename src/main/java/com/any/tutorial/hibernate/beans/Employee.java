package com.any.tutorial.hibernate.beans;

public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private int sallary;

	public Employee() {	}

	public Employee(String firstName, String lastName, int sallary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sallary = sallary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSallary() {
		return sallary;
	}

	public void setSallary(int sallary) {
		this.sallary = sallary;
	}

}
