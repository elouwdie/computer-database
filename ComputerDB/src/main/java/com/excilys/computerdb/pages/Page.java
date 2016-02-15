package com.excilys.computerdb.pages;

import java.util.List;

import com.excilys.computerdb.dao.impl.ComputerDAO;
import com.excilys.computerdb.model.Computer;

public class Page {
	private int totalNbPages;
	private int actualDisplay;
	private int maxPrint;
	private int sizeComputers;
	private ComputerDAO computerDAO;
	

	public Page(ComputerDAO computerDAO, int maxPrint) {
		this.computerDAO = computerDAO;
		this.maxPrint = maxPrint;
		
		sizeComputers = computerDAO.getCount();
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

}
