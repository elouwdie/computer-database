package com.excilys.computerdb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.dao.impl.CompanyDAO;
import com.excilys.computerdb.dao.impl.ComputerDAO;
import com.excilys.computerdb.dto.ComputerDTO;
import com.excilys.computerdb.mapper.Mapper;
import com.excilys.computerdb.model.Company;

public class AddComputerMenu extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CompanyDAO companyDAO = new CompanyDAO();
		List<Company> companies = companyDAO.findAll();

		request.setAttribute("companies", companies);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/addcomputermenu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ComputerDTO computerDTO = new ComputerDTO();
		ComputerDAO computerDAO = new ComputerDAO();

		String paramName = request.getParameter("computerName");
		String paramIntroduced = request.getParameter("introduced");
		String paramDiscontinued = request.getParameter("discontinued");
		String paramCompanyId = request.getParameter("companyId");

		if (paramName != null) {
			computerDTO.setName(paramName);
		}
		if (paramIntroduced != null) {
			if (!paramIntroduced.equals("")) {
				computerDTO.setIntroduced(paramIntroduced);
			}
		}
		if (paramDiscontinued != null) {
			if (!paramDiscontinued.equals("")) {
				computerDTO.setIntroduced(paramDiscontinued);
			}
		}
		if (!paramCompanyId.equals("--")) {
			computerDTO.setCompany(Integer.parseInt(request.getParameter("companyId")));
		}

		System.out.println(Mapper.DTOToComputer(computerDTO).toString());

		doGet(request, response);
	}

}
