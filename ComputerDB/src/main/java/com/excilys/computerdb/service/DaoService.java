package com.excilys.computerdb.service;

import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.model.Computer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DaoService {

  /**
   * Sets the statement adapted to the given computer.
   * @param computer : the computer to convert
   * @param statement : the statement to adapt.
   * @throws DaoException : when there is a SQLException.
   */
  public static void set(Computer computer, PreparedStatement statement) throws DaoException {

    try {
      statement.setString(1, computer.getName());
      if (computer.getIntroduced() != null) {
        statement.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced().atStartOfDay()));
      } else {
        statement.setTimestamp(2, null);
      }
      if (computer.getDiscontinued() != null) {
        statement.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued().atStartOfDay()));
      } else {
        statement.setTimestamp(3, null);
      }
      if (computer.getCompany() != null) {
        statement.setLong(4, computer.getCompany().getId());
      } else {
        statement.setObject(4, null);
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  /**
   * Closes the given resultset.
   * @param rs : the resultset to close.
   */
  public static void closeRs(ResultSet rs) {
    try {
      rs.close();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

}
