package com.excilys.computerdb.mapper;

import java.time.LocalDate;

import com.excilys.computerdb.dao.exceptions.DAOException;
import com.excilys.computerdb.dto.ComputerDTO;
import com.excilys.computerdb.mapper.exceptions.MapperException;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.CompanyService;
import com.excilys.computerdb.validation.DataVerification;
import com.excilys.computerdb.validation.exceptions.DataException;

/**
 * Converts a ResultSet to a ComputerDTO object
 * 
 * @author ecayez
 *
 */
public class MapperDTOComputer {

	/**
	 * Converts the given ResultSet to a ComputerDTO
	 * 
	 * @param rs
	 *            : the ResultSet to convert
	 * @return the corresponding ComputerDTO
	 * @throws DAOException
	 */
	public static Computer DTOToComputer(ComputerDTO computerDTO) throws MapperException {
		Computer computer = null;
		LocalDate intro = null;
		LocalDate discont = null;

		if (computerDTO.getIntroduced() != null) {
			intro = LocalDate.parse(computerDTO.getIntroduced());
		}
		if (computerDTO.getDiscontinued() != null) {
			discont = LocalDate.parse(computerDTO.getDiscontinued());
		}
		try {
			DataVerification.areDatesOk(intro, discont);
			computer = new Computer();
			computer.setName(computerDTO.getName());
			computer.setIntroduced(intro);
			computer.setDiscontinued(discont);
			computer.setCompany(CompanyService.findById(computerDTO.getCompany()));
		} catch (DataException e) {
			throw new MapperException("Computer object wasn't created. Cause : \n\t" + e.getMessage());
		}
		return computer;
	}
}
