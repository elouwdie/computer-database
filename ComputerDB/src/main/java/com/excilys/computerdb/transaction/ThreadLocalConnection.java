package com.excilys.computerdb.transaction;

import java.sql.Connection;

public class ThreadLocalConnection {

  public static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL =
      new ThreadLocal<Connection>();

  /**
   * Gives the current connection to the Local Thread.
   * @param connection : the current connection.
   */
  public static void set(Connection connection) {
    CONNECTION_THREAD_LOCAL.set(connection);
  }

  /**
   * Removes the Local Thread.
   */
  public static void unset() {
    CONNECTION_THREAD_LOCAL.remove();
  }

  /**
   * Returns the Local Thread.
   * @return : the Local Thread.
   */
  public static Connection get() {
    return CONNECTION_THREAD_LOCAL.get();
  }

}
