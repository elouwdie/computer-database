package com.excilys.computerdb.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.computerdb.dao.ComputerDao;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.mapper.MapperDaoComputer;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.impl.DaoService;

public class ComputerDaoImpl implements ComputerDao {

  public static final String SELECT_COUNT = "SELECT COUNT(*) FROM computer";
  public static final String SELECT_COUNT_JOIN = "SELECT COUNT(*) FROM computer LEFT JOIN company ON computer.company_id = company.id";
  public static final String WHERE_NAME = " WHERE computer.name like ?";
  public static final String WHERE_COMPANY = " WHERE company.name like ?";
  public static final String WHERE_ID = " WHERE computer.id = ?";
  public static final String SELECT =
      "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
  public static final String UPDATE =
      "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
  public static final String DELETE = "DELETE FROM computer WHERE id = ?";
  public static final String INSERT =
      "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
  public static final String OFFSET_LIMIT = " LIMIT ? OFFSET ?";

  private int nbComputers;

  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int getCount() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    nbComputers = jdbcTemplate.queryForObject(SELECT_COUNT, Integer.class);

    return nbComputers;
  }

  @Override
  public int getCountBy(EnumSearch search, String name) {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    if (search.equals(EnumSearch.NAME)) {
      nbComputers =
          jdbcTemplate.queryForObject(SELECT_COUNT + WHERE_NAME, Integer.class, "%" + name + "%");
    } else {
      nbComputers =
          jdbcTemplate.queryForObject(SELECT_COUNT_JOIN + WHERE_COMPANY, Integer.class, "%" + name + "%");
    }
    return nbComputers;
  }

  @Override
  public List<Computer> findAll(Page page) {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    List<Computer> computers =
        jdbcTemplate.query(SELECT + OFFSET_LIMIT, new MapperDaoComputer(), page.getLimit(),
            page.getStart());

    return computers;
  }

  @Override
  public Computer findById(long id) {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    Computer computer = (Computer) jdbcTemplate.queryForObject(
        SELECT + WHERE_ID, new MapperDaoComputer(), id);

    return computer;
  }

  @Override
  public List<Computer> findByName(EnumSearch search, String name, Page page) {

    String where = null;

    if (search.equals(EnumSearch.NAME)) {
      where = WHERE_NAME;
    } else {
      where = WHERE_COMPANY;
    }

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    List<Computer> computers =
        jdbcTemplate.query(SELECT + where + OFFSET_LIMIT, new MapperDaoComputer(), "%" + name + "%",
            page.getLimit(), page.getStart());

    return computers;
  }

  @Override
  public void create(Computer computer) {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    Object[] obj = DaoService.set(computer);
    jdbcTemplate.update(INSERT, obj);

  }

  @Override
  public void update(Computer computer) {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    Object[] obj = DaoService.set(computer);
    jdbcTemplate.update(UPDATE, obj, computer.getId());

  }

  @Override
  public void delete(long id) {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.update(DELETE, id);

  }
}