package com.excilys.computerdb.service;

import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;

public interface ComputerService {

  /**
   * counts the number of computers in the database.
   * @return : the count of computers in the database.
   * @throws DaoException : when there is a problem in the database.
   */
  int getCount();

  /**
   * counts the number of computers with the given name / company name in the
   * database.
   * @param search : the type of search : name or company name.
   * @param name : the name to search.
   * @return : the count of computers with the given name / company name in the
   *         database.
   * @throws DaoException : when there is a problem in the database.
   */
  int getCountBy(EnumSearch search, String name);

  /**
   * Finds all computers with the specified name / company name.
   * @param search : the type of search.
   * @param name : the name to search.
   * @param page : the page to fill with the results.
   * @throws DaoException : problem in the database.
   */
  void findByName(EnumSearch search, String name, Page page);

  /**
   * Finds all computers in the database.
   * @param page : the page to fill with the results.
   * @throws DaoException : problem in the database.
   */
  void findAll(Page page);

  /**
   * Find a computer in the database.
   * @param id : the id of the computer to search.
   * @return : the computer with the corresponding id.
   * @throws DaoException : problem in the database.
   */
  Computer findById(long id);

  /**
   * Creates a computer in the database.
   * @param computer : the computer to create.
   * @throws DaoException : problem in the database.
   */
  void create(Computer computer);

  /**
   * Updates a computer in the database with the given computer.
   * @param computer : the computer to update.
   * @throws DaoException : problem in the database.
   */
  void update(Computer computer);

  /**
   * Deletes a computer from the database.
   * @param id : the id of the computer to delete.
   * @throws DaoException : problem in the database.
   */
  void delete(long id);
}
