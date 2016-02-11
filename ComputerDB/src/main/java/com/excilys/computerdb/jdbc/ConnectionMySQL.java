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

	private static final String PROPERTIES = "com/excilys/computerdb/jdbc/jdbc.properties";

	private static final String URL = "url";

	private static final String USER = "nomutilisateur";

	private static final String PASSWORD = "motdepasse";

	private static ConnectionMySQL connectionMySQL = new ConnectionMySQL();

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
		}
	}

	private ConnectionMySQL() {
	}

	public static ConnectionMySQL getInstance() {
		return connectionMySQL;
	}

	public static Connection getConnection() throws DAOConfigurationException {
		Connection connection = null;
		Properties properties = new Properties();
		String url = null;
		String user = null;
		String passwd = null;

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
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible de charger le fichier properties " + PROPERTIES, e);
		} finally {
			try {
				fProperties.close();
			} catch (IOException e) {
			}
		}
		// Connection to the database using the user's ID and password
		try {
			if (connection == null) {
				return connection = DriverManager.getConnection(url, user, passwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
