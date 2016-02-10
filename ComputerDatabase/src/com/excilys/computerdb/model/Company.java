package com.excilys.computerdb.model;

/**
 * Class company : corresponds to an instance of table company in the database.
 * @author excilys
 *
 */
public class Company {
	private long id = 0;
	private String name;
	
	public Company() {
		
	}
	
	public Company(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return : the information of the company
	 */
	public String toString() {
		return ("Company with ID : " + id + ", named " + name);
	}
	
}