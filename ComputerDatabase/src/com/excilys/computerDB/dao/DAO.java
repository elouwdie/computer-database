package com.excilys.computerDB.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerDB.jdbc.ConnectionMySQL;

public abstract class DAO<T> {

	public Connection connect = ConnectionMySQL.getInstance();
	
	public abstract T findById(long id);

	public abstract List<T> findAll();
	
	public abstract T create(T obj);
	
	public abstract T update(T obj);
	
	public abstract void delete(T obj);
	
}
