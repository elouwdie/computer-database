package com.excilys.computerdb.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.computerdb.validation.annotations.Date;

public class ComputerDto {

  private long id;
  private long companyId;
  private String companyName;
  @NotEmpty
  @Size(min = 2, max = 30)
  private String name;
  @Date
  private String introduced;
  @Date
  private String discontinued;

  /**
   * Creates an empty computerDTO.
   */
  public ComputerDto() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntroduced() {
    return introduced;
  }

  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }
}
