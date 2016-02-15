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
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Test() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ComputerDAO computerDAO = new ComputerDAO();
		Page page = new Page(computerDAO, 10);
		request.setAttribute("page", page);
		request.setAttribute("size", page.getSizeComputers());
				
		this.getServletContext().getRequestDispatcher("/WEB-INF/mainmenu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doGet(request, response);
	}
}
