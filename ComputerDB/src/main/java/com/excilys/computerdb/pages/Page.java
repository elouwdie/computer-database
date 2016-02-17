package com.excilys.computerdb.pages;

import java.util.List;

import com.excilys.computerdb.dao.impl.ComputerDAO;
import com.excilys.computerdb.model.Computer;

public class Page {
	private int totalNbPages;
	private int actualDisplay;
	private int maxPrint;
	private int sizeComputers;
	private String name;
	private ComputerDAO computerDAO;
	

	public Page(ComputerDAO computerDAO, int maxPrint) {
		this.computerDAO = computerDAO;
		this.maxPrint = maxPrint;
		name = "";
		updateValues();
	}
	
	public Page(ComputerDAO computerDAO, String name, int maxPrint) {
		this.computerDAO = computerDAO;
		this.maxPrint = maxPrint;
		this.name = name;
		updateValues();
	}
	
	private void updateValues() {
		if(!name.equals("")) {
			sizeComputers = computerDAO.getCountByName(name);
		}
		else {
			sizeComputers = computerDAO.getCount();
		}
		int nbPages = sizeComputers / maxPrint;
		totalNbPages = sizeComputers % maxPrint > 0 ? nbPages : nbPages + 1;
		actualDisplay = 0;
	}

	public List<Computer> nextPage() {
		List<Computer> computersToReturn;
		computersToReturn = computerDAO.findAll(actualDisplay, maxPrint);
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
		if(name != null) {
			computersToReturn = computerDAO.findByName(name, begin, end);
		}
		else {
			computersToReturn = computerDAO.findAll(begin, end);
		}
		return computersToReturn;
	}

}
