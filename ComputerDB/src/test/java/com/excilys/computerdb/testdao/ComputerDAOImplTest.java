package com.excilys.computerdb.testdao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.dbunit.dataset.IDataSet;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.computerdb.dao.impl.CompanyDAOImpl;
import com.excilys.computerdb.dao.impl.ComputerDAOImpl;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.pages.Page;
import com.excilys.computerdb.testdatabase.DBTestConnector;

public class ComputerDAOImplTest {

	private static CompanyDAOImpl companyDAO;
	private static ComputerDAOImpl computerDAO;
	private static Company company;
	private static Computer computer;

	private static DBTestConnector cfm;

	@BeforeClass
	public static void init() {

		cfm = new DBTestConnector();
		cfm.initConnection();
		cfm.initSchema("src/test/resources/schema.sql");
		cfm.initDataSource();

		companyDAO = new CompanyDAOImpl();
		computerDAO = new ComputerDAOImpl();
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

	@Test
	public void testAllCompany() {
		assertTrue(companyDAO.findAll().size() > 0);
	}

	@Test
	public void testFindCompany() {
		assertEquals(company, companyDAO.findById(1));
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
		assertEquals(computerDAO.findById(computer.getId()).getName(), "testUpdate");
		computerDAO.delete(computer.getId());
	}
}
