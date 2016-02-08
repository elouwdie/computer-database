package com.excilys.computerDB.model;

import com.excilys.computerDB.dao.concrete.CompanyDAO;

public class Computer {
	private long id = 0;
	private long companyId = 0;
	private String name = null;
	private String introduced = null;
	private String discontinued = null;
	
	public Computer() {
		
	}

	public Computer(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Computer(long id, String name, String introduced, String discontinued, long companyId) {
		this.id = id;
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
	
	public String getIntroduced() {
		return introduced;
	}
	
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	
	public String getDiscontinued() {
		return discontinued;
	}
	
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	
	public String toString() {
		StringBuffer chaine = new StringBuffer("Computer with ID " + id + ", named " + name);
		if(introduced != null){
			chaine.append("\nIntroduced in " + introduced);
		}
		if(discontinued != null){
			chaine.append("\nDiscontinued in " + discontinued);
		}
		if(companyId > 0){
			CompanyDAO companyDAO = new CompanyDAO();
			String companyName = companyDAO.findById(companyId).getName();
			chaine.append("\nCreated by " + companyName);
		}
		return chaine.toString();
	}

}
