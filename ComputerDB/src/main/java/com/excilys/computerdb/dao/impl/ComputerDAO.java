package com.excilys.computerdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.dao.DAO;
import com.excilys.computerdb.exceptions.DAOException;
import com.excilys.computerdb.jdbc.ConnectionMySQL;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;

/**
 * Data access object for table computer in the computer-database
 * 
 * @author excilys
 *
 */
public class ComputerDAO implements DAO<Computer> {

	@Override
	public List<Computer> findAll() {
		Computer computer = new Computer();
		List<Computer> computers = new ArrayList<>();

		try (Connection connect = ConnectionMySQL.getConnection(); Statement statement = connect.createStatement();) {
			// SQL query
			ResultSet resultSet = statement.executeQuery("SELECT id, name  FROM computer");

			// Creation of the list
			if (resultSet.first()) {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					computer = new Computer(resultSet.getLong("id"), resultSet.getString("name"));
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
		Computer computer = new Computer();
		Company company = null;

		try (Connection connect = ConnectionMySQL.getConnection();
				PreparedStatement statement = connect.prepareStatement(
						"SELECT o.*, c.* FROM computer o LEFT JOIN company c ON o.company_id = c.id WHERE o.id = ?");) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				String name = resultSet.getString("o.name");
				Timestamp introducedTmp = resultSet.getTimestamp("o.introduced");
				LocalDate introduced = introducedTmp == null ? null : introducedTmp.toLocalDateTime().toLocalDate();
				Timestamp discontinuedTmp = resultSet.getTimestamp("o.discontinued");
				LocalDate discontinued = discontinuedTmp == null ? null
						: discontinuedTmp.toLocalDateTime().toLocalDate();
				Long cId = resultSet.getLong("o.company_id");
				if (cId != 0) {
					Long companyId = resultSet.getLong("c.id");
					String companyName = resultSet.getString("c.name");
					company = new Company(companyId, companyName);
				}
				computer = new Computer(id, name, introduced, discontinued, company);
			}
			resultSet.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return computer;
	}

	/**
	 * Creates an instance of the table computer in the database
	 * 
	 * @param obj
	 *            : the computer to create
	 */
	public void create(Computer obj) throws DAOException {

		try (Connection connect = ConnectionMySQL.getConnection();
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
			if (resultSet.first()) {
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
		try (Connection connect = ConnectionMySQL.getConnection();
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
		try (Connection connect = ConnectionMySQL.getConnection();
				PreparedStatement statement = connect.prepareStatement("DELETE FROM computer WHERE id = ?");) {
			statement.setLong(1, id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean exists(Computer computer) {
		// TODO Auto-generated method stub
		return false;
	}

}
