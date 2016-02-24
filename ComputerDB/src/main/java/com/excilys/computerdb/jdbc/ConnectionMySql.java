package com.excilys.computerdb.jdbc;

import com.excilys.computerdb.jdbc.exception.DaoConfigurationException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection to the database.
 * @author ecayez
 */
public class ConnectionMySql {

  private static final String PROPERTIES = "jdbc.properties";
  private static final String URL = "url";
  private static final String USER = "nomutilisateur";
  private static final String PASSWORD = "motdepasse";
  private static final String DRIVER = "driver";

  private BoneCP connectionPool = null;

  private String url;
  private String user;
  private String password;
  private String driver;

  private static ConnectionMySql connectionMySQL;

  static {

    Properties properties = new Properties();
    String url = null;
    String user = null;
    String passwd = null;
    String driver = null;
    BoneCP connectionPool = null;

    // Loading the .properties file containing user's ID and password
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream fproperties = classLoader.getResourceAsStream(PROPERTIES);
    if (fproperties == null) {
      throw new DaoConfigurationException(
          "Le fichier properties " + PROPERTIES + " est introuvable.");
    }
    try {
      properties.load(fproperties);
      url = properties.getProperty(URL);
      user = properties.getProperty(USER);
      passwd = properties.getProperty(PASSWORD);
      driver = properties.getProperty(DRIVER);

    } catch (IOException e) {
      throw new DaoConfigurationException(
          "Impossible de charger le fichier properties " + PROPERTIES, e);
    } finally {
      try {
        fproperties.close();
      } catch (IOException e) {
        throw new DaoConfigurationException(
            "Impossible de fermer le fichier properties " + PROPERTIES, e);
      }
    }

    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      throw new DaoConfigurationException("Le driver est introuvable dans le classpath.", e);
    }

    try {
      BoneCPConfig config = new BoneCPConfig();

      config.setJdbcUrl(url);
      config.setUsername(user);
      config.setPassword(passwd);
      config.setMinConnectionsPerPartition(5);
      config.setMaxConnectionsPerPartition(10);
      config.setPartitionCount(2);

      connectionPool = new BoneCP(config);

    } catch (SQLException e) {
      throw new DaoConfigurationException("Erreur de configuration du pool de connexions.", e);
    }
    connectionMySQL = new ConnectionMySql(connectionPool);
  }

  /**
   * Allocates the given connectionPool.
   * @param connectionPool : the connectionPool to allocate.
   */
  private ConnectionMySql(BoneCP connectionPool) {
    this.connectionPool = connectionPool;
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
      return connectionPool.getConnection();
    } catch (SQLException e) {
      throw new DaoConfigurationException(e);
    }
  }

  public String getUrl() {
    return url;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public String getDriver() {
    return driver;
  }
}