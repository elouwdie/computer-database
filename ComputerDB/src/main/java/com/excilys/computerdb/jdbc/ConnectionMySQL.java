package com.excilys.computerdb.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computerdb.jdbc.exceptions.DAOConfigurationException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * Connection to the database
 * 
 * @author ecayez
 * 
 */
public class ConnectionMySQL {

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

	private static ConnectionMySQL connectionMySQL;

	static {

		Properties properties = new Properties();
		String url = null;
		String user = null;
		String passwd = null;
		String driver = null;
		BoneCP connectionPool = null;

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
			throw new DAOConfigurationException("Erreur de configuration du pool de connexions.", e);
		}
		connectionMySQL = new ConnectionMySQL(connectionPool);
	}

	private ConnectionMySQL(BoneCP connectionPool) {
		this.connectionPool = connectionPool;
	}

	public static ConnectionMySQL getInstance() {
		return connectionMySQL;
	}

	public Connection getConnection() throws DAOConfigurationException {
		try {
			return connectionPool.getConnection();
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