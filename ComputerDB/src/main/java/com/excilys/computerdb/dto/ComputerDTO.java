package com.excilys.computerdb.dto;

public class ComputerDTO {

	private int companyId = 0;
	private String name = null;
	private String introduced = null;
	private String discontinued = null;

	public ComputerDTO() {

	}

	public int getCompany() {
		return companyId;
	}

	public void setCompany(int companyId) {
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
}
