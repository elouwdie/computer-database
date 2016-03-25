package com.excilys.computerdb.cli;

import com.excilys.computerdb.mapper.exception.MapperException;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

/**
 * Principal class. The user will enter the action he wants to do, and execute
 * it here.
 * @author ecayez
 *
 */
public class Main {

  /**
   * @param args : Not used here.
   * @throws MapperException 
   * @throws UniformInterfaceException 
   * @throws ClientHandlerException 
   */
  public static void main(String[] args) throws ClientHandlerException, UniformInterfaceException, MapperException {

    TraitementCli cli = new TraitementCli();
    cli.messageStandard();
  }
}