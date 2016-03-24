package com.excilys.computerdb.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.dao.ComputerDao;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;

@ContextConfiguration(locations = "classpath*:**/application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ComputerDaoTest {

  // @Autowired
  // private static CompanyDao companyDAO;

  @Autowired
  private ComputerDao computerDAO;

  private static Company company;
  private static Computer computer;
  private static Page page;

  /**
   * Initializes the context, and the test data.
   */
  @BeforeClass
  public static void init() {
    company = new Company();
    company.setId(1);
    company.setName("Excilys");
    computer = new Computer();
    computer.setName("elodie");
    computer.setIntroduced(LocalDate.now());
    page = new Page(1, 30);
  }

  @AfterClass
  public static void end() {
    company = null;
    computer = null;
    page = null;
  }

 @Test
  @Transactional
  public void testAllComputer() {
    assertTrue(computerDAO.findAll(page).size() > 0);
  }

  @Test
  @Transactional
  public void testFindByName() {
    assertNotNull(computerDAO.findByName(EnumSearch.NAME, "com", page));
  }

  @Test
  @Transactional
  public void testGetCount() {
    assertEquals(computerDAO.getCount(), 576);
  }

  @Test
  @Transactional
  public void testFindComputer() {
    assertEquals(computerDAO.findById(1).getId(), 1L);
  }

 @Test
  @Transactional
  public void testCreate() {
    computerDAO.create(computer);
    System.out.println(computerDAO.findById(577).toString());
    assertEquals(computerDAO.findById(577).getName(), computer.getName());
  }
}
