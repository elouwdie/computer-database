package com.excilys.computerdb.cli;

import java.time.LocalDate;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.CompanyService;
import com.excilys.computerdb.service.ComputerService;
import com.excilys.computerdb.service.impl.CompanyServiceImpl;
import com.excilys.computerdb.service.impl.ComputerServiceImpl;
import com.excilys.computerdb.validation.DataVerification;
import com.excilys.computerdb.validation.exception.DataException;

public class TraitementCli {

  public final Scanner REP_UTILISATEUR = new Scanner(System.in);
  Logger log;
  ClassPathXmlApplicationContext ctx;
  ComputerService computerService;
  CompanyService companyService;

  /**
   * Main menu, where the user can choose an action to do.
   */
  public void messageStandard() {
    boolean continuer = true;
    log = LoggerFactory.getLogger(TraitementCli.class);
    ctx = new ClassPathXmlApplicationContext(
        "application-context.xml");
    computerService = ctx.getBean("computerService", ComputerServiceImpl.class);
    companyService = ctx.getBean("companyService", CompanyServiceImpl.class);

    while (continuer) {
      System.out.println("**********************************" + "\nWhat do you want to do ?"
          + "\n1 : search computer" + "\n2 : search company" + "\n3 : add new computer"
          + "\n4 : update a computer" + "\n5 : delete a computer" + "\n6 : delete a company"
          + "\na : see all computers" + "\nq : quit" + "\n**********************************");

      switch (REP_UTILISATEUR.next().trim()) {
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
        case "6":
          deleteCompany();
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
  private void findAllComputers() {
    int nbComputers = computerService.getCount();
    Page page = new Page(1, nbComputers, 30);
    while (page.getNumber() <= page.getTotalNbPages()) {
      computerService.findAll(page);
      System.out.println("**********************************");
      for (Computer c : page.getComputers()) {
        System.out.println("ID : " + c.getId() + " name : " + c.getName());
      }
      System.out.println("Next page (n) - return back (r)");
      switch (REP_UTILISATEUR.next()) {
        case "n":
          page.setNumber(page.getNumber() + 1);
          break;
        case "r":
          page.setNumber(page.getTotalNbPages() + 1);
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
  private void searchComputer() {
    boolean ok = false;

    do {
      System.out.println("\nEnter the id of the computer : " + "\nr : return back");
      if (REP_UTILISATEUR.hasNextLong()) {
        Computer computer = null;
        computer = computerService.findById(REP_UTILISATEUR.nextLong());
        // Verify if the computer exists
        if (computer.getName() != null) {
          System.out.println(computer.toString());
          ok = true;
        } else {
          log.error("This ID is not valid.");
        }
      }
    } while (!REP_UTILISATEUR.nextLine().trim().equals("r") && !ok);
  }

  /**
   * Search a company in the database, and prints its properties The user enters
   * the company's ID.
   */
  private void searchCompany() {
    boolean ok = false;

    do {
      System.out.println("\nEnter the id of the company : " + "\nr : return back");
      if (REP_UTILISATEUR.hasNextLong()) {
        Company company = null;
        company = companyService.findById(REP_UTILISATEUR.nextLong());
        // Verify if the company exists
        if (company.getName() != null) {
          ok = true;
          System.out.println(company.toString());
        } else {
          log.error("This ID is not valid.");
        }
      }
    } while (!REP_UTILISATEUR.nextLine().trim().equals("r") && !ok);
  }

  /**
   * Creates a computer in the database. Asks the user what he wants to detail,
   * but the name is a required field
   */
  private void createComputer() {
    LocalDate intro = null;
    LocalDate discont = null;
    Company company = null;
    String name = null;
    // name
    System.out.println("\nEnter the name of the computer: ");
    name = REP_UTILISATEUR.next();
    // introduction date
    boolean ok = false;
    do {
      System.out.println("\nEnter the introduction date of the computer ? y/n");
      if (REP_UTILISATEUR.next().equals("y")) {
        intro = enterDate();
      } else {
        ok = true;
      }
      // discontinued date
      System.out.println("\nEnter the date when computer discontinued ? y/n");
      if (REP_UTILISATEUR.next().equals("y")) {
        try {
          discont = enterDate();
          DataVerification.areDatesOk(intro, discont);
          ok = true;
        } catch (DataException e) {
          log.error(e.getMessage());
        }
      } else {
        ok = true;
      }
    } while (!ok);
    // company ID
    System.out.println("\nEnter the company ID ? y/n");
    if (REP_UTILISATEUR.next().equals("y")) {
      boolean ok2 = false;
      while (!ok2) {
        System.out.println("\nCompany ID ?");
        if (REP_UTILISATEUR.hasNextLong()) {
          company = companyService.findById(REP_UTILISATEUR.nextLong());
          ok2 = (company != null);
        } else {
          log.error("This ID is not valid.");
        }
      }
    }
    // Creates the computer in the database
    Computer computer = new Computer(name, intro, discont, company);
    computerService.create(computer);
  }

  /**
   * Modifies a computer already existing in the database. The user can modify
   * the name of the computer, the dates, and the company.
   */
  private void updateComputer() {
    long companyId = 0;
    Computer computer = null;
    boolean ok = false;

    do {
      System.out.println("\nEnter the id of the computer to update: " + "\nr : return back");
      if (REP_UTILISATEUR.hasNextLong()) {
        computer = computerService.findById(REP_UTILISATEUR.nextLong());
        // Verify if the computer exists
        if (computer.getName() != null) {

          System.out.println("\nModify the name of the computer ? y/n ");
          if (REP_UTILISATEUR.next().equals("y")) {
            System.out.println("\nEnter the new name of the computer : ");
            String name = REP_UTILISATEUR.next();
            computer.setName(name);
          }
          LocalDate intro = computer.getIntroduced();
          LocalDate discont = computer.getDiscontinued();
          boolean datesOk = false;
          do {
            // introduction date
            System.out.println("\nEnter the introduction date of the computer ? y/n");
            if (REP_UTILISATEUR.next().equals("y")) {
              intro = enterDate();
            }
            // Discondinued date
            System.out.println("\nEnter the date when computer discontinued ? y/n");
            if (REP_UTILISATEUR.next().equals("y")) {
              discont = enterDate();
            }
            try {
              DataVerification.areDatesOk(intro, discont);
              computer.setIntroduced(intro);
              computer.setDiscontinued(discont);
              datesOk = true;
            } catch (DataException e) {
              log.error(e.getMessage());
            }
          } while (!datesOk);
          // Company ID
          System.out.println("\nEnter the company ID ? y/n");
          if (REP_UTILISATEUR.next().equals("y")) {
            do {
              System.out.println("\nCompany ID ?");
              if (REP_UTILISATEUR.hasNextLong()) {
                companyId = REP_UTILISATEUR.nextLong();
              } else {
                log.error("This ID is not valid.");
              }
            } while (companyService.findById(companyId) == null);
            computer.setCompany(companyService.findById(companyId));
          }
        }
      }
      // Updating the database
      computerService.update(computer);
      ok = true;
      log.info("Modification complete");
    } while (!REP_UTILISATEUR.nextLine().trim().equals("r") && !ok);

  }

  /**
   * Verifies if the computer entered exists, and if it does, delete it. The
   * user enters the computer's ID.
   */
  private void deleteComputer() {
    boolean ok = false;

    do {
      System.out.println("\nEnter the id of the computer to delete: " + "\nr : return back");
      if (REP_UTILISATEUR.hasNextLong()) {
        Computer computer = null;
        computer = computerService.findById(REP_UTILISATEUR.nextLong());
        // Verify if the computer exists
        if (computer.getName() != null) {
          // Deletes the computer in the database
          computerService.delete(computer.getId());
          ok = true;
          log.info("\nSuppression done.");
        } else {
          log.error("This ID is not valid.");
        }
      }
    } while (!REP_UTILISATEUR.nextLine().trim().equals("r") && !ok);
  }

  /**
   * Verifies if the computer entered exists, and if it does, delete it. The
   * user enters the computer's ID.
   */
  private void deleteCompany() {
    boolean ok = false;

    do {
      System.out.println("\nEnter the id of the company to delete: " + "\nr : return back");
      if (REP_UTILISATEUR.hasNextLong()) {
        Company company = null;
        company = companyService.findById(REP_UTILISATEUR.nextLong());
        // Verify if the computer exists
        if (company.getName() != null) {
          // Deletes the computer in the database
          companyService.delete(company.getId());
          ok = true;
          log.info("\nSuppression done.");
        } else {
          log.error("This ID is not valid.");
        }
      }
    } while (!REP_UTILISATEUR.nextLine().trim().equals("r") && !ok);
  }

  /**
   * This functions asks the user to enter a date : He first enters the year,
   * then the month, and finally the day.
   * @return the object LocalDate created with those details.
   */
  private LocalDate enterDate() {
    boolean ok = false;
    int year = 0;
    int month = 0;
    int day = 0;
    do {
      System.out.println("Year ? (YYYY)");
      year = REP_UTILISATEUR.nextInt();
      System.out.println("Month ? (MM)");
      month = REP_UTILISATEUR.nextInt();
      System.out.println("Day ? (DD)");
      day = REP_UTILISATEUR.nextInt();
      ok = DataVerification.isDateCorrect(year, month, day);
      if (!ok) {
        log.error("Date format incorrect. Please retry.");
      }
    } while (!ok);

    return LocalDate.of(year, month, day);
  }

  /**
   * Stops the command-line input.
   */
  private void quit() {
    log.info("Au revoir et à bientôt !");
    ctx.close();
    REP_UTILISATEUR.close();
  }
}
