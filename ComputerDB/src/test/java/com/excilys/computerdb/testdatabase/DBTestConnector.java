package com.excilys.computerdb.testdatabase;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.SQLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

import com.excilys.computerdb.jdbc.ConnectionMySQL;

public class DBTestConnector {

	private ConnectionMySQL connectionDB;
	private IDatabaseTester databaseTester;
	private JdbcDataSource dataSource;

	public void initDataSource() {

		dataSource = new JdbcDataSource();
		dataSource.setURL(connectionDB.getUrl());
		dataSource.setUser(connectionDB.getUser());
		dataSource.setPassword(connectionDB.getPassword());
	}

	public void initSchema(String pathToSqlSchema) {

		try {
			RunScript.execute(connectionDB.getUrl(), connectionDB.getUser(), connectionDB.getPassword(),
					pathToSqlSchema, Charset.forName("UTF8"), false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void initConnection() {
		connectionDB = ConnectionMySQL.getInstance();
	}

	public void cleanlyInsert(IDataSet dataSet) throws Exception {

		databaseTester = new JdbcDatabaseTester(connectionDB.getDriver(), connectionDB.getUrl(), connectionDB.getUser(),
				connectionDB.getPassword());
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
