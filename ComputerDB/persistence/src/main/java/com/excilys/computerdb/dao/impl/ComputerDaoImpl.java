package com.excilys.computerdb.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.excilys.computerdb.dao.ComputerDao;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.model.QCompany;
import com.excilys.computerdb.model.QComputer;
import com.excilys.computerdb.page.Page;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ComputerDaoImpl implements ComputerDao {

  private int nbComputers;

  @PersistenceContext
  private EntityManager entityManager;

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
          (int) queryFactory.selectFrom(computer).where(computer.name.contains(name)).fetchCount();
    } else {
      nbComputers = (int) queryFactory.selectFrom(computer).leftJoin(computer.company, company)
          .where(company.name.contains(name)).fetchCount();
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
          .where(computer.name.contains(name)).offset(page.getStart()).fetch();
      System.out.println(computers.size());
    } else {
      computers = queryFactory.selectFrom(computer).leftJoin(computer.company, company)
          .where(company.name.contains(name)).offset(page.getStart()).fetch();
    }

    return computers.subList(0, Math.min(computers.size(), page.getLimit()));
  }

  @Override
  public void create(Computer computer) {
    entityManager.persist(computer);
  }

  @Override
  public void update(Computer newComputer) {
    entityManager.merge(newComputer);
  }

  @Override
  public void delete(long id) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QComputer computer = QComputer.computer;
    queryFactory.delete(computer).where(computer.id.eq(id)).execute();
  }

  @Override
  public void deleteByCompany(long companyId) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QComputer computer = QComputer.computer;
    queryFactory.delete(computer).where(computer.company.id.eq(companyId)).execute();
  }
}