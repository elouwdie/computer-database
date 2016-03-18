package com.excilys.computerdb.service.impl;

import com.excilys.computerdb.page.Page;

public class PageService {

  /**
   * Updates a page with the number of computers found.
   * @param page : the page to update.
   * @param nbComputers : the current number of computers.
   */
  public static void updatePage(Page page, int nbComputers) {
    calculateTotalNbPages(page, nbComputers);
    calculateStart(page);
  }

  /**
   * Calculates the number of total pages, with the number of computers and the
   * page's limit of print.
   * @param page : the page for the limit of print.
   * @param nbComputers : the current number of computers.
   */
  public static void calculateTotalNbPages(Page page, int nbComputers) {
    int nbPages = nbComputers / page.getLimit();
    page.setTotalNbPages(nbComputers % page.getLimit() > 0 ? nbPages + 1 : nbPages);
  }

  /**
   * Calculates where the page starts in the list of computers, with the page's
   * number and the page's limit of print.
   * @param page : the page to update.
   */
  public static void calculateStart(Page page) {
    page.setStart(page.getLimit() * (page.getNumber() - 1));
  }
}
