package com.excilys.computerdb.mapper;

import java.time.LocalDate;

import com.excilys.computerdb.dto.ComputerDto;
import com.excilys.computerdb.mapper.exception.MapperException;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.validation.DataVerification;
import com.excilys.computerdb.validation.exception.DataException;

/**
 * Converts a ResultSet to a ComputerDTO object.
 * @author ecayez
 *
 */
public class MapperDtoComputer {

  /**
   * Converts the given ComputerDto to a Computer.
   * @param computerDto : the ComputerDto to convert.
   * @return the corresponding Computer.
   * @throws MapperException : when the data are not correct.
   */
  public static Computer dtoToComputer(ComputerDto computerDto) throws MapperException {
    Computer computer = null;
    LocalDate intro = null;
    LocalDate discont = null;

    if (computerDto.getIntroduced() != null) {
      intro = LocalDate.parse(computerDto.getIntroduced());
    }
    if (computerDto.getDiscontinued() != null) {
      discont = LocalDate.parse(computerDto.getDiscontinued());
    }
    try {
      DataVerification.areDatesOk(intro, discont);
      computer = new Computer();
      computer.setId(computerDto.getId());
      computer.setName(computerDto.getName());
      computer.setIntroduced(intro);
      computer.setDiscontinued(discont);
    } catch (DataException e) {
      throw new MapperException("Computer object wasn't created. Cause : \n\t" + e.getMessage());
    }
    return computer;
  }

  /**
   * Converts the given Computer to a ComputerDto.
   * @param computer : the Computer to convert.
   * @return the corresponding ComputerDto.
   * @throws MapperException : when the data are not correct.
   */
  public static ComputerDto computerToDto(Computer computer) throws MapperException {
    String introduced = null;
    String discontinued = null;
    long companyId = 0;

    if (computer.getIntroduced() != null) {
      introduced = computer.getIntroduced().toString();
    }
    if (computer.getDiscontinued() != null) {
      discontinued = computer.getDiscontinued().toString();
    }
    if (computer.getCompany() != null) {
      companyId = computer.getCompany().getId();
    }

    ComputerDto computerDto = new ComputerDto();
    computerDto.setId(computer.getId());
    computerDto.setName(computer.getName());
    computerDto.setIntroduced(introduced);
    computerDto.setDiscontinued(discontinued);
    computerDto.setCompanyId(companyId);

    return computerDto;
  }
}
