package com.excilys.computerdb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.dto.ComputerDTO;
import com.excilys.computerdb.exceptions.DataException;
import com.excilys.computerdb.mapper.MapperComputerDTO;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.CompanyService;

public class AddComputerMenu extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Company> companies = CompanyService.findAll();

		request.setAttribute("companies", companies);

		this.getServletContext().getRequestDispatcher("/WEB-INF/addcomputermenu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ComputerDTO computerDTO = new ComputerDTO();

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
				computerDTO.setDiscontinued(paramDiscontinued);
			}
		}
		if (!paramCompanyId.equals("--")) {
			computerDTO.setCompany(Integer.parseInt(request.getParameter("companyId")));
		}

		try {
			Computer computer = MapperComputerDTO.DTOToComputer(computerDTO);
		} catch (DataException e) {
			
		}

		doGet(request, response);
	}

}
