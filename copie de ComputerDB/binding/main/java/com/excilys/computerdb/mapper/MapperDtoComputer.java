package com.excilys.computerdb.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.dto.ComputerDto;
import com.excilys.computerdb.mapper.exception.MapperException;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.CompanyService;
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
   * @param companyService : the company service, finds a company with an id.
   * @param langue : the locale language.
   * @return the corresponding Computer.
   * @throws MapperException : when the data are not correct.
   */
  public static Computer dtoToComputer(ComputerDto computerDto, CompanyService companyService,
      String langue) throws MapperException {
    Computer computer = null;
    LocalDate intro = null;
    LocalDate discont = null;

    try {
      if (!computerDto.getIntroduced().isEmpty()) {
        if (langue.equals("fr")) {
          intro = MapperStringDate.frenchDateOf(computerDto.getIntroduced());
        } else {
          intro = MapperStringDate.englishDateOf(computerDto.getIntroduced());
        }
      }
      if (!computerDto.getDiscontinued().isEmpty()) {
        if (langue.equals("fr")) {
          discont = MapperStringDate.frenchDateOf(computerDto.getDiscontinued());
        } else {
          discont = MapperStringDate.englishDateOf(computerDto.getDiscontinued());
        }
      }
      DataVerification.areDatesOk(intro, discont);
      computer = new Computer();
      computer.setId(computerDto.getId());
      computer.setName(computerDto.getName());
      computer.setIntroduced(intro);
      computer.setDiscontinued(discont);
      computer.setCompany(companyService.findById(computerDto.getCompanyId()));
    } catch (DataException | DateTimeParseException e) {
      throw new MapperException("Computer object wasn't created. Cause : \n\t" + e.getMessage());
    }
    return computer;
  }

  /**
   * Translates a list of computers into a list of computerDtos.
   * @param computers : the list to translate.
   * @param language : the locale language.
   * @param companyService : the company service, finds a company with an id.
   * @return : the list of computerDtos.
   */
  public static List<ComputerDto> computersToDtos(List<Computer> computers,
      CompanyService companyService, String language) {
    List<ComputerDto> computersDto = new ArrayList<>();
    for (Computer computer : computers) {
      computersDto.add(computerToDto(computer, companyService, language));
    }
    return computersDto;
  }

  /**
   * Converts the given Computer to a ComputerDto.
   * @param computer : the Computer to convert.
   * @param language : the locale language.
   * @param companyService : the company service, finds a company with an id.
   * @return the corresponding ComputerDto.
   * @throws MapperException : when the data are not correct.
   */
  public static ComputerDto computerToDto(Computer computer, CompanyService companyService,
      String language) {
    String introduced = null;
    String discontinued = null;
    String companyName = null;
    long companyId = 0;

    if (computer.getIntroduced() != null) {
      if (language.equals("fr")) {
        introduced = MapperStringDate.frenchStringOf(computer.getIntroduced());
      } else {
        introduced = MapperStringDate.englishStringOf(computer.getIntroduced());
      }
    }
    if (computer.getDiscontinued() != null) {
      if (language.equals("fr")) {
        discontinued = MapperStringDate.frenchStringOf(computer.getDiscontinued());
      } else {
        discontinued = MapperStringDate.englishStringOf(computer.getDiscontinued());
      }
    }
    if (computer.getCompany() != null) {
      companyId = computer.getCompany().getId();
      companyName = companyService.findById(computer.getCompany().getId()).getName();
    }

    ComputerDto computerDto = new ComputerDto();
    computerDto.setId(computer.getId());
    computerDto.setName(computer.getName());
    computerDto.setIntroduced(introduced);
    computerDto.setDiscontinued(discontinued);
    computerDto.setCompanyId(companyId);
    computerDto.setCompanyName(companyName);

    return computerDto;
  }
}