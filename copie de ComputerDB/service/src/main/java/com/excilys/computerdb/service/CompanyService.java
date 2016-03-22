package com.excilys.computerdb.service;

import java.util.List;

import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.model.Company;

public interface CompanyService {

  /**
   * Finds the list of companies in the database.
   * @return the list of companies.
   * @throws DaoException : problem in the database.
   */
  List<Company> findAll();

  /**
   * Finds a company in the database.
   * @param id : the id of the company to find.
   * @return : the corresponding company.
   * @throws DaoException : problem in the database.
   */
  Company findById(long id);

  /**
   * Deletes a company in the database, and every computer related to this
   * company.
   * @param id : the id of the company to delete.
   */
  void delete(long id);

}
