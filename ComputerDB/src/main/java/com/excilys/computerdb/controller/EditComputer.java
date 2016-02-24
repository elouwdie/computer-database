package com.excilys.computerdb.controller;

import com.excilys.computerdb.cli.TraitementCli;
import com.excilys.computerdb.dto.ComputerDto;
import com.excilys.computerdb.mapper.MapperDtoComputer;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.CompanyService;
import com.excilys.computerdb.service.ComputerService;
import com.excilys.computerdb.validation.exception.DataException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditComputer extends HttpServlet {

  private static final long serialVersionUID = 1L;
  static Logger log;
  private ComputerDto computerDto;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    List<Company> companies = CompanyService.findAll();
    Computer computer = ComputerService.findById(Long.valueOf(request.getParameter("computerid")));
    computerDto = MapperDtoComputer.computerToDto(computer);

    request.setAttribute("companies", companies);
    request.setAttribute("computer", computerDto);

    this.getServletContext().getRequestDispatcher("/WEB-INF/editcomputer.jsp").forward(request,
        response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    computerDto = new ComputerDto();
    log = LoggerFactory.getLogger(TraitementCli.class);

    String paramName = request.getParameter("computerName");
    if (paramName != null) {
      computerDto.setName(paramName);
    }

    String paramIntroduced = request.getParameter("introduced");
    if (paramIntroduced != null) {
      if (!paramIntroduced.equals("")) {
        computerDto.setIntroduced(paramIntroduced);
      }
    }

    String paramDiscontinued = request.getParameter("discontinued");
    if (paramDiscontinued != null) {
      if (!paramDiscontinued.equals("")) {
        computerDto.setDiscontinued(paramDiscontinued);
      }
    }

    String paramCompanyId = request.getParameter("companyId");
    if (!paramCompanyId.equals("--")) {
      computerDto.setCompanyId(Integer.parseInt(request.getParameter("companyId")));
    }

    try {
      Computer computer = MapperDtoComputer.dtoToComputer(computerDto);
      System.out.println(computer.toString());
    } catch (DataException e) {
      log.error(e.getMessage());
    }

    response.sendRedirect("computerdb");
  }
}
