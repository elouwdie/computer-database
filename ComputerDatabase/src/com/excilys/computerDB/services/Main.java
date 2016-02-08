package com.excilys.computerDB.services;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDB.dao.DAO;
import com.excilys.computerDB.dao.concrete.ComputerDAO;
import com.excilys.computerDB.model.Computer;

public class Main {
	
	public static void main(String[] args) {
		Computer computer = new Computer();
		DAO<Computer> cmp = new ComputerDAO();
		computer = cmp.findById(1);
		System.out.println(computer.toString());
		
		/*List<Computer> cmps = new ArrayList<Computer>();
		cmps = cmp.findAll();
		for(Computer c : cmps){
			System.out.println("ID : " + c.getId() + " name : " + c.getName());
		}*/
	}
}
