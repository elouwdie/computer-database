package com.excilys.computerdb.service;

import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.dao.impl.ComputerDaoImpl;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.transaction.TransactionManager;
import com.excilys.computerdb.transaction.exception.TransactionException;

import java.sql.SQLException;

public class ComputerService {
  private static ComputerDaoImpl computerDAO;

  /**
   * counts the number of computers in the database.
   * 
   * @return : the count of computers in the database.
   * @throws DaoException
   *           : when there is a problem in the database.
   */
  public static int getCount() throws DaoException {
    computerDAO = new ComputerDaoImpl();
    int count = 0;
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        count = computerDAO.getCount();
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
    return count;
  }

  /**
   * counts the number of computers with the given name / company name in the
   * database.
   * 
   * @param search
   *          : the type of search : name or company name.
   * @param name
   *          : the name to search.
   * @return : the count of computers with the given name / company name in the
   *         database.
   * @throws DaoException
   *           : when there is a problem in the database.
   */
  public static int getCountBy(EnumSearch search, String name) throws DaoException {
    computerDAO = new ComputerDaoImpl();
    int count = 0;
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        count = computerDAO.getCountBy(search, name);
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
    return count;
  }

  /**
   * Finds all computers with the specified name / company name.
   * 
   * @param search
   *          : the type of search.
   * @param name
   *          : the name to search.
   * @param page
   *          : the page to fill with the results.
   * @throws DaoException
   *           : problem in the database.
   */
  public static void findByName(EnumSearch search, String name, Page page) throws DaoException {
    computerDAO = new ComputerDaoImpl();
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        page.setComputers(computerDAO.findByName(search, name, page));
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
  }

  /**
   * Finds all computers in the database.
   * 
   * @param page
   *          : the page to fill with the results.
   * @throws DaoException
   *           : problem in the database.
   */
  public static void findAll(Page page) throws DaoException {
    computerDAO = new ComputerDaoImpl();
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        page.setComputers(computerDAO.findAll(page));
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
  }

  /**
   * Find a computer in the database.
   * 
   * @param id
   *          : the id of the computer to search.
   * @return : the computer with the corresponding id.
   * @throws DaoException
   *           : problem in the database.
   */
  public static Computer findById(long id) throws DaoException {
    computerDAO = new ComputerDaoImpl();
    Computer computer = null;
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        computer = computerDAO.findById(id);
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
    return computer;
  }

  /**
   * Creates a computer in the database.
   * 
   * @param computer
   *          : the computer to create.
   * @throws DaoException
   *           : problem in the database.
   */
  public static void create(Computer computer) throws DaoException {
    computerDAO = new ComputerDaoImpl();
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        computerDAO.create(computer);
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
  }

  /**
   * Updates a computer in the database with the given computer.
   * 
   * @param computer
   *          : the computer to update.
   * @throws DaoException
   *           : problem in the database.
   */
  public static void update(Computer computer) throws DaoException {
    computerDAO = new ComputerDaoImpl();
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        computerDAO.update(computer);
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
  }

  /**
   * Deletes a computer from the database.
   * 
   * @param id
   *          : the id of the computer to delete.
   * @throws DaoException
   *           : problem in the database.
   */
  public static void delete(long id) throws DaoException {
    computerDAO = new ComputerDaoImpl();
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        computerDAO.delete(id);
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
  }
}
