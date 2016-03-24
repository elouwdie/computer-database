package com.excilys.computerdb.page;

import com.excilys.computerdb.model.Computer;

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
    updatePage(this, nbComputers);
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
    updatePage(this, nbComputers);
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
    calculateStart(this);
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
    calculateTotalNbPages(this, nbComputers);
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
