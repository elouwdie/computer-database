package com.excilys.computerdb.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.excilys.computerdb.dao.CompanyDao;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.QCompany;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CompanyDaoImpl implements CompanyDao {

  public static final String FIND_ALL = "SELECT * FROM company";
  public static final String DELETE = "DELETE FROM company WHERE id = ?";
  public static final String WHERE_ID = " WHERE id = ?";

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Company> findAll() {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QCompany company = QCompany.company;

    return queryFactory.selectFrom(company).fetch();
  }

  @Override
  public void delete(long id) {

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QCompany company = QCompany.company;
    queryFactory.delete(company).where(company.id.eq(id)).execute();

  }

  @Override
  public Company findById(long id) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QCompany company = QCompany.company;

    return queryFactory.selectFrom(company).where(company.id.eq(id)).fetchOne();
  }
}