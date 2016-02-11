package com.excilys.computerdb.model;

import java.time.LocalDate;

/**
 * Class computer : corresponds to an instance of the table computer in the
 * database
 * 
 * @author excilys
 *
 */
public class Computer {
	private long id = 0;
	private Company company = null;
	private String name = null;
	private LocalDate introduced = null;
	private LocalDate discontinued = null;

	public Computer() {

	}

	public Computer(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public Computer(String name, LocalDate introduced, LocalDate discontinued, Company company) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public String toString() {
		StringBuffer chaine = new StringBuffer("Computer with ID " + id + ", named " + name);
		if (introduced != null) {
			chaine.append("\nIntroduced in " + introduced.toString());
		}
		if (discontinued != null) {
			chaine.append("\nDiscontinued in " + discontinued.toString());
		}
		if (company != null) {
			chaine.append("\nCreated by " + company.getName());
		}
		return chaine.toString();
	}

}
