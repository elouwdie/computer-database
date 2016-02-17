package com.excilys.computerdb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.dao.impl.ComputerDAO;
import com.excilys.computerdb.pages.Page;

/**
 * Servlet implementation class Test
 */
@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public DashBoard() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		int recordsPerPage = 10;
	
		if (request.getParameter("page") != null) {
			page = Math.max(Integer.parseInt(request.getParameter("page")), 1);
		}
		if (request.getParameter("records") != null) {
			recordsPerPage = Integer.parseInt(request.getParameter("records"));
		}
		
		ComputerDAO computerDAO = new ComputerDAO();
		Page pages = new Page(computerDAO, recordsPerPage);

		if (request.getParameter("search") != null) {
			pages.setName(request.getParameter("search"));
			request.setAttribute("employeeList", pages.getPage(Math.max(0, (page - 1) * recordsPerPage), recordsPerPage));
			request.setAttribute("search", request.getParameter("search"));
		}

		else {
			request.setAttribute("employeeList",
					pages.getPage(Math.max(0, (page - 1) * recordsPerPage), recordsPerPage));
			request.setAttribute("noOfPages", pages.getTotalNbPages());
		}
		
		request.setAttribute("noOfPages", pages.getTotalNbPages());
		request.setAttribute("size", pages.getSizeComputers());
		request.setAttribute("currentPage", page);
		request.setAttribute("records", recordsPerPage);

		this.getServletContext().getRequestDispatcher("/WEB-INF/mainmenu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
