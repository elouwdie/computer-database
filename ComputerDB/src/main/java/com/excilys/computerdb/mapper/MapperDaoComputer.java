package com.excilys.computerdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;

/**
 * Converts a ResultSet to a Computer object.
 * @author ecayez
 *
 */
public class MapperDaoComputer implements RowMapper<Computer> {

  @Override
  public Computer mapRow(ResultSet rs, int rowId) throws SQLException {
    Company company = null;

    Timestamp introducedTmp = rs.getTimestamp("computer.introduced");
    LocalDate introduced =
        introducedTmp == null ? null : introducedTmp.toLocalDateTime().toLocalDate();
    Timestamp discontinuedTmp = rs.getTimestamp("computer.discontinued");
    LocalDate discontinued =
        discontinuedTmp == null ? null : discontinuedTmp.toLocalDateTime().toLocalDate();
    Computer computer = new Computer();
    // Id and name
    computer.setId(rs.getLong("computer.id"));
    computer.setName(rs.getString("computer.name"));
    // dates
    computer.setIntroduced(introduced);
    computer.setDiscontinued(discontinued);
    // company ID
    Long companyId = rs.getLong("computer.company_id");
    if (companyId != 0) {
      String companyName = rs.getString("company.name");
      company = new Company();
      company.setId(companyId);
      company.setName(companyName);
    }
    computer.setCompany(company);
    return computer;
  }
}
