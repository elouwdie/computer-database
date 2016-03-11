package com.excilys.computerdb.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.computerdb.dao.CompanyDao;
import com.excilys.computerdb.mapper.MapperDaoCompany;
import com.excilys.computerdb.model.Company;

public class CompanyDaoImpl implements CompanyDao {

  public static final String FIND_ALL = "SELECT * FROM company";
  public static final String DELETE = "DELETE FROM company WHERE id = ?";
  public static final String WHERE_ID = " WHERE id = ?";

  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<Company> findAll() {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    List<Company> companies =
        jdbcTemplate.query(FIND_ALL, new MapperDaoCompany());

    return companies;
  }

  @Override
  public void delete(long id) {
    // throw new DaoException("nope");

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.update(DELETE, id);

  }

  @Override
  public Company findById(long id) {
    Company company = null;
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    if (id != 0) {
      company = (Company) jdbcTemplate.queryForObject(
          FIND_ALL + WHERE_ID, new MapperDaoCompany(), id);
    }

    return company;
  }
}