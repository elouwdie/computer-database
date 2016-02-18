package com.excilys.computerdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerdb.exceptions.DAOException;
import com.excilys.computerdb.model.Company;

/**
 * Converts a ResultSet to a Company object
 * 
 * @author ecayez
 *
 */
public class MapperCompany {

	/**
	 * Converts the given ResultSet to a Computer
	 * 
	 * @param rs
	 *            : the ResultSet to convert
	 * @return the corresponding Computer object
	 * @throws DAOException
	 */
	public static Company companyMap(ResultSet rs) throws DAOException {
		Company company = new Company();
		try {
			company.setId(rs.getLong("id"));
			company.setName(rs.getString("name"));
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return company;
	}

}
