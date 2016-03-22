package com.excilys.computerdb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.dao.impl.CompanyDaoImpl;
import com.excilys.computerdb.dao.impl.ComputerDaoImpl;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.service.CompanyService;

@Transactional
public class CompanyServiceImpl implements CompanyService {

  @Autowired
  private CompanyDaoImpl companyDAO;
  @Autowired
  private ComputerDaoImpl computerDAO;

  @Override
  public List<Company> findAll() {
    List<Company> companies = companyDAO.findAll();
    return companies;
  }

  @Override
  public Company findById(long id) {
    Company company = companyDAO.findById(id);
    return company;
  }

  @Override
  public void delete(long id) {
    computerDAO.deleteByCompany(id);
    companyDAO.delete(id);
  }
}