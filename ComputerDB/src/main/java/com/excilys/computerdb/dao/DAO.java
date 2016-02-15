package com.excilys.computerdb.dao;

import java.util.List;

import com.excilys.computerdb.exceptions.DAOException;

/**
 * Data access object, abstract class.
 * 
 * @author excilys
 * @param <T>
 *            : type of object and name of the table in the database : computer
 *            or company.
 */
public interface DAO<T> {
	/**
	 * Find a computer in the database.
	 * 
	 * @param id
	 *            : the ID of the computer
	 * @return : the computer corresponding to the ID in the database
	 */
	T findById(long id) throws DAOException;

	/**
	 * Finds all computers in the database.
	 * 
	 * @return : the list of all computers.
	 */
	List<T> findAll(int min, int max) throws DAOException;

}
