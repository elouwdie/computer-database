package com.excilys.computerdb.cli;

/**
 * Principal class. The user will enter the action he wants to do, and execute
 * it here.
 * @author ecayez
 *
 */
public class Main {

  /**
   * @param args .
   */
  public static void main(String[] args) {
    TraitementCli cli = new TraitementCli();
    cli.messageStandard();
  }
}