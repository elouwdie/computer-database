package com.excilys.computerdb.dao;

import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;

import java.util.List;

/**
 * Data access object.
 * @author ecayez
 */
public interface ComputerDao {

  /**
   * Counts the number of computers in the database.
   * @return the number of computers in the database.
   * @throws DaoException when there is a problem in the database.
   */
  int getCount() throws DaoException;

  /**
   * Counts the number of computers with a given name in the database.
   * @param search : the field to look at.
   * @param name the name computers must have.
   * @return the list of computers with the given name.
   * @throws DaoException : when there is a problem in the database
   */
  int getCountBy(EnumSearch search, String name) throws DaoException;

  /**
   * Fills the given page with computers corresponding to the search.
   * @param search : the field of the computer to compare.
   * @param name : the name the field must have.
   * @param page : the page to fill with computers.
   * @return the list of the computers found.
   * @throws DaoException : when there is a problem in the database.
   */
  List<Computer> findByName(EnumSearch search, String name, Page page) throws DaoException;

  /**
   * Finds a given number of computers in the database, from offset.
   * @param page : the page to fill.
   * @return the list of computers.
   * @throws DaoException : when there is a problem in the database.
   */
  List<Computer> findAll(Page page) throws DaoException;

  /**
   * Find a computer in the database.
   * @param id : the ID of the computer
   * @return : the computer corresponding to the ID in the database.
   * @throws DaoException : when there is a problem in the database.
   */
  Computer findById(long id) throws DaoException;

  /**
   * Creates an instance of the table computer in the database.
   * @param computer : the computer to create.
   * @throws DaoException : when there is a problem in the database.
   */
  void create(Computer computer) throws DaoException;

  /**
   * Updates the instance of a computer in the database replaces the old
   * attributes by the ones of the given parameter.
   * @param computer : the computer to modify.
   * @throws DaoException : when there is a problem in the database.
   */
  void update(Computer computer) throws DaoException;

  /**
   * Deletes a computer instance in the database.
   * @param id : the ID of the computer to delete.
   * @throws DaoException : when there is a problem in the database.
   */
  void delete(long id) throws DaoException;

  /**
   * Deletes all computers in the database with the given company_id.
   * @param companyId : the id of the company.
   */
  void deleteByCompany(long companyId);
}
