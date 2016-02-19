package com.excilys.computerdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.dao.CompanyDAO;
import com.excilys.computerdb.dao.exceptions.DAOException;
import com.excilys.computerdb.jdbc.ConnectionMySQL;
import com.excilys.computerdb.jdbc.exceptions.DAOConfigurationException;
import com.excilys.computerdb.mapper.MapperDAOCompany;
import com.excilys.computerdb.mapper.exceptions.MapperException;
import com.excilys.computerdb.model.Company;

public class CompanyDAOImpl implements CompanyDAO {

	public static final String FIND_ALL = "SELECT * FROM company";
	public static final String WHERE_ID = " WHERE id = ?";

	@Override
	public List<Company> findAll() {
		Company company = new Company();
		ResultSet resultSet = null;
		List<Company> companies = new ArrayList<>();

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				Statement statement = connect.createStatement();) {

			resultSet = statement.executeQuery(FIND_ALL);
			if (resultSet != null) {
				while (resultSet.next()) {
					company = MapperDAOCompany.companyMap(resultSet);
					companies.add(company);
				}
			}
		} catch (SQLException | DAOConfigurationException | MapperException e) {
			throw new DAOException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return companies;
	}

	@Override
	public Company findById(long id) {
		Company company = null;
		ResultSet resultSet = null;

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(FIND_ALL + WHERE_ID);) {

			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				company = MapperDAOCompany.companyMap(resultSet);
			}

		} catch (SQLException | DAOConfigurationException | MapperException e) {
			throw new DAOException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return company;
	}
}
