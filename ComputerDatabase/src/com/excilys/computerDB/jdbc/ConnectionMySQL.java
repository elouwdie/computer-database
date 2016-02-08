package com.excilys.computerDB.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

	private static String url = "jdbc:mysql://localhost:3306/computer-database-db";
	
	private static String user = "admincdb";
	
	private static String password = "qwerty1234";
	
	private static Connection connection;
	
	public static Connection getInstance() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(connection == null) {
				connection = DriverManager.getConnection(url, user, password);
			}
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection() {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch(Exception e) {
			
		}
	}
}
