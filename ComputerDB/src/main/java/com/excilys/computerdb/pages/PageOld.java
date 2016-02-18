package com.excilys.computerdb.pages;

import java.util.List;

import com.excilys.computerdb.dao.impl.ComputerDAOImpl;
import com.excilys.computerdb.model.Computer;

public class PageOld {
	private int totalNbPages;
	private int actualDisplay;
	private int maxPrint;
	private int sizeComputers;
	private String name;
	private ComputerDAOImpl computerDAO;

	public PageOld(ComputerDAOImpl computerDAO, int maxPrint) {
		this.computerDAO = computerDAO;
		this.maxPrint = maxPrint;
		name = "";
		updateValues();
	}

	public PageOld(ComputerDAOImpl computerDAO, String name, int maxPrint) {
		this.computerDAO = computerDAO;
		this.maxPrint = maxPrint;
		this.name = name;
		updateValues();
	}

	private void updateValues() {
		if (!name.equals("")) {
			sizeComputers = computerDAO.getCountByName(name);
		} else {
			sizeComputers = computerDAO.getCount();
		}
		int nbPages = sizeComputers / maxPrint;
		totalNbPages = sizeComputers % maxPrint > 0 ? nbPages : nbPages + 1;
		actualDisplay = 0;
	}

	public List<Computer> nextPage() {
		List<Computer> computersToReturn;
		computersToReturn = computerDAO.findAll(new Page(actualDisplay, maxPrint));
		actualDisplay += maxPrint;
		return computersToReturn;
	}

	public int getTotalNbPages() {
		return totalNbPages;
	}

	public int getSizeComputers() {
		return sizeComputers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		updateValues();
	}

	public List<Computer> getPage(int begin, int end) {
		List<Computer> computersToReturn;
		if (name != null) {
			computersToReturn = computerDAO.findByName(name, new Page(begin, end));
		} else {
			computersToReturn = computerDAO.findAll(new Page(begin, end));
		}
		return computersToReturn;
	}

}
