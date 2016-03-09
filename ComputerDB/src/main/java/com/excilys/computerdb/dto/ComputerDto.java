package com.excilys.computerdb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ComputerDto {

  private long id = 0;
  private long companyId = 0;
  @NotNull
  @Size(min = 2, max = 30)
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String introduced;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String discontinued;

  /**
   * Creates a computerDTO.
   */
  public ComputerDto() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
