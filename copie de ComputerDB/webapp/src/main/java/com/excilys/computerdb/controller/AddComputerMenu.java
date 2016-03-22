package com.excilys.computerdb.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdb.dto.ComputerDto;
import com.excilys.computerdb.mapper.MapperDtoComputer;
import com.excilys.computerdb.mapper.exception.MapperException;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.service.CompanyService;

@Controller
@RequestMapping("/addcomputer")
public class AddComputerMenu {

  public static final Logger LOG = LoggerFactory.getLogger(AddComputerMenu.class);

  @Autowired
  private CompanyService companyService;

  @RequestMapping(method = RequestMethod.GET)
  protected String doGet(Model model) {
    List<Company> companies = companyService.findAll();

    ComputerDto computerDTO = new ComputerDto();
    model.addAttribute("computerDTO", computerDTO);

    model.addAttribute("companies", companies);
    return "addcomputer";
  }

  @RequestMapping(method = RequestMethod.POST)
  protected String doPost(@ModelAttribute("computerDTO") @Valid ComputerDto computerDTO,
      BindingResult bindingResult) throws MapperException {

    Locale locale = LocaleContextHolder.getLocale();
    if (bindingResult.hasErrors()) {
      LOG.error("Error creating the new computer. Number of errors : " + bindingResult.getErrorCount());
      return "addcomputer";
    } else {
      Computer computer =
          MapperDtoComputer.dtoToComputer(computerDTO, companyService, locale.toString());
      System.out.println(computer.toString());

      return "redirect:/computerdb";
    }
  }
}