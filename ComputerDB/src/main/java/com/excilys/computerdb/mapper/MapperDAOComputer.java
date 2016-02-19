package com.excilys.computerdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.excilys.computerdb.dao.exceptions.DAOException;
import com.excilys.computerdb.mapper.exceptions.MapperException;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.validation.exceptions.DataException;

/**
 * Converts a ResultSet to a Computer object
 * 
 * @author ecayez
 *
 */
public class MapperDAOComputer {

	/**
	 * Converts the given ResultSet to a Computer
	 * 
	 * @param rs
	 *            : the ResultSet to convert
	 * @return the corresponding Computer
	 * @throws DAOException
	 */
	public static Computer computerMap(ResultSet rs) throws MapperException, SQLException {
		Computer computer = null;
		Company company = null;

		Timestamp introducedTmp = rs.getTimestamp("computer.introduced");
		LocalDate introduced = introducedTmp == null ? null : introducedTmp.toLocalDateTime().toLocalDate();
		Timestamp discontinuedTmp = rs.getTimestamp("computer.discontinued");
		LocalDate discontinued = discontinuedTmp == null ? null : discontinuedTmp.toLocalDateTime().toLocalDate();
		try {
			computer = new Computer();
			// Id and name
			computer.setId(rs.getLong("computer.id"));
			computer.setName(rs.getString("computer.name"));
			// dates
			computer.setIntroduced(introduced);
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
		} catch (DataException e) {
			throw new MapperException("Computer object was not found. Cause : \n\t" + e.getMessage());
		}
		return computer;
	}
}
