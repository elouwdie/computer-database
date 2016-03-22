package com.excilys.computerdb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class company : corresponds to an instance of table company in the database.
 * @author ecayez
 *
 */
@Entity
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id = 0;
  private String name;

  /**
   * Creates a new empty company.
   */
  public Company() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return ("Company with ID : " + id + ", named " + name);
  }

  @Override
  public int hashCode() {
    int result = 1;
    result *= id;
    result *= name.hashCode();
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

    if (this.id == ((Company) obj).getId() && this.name.equals(((Company) obj).getName())) {
      return true;
    }
    return false;
  }
}