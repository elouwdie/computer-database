package com.excilys.computerdb.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

	private static Connection connection;

	private static ConnectionMySQL connectionMySQL = new ConnectionMySQL();

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private ConnectionMySQL() {
		Properties properties = new Properties();
		String url = null;
		String user = null;
		String passwd = null;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fProperties = classLoader.getResourceAsStream(PROPERTIES);
		try {
			properties.load(fProperties);
			System.out.println("okkkk");
			url = properties.getProperty(URL);
			user = properties.getProperty(USER);
			passwd = properties.getProperty(PASSWORD);
		} catch (IOException e) {

		} finally {
			try {
				fProperties.close();
			} catch (IOException e) {
			}
		}
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(url, user, passwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionMySQL getInstance() {
		return connectionMySQL;
	}

	public static Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
