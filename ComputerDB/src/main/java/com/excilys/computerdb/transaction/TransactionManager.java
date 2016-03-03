package com.excilys.computerdb.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.computerdb.jdbc.ConnectionMySql;
import com.excilys.computerdb.transaction.exception.TransactionException;

/**
 * Manages the users transactions.
 * @author ecayez
 *
 */
public class TransactionManager {

  public static boolean isSessionOpened() {
    return ThreadLocalConnection.get() != null;
  }

  /**
   * returns the current connection.
   * @return : the current connection.
   * @throws TransactionException : if there is a connection problem.
   */
  public static Connection currentConnection() throws TransactionException {
    Connection conn = ThreadLocalConnection.get();
    if (conn == null) {
      throw new TransactionException("Session not open.");
    }
    return conn;
  }

  /**
   * Closes the current session.
   * @throws SQLException : if there is a connection problem.
   */
  public static void closeSession() throws SQLException {
    Connection conn = currentConnection();
    conn.close();
    conn = null;
    ThreadLocalConnection.set(null);
  }

  /**
   * Opens a new session.
   * @param isBeginTransaction : true if the transaction is already started,
   *          false if not.
   * @throws TransactionException : if there is a connection problem.
   * @throws SQLException : if there is a connection problem.
   */
  public static void openSession(boolean isBeginTransaction)
      throws TransactionException, SQLException {
    Connection conn = ThreadLocalConnection.get();
    if (conn != null) {
      throw new TransactionException("Session is already opened.");
    }
    Connection connection = ConnectionMySql.getInstance().getConnection();
    connection.setAutoCommit(isBeginTransaction);
    conn = connection;
    ThreadLocalConnection.set(conn);
  }

}
