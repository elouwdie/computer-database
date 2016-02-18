package com.excilys.computerdb.mapper;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdb.cli.TraitementCLI;
import com.excilys.computerdb.dto.ComputerDTO;
import com.excilys.computerdb.exceptions.DAOException;
import com.excilys.computerdb.exceptions.DataException;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.CompanyService;
import com.excilys.computerdb.validation.DataVerification;

/**
 * Converts a ResultSet to a ComputerDTO object
 * 
 * @author ecayez
 *
 */
public class MapperComputerDTO {

	static Logger log;

	/**
	 * Converts the given ResultSet to a ComputerDTO
	 * 
	 * @param rs
	 *            : the ResultSet to convert
	 * @return the corresponding ComputerDTO
	 * @throws DAOException
	 */
	public static Computer DTOToComputer(ComputerDTO computerDTO) {
		log = LoggerFactory.getLogger(TraitementCLI.class);
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
			throw new DataException("Computer object wasn't created. Cause : \n\t" + e.getMessage());
		}
		return computer;
	}

}
