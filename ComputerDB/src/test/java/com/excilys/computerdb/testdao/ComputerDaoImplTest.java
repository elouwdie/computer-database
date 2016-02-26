package com.excilys.computerdb.testdao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.excilys.computerdb.dao.impl.CompanyDaoImpl;
import com.excilys.computerdb.dao.impl.ComputerDaoImpl;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.testdatabase.DbTestConnector;

import org.dbunit.dataset.IDataSet;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComputerDaoImplTest {

  private static CompanyDaoImpl companyDAO;
  private static ComputerDaoImpl computerDAO;
  private static Company company;
  private static Computer computer;

  private static DbTestConnector cfm;

  /**
   * Initializes the database, and the test data.
   */
  @BeforeClass
  public static void init() {

    cfm = new DbTestConnector();
    cfm.initConnection();
    cfm.initSchema("src/test/resources/schema.sql");
    cfm.initDataSource();

    companyDAO = new CompanyDaoImpl();
    computerDAO = new ComputerDaoImpl();
    company = new Company();
    company.setId(1);
    company.setName("Excilys");
    computer = new Computer();
    computer.setId(1);
    computer.setName("MacBook Pro 15.4 inch");
  }

  @AfterClass
  public static void end() {
    cfm = null;
    computerDAO = null;
  }

  /**
   * Imports data set.
   */
  @Before
  public void importDataSet() {

    IDataSet dataSet;
    try {
      dataSet = cfm.readDataSet("src/test/resources/dbTest.xml");
      cfm.cleanlyInsert(dataSet);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /*
   * @Test public void testAllCompany() { assertTrue(companyDAO.findAll().size()
   * > 0); }
   * 
   * @Test public void testFindCompany() { assertEquals(company,
   * companyDAO.findById(1)); }
   * 
   * @Test public void testAllComputers() { assertTrue(computerDAO.findAll(new
   * Page(1, 10)).size() > 0); }
   * 
   * 
   * @Test public void testFindComputer() { computer.setCompany(company);
   * assertEquals(computer, computerDAO.findById(1)); }
   * 
   * @Test public void testCreateComputer() { long idInitial = computer.getId();
   * computer.setName("testCreate"); computerDAO.create(computer);
   * assertTrue(computer.getId() != idInitial);
   * computerDAO.delete(computer.getId()); }
   * 
   * @Test public void testDeleteComputer() { computer.setName("testDelete");
   * computerDAO.create(computer); long idCree = computer.getId();
   * computerDAO.delete(computer.getId());
   * assertEquals(computerDAO.findById(idCree), null); }
   * 
   * @Test public void testUpdateComputer() { computer.setName("test");
   * computerDAO.create(computer); computer.setName("testUpdate");
   * computerDAO.update(computer);
   * assertEquals(computerDAO.findById(computer.getId()).getName(),
   * "testUpdate"); computerDAO.delete(computer.getId()); }
   */
}
