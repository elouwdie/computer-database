package com.excilys.computerdb.testdao;

import com.excilys.computerdb.dao.impl.CompanyDAO;
import com.excilys.computerdb.dao.impl.ComputerDAO;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;

import junit.framework.TestCase;

public class TestDAO extends TestCase {

	CompanyDAO companyDAO;
	ComputerDAO computerDAO;
	Company company;
	Computer computer;

	public void setUp() {
		companyDAO = new CompanyDAO();
		computerDAO = new ComputerDAO();
		company = new Company();
		company.setId(1);
		company.setName("Apple Inc.");
		computer = new Computer();
		computer.setId(1);
		computer.setName("MacBook Pro 15.4 inch");
		
	}

	public void testAllCompany() {
		assertTrue(companyDAO.findAll(1, 10).size() > 0);
	}

	public void testFindCompany() {
		assertEquals(company, companyDAO.findById(1));
	}

	public void testAllComputers() {
		assertTrue(computerDAO.findAll(1, 90).size() > 0);
	}

	public void testFindComputer() {
		computer.setCompany(company);
		assertEquals(computer, computerDAO.findById(1));
	}

	public void testCreateComputer() {
		long idInitial = computer.getId();
		computer.setName("testCreate");
		computerDAO.create(computer);
		assertTrue(computer.getId() != idInitial);
		computerDAO.delete(computer.getId());
	}

	public void testDeleteComputer() {
		computer.setName("testDelete");
		computerDAO.create(computer);
		long idCree = computer.getId();
		computerDAO.delete(computer.getId());
		assertEquals(computerDAO.findById(idCree), null);
	}

	public void testUpdateComputer() {
		computer.setName("test");
		computerDAO.create(computer);
		computer.setName("testUpdate");
		computerDAO.update(computer);
		assertEquals(computerDAO.findById(computer.getId()).getName(), "testUpdate");
		computerDAO.delete(computer.getId());
	}
}
