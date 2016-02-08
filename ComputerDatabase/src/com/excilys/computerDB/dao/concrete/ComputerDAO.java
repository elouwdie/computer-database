package com.excilys.computerDB.dao.concrete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDB.dao.DAO;
import com.excilys.computerDB.model.Computer;

public class ComputerDAO extends DAO<Computer> {

	public Computer findById(long id) {
		// TODO Auto-generated method stub
		Computer computer = new Computer();

		Statement statement;
		try {
			statement = this.connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM computer WHERE id = " + id);
			
			if(resultSet.first()) {
				computer = new Computer(id, resultSet.getString("name"), resultSet.getString("introduced")
						, resultSet.getString("discontinued"), resultSet.getLong("company_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}

	public Computer create(Computer obj) {
		return null;
	}

	public Computer update(Computer obj) {
		return null;
	}

	public void delete(Computer obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Computer> findAll() {
		// TODO Auto-generated method stub
		Computer computer = new Computer();
		List<Computer> computers = new ArrayList<Computer>();

		Statement statement;
		try {
			statement = this.connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id, name  FROM computer");
			
			if(resultSet.first()) {
				resultSet.beforeFirst();
				
				while(resultSet.next()) {
					computer = new Computer(resultSet.getLong("id"), resultSet.getString("name"));
					computers.add(computer);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computers;
	}

}
