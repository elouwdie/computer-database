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

  public static final Logger LOG = LoggerFactory.getLogger(EditComputer.class);

  private ComputerDto computerDTO;
  @Autowired
  CompanyService companyService;
  @Autowired
  ComputerService computerService;

  @RequestMapping(method = RequestMethod.GET)
  protected String doGet(@RequestParam("computerid") int id, Model model) {
    Locale locale = LocaleContextHolder.getLocale();
    List<Company> companies = companyService.findAll();
    Computer computer = computerService.findById(Long.valueOf(id));
    computerDTO = MapperDtoComputer.computerToDto(computer, companyService, locale.toString());

    model.addAttribute("companies", companies);
    model.addAttribute("computerDTO", computerDTO);

    return "editcomputer";
  }

  @RequestMapping(method = RequestMethod.POST)
  protected String doPost(@ModelAttribute("computerDTO") @Valid ComputerDto computerDTO,
      BindingResult bindingResult) throws MapperException {

    Locale locale = LocaleContextHolder.getLocale();
    if (bindingResult.hasErrors()) {
      LOG.error("noooooooooooooon");
      return "editcomputer";
    } else {
      Computer computer =
          MapperDtoComputer.dtoToComputer(computerDTO, companyService, locale.toString());
      System.out.println(computer.toString());
      return "redirect:/computerdb";
    }
  }
}
