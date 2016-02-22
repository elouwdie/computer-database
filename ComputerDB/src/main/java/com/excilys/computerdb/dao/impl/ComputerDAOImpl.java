package com.excilys.computerdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.enumerations.EnumSearch;
import com.excilys.computerdb.dao.ComputerDAO;
import com.excilys.computerdb.dao.exceptions.DAOException;
import com.excilys.computerdb.jdbc.ConnectionMySQL;
import com.excilys.computerdb.jdbc.exceptions.DAOConfigurationException;
import com.excilys.computerdb.mapper.MapperDAOComputer;
import com.excilys.computerdb.mapper.exceptions.MapperException;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.pages.Page;

/**
 * Data access object for table computer in the computer-database
 * 
 * @author ecayez
 *
 */
public class ComputerDAOImpl implements ComputerDAO {

	public static final String SELECT_COUNT = "SELECT COUNT(*) FROM computer";
	public static final String WHERE_NAME = " WHERE computer.name like ?";
	public static final String WHERE_COMPANY = " WHERE company.name like ?";
	public static final String WHERE_ID = " WHERE computer.id = ?";
	public static final String SELECT = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
	public static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	public static final String DELETE = "DELETE FROM computer WHERE id = ?";
	public static final String INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	public static final String OFFSET_LIMIT = " LIMIT ? OFFSET ?";

	int nbComputers;

	@Override
	public int getCount() {
		ResultSet resultSet = null;

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				Statement statement = connect.createStatement();) {

			resultSet = statement.executeQuery(SELECT_COUNT);

			resultSet.next();
			nbComputers = resultSet.getInt(1);

		} catch (SQLException | DAOConfigurationException e) {
			throw new DAOException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
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
		
		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(SELECT + where);) {

			statement.setString(1, "%" + name + "%");
			resultSet = statement.executeQuery();

			resultSet.next();
			nbComputers = resultSet.getInt(1);

		} catch (SQLException | DAOConfigurationException e) {
			throw new DAOException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return nbComputers;
	}

	@Override
	public List<Computer> findAll(Page page) {
		ResultSet resultSet = null;
		List<Computer> computers = new ArrayList<>();

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(SELECT + OFFSET_LIMIT);) {
			// SQL query
			statement.setInt(2, page.getStart());
			statement.setInt(1, page.getLimit());

			resultSet = statement.executeQuery();
			// Creation of the list
			if (resultSet != null) {
				while (resultSet.next()) {
					Computer computer = MapperDAOComputer.computerMap(resultSet);
					computers.add(computer);
				}
			}

		} catch (SQLException | DAOConfigurationException | MapperException e) {
			throw new DAOException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return computers;
	}

	@Override
	public Computer findById(long id) {
		ResultSet resultSet = null;
		Computer computer = null;

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(SELECT + WHERE_ID);) {

			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				computer = MapperDAOComputer.computerMap(resultSet);
			}

		} catch (SQLException | DAOConfigurationException | MapperException e) {
			throw new DAOException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
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
		
		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(SELECT + where + OFFSET_LIMIT);) {

			statement.setString(1, "%" + name + "%");
			statement.setInt(3, page.getStart());
			statement.setInt(2, page.getLimit());
			resultSet = statement.executeQuery();
			// Creation of the list
			if (resultSet != null) {
				while (resultSet.next()) {
					Computer computer = MapperDAOComputer.computerMap(resultSet);
					computers.add(computer);
				}
			}

		} catch (SQLException | DAOConfigurationException | MapperException e) {
			throw new DAOException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return computers;
	}

	@Override
	public void create(Computer computer) {
		ResultSet resultSet = null;

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);) {

			set(computer, statement);

			statement.executeUpdate();

			resultSet = statement.getGeneratedKeys();
			if (resultSet != null && resultSet.next()) {
				computer.setId(resultSet.getLong(1));
			}

		} catch (SQLException | DAOConfigurationException e) {
			throw new DAOException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public void update(Computer computer) {
		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(UPDATE);) {

			set(computer, statement);
			statement.setLong(5, computer.getId());
			statement.executeUpdate();
		} catch (SQLException | DAOConfigurationException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(long id) {
		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(DELETE);) {

			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException | DAOConfigurationException e) {
			throw new DAOException(e);
		}
	}

	public void set(Computer computer, PreparedStatement statement) throws DAOException {

		try {
			statement.setString(1, computer.getName());
			if (computer.getIntroduced() != null) {
				statement.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced().atStartOfDay()));
			} else {
				statement.setTimestamp(2, null);
			}
			if (computer.getDiscontinued() != null) {
				statement.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued().atStartOfDay()));
			} else {
				statement.setTimestamp(3, null);
			}
			if (computer.getCompany() != null) {
				statement.setLong(4, computer.getCompany().getId());
			} else {
				statement.setObject(4, null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
