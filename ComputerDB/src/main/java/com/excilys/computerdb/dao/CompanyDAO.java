package com.excilys.computerdb.dao;

import java.util.List;

import com.excilys.computerdb.model.Company;

/**
 * Data access object
 * 
 * @author ecayez
 *
 */
public interface CompanyDAO {

	/**
	 * Finds all companies in the database.
	 * 
	 * @return : the list of all companies.
	 */
	List<Company> findAll();

	/**
	 * Find a company in the database.
	 * 
	 * @param id
	 *            : the ID of the company
	 * @return : the company corresponding to the ID in the database
	 */
	Company findById(long id);

}
