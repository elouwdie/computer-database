package com.excilys.computerdb.dao.impl;

import com.excilys.computerdb.dao.ComputerDao;
import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.jdbc.exception.DaoConfigurationException;
import com.excilys.computerdb.mapper.MapperDaoComputer;
import com.excilys.computerdb.mapper.exception.MapperException;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.DaoService;
import com.excilys.computerdb.transaction.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComputerDaoImpl implements ComputerDao {

  public static final String SELECT_COUNT = "SELECT COUNT(*) FROM computer";
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

  int nbComputers;

  @Override
  public int getCount() {
    ResultSet resultSet = null;

    try (Connection connect = TransactionManager.currentConnection();
        Statement statement = connect.createStatement();) {

      resultSet = statement.executeQuery(SELECT_COUNT);

      resultSet.next();
      nbComputers = resultSet.getInt(1);

    } catch (SQLException | DaoConfigurationException e) {
      throw new DaoException(e);
    } finally {
      DaoService.closeRs(resultSet);
    }
    return nbComputers;
  }

  @Override
  public int getCountBy(EnumSearch search, String name) {
    ResultSet resultSet = null;
    String where = null;

    if (search.equals(EnumSearch.NAME)) {
      where = WHERE_NAME;
    } else {
      where = WHERE_COMPANY;
    }

    try (Connection connect = TransactionManager.currentConnection();
        PreparedStatement statement = connect.prepareStatement(SELECT + where);) {

      statement.setString(1, "%" + name + "%");
      resultSet = statement.executeQuery();

      resultSet.next();
      nbComputers = resultSet.getInt(1);

    } catch (SQLException | DaoConfigurationException e) {
      throw new DaoException(e);
    } finally {
      DaoService.closeRs(resultSet);
    }
    return nbComputers;
  }

  @Override
  public List<Computer> findAll(Page page) {
    ResultSet resultSet = null;
    List<Computer> computers = new ArrayList<>();

    try (Connection connect = TransactionManager.currentConnection();
        PreparedStatement statement = connect.prepareStatement(SELECT + OFFSET_LIMIT);) {
      // SQL query
      statement.setInt(2, page.getStart());
      statement.setInt(1, page.getLimit());

      resultSet = statement.executeQuery();
      // Creation of the list
      if (resultSet != null) {
        while (resultSet.next()) {
          Computer computer = MapperDaoComputer.computerMap(resultSet);
          computers.add(computer);
        }
      }

    } catch (SQLException | DaoConfigurationException | MapperException e) {
      throw new DaoException(e);
    } finally {
      DaoService.closeRs(resultSet);
    }
    return computers;
  }

  @Override
  public Computer findById(long id) {
    ResultSet resultSet = null;
    Computer computer = null;

    try (Connection connect = TransactionManager.currentConnection();
        PreparedStatement statement = connect.prepareStatement(SELECT + WHERE_ID);) {

      statement.setLong(1, id);
      resultSet = statement.executeQuery();
      if (resultSet != null && resultSet.next()) {
        computer = MapperDaoComputer.computerMap(resultSet);
      }

    } catch (SQLException | DaoConfigurationException | MapperException e) {
      throw new DaoException(e);
    } finally {
      DaoService.closeRs(resultSet);
    }
    return computer;
  }

  @Override
  public List<Computer> findByName(EnumSearch search, String name, Page page) {
    List<Computer> computers = new ArrayList<>();
    ResultSet resultSet = null;
    String where = null;

    if (search.equals(EnumSearch.NAME)) {
      where = WHERE_NAME;
    } else {
      where = WHERE_COMPANY;
    }

    try (Connection connect = TransactionManager.currentConnection();
        PreparedStatement statement = connect.prepareStatement(SELECT + where + OFFSET_LIMIT);) {

      statement.setString(1, "%" + name + "%");
      statement.setInt(3, page.getStart());
      statement.setInt(2, page.getLimit());
      resultSet = statement.executeQuery();
      // Creation of the list
      if (resultSet != null) {
        while (resultSet.next()) {
          Computer computer = MapperDaoComputer.computerMap(resultSet);
          computers.add(computer);
        }
      }

    } catch (SQLException | DaoConfigurationException | MapperException e) {
      throw new DaoException(e);
    } finally {
      DaoService.closeRs(resultSet);
    }
    return computers;
  }

  @Override
  public void create(Computer computer) {
    ResultSet resultSet = null;

    try (Connection connect = TransactionManager.currentConnection();
        PreparedStatement statement =
            connect.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);) {

      DaoService.set(computer, statement);

      statement.executeUpdate();

      resultSet = statement.getGeneratedKeys();
      if (resultSet != null && resultSet.next()) {
        computer.setId(resultSet.getLong(1));
      }

    } catch (SQLException | DaoConfigurationException e) {
      throw new DaoException(e);
    } finally {
      DaoService.closeRs(resultSet);
    }
  }

  @Override
  public void update(Computer computer) {
    try (Connection connect = TransactionManager.currentConnection();
        PreparedStatement statement = connect.prepareStatement(UPDATE);) {

      DaoService.set(computer, statement);
      statement.setLong(5, computer.getId());
      statement.executeUpdate();
    } catch (SQLException | DaoConfigurationException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void delete(long id) {
    try (Connection connect = TransactionManager.currentConnection();
        PreparedStatement statement = connect.prepareStatement(DELETE);) {

      statement.setLong(1, id);
      statement.executeUpdate();
    } catch (SQLException | DaoConfigurationException e) {
      throw new DaoException(e);
    }
  }
}