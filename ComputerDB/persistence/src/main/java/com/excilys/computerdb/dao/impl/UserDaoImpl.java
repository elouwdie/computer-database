package com.excilys.computerdb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.excilys.computerdb.dao.UserDao;
import com.excilys.computerdb.model.QUser;
import com.excilys.computerdb.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserDaoImpl implements UserDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public User findByUserName(String username) {

    List<User> users = new ArrayList<User>();

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QUser user = QUser.user;
    users = queryFactory.selectFrom(user).where(user.username.eq(username)).fetch();

    if (users.size() > 0) {
      return users.get(0);
    } else {
      return null;
    }

  }

}