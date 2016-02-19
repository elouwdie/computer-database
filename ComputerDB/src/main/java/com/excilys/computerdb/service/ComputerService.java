package com.excilys.computerdb.service;

import com.excilys.computerdb.dao.exceptions.DAOException;
import com.excilys.computerdb.dao.impl.ComputerDAOImpl;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.pages.Page;

public class ComputerService {
	private static ComputerDAOImpl computerDAO;

	public static int getCount() throws DAOException {
		computerDAO = new ComputerDAOImpl();
		return computerDAO.getCount();
	}

	public static int getCountByName(String name) throws DAOException {
		computerDAO = new ComputerDAOImpl();
		return computerDAO.getCountByName(name);
	}

	public static void findByName(String name, Page page) throws DAOException {
		computerDAO = new ComputerDAOImpl();
		page.setComputers(computerDAO.findByName(name, page));
		;
	}

	public static void findAll(Page page) throws DAOException {
		computerDAO = new ComputerDAOImpl();
		page.setComputers(computerDAO.findAll(page));
	}

	public static Computer findById(long id) throws DAOException {
		computerDAO = new ComputerDAOImpl();
		return computerDAO.findById(id);
	}

	public static void create(Computer computer) throws DAOException {
		computerDAO = new ComputerDAOImpl();
		computerDAO.create(computer);
	}

	public static void update(Computer computer) throws DAOException {
		computerDAO = new ComputerDAOImpl();
		computerDAO.update(computer);
	}

	public static void delete(long l) throws DAOException {
		computerDAO = new ComputerDAOImpl();
		computerDAO.delete(l);
	}
}
