package com.excilys.computerdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.excilys.computerdb.dao.impl.CompanyDAO;
import com.excilys.computerdb.dto.ComputerDTO;
import com.excilys.computerdb.exceptions.DAOException;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;

public class Mapper {

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
		return computer; // TODO
	}

	public static Computer simpleComputerMap(ResultSet rs) throws DAOException {
		Computer computer = new Computer();
		try {
			// Id and name
			computer.setId(rs.getLong("id"));
			computer.setName(rs.getString("name"));
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return computer; // TODO
	}

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

	public static Computer DTOToComputer(ComputerDTO computerDTO) {
		Computer computer = new Computer();
		CompanyDAO companyDAO = new CompanyDAO();
		
		computer.setName(computerDTO.getName());
		if(computerDTO.getIntroduced() != null) {
			computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced()));
		}
		if(computerDTO.getDiscontinued() != null) {
			computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued()));
		}
		computer.setCompany(companyDAO.findById(computerDTO.getCompany()));
		return computer;
	}

}
