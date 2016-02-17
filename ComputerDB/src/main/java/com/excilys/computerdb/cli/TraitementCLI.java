package com.excilys.computerdb.cli;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdb.dao.impl.CompanyDAO;
import com.excilys.computerdb.dao.impl.ComputerDAO;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.pages.Page;
import com.excilys.computerdb.service.DataVerification;

public class TraitementCLI {

	public static final Scanner repUtilisateur = new Scanner(System.in);
	static Logger log;

	/**
	 * Main menu, where the user can choose an action to do
	 */
	public static void messageStandard() {
		boolean continuer = true;
		log = LoggerFactory.getLogger(TraitementCLI.class);

		while (continuer) {
			System.out.println("**********************************" + "\nWhat do you want to do ?"
					+ "\n1 : search computer" + "\n2 : search company" + "\n3 : add new computer"
					+ "\n4 : update a computer" + "\n5 : delete a computer" + "\na : see all computers" + "\nq : quit"
					+ "\n**********************************");

			switch (repUtilisateur.next().trim()) {
			case "1":
				searchComputer();
				break;
			case "2":
				searchCompany();
				break;
			case "3":
				createComputer();
				break;
			case "4":
				updateComputer();
				break;
			case "5":
				deleteComputer();
				break;
			case "a":
				findAllComputers();
				break;
			case "q":
				quit();
				continuer = false;
				break;
			default:
				log.error("Unknown command.");
			}
		}
	}

	/**
	 * Returns the list of all computers in the database.
	 */
	private static void findAllComputers() {
		ComputerDAO dao = new ComputerDAO();
		Page page = new Page(dao, 30);
		int indice = 0;
		while (indice <= page.getTotalNbPages()) {
			List<Computer> computersPrint = page.getPage(0, 10);
			System.out.println("**********************************");
			for (Computer c : computersPrint) {
				System.out.println("ID : " + c.getId() + " name : " + c.getName());
			}
			System.out.println("Next page (n) - return back (r)");
			switch (repUtilisateur.next()) {
			case "n":
				indice += 1;
				break;
			case "r":
				indice = page.getTotalNbPages() + 1;
				break;
			default:
				System.out.println("commande inconnue");
			}
		}
	}

	/**
	 * Search a computer in the database, and prints its properties The user
	 * enters the computer's ID.
	 */
	private static void searchComputer() {
		ComputerDAO dao = new ComputerDAO();
		boolean ok = false;

		do {
			System.out.println("\nEnter the id of the computer : " + "\nr : return back");
			if (repUtilisateur.hasNextLong()) {
				{
					Computer computer = null;
					computer = dao.findById(repUtilisateur.nextLong());
					// Verify if the computer exists
					if (computer.getName() != null) {
						System.out.println(computer.toString());
						ok = true;
					} else
						log.error("This ID is not valid.");
				}
			}
		} while (!repUtilisateur.nextLine().trim().equals("r") && ok == false);
	}

	/**
	 * Search a company in the database, and prints its properties The user
	 * enters the company's ID.
	 */
	private static void searchCompany() {
		CompanyDAO dao = new CompanyDAO();
		boolean ok = false;

		do {
			System.out.println("\nEnter the id of the company : " + "\nr : return back");
			if (repUtilisateur.hasNextLong()) {
				{
					Company company = null;
					company = dao.findById(repUtilisateur.nextLong());
					// Verify if the company exists
					if (company.getName() != null) {
						ok = true;
						System.out.println(company.toString());
					} else
						log.error("This ID is not valid.");
				}
			}
		} while (!repUtilisateur.nextLine().trim().equals("r") && ok == false);
	}

	/**
	 * Creates a computer in the database. Asks the user what he wants to
	 * detail, but the name is a required field
	 */
	private static void createComputer() {
		ComputerDAO dao = new ComputerDAO();
		LocalDate intro = null;
		LocalDate discont = null;
		Company company = null;
		// name
		System.out.println("\nEnter the name of the computer: ");
		String name = repUtilisateur.next();
		// introduction date
		System.out.println("\nEnter the introduction date of the computer ? y/n");
		if (repUtilisateur.next().equals("y")) {
			intro = enterDate();
		}
		// discontinued date
		System.out.println("\nEnter the date when computer discontinued ? y/n");
		if (repUtilisateur.next().equals("y")) {
			boolean ok = false;
			do {
				discont = enterDate();
				ok = DataVerification.areDatesOk(intro, discont);
				if (!ok) {
					log.error("Discontinued date must be after introduction date. Please retry with a date after {}.",
							intro);
				}
			} while (!ok);
		}
		// company ID
		System.out.println("\nEnter the company ID ? y/n");
		if (repUtilisateur.next().equals("y")) {
			boolean ok = false;
			while (!ok) {
				System.out.println("\nCompany ID ?");
				if (repUtilisateur.hasNextLong()) {
					CompanyDAO cDao = new CompanyDAO();
					company = cDao.findById(repUtilisateur.nextLong());
					ok = (company != null);
				} else {
					log.error("This ID is not valid.");
				}
			}
		}
		// Creates the computer in the database
		Computer computer = new Computer(name, intro, discont, company);
		dao.create(computer);
	}

	/**
	 * Modifies a computer already existing in the database. The user can modify
	 * the name of the computer, the dates, and the company.
	 */
	private static void updateComputer() {
		ComputerDAO dao = new ComputerDAO();
		LocalDate intro = null;
		LocalDate discont = null;
		Company company = null;
		Computer computer = null;
		boolean ok = false;

		do {
			System.out.println("\nEnter the id of the computer to update: " + "\nr : return back");
			if (repUtilisateur.hasNextLong()) {
				computer = dao.findById(repUtilisateur.nextLong());
				// Verify if the computer exists
				if (computer.getName() != null) {

					System.out.println("\nModisy the name of the computer ? y/n ");
					if (repUtilisateur.next().equals("y")) {
						System.out.println("\nEnter the new name of the computer : ");
						String name = repUtilisateur.next();
						computer.setName(name);
					}
					// introduction date
					System.out.println("\nEnter the introduction date of the computer ? y/n");
					if (repUtilisateur.next().equals("y")) {
						intro = enterDate();
						computer.setIntroduced(intro);
					}
					// Discondinued date
					System.out.println("\nEnter the date when computer discontinued ? y/n");
					if (repUtilisateur.next().equals("y")) {
						boolean ok2 = false;
						do {
							discont = enterDate();
							if (intro == null) {
								ok2 = true;
							} else {
								ok2 = DataVerification.areDatesOk(intro, discont);
								if (!ok2) {
									log.error(
											"Discontinued date must be after introduction date. Please retry with a date after {}.",
											intro);
								} else {
									computer.setDiscontinued(discont);
								}
							}
						} while (!ok2);
					}
					// Company ID
					System.out.println("\nEnter the company ID ? y/n");
					if (repUtilisateur.next().equals("y")) {
						boolean ok2 = false;
						while (!ok2) {
							System.out.println("\nCompany ID ?");
							if (repUtilisateur.hasNextLong()) {
								CompanyDAO cDao = new CompanyDAO();
								company = cDao.findById(repUtilisateur.nextLong());
								ok2 = (company != null);
							} else {
								log.error("This ID is not valid.");
							}
						}
						computer.setCompany(company);
					}
				}
			}
			// Updating the database
			dao.update(computer);
			log.info("Modification complete");
		} while (!repUtilisateur.nextLine().trim().equals("r") && ok == false);
	}

	/**
	 * Verifies if the computer entered exists, and if it does, delete it. The
	 * user enters the computer's ID.
	 */
	private static void deleteComputer() {
		ComputerDAO dao = new ComputerDAO();
		boolean ok = false;

		do {
			System.out.println("\nEnter the id of the computer to delete: " + "\nr : return back");
			if (repUtilisateur.hasNextLong()) {
				{
					Computer computer = null;
					computer = dao.findById(repUtilisateur.nextLong());
					// Verify if the computer exists
					if (computer.getName() != null) {
						// Deletes the computer in the database
						dao.delete(computer.getId());
						ok = true;
						log.info("\nSuppression effectuée");
					} else
						log.error("This ID is not valid.");
				}
			}
		} while (!repUtilisateur.nextLine().trim().equals("r") && ok == false);
	}

	/**
	 * This functions asks the user to enter a date : He first enters the year,
	 * then the month, and finally the day.
	 * 
	 * @return the object LocalDate created with those details.
	 */
	private static LocalDate enterDate() {
		boolean ok = false;
		int year = 0;
		int month = 0;
		int day = 0;
		do {
			System.out.println("Year ? (YYYY)");
			year = repUtilisateur.nextInt();
			System.out.println("Month ? (MM)");
			month = repUtilisateur.nextInt();
			System.out.println("Day ? (DD)");
			day = repUtilisateur.nextInt();
			ok = DataVerification.isDateCorrect(year, month, day);
			if (!ok) {
				log.error("Date format incorrect. Please retry.");
			}
		} while (!ok);

		return LocalDate.of(year, month, day);
	}

	/**
	 * Stops the command-line input
	 */
	private static void quit() {
		repUtilisateur.close();
		log.info("Au revoir et à bientôt !");
	}
}
