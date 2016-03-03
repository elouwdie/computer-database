package com.excilys.computerdb.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.excilys.computerdb.jdbc.exception.DaoConfigurationException;

/**
 * Connection to the database.
 * @author ecayez
 */
public class ConnectionMySql {

  private static DataSource dataSource;

  private static ConnectionMySql connectionMySQL = new ConnectionMySql();

  /**
   * Allocates the given connectionPool.
   */
  private ConnectionMySql() {
  }

  public static DataSource getDataSource() {
    return dataSource;
  }

  public static void setDataSource(DataSource dataSource) {
    ConnectionMySql.dataSource = dataSource;
  }

  public static ConnectionMySql getInstance() {
    return connectionMySQL;
  }

  /**
   * Returns a connection to the database.
   * @return : the connection to the database.
   * @throws DaoConfigurationException : config not good.
   */
  public Connection getConnection() throws DaoConfigurationException {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      throw new DaoConfigurationException(e);
    }
  }
}