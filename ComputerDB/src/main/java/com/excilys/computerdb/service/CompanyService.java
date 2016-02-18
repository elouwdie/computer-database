package com.excilys.computerdb.service;

import java.util.List;

import com.excilys.computerdb.dao.impl.CompanyDAOImpl;
import com.excilys.computerdb.model.Company;

public class CompanyService {
	private static CompanyDAOImpl companyDAO;

	public static List<Company> findAll() {
		companyDAO = new CompanyDAOImpl();
		return companyDAO.findAll();
	}

	public static Company findById(long id) {
		companyDAO = new CompanyDAOImpl();
		return companyDAO.findById(id);
	}
}
