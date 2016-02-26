package com.excilys.computerdb.service;

import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.dao.impl.CompanyDaoImpl;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.transaction.TransactionManager;
import com.excilys.computerdb.transaction.exception.TransactionException;

import java.sql.SQLException;
import java.util.List;

public class CompanyService {
  private static CompanyDaoImpl companyDAO;

  /**
   * Finds the list of companies in the database.
   * @return the list of companies.
   * @throws DaoException : problem in the database.
   */
  public static List<Company> findAll() throws DaoException {
    companyDAO = new CompanyDaoImpl();
    List<Company> companies = null;
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        companies = companyDAO.findAll();
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
    return companies;
  }

  /**
   * Finds a company in the database.
   * @param id : the id of the company to find.
   * @return : the corresponding company.
   * @throws DaoException : problem in the database.
   */
  public static Company findById(long id) throws DaoException {
    companyDAO = new CompanyDaoImpl();
    Company company = null;
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        company = companyDAO.findById(id);
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
    return company;
  }

  /**
   * Deletes a company in the database, and every computer related to this
   * company.
   * @param id : the id of the company to delete.
   */
  public static void delete(long id) {
    companyDAO = new CompanyDaoImpl();
    if (!TransactionManager.isSessionOpened()) {
      try {
        TransactionManager.openSession(false);
        companyDAO.delete(id);
        TransactionManager.closeSession();
      } catch (TransactionException | SQLException e) {
        throw new DaoException(e.getMessage());
      }
    }
  }
}
