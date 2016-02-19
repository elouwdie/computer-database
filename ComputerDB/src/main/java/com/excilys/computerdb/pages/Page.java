package com.excilys.computerdb.pages;

import java.util.List;

import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.PageService;

public class Page {

	private int start;
	private int limit;
	private int number;
	private int totalNbPages;
	private int nbComputers;
	private List<Computer> computers;

	public Page(int number, int limit) {
		this.limit = limit;
		this.number = number;
	}

	public Page(int number, int nbComputers, int limit) {
		this.limit = limit;
		this.number = number;
		this.nbComputers = nbComputers;
		PageService.updatePage(this, nbComputers);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		PageService.updatePage(this, nbComputers);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		PageService.calculateStart(this);
	}

	public int getTotalNbPages() {
		return totalNbPages;
	}

	public int getNbComputers() {
		return nbComputers;
	}

	public void setNbComputers(int nbComputers) {
		this.nbComputers = nbComputers;
		PageService.calculateTotalNbPages(this, nbComputers);
	}

	public void setTotalNbPages(int totalNbPages) {
		this.totalNbPages = totalNbPages;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}
}
