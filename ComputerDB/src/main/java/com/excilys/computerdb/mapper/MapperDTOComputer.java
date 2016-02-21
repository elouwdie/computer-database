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
			computer.setId(computerDTO.getId());
			computer.setName(computerDTO.getName());
			computer.setIntroduced(intro);
			computer.setDiscontinued(discont);
			computer.setCompany(CompanyService.findById(computerDTO.getCompanyId()));
		} catch (DataException e) {
			throw new MapperException("Computer object wasn't created. Cause : \n\t" + e.getMessage());
		}
		return computer;
	}
	
	public static ComputerDTO computerToDTO(Computer computer) throws MapperException {
		ComputerDTO computerDTO = new ComputerDTO();
		String introduced = null;
		String discontinued = null;
		long companyId = 0;
		
		if(computer.getIntroduced() != null) {
			introduced = computer.getIntroduced().toString();
		}
		if(computer.getDiscontinued() != null) {
			discontinued = computer.getDiscontinued().toString();
		}
		if(computer.getCompany() != null) {
			companyId = computer.getCompany().getId();
		}
		
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		computerDTO.setIntroduced(introduced);
		computerDTO.setDiscontinued(discontinued);
		computerDTO.setCompanyId(companyId);
		
		return computerDTO;
	}
}
