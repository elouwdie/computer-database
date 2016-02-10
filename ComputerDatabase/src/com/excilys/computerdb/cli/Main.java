package com.excilys.computerdb.cli;

import static com.excilys.computerdb.cli.TraitementCLI.messageStandard;

import java.util.Scanner;

/**
 * Principal class. The user will enter the action he wants to do, and execute
 * it here.
 * 
 * @author excilys
 *
 */
public class Main {

	public static void main(String[] args) {
		/*
		 * Computer computer = new Computer(); computer = cmp.findById(5);
		 * GregorianCalendar date = new GregorianCalendar(1975, 9, 10);
		 * GregorianCalendar date2 = new GregorianCalendar(1970, 9, 10);
		 * Computer computer2 = new Computer("coucou", new
		 * Timestamp(date.getTimeInMillis()), new
		 * Timestamp(date2.getTimeInMillis()), 3);
		 * 
		 * List<Computer> cmps = new ArrayList<Computer>(); cmps =
		 * cmp.findAll(); for(Computer c : cmps){ System.out.println("ID : " +
		 * c.getId() + " name : " + c.getName()); }
		 */
		Scanner repUtilisateur = new Scanner(System.in);
		messageStandard(repUtilisateur);
	}
}