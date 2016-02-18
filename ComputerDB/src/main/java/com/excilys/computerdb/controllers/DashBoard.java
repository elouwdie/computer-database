package com.excilys.computerdb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.pages.Page;
import com.excilys.computerdb.service.ComputerService;

/**
 * The main menu, here the user can check the list of computers,
 * add/delete/update a computer, and search a computer by name.
 */
public class DashBoard extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DashBoard() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			page.setNbComputers(ComputerService.getCountByName(name));
			ComputerService.findByName(name, page);
			request.setAttribute("search", request.getParameter("search"));
		}

		else {
			ComputerService.findAll(page);
		}

		request.setAttribute("employeeList", page.getComputers());
		request.setAttribute("noOfPages", page.getTotalNbPages());
		request.setAttribute("size", page.getNbComputers());
		request.setAttribute("currentPage", page.getNumber());
		request.setAttribute("records", page.getLimit());

		this.getServletContext().getRequestDispatcher("/WEB-INF/mainmenu.jsp").forward(request, response);
	}
}
