package com.excilys.computerdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdb.model.Company;

/**
 * Converts a ResultSet to a Company object.
 * @author ecayez
 *
 */
public class MapperDaoCompany implements RowMapper<Company> {

  @Override
  public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
    Company company = new Company();
    company.setId(rs.getLong("id"));
    company.setName(rs.getString("name"));

    return company;
  }

}
