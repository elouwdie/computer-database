package com.excilys.computerdb.page;

import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.PageService;

import java.util.List;

public class Page {

  private int start;
  private int limit;
  private int number;
  private int totalNbPages;
  private int nbComputers;
  private List<Computer> computers;

  /**
   * Creates a page with the given arguments.
   * @param number : the number of the page.
   * @param limit : the limit of computers in the page.
   */
  public Page(int number, int limit) {
    this.limit = limit;
    this.number = number;
  }

  /**
   * Creates a page with the given arguments.
   * @param number : the number of the page.
   * @param nbComputers : the total number of computers.
   * @param limit : the limit of computers in the page.
   */
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

  /**
   * Sets the limit and update the page.
   * @param limit : the new limit.
   */
  public void setLimit(int limit) {
    this.limit = limit;
    PageService.updatePage(this, nbComputers);
  }

  public int getNumber() {
    return number;
  }

  /**
   * Sets the number and update the page.
   * @param number : the new number of the page.
   */
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

  /**
   * Sets the number of computers and updates the page.
   * @param nbComputers : the new number of computers in the page.
   */
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
