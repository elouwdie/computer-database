package com.excilys.computerdb.testconnectionbdd;

import com.excilys.computerdb.jdbc.ConnectionMySQL;

import junit.framework.TestCase;

public class TestInteractions extends TestCase {

	public void testConnection() {
		assertTrue(ConnectionMySQL.getInstance().getConnection() != null);
	}
}
