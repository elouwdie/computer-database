package com.excilys.computerdb.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.dao.impl.CompanyDaoImpl;
import com.excilys.computerdb.dao.impl.ComputerDaoImpl;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {

  private CompanyDaoImpl companyDAO;
  private ComputerDaoImpl computerDAO;

  public void setCompanyDAO(CompanyDaoImpl companyDAO) {
    this.companyDAO = companyDAO;
  }

  public void setComputerDAO(ComputerDaoImpl computerDAO) {
    this.computerDAO = computerDAO;
  }

  @Override
  @Transactional
  public List<Company> findAll() {
    List<Company> companies = companyDAO.findAll();
    return companies;
  }

  @Override
  @Transactional
  public Company findById(long id) {
    Company company = companyDAO.findById(id);
    return company;
  }

  @Override
  @Transactional
  public void delete(long id) {
    computerDAO.deleteByCompany(id);
    companyDAO.delete(id);
  }
}