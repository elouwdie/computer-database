package com.excilys.computerdb.testdao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.excilys.computerdb.dao.CompanyDao;
import com.excilys.computerdb.dao.ComputerDao;
import com.excilys.computerdb.dao.impl.CompanyDaoImpl;
import com.excilys.computerdb.dao.impl.ComputerDaoImpl;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;

@ContextConfiguration
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerDaoImplTest {

  private static CompanyDao companyDAO;
  private static ComputerDao computerDAO;
  private static Company company;
  private static Computer computer;
  private static ClassPathXmlApplicationContext ctx;

  /**
   * Initializes the database, and the test data.
   */
  @BeforeClass
  public static void init() {
    ctx = new ClassPathXmlApplicationContext(
        "test-context.xml");

    computerDAO = ctx.getBean("computerDAO", ComputerDaoImpl.class);
    companyDAO = ctx.getBean("companyDAO", CompanyDaoImpl.class);
    company = new Company();
    company.setId(1);
    company.setName("Excilys");
    computer = new Computer();
    computer.setId(1);
    computer.setName("MacBook Pro 15.4 inch");

  }

  @AfterClass
  public static void end() {
    computerDAO = null;
    ctx.close();
  }

  @Test
  public void testAllCompany() {
    assertTrue(companyDAO.findAll().size() > 0);
  }

  @Test
  public void testFindCompany() {
    assertEquals(company,
        companyDAO.findById(1));
  }

  /*
   * @Test public void testAllComputers() { assertTrue(computerDAO.findAll(new
   * Page(1, 10)).size() > 0); }
   */
  @Test
  public void testFindComputer() {
    computer.setCompany(company);
    assertEquals(computer, computerDAO.findById(1));
  }

  @Test
  public void testCreateComputer() {
    long idInitial = computer.getId();
    computer.setName("testCreate");
    computerDAO.create(computer);
    assertTrue(computer.getId() != idInitial);
    computerDAO.delete(computer.getId());
  }

  @Test
  public void testDeleteComputer() {
    computer.setName("testDelete");
    computerDAO.create(computer);
    long idCree = computer.getId();
    computerDAO.delete(computer.getId());
    assertEquals(computerDAO.findById(idCree), null);
  }

  @Test
  public void testUpdateComputer() {
    computer.setName("test");
    computerDAO.create(computer);
    computer.setName("testUpdate");
    computerDAO.update(computer);
    assertEquals(computerDAO.findById(computer.getId()).getName(),
        "testUpdate");
    computerDAO.delete(computer.getId());
  }

}
