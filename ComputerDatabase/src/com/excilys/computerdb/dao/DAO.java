package com.excilys.computerdb.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerdb.jdbc.ConnectionMySQL;

/**
 * Data access object, abstract class. 
 * @author excilys
 * @param <T> : type of object and name of the table in the database : computer or company.
 */
public abstract class DAO<T> {

	/**
	 * get a connection in the database
	 */
	public Connection connect = ConnectionMySQL.getConnection();

	/**
	 * Find a computer in the database.
	 * @param id : the ID of the computer 
	 * @return : the computer corresponding to the ID in the database
	 */
	public abstract T findById(long id);
	
	/**
	 * Find a computer in the database.
	 * @param name : the name of the computer
	 * @return : the computer corresponding to the ID in the database.
	 * If there is many computers corresponding to the name, returns the first one.
	 */
	public abstract T findByName(String name);

	/**
	 * Finds all computers in the database. 
	 * @return : the list of all computers.
	 */
	public abstract List<T> findAll();
	
}
