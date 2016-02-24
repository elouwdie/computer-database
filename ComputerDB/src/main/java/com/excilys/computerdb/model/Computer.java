package com.excilys.computerdb.model;

import java.time.LocalDate;

/**
 * Class computer : corresponds to an instance of the table computer in the
 * database.
 * @author ecayez
 *
 */
public class Computer {
  private long id = 0;
  private Company company = null;
  private String name = null;
  private LocalDate introduced = null;
  private LocalDate discontinued = null;

  /**
   * Creates a new empty computer.
   */
  public Computer() {

  }

  /**
   * Creates a new computer with the given arguments.
   * @param name : the name of the computer.
   * @param introduced : the introduced date.
   * @param discontinued : the discontinued date.
   * @param company : the company which created the computer.
   */
  public Computer(String name, LocalDate introduced, LocalDate discontinued, Company company) {
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getIntroduced() {
    return introduced;
  }

  public void setIntroduced(LocalDate introduced) {
    this.introduced = introduced;
  }

  public LocalDate getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(LocalDate discontinued) {
    this.discontinued = discontinued;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result *= id;
    result *= name.hashCode();
    result = introduced != null ? result * introduced.hashCode() : result;
    result = discontinued != null ? result * discontinued.hashCode() : result;
    result = company != null ? result * company.hashCode() : result;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (getClass() != obj.getClass()) {
      return false;
    }

    if (this.id == ((Computer) obj).getId() && this.name.equals(((Computer) obj).getName())) {
      if (this.introduced != null && this.introduced.equals(((Computer) obj).getIntroduced())) {
        if (this.discontinued != null
            && this.discontinued.equals(((Computer) obj).getDiscontinued())) {
          if (this.company != null && this.company.equals(((Computer) obj).getCompany())) {
            return true;
          }
        }
      }
    }

    if (toString().equals(((Computer) obj).toString())) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuffer chaine = new StringBuffer("Computer with ID " + id + ", named " + name);
    if (introduced != null) {
      chaine.append("\nIntroduced in " + introduced.toString());
    }
    if (discontinued != null) {
      chaine.append("\nDiscontinued in " + discontinued.toString());
    }
    if (company != null) {
      chaine.append("\nCreated by " + company.getName());
    }
    return chaine.toString();
  }

}
