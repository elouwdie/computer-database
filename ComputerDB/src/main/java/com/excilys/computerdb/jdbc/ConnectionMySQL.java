package com.excilys.computerdb.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computerdb.exceptions.DAOConfigurationException;

/**
 * Connection to the database
 * 
 * @author excilys
 * 
 */
public class ConnectionMySQL {

	private static final String PROPERTIES = "jdbc.properties";

	private static final String URL = "url";

	private static final String USER = "nomutilisateur";

	private static final String PASSWORD = "motdepasse";

	private static final String DRIVER = "driver";

	private String url;
	private String user;
	private String password;
	private String driver;

	private static ConnectionMySQL connectionMySQL;

	static {

		Properties properties = new Properties();
		String url = null;
		String user = null;
		String passwd = null;
		String driver = null;

		// Loading the .properties file containing user's ID and password
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fProperties = classLoader.getResourceAsStream(PROPERTIES);
		if (fProperties == null) {
			throw new DAOConfigurationException("Le fichier properties " + PROPERTIES + " est introuvable.");
		}
		try {
			properties.load(fProperties);
			url = properties.getProperty(URL);
			user = properties.getProperty(USER);
			passwd = properties.getProperty(PASSWORD);
			driver = properties.getProperty(DRIVER);

		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible de charger le fichier properties " + PROPERTIES, e);
		} finally {
			try {
				fProperties.close();
			} catch (IOException e) {
			}
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
		}
		connectionMySQL = new ConnectionMySQL(url, user, passwd, driver);
	}

	private ConnectionMySQL(String url, String user, String password, String driver) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.driver = driver;
	}

	public static ConnectionMySQL getInstance() {
		return connectionMySQL;
	}

	public Connection getConnection() throws DAOConfigurationException {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DAOConfigurationException(e);
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
