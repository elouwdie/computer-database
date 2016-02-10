package com.excilys.computerdb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.dao.DAO;
import com.excilys.computerdb.model.Company;

public class CompanyDAO extends DAO<Company>{

	@Override
	public List<Company> findAll() {
		Company company = new Company();
		List<Company> companies = new ArrayList<Company>();

		Statement statement;
		try {
			statement = this.connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id, name  FROM company");
			
			if(resultSet.first()) {
				resultSet.beforeFirst();
				
				while(resultSet.next()) {
					company = new Company(resultSet.getLong("id"), resultSet.getString("name"));
					companies.add(company);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
	
	@Override
	public Company findById(long id) {
				Company company = new Company();

				Statement statement;
				try {
					statement = this.connect.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM company WHERE id = " + id);
					
					if(resultSet.first()) {
						company = new Company(id, resultSet.getString("name"));
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return company;
	}

	@Override
	public Company findByName(String name) {
		Company company = new Company();

		Statement statement;
		try {
			statement = this.connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM company WHERE name = " + name);
			
			if(resultSet.first()) {
				company = new Company(resultSet.getLong("id"), name);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}	
}
