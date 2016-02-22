package com.excilys.computerdb.service;

import java.util.List;

import com.excilys.computerdb.dao.exceptions.DAOException;
import com.excilys.computerdb.dao.impl.CompanyDAOImpl;
import com.excilys.computerdb.model.Company;

public class CompanyService {
	private static CompanyDAOImpl companyDAO;

	public static List<Company> findAll() throws DAOException {
		companyDAO = new CompanyDAOImpl();
		return companyDAO.findAll();
	}

	public static Company findById(long id) throws DAOException {
		companyDAO = new CompanyDAOImpl();
		return companyDAO.findById(id);
	}

	public static void delete(long id) {
		companyDAO = new CompanyDAOImpl();
		companyDAO.delete(id);
	}
}
