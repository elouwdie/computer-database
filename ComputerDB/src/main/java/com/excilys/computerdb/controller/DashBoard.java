package com.excilys.computerdb.controller;

import com.excilys.computerdb.cli.TraitementCli;
import com.excilys.computerdb.dao.exception.DaoException;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.ComputerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The main menu, here the user can check the list of computers,
 * add/delete/update a computer, and search a computer by name.
 */
public class DashBoard extends HttpServlet {

  private static final long serialVersionUID = 1L;
  static Logger log;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    log = LoggerFactory.getLogger(TraitementCli.class);

    try {
      String name = null;
      Page page = new Page(1, ComputerService.getCount(), 10);

      if (request.getParameter("page") != null) {
        page.setNumber(Math.max(Integer.parseInt(request.getParameter("page")), 1));
      }
      if (request.getParameter("records") != null) {
        page.setLimit(Integer.parseInt(request.getParameter("records")));
      }
      if (request.getParameter("search") != null) {
        name = request.getParameter("search");
        if (request.getParameter("searchCompany") != null) {
          page.setNbComputers(ComputerService.getCountBy(EnumSearch.COMPANY, name));
          ComputerService.findByName(EnumSearch.COMPANY, name, page);
        } else {
          page.setNbComputers(ComputerService.getCountBy(EnumSearch.NAME, name));
          ComputerService.findByName(EnumSearch.NAME, name, page);
        }
        request.setAttribute("search", request.getParameter("search"));
      } else {
        ComputerService.findAll(page);
      }

      request.setAttribute("employeeList", page.getComputers());
      request.setAttribute("noOfPages", page.getTotalNbPages());
      request.setAttribute("size", page.getNbComputers());
      request.setAttribute("currentPage", page.getNumber());
      request.setAttribute("records", page.getLimit());
    } catch (DaoException e) {
      log.error(e.getMessage());
    }

    this.getServletContext().getRequestDispatcher("/WEB-INF/mainmenu.jsp").forward(request,
        response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String id = request.getParameter("selection");
    String[] tab = id.split(",");
    for (String i : tab) {
      System.out.println("Deleting computer" + i);
    }

    doGet(request, response);
  }
}
