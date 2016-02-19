package com.excilys.computerdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerdb.dao.exceptions.DAOException;
import com.excilys.computerdb.model.Company;

/**
 * Converts a ResultSet to a Company object
 * 
 * @author ecayez
 *
 */
public class MapperDAOCompany {

	/**
	 * Converts the given ResultSet to a Computer
	 * 
	 * @param rs
	 *            : the ResultSet to convert
	 * @return the corresponding Computer object
	 * @throws DAOException
	 */
	public static Company companyMap(ResultSet rs) throws SQLException {
		Company company = new Company();
		company.setId(rs.getLong("id"));
		company.setName(rs.getString("name"));

		return company;
	}

}
