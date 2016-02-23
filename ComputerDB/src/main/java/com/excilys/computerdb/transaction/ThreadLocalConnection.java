package com.excilys.computerdb.transaction;

import java.sql.Connection;

public class ThreadLocalConnection {

  public static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

  public static void set(Connection connection) {
    connectionThreadLocal.set(connection);
  }

  public static void unset() {
    connectionThreadLocal.remove();
  }

  public static Connection get() {
    return connectionThreadLocal.get();
  }

}
