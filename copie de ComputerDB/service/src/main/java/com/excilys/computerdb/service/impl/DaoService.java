package com.excilys.computerdb.service.impl;

import java.sql.Timestamp;

import com.excilys.computerdb.model.Computer;

public class DaoService {

  /**
   * Returns a tab of objects adapted to the given computer.
   * @param computer : the computer to convert
   * @return : a tab of objects
   */
  public static Object[] set(Computer computer) {
    Object[] objects = new Object[4];

    objects[0] = computer.getName();
    if (computer.getIntroduced() != null) {
      objects[1] = Timestamp.valueOf(computer.getIntroduced().atStartOfDay());
    } else {
      objects[1] = null;
    }
    if (computer.getDiscontinued() != null) {
      objects[2] = Timestamp.valueOf(computer.getDiscontinued().atStartOfDay());
    } else {
      objects[2] = null;
    }
    if (computer.getCompany() != null) {
      objects[3] = computer.getCompany().getId();
    } else {
      objects[3] = null;
    }
    return objects;
  }
}
