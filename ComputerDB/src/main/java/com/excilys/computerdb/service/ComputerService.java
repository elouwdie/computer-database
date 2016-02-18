package com.excilys.computerdb.service;

import com.excilys.computerdb.dao.impl.ComputerDAOImpl;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.pages.Page;

public class ComputerService {
	private static ComputerDAOImpl computerDAO;

	public static int getCount() {
		computerDAO = new ComputerDAOImpl();
		return computerDAO.getCount();
	}

	public static int getCountByName(String name) {
		computerDAO = new ComputerDAOImpl();
		return computerDAO.getCountByName(name);
	}

	public static void findByName(String name, Page page) {
		computerDAO = new ComputerDAOImpl();
		page.setComputers(computerDAO.findByName(name, page));
		;
	}

	public static void findAll(Page page) {
		computerDAO = new ComputerDAOImpl();
		page.setComputers(computerDAO.findAll(page));
	}

	public static Computer findById(long id) {
		computerDAO = new ComputerDAOImpl();
		return computerDAO.findById(id);
	}

	public static void create(Computer computer) {
		computerDAO = new ComputerDAOImpl();
		computerDAO.create(computer);
	}

	public static void update(Computer computer) {
		computerDAO = new ComputerDAOImpl();
		computerDAO.update(computer);
	}

	public static void delete(long l) {
		computerDAO = new ComputerDAOImpl();
		computerDAO.delete(l);
	}
}
