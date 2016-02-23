package com.excilys.computerdb.testdatabase;

import com.excilys.computerdb.jdbc.ConnectionMySql;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.SQLException;

public class DbTestConnector {

  private ConnectionMySql connectionDb;
  private IDatabaseTester databaseTester;
  private JdbcDataSource dataSource;

  /**
   * Initializes the data source.
   */
  public void initDataSource() {

    dataSource = new JdbcDataSource();
    dataSource.setURL(connectionDb.getUrl());
    dataSource.setUser(connectionDb.getUser());
    dataSource.setPassword(connectionDb.getPassword());
  }

  /**
   * Initializes the path to Sql shema.
   * 
   * @param pathToSqlSchema
   *          : the schema path to sql.
   */
  public void initSchema(String pathToSqlSchema) {

    try {
      RunScript.execute(connectionDb.getUrl(), connectionDb.getUser(), connectionDb.getPassword(),
          pathToSqlSchema, Charset.forName("UTF8"), false);
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }

  public void initConnection() {
    connectionDb = ConnectionMySql.getInstance();
  }

  /**
   * Setups the database.
   * 
   * @param dataSet
   *          : the data set.
   * @throws Exception
   *           : when there is a problem with the connection to the database.
   */
  public void cleanlyInsert(IDataSet dataSet) throws Exception {

    databaseTester = new JdbcDatabaseTester(connectionDb.getDriver(), connectionDb.getUrl(),
        connectionDb.getUser(),
        connectionDb.getPassword());
    databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
  }

  public IDataSet readDataSet(String pathToTheFile) throws Exception {

    return new FlatXmlDataSetBuilder().build(new File(pathToTheFile));
  }

  public IDatabaseTester getDatabaseTester() {

    return databaseTester;
  }
}
