package com.any.tutorial.hibernate.beans;

public class Certificate {

	private int id;
	private String name;

	public Certificate() {
	}

	public Certificate(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int tmp = 0;
		tmp = (id + name).hashCode();
		return tmp;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;

		Certificate certObj = (Certificate) obj;
		if ((this.getId() == certObj.getId()) && (this.getName().equals(certObj.getName()))) {
			return true;
		}
		return false;
	}

}
