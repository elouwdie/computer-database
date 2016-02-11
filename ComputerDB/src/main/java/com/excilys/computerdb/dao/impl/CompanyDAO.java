package com.excilys.computerdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.dao.DAO;
import com.excilys.computerdb.exceptions.DAOException;
import com.excilys.computerdb.jdbc.ConnectionMySQL;
import com.excilys.computerdb.model.Company;

public class CompanyDAO implements DAO<Company> {

	@Override
	public List<Company> findAll() {
		Company company = new Company();
		List<Company> companies = new ArrayList<>();

		try (Connection connect = ConnectionMySQL.getConnection(); Statement statement = connect.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT id, name  FROM company");

			if (resultSet.first()) {
				resultSet.beforeFirst();

				while (resultSet.next()) {
					company = new Company(resultSet.getLong("id"), resultSet.getString("name"));
					companies.add(company);
				}
			}
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return companies;
	}

	@Override
	public Company findById(long id) {
		Company company = null;

		try (Connection connect = ConnectionMySQL.getConnection();
				PreparedStatement statement = connect.prepareStatement("SELECT * FROM company WHERE id = ?");) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				company = new Company(id, resultSet.getString("name"));
			}
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return company;
	}

	@Override
	public boolean exists(Company company) {
		// TODO Auto-generated method stub
		return false;
	}
}
