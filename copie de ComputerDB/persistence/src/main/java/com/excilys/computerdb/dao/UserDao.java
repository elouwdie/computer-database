package com.excilys.computerdb.dao;

import com.excilys.computerdb.model.User;

public interface UserDao {

  /**
   * Searches a user by his name.
   * @param username : the name of the user to search.
   * @return : the user found.
   */
    User findByUserName(String username);

}