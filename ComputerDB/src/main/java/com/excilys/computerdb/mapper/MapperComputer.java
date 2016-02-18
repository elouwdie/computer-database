package com.excilys.computerdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.excilys.computerdb.exceptions.DAOException;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;

/**
 * Converts a ResultSet to a Computer object
 * 
 * @author ecayez
 *
 */
public class MapperComputer {

	/**
	 * Converts the given ResultSet to a Computer
	 * @param rs
	 *            : the ResultSet to convert
	 * @return the corresponding Computer
	 * @throws DAOException
	 */
	public static Computer computerMap(ResultSet rs) throws DAOException {
		Computer computer = new Computer();
		Company company = null;
		try {
			// Id and name
			computer.setId(rs.getLong("computer.id"));
			computer.setName(rs.getString("computer.name"));
			// dates
			Timestamp introducedTmp = rs.getTimestamp("computer.introduced");
			LocalDate introduced = introducedTmp == null ? null : introducedTmp.toLocalDateTime().toLocalDate();
			computer.setIntroduced(introduced);
			Timestamp discontinuedTmp = rs.getTimestamp("computer.discontinued");
			LocalDate discontinued = discontinuedTmp == null ? null : discontinuedTmp.toLocalDateTime().toLocalDate();
			computer.setDiscontinued(discontinued);
			// company ID
			Long cId = rs.getLong("computer.company_id");
			if (cId != 0) {
				Long companyId = rs.getLong("company.id");
				String companyName = rs.getString("company.name");
				company = new Company();
				company.setId(companyId);
				company.setName(companyName);
			}
			computer.setCompany(company);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return computer;
	}
}
