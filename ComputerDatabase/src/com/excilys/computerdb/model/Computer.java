package com.excilys.computerdb.model;

import java.sql.Timestamp;

import com.excilys.computerdb.dao.impl.CompanyDAO;

/**
 * Class computer : corresponds to an instance of the table computer in the database
 * @author excilys
 *
 */
public class Computer {
	private long id = 0;
	private long companyId = 0;
	private String name = null;
	private Timestamp introduced = null;
	private Timestamp discontinued = null;
	
	public Computer() {
		
	}

	public Computer(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Computer(long id, String name, Timestamp introduced, Timestamp discontinued, long companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	public Computer(String name, Timestamp introduced, Timestamp discontinued, long companyId) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Timestamp getIntroduced() {
		return introduced;
	}
	
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * @return : a String containing all possible informations on the computer
	 */
	public String toString() {
		StringBuffer chaine = new StringBuffer("Computer with ID " + id + ", named " + name);
		if(introduced != null){
			chaine.append("\nIntroduced in " + introduced.toString().substring(0, 10));
		}
		if(discontinued != null){
			chaine.append("\nDiscontinued in " + discontinued.toString().substring(0, 10));
		}
		if(companyId > 0){
			//Find the corresponding company in the database
			CompanyDAO companyDAO = new CompanyDAO();
			String companyName = companyDAO.findById(companyId).getName();
			chaine.append("\nCreated by " + companyName);
		}
		return chaine.toString();
	}

}
