package com.excilys.computerdb.service;

import com.excilys.computerdb.pages.Page;

public class PageService {

	public static void updatePage(Page page, int nbComputers) {
		calculateTotalNbPages(page, nbComputers);
		calculateStart(page);
	}

	public static void calculateTotalNbPages(Page page, int nbComputers) {
		int nbPages = nbComputers / page.getLimit();
		page.setTotalNbPages(nbComputers % page.getLimit() > 0 ? nbPages + 1 : nbPages);
	}

	public static void calculateStart(Page page) {
		page.setStart(page.getLimit() * (page.getNumber() - 1));
	}
}
