package com.excilys.computerdb.cli;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdb.dao.impl.CompanyDAO;
import com.excilys.computerdb.dao.impl.ComputerDAO;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;

public class TraitementCLI {

	/**
	 * Main menu, where the user can choose an action to do
	 * 
	 * @param repUtilisateur
	 *            : Scanner object that contains the input
	 */
	public static void messageStandard(Scanner repUtilisateur) {
		boolean continuer = true;

		while (continuer) {
			System.out.println("\nWhat do you want to do ?" + "\n1 : search computer" + "\n2 : search company"
					+ "\n3 : add new computer" + "\n4 : update a computer" + "\n5 : delete a computer"
					+ "\na : see all computers" + "\nq : quit");

			switch (repUtilisateur.nextLine().trim()) {
			case "1":
				searchComputer(repUtilisateur);
				break;
			case "2":
				searchCompany(repUtilisateur);
				break;
			case "3":
				continuer = false;
				break;
			case "4":
				continuer = false;
				break;
			case "5":
				deleteComputer(repUtilisateur);
				break;
			case "a":
				findAllComputers();
				break;
			case "q":
				quitter(repUtilisateur);
				continuer = false;
				break;
			default:
				System.out.println("Unknown command.");
			}
		}
	}

	/**
	 * Returns the list of all computers in the database.
	 */
	private static void findAllComputers() {
		ComputerDAO dao = new ComputerDAO();

		List<Computer> computers = new ArrayList<Computer>();
		computers = dao.findAll();
		for (Computer c : computers) {
			System.out.println("ID : " + c.getId() + " name : " + c.getName());
		}

		try {
			dao.connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search a computer in the database, and prints its properties The user
	 * enters the computer's ID.
	 * 
	 * @param repUtilisateur
	 *            : Scanner object that contains the input
	 */
	private static void searchComputer(Scanner repUtilisateur) {
		ComputerDAO dao = new ComputerDAO();

		do {
			System.out.println("\nEnter the id of the computer : " + "\nr : return back");
			if (repUtilisateur.hasNextLong()) {
				{
					Computer computer = null;
					computer = dao.findById(repUtilisateur.nextLong());
					// Verify if the computer exists
					if (computer.getName() != null) {
						System.out.println(computer.toString());
					} else
						System.out.println("This ID is not valid.");
				}
			}
		} while (!repUtilisateur.nextLine().trim().equals("r"));

		try {
			dao.connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search a company in the database, and prints its properties The user
	 * enters the company's ID.
	 * 
	 * @param repUtilisateur
	 *            : Scanner object that contains the input
	 */
	private static void searchCompany(Scanner repUtilisateur) {
		CompanyDAO dao = new CompanyDAO();

		do {
			System.out.println("\nEnter the id of the company : " + "\nr : return back");
			if (repUtilisateur.hasNextLong()) {
				{
					Company company = null;
					company = dao.findById(repUtilisateur.nextLong());
					// Verify if the company exists
					if (company.getName() != null) {
						System.out.println(company.toString());
					} else
						System.out.println("This ID is not valid.");
				}
			}
		} while (!repUtilisateur.nextLine().trim().equals("r"));

		try {
			dao.connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createComputer(Scanner repUtilisateur) {
		ComputerDAO dao = new ComputerDAO();

	}

	/**
	 * Verifies if the computer entered exists, and if it does, delete it. The
	 * user enters the computer's ID.
	 * 
	 * @param repUtilisateur
	 *            : Scanner object that contains the input
	 */
	private static void deleteComputer(Scanner repUtilisateur) {
		ComputerDAO dao = new ComputerDAO();

		do {
			System.out.println("\nEnter the id of the computer to delete: " + "\nr : return back");
			if (repUtilisateur.hasNextLong()) {
				{
					Computer computer = null;
					computer = dao.findById(repUtilisateur.nextLong());
					// Verify if the computer exists
					if (computer.getName() != null) {
						dao.delete(computer.getId());
					} else
						System.out.println("This ID is not valid.");
				}
			}
		} while (!repUtilisateur.nextLine().trim().equals("r"));

		try {
			dao.connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the command-line input
	 * 
	 * @param repUtilisateur
	 *            : Scanner object that contains the input
	 */
	private static void quitter(Scanner repUtilisateur) {
		repUtilisateur.close();
	}
}
