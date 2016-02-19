package com.excilys.computerdb.dao;

import java.util.List;

import com.excilys.computerdb.dao.exceptions.DAOException;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.pages.Page;

/**
 * Data access object
 * 
 * @author ecayez
 * 
 */
public interface ComputerDAO {

	/**
	 * Counts the number of computers in the database
	 * 
	 * @return the number of computers in the database
	 * @throws DAOException
	 */
	int getCount() throws DAOException;

	/**
	 * Counts the number of computers with a given name in the database
	 * 
	 * @param name
	 *            the name computers must have
	 * @return the list of computers with the given name
	 * @throws DAOException
	 */
	int getCountByName(String name) throws DAOException;

	/**
	 * Finds a given number of computers with a given name in the database, from
	 * offset
	 * 
	 * @param name
	 *            : the name computers must have
	 * @param offset
	 *            : the index to start searching
	 * @param limit
	 *            : the limit of computers to return
	 * @return : the given numbers of computers, with the given name
	 * @throws DAOException
	 */
	List<Computer> findByName(String name, Page page) throws DAOException;

	/**
	 * Finds a given number of computers in the database, from offset
	 * 
	 * @param offset
	 *            : the index to start searching
	 * @param limit
	 *            : the limit of computers to return
	 * @return the list of computers
	 * @throws DAOException
	 */
	List<Computer> findAll(Page page) throws DAOException;

	/**
	 * Find a computer in the database.
	 * 
	 * @param id
	 *            : the ID of the computer
	 * @return : the computer corresponding to the ID in the database
	 * @throws DAOException
	 */
	Computer findById(long id) throws DAOException;

	/**
	 * Creates an instance of the table computer in the database
	 * 
	 * @param obj
	 *            : the computer to create
	 * @throws DAOException
	 */
	void create(Computer computer) throws DAOException;

	/**
	 * Updates the instance of a computer in the database replaces the old
	 * attributes by the ones of the given parameter
	 * 
	 * @param obj
	 *            : the computer to modify
	 * @throws DAOException
	 */
	void update(Computer computer) throws DAOException;

	/**
	 * Deletes a computer instance in the database
	 * 
	 * @param id
	 *            : the ID of the computer to delete
	 * @throws DAOException
	 */
	void delete(long id) throws DAOException;

}
