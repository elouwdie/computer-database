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
import com.excilys.computerdb.mapper.Mapper;
import com.excilys.computerdb.model.Company;

public class CompanyDAO implements DAO<Company> {

	
	public List<Company> findAll(int min, int max) {
		//TODO modifier
		Company company = new Company();
		List<Company> companies = new ArrayList<>();

		try (Connection connect = ConnectionMySQL.getInstance().getConnection(); Statement statement = connect.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT id, name  FROM company");

			if (resultSet != null) {
				while (resultSet.next()) {
					company = Mapper.companyMap(resultSet);
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

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement("SELECT * FROM company WHERE id = ?");) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet != null && resultSet.next()) {
				company = Mapper.companyMap(resultSet);
			}
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return company;
	}

}
