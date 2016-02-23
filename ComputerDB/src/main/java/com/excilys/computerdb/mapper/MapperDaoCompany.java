package com.excilys.computerdb.mapper;

import com.excilys.computerdb.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Converts a ResultSet to a Company object.
 * 
 * @author ecayez
 *
 */
public class MapperDaoCompany {

  /**
   * Converts the given ResultSet to a Computer.
   * 
   * @param rs
   *          : the ResultSet to convert.
   * @return the corresponding Computer object.
   * @throws SQLException
   *           : if there is a problem with the resultSet.
   */
  public static Company companyMap(ResultSet rs) throws SQLException {
    Company company = new Company();
    company.setId(rs.getLong("id"));
    company.setName(rs.getString("name"));

    return company;
  }

}
