package com.excilys.computerdb.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.dao.impl.ComputerDaoImpl;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.ComputerService;

public class ComputerServiceImpl implements ComputerService {

  private ComputerDaoImpl computerDAO;

  public void setComputerDAO(ComputerDaoImpl computerDAO) {
    this.computerDAO = computerDAO;
  }

  @Override
  @Transactional
  public int getCount() {
    int count = computerDAO.getCount();
    return count;
  }

  @Override
  @Transactional
  public int getCountBy(EnumSearch search, String name) throws DaoException {
    int count = computerDAO.getCountBy(search, name);
    return count;
  }

  @Override
  @Transactional
  public void findByName(EnumSearch search, String name, Page page) throws DaoException {
    page.setComputers(computerDAO.findByName(search, name, page));
  }

  @Override
  @Transactional
  public void findAll(Page page) throws DaoException {
    page.setComputers(computerDAO.findAll(page));
  }

  @Override
  @Transactional
  public Computer findById(long id) throws DaoException {
    Computer computer = computerDAO.findById(id);
    return computer;
  }

  @Override
  @Transactional
  public void create(Computer computer) throws DaoException {
    computerDAO.create(computer);
  }

  @Override
  @Transactional
  public void update(Computer computer) throws DaoException {
    computerDAO.update(computer);
  }

  @Override
  @Transactional
  public void delete(long id) throws DaoException {
    computerDAO.delete(id);
  }
}
