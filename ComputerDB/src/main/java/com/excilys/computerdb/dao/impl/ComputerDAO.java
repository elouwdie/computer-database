package com.excilys.computerdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.dao.DAO;
import com.excilys.computerdb.exceptions.DAOException;
import com.excilys.computerdb.jdbc.ConnectionMySQL;
import com.excilys.computerdb.mapper.Mapper;
import com.excilys.computerdb.model.Computer;

/**
 * Data access object for table computer in the computer-database
 * 
 * @author excilys
 *
 */
public class ComputerDAO implements DAO<Computer> {
	int nbComputers;

	public int getCount() {
		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				Statement statement = connect.createStatement();) {
			// SQL query
			ResultSet resultSet = statement
					.executeQuery("SELECT COUNT(*) FROM computer");

			resultSet.next();
			nbComputers = resultSet.getInt(1);
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return nbComputers;
	}

	public int getCountByName(String name) {
		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement("SELECT COUNT(*) FROM computer WHERE computer.name like ?");) {
			// SQL query
			statement.setString(1, "%" + name + "%");
			ResultSet resultSet = statement
					.executeQuery();

			resultSet.next();
			nbComputers = resultSet.getInt(1);
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return nbComputers;
	}
	
	@Override
	public List<Computer> findAll(int min, int max) {
		List<Computer> computers = new ArrayList<>();

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement("SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?");) {
			// SQL query
			statement.setInt(1, max);
			statement.setInt(2, min);

			ResultSet resultSet = statement.executeQuery();
			// Creation of the list
			if (resultSet != null) {
				while (resultSet.next()) {
					Computer computer = Mapper.computerMap(resultSet);
					computers.add(computer);
				}
			}
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return computers;
	}

	@Override
	public Computer findById(long id) {
		Computer computer = null;

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(
						"SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ?");) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				computer = Mapper.computerMap(resultSet);
			}
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return computer;
	}

	public List<Computer> findByName(String name, int min, int max) {
		List<Computer> computers = new ArrayList<>();

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement("SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name like ? LIMIT ? OFFSET ?");) {
			// SQL query
			statement.setString(1, "%" + name + "%");
			statement.setInt(2, max);
			statement.setInt(3, min);

			ResultSet resultSet = statement.executeQuery();
			// Creation of the list
			if (resultSet != null) {
				while (resultSet.next()) {
					Computer computer = Mapper.computerMap(resultSet);
					computers.add(computer);
				}
			}
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return computers;
	}

	/**
	 * Creates an instance of the table computer in the database
	 * 
	 * @param obj
	 *            : the computer to create
	 */
	public void create(Computer obj) throws DAOException {

		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);) {
			statement.setString(1, obj.getName());
			if (obj.getIntroduced() != null) {
				statement.setTimestamp(2, Timestamp.valueOf(obj.getIntroduced().atStartOfDay()));
			} else {
				statement.setTimestamp(2, null);
			}
			if (obj.getDiscontinued() != null) {
				statement.setTimestamp(3, Timestamp.valueOf(obj.getDiscontinued().atStartOfDay()));
			} else {
				statement.setTimestamp(3, null);
			}
			if (obj.getCompany() != null) {
				statement.setLong(4, obj.getCompany().getId());
			} else {
				statement.setObject(4, null);
			}

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet != null && resultSet.next()) {
				obj.setId(resultSet.getLong(1));
			}
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Updates the instance of a computer in the database replaces the old
	 * attributes by the ones of the given parameter
	 * 
	 * @param obj
	 *            : the computer to modify
	 */
	public void update(Computer obj) throws DAOException {
		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement("UPDATE computer SET name = ?, introduced = ?, "
						+ "discontinued = ?, company_id = ? WHERE id = ?");) {
			statement.setString(1, obj.getName());
			if (obj.getIntroduced() != null) {
				statement.setTimestamp(2, Timestamp.valueOf(obj.getIntroduced().atStartOfDay()));
			} else {
				statement.setObject(2, null);
			}
			if (obj.getDiscontinued() != null) {
				statement.setTimestamp(3, Timestamp.valueOf(obj.getDiscontinued().atStartOfDay()));
			} else {
				statement.setObject(3, null);
			}
			if (obj.getCompany() != null) {
				statement.setLong(4, obj.getCompany().getId());
			} else {
				statement.setObject(4, null);
			}
			statement.setLong(5, obj.getId());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Deletes a computer instance in the database
	 * 
	 * @param id
	 *            : the ID of the computer to delete
	 */
	public void delete(long id) throws DAOException {
		try (Connection connect = ConnectionMySQL.getInstance().getConnection();
				PreparedStatement statement = connect.prepareStatement("DELETE FROM computer WHERE id = ?");) {
			statement.setLong(1, id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
