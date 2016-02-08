package com.excilys.computerDB.dao.concrete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDB.dao.DAO;
import com.excilys.computerDB.model.Company;
import com.excilys.computerDB.model.Computer;

public class CompanyDAO extends DAO<Company>{

	@Override
	public Company findById(long id) {
		// TODO Auto-generated method stub
				Company company = new Company();

				Statement statement;
				try {
					statement = this.connect.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM company WHERE id = " + id);
					
					if(resultSet.first()) {
						company = new Company(id, resultSet.getString("name"));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return company;
	}

	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;
	}

	@Override
	public Company create(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company update(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company obj) {
		// TODO Auto-generated method stub
		
	}

}
