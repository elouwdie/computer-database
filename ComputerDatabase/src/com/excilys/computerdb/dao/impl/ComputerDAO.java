package com.excilys.computerdb.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.dao.DAO;
import com.excilys.computerdb.model.Computer;

/**
 * Data access object for table computer in the computer-database
 * @author excilys
 *
 */
public class ComputerDAO extends DAO<Computer> {

	@Override
	public List<Computer> findAll() {
		Computer computer = new Computer();
		List<Computer> computers = new ArrayList<Computer>();

		Statement statement;
		try {
			statement = this.connect.createStatement();
			//SQL query
			ResultSet resultSet = statement.executeQuery("SELECT id, name  FROM computer");
			
			//Creation of the list
			if(resultSet.first()) {
				resultSet.beforeFirst();
				while(resultSet.next()) {
					computer = new Computer(resultSet.getLong("id"), resultSet.getString("name"));
					computers.add(computer);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
	
	@Override
	public Computer findById(long id) {
		Computer computer = new Computer();

		Statement statement;
		try {
			statement = this.connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM computer WHERE id = " + id);
			
			if(resultSet.first()) {
				computer = new Computer(id, resultSet.getString("name"), resultSet.getTimestamp("introduced")
						, resultSet.getTimestamp("discontinued"), resultSet.getLong("company_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public Computer findByName(String name) {
		Computer computer = new Computer();

		Statement statement;
		try {
			statement = this.connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM computer WHERE name = " + name);
			
			if(resultSet.first()) {
				computer = new Computer(resultSet.getLong("id"), name, resultSet.getTimestamp("introduced")
						, resultSet.getTimestamp("discontinued"), resultSet.getLong("company_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	/**
	 * Creates an instance of the table computer in the database
	 * @param obj : the computer to create
	 */
	public void create(Computer obj) {
		PreparedStatement statement = null;
		
		try {
			statement = this.connect.prepareStatement("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)");
			statement.setString(1, obj.getName());
			statement.setTimestamp(2, obj.getIntroduced());
			statement.setTimestamp(3, obj.getDiscontinued());
			statement.setLong(4, obj.getCompanyId());
	
			statement.executeUpdate();
			
			ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS id");
			if(resultSet.first()) {
				obj.setId(resultSet.getLong("id"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Updates the instance of a computer in the database
	 * replaces the old attributes by the ones of the given parameter
	 * @param obj : the computer to modify
	 */
	public void update(Computer obj) {
	       PreparedStatement statement = null;
	       try {
	           statement = connect.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");
	           statement.setString(1, obj.getName());
	           statement.setTimestamp(2, obj.getIntroduced());
	           statement.setTimestamp(3, obj.getDiscontinued());
	           statement.setLong(4, obj.getCompanyId());
	           statement.setLong(5, obj.getId());

	           statement.executeUpdate();
	       } catch (Exception e) {
	           throw new RuntimeException(e);
	       }
	}

	/**
	 * Deletes a computer instance in the database
	 * @param id : the ID of the computer to delete
	 */
	public void delete(long id) {
		Statement statement;
		try {
			statement = this.connect.createStatement();
			statement.executeUpdate("DELETE FROM computer WHERE id = " + id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
