package com.excilys.computerdb.dao;

import com.excilys.computerdb.model.Company;

import java.util.List;

/**
 * Data access object.
 * @author ecayez
 *
 */
public interface CompanyDao {

  /**
   * Finds all companies in the database.
   * @return : the list of all companies.
   */
  List<Company> findAll();

  /**
   * Deletes a company in the database, and all the computers related to this
   * company.
   * @param id : the id of the company to delete.
   */
  void delete(long id);

  /**
   * Find a company in the database.
   * @param id : the ID of the company
   * @return : the company corresponding to the ID in the database
   */
  Company findById(long id);

}
