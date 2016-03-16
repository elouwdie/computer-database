package com.excilys.computerdb.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.computerdb.dao.ComputerDao;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.model.QCompany;
import com.excilys.computerdb.model.QComputer;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.impl.DaoService;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ComputerDaoImpl implements ComputerDao {

  public static final String SELECT_COUNT = "SELECT COUNT(*) FROM computer";
  public static final String JOIN_COMPANY =
      " LEFT JOIN company ON computer.company_id = company.id";
  public static final String WHERE_NAME = " WHERE computer.name like ?";
  public static final String WHERE_COMPANY = " WHERE company.name like ?";
  public static final String WHERE_ID = " WHERE computer.id = ?";
  public static final String SELECT =
      "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
  public static final String UPDATE =
      "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
  public static final String DELETE = "DELETE FROM computer WHERE id = ?";
  public static final String DELETE_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?";
  public static final String INSERT =
      "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
  public static final String OFFSET_LIMIT = " LIMIT ? OFFSET ?";

  private int nbComputers;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private DataSource dataSource;

  @Override
  public int getCount() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QComputer computer = QComputer.computer;
    nbComputers = (int) queryFactory.selectFrom(computer).fetchCount();
    return nbComputers;
  }

  @Override
  public int getCountBy(EnumSearch search, String name) {

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QComputer computer = QComputer.computer;
    QCompany company = QCompany.company;

    if (search.equals(EnumSearch.NAME)) {
      nbComputers =
          (int) queryFactory.selectFrom(computer).where(computer.name.eq(name)).fetchCount();
    } else {
      nbComputers = (int) queryFactory.selectFrom(computer).leftJoin(computer.company, company)
          .where(company.name.eq(name)).fetchCount();
    }
    return nbComputers;
  }

  @Override
  public List<Computer> findAll(Page page) {

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QComputer computer = QComputer.computer;
    QCompany company = QCompany.company;
    List<Computer> computers =
        queryFactory.selectFrom(computer).leftJoin(computer.company, company)
            .offset(page.getStart()).fetch();

    return computers.subList(0, Math.min(computers.size(), page.getLimit()));
  }

  @Override
  public Computer findById(long id) {

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QComputer computer = QComputer.computer;
    QCompany company = QCompany.company;

    return queryFactory.selectFrom(computer).leftJoin(computer.company, company)
        .where(computer.id.eq(id)).fetchOne();
  }

  @Override
  public List<Computer> findByName(EnumSearch search, String name, Page page) {

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QComputer computer = QComputer.computer;
    QCompany company = QCompany.company;
    List<Computer> computers = null;

    if (search.equals(EnumSearch.NAME)) {
      computers = queryFactory.selectFrom(computer).leftJoin(computer.company, company)
          .where(computer.name.eq(name)).offset(page.getStart()).fetch();
      System.out.println(computers.size());
    } else {
      computers = queryFactory.selectFrom(computer).leftJoin(computer.company, company)
          .where(company.name.eq(name)).offset(page.getStart()).fetch();
    }

    return computers.subList(0, Math.min(computers.size(), page.getLimit()));
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

  @Override
  public void deleteByCompany(long companyId) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.update(DELETE_BY_COMPANY, companyId);
  }
}