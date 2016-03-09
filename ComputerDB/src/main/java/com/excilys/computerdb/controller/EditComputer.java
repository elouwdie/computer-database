package com.excilys.computerdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdb.dto.ComputerDto;
import com.excilys.computerdb.mapper.MapperDtoComputer;
import com.excilys.computerdb.mapper.exception.MapperException;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.CompanyService;
import com.excilys.computerdb.service.ComputerService;

@Controller
@RequestMapping("/editcomputer")
public class EditComputer {

  static Logger log;
  private ComputerDto computerDto;
  @Autowired
  CompanyService companyService;
  @Autowired
  ComputerService computerService;

  @RequestMapping(method = RequestMethod.GET)
  protected String doGet(@RequestParam("computerid") int id, Model model)
      throws ServletException, IOException {

    List<Company> companies = companyService.findAll();
    Computer computer = computerService.findById(Long.valueOf(id));
    computerDto = MapperDtoComputer.computerToDto(computer);

    model.addAttribute("companies", companies);
    model.addAttribute("computer", computerDto);

    return "editcomputer";
  }

  @RequestMapping(method = RequestMethod.POST)
  protected String doPost(@ModelAttribute("computerDTO") @Valid ComputerDto computerDTO,
      ModelMap model) throws MapperException {

    Computer computer = MapperDtoComputer.dtoToComputer(computerDTO, companyService);
    System.out.println(computer.toString());

    return "editcomputer";
  }
}
