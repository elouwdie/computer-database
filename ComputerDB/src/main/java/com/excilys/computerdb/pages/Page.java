package com.excilys.computerdb.pages;

import java.util.List;

import com.excilys.computerdb.model.Computer;

public class Page {

	private int start;
	private int limit;
	private int number;
	private int totalNbPages;
	private int nbComputers;
	private List<Computer> computers;

	public Page(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}// TODO a supprimer

	public Page(int number, int nbComputers, int limit) {
		this.limit = limit;
		this.number = number;
		this.nbComputers = nbComputers;
		calculateTotalNbPages();
		calculateStart();
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
		calculateTotalNbPages();
		calculateStart();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		calculateStart();
	}

	public int getTotalNbPages() {
		return totalNbPages;
	}

	public int getNbComputers() {
		return nbComputers;
	}

	public void setNbComputers(int nbComputers) {
		this.nbComputers = nbComputers;
		calculateTotalNbPages();
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

	public void calculateTotalNbPages() {
		int nbPages = nbComputers / limit;
		totalNbPages = nbComputers % limit > 0 ? nbPages + 1 : nbPages;
	}

	public void calculateStart() {
		this.start = limit * (number - 1);
	}
}
