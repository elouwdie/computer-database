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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdb.dto.ComputerDto;
import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.mapper.MapperDtoComputer;
import com.excilys.computerdb.mapper.exception.MapperException;
import com.excilys.computerdb.model.Company;
import com.excilys.computerdb.model.Computer;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.CompanyService;
import com.excilys.computerdb.service.ComputerService;

/**
 * The main menu, here the user can check the list of computers,
 * add/delete/update a computer, and search a computer by name.
 */
@Controller
public class DashBoard {

  public static final Logger LOG = LoggerFactory.getLogger(DashBoard.class);

  private ComputerDto computerDTO;

  @Autowired
  CompanyService companyService;
  @Autowired
  ComputerService computerService;

  // ***** MAIN MENU *****
  @RequestMapping("/")
  public String welcome(Model model) {
    return "redirect:/computers";
  }

  // ***** SEE ALL COMPUTERS *****
  @RequestMapping(path = "/computers", method = RequestMethod.GET)
  protected String seeAll(@RequestParam(value = "page", required = false) Integer pageNb,
      @RequestParam(value = "records", required = false) Integer records,
      @RequestParam(value = "search", required = false) String search,
      @RequestParam(value = "searchCompany", required = false) String searchCompany,
      Model model) {
    Locale locale = LocaleContextHolder.getLocale();
    String name = null;
    Page page = new Page(1, computerService.getCount(), 10);

    if (pageNb != null) {
      page.setNumber(Math.max(pageNb, 1));
    }

    if (records != null) {
      page.setLimit(records);
    }

    if (search != null) {
      name = search;
      if (searchCompany != null) {
        page.setNbComputers(computerService.getCountBy(EnumSearch.COMPANY, name));
        computerService.findByName(EnumSearch.COMPANY, name, page);
      } else {
        page.setNbComputers(computerService.getCountBy(EnumSearch.NAME, name));
        computerService.findByName(EnumSearch.NAME, name, page);
      }
      model.addAttribute("search", search);
    } else {
      computerService.findAll(page);
    }

    model.addAttribute("computers",
        MapperDtoComputer.computersToDtos(page.getComputers(), companyService, locale.toString()));
    model.addAttribute("noOfPages", page.getTotalNbPages());
    model.addAttribute("size", page.getNbComputers());
    model.addAttribute("currentPage", page.getNumber());
    model.addAttribute("records", page.getLimit());

    return "mainmenu";
  }

  // ***** EDIT COMPUTER *****
  @RequestMapping(path = "/computers/{id}", method = RequestMethod.GET)
  protected String editComputerGet(@PathVariable("id") int id, Model model) {
    Locale locale = LocaleContextHolder.getLocale();
    List<Company> companies = companyService.findAll();
    Computer computer = computerService.findById(Long.valueOf(id));
    computerDTO = MapperDtoComputer.computerToDto(computer, companyService, locale.toString());

    model.addAttribute("companies", companies);
    model.addAttribute("computerDTO", computerDTO);

    return "editcomputer";
  }

  @RequestMapping(path = "/computers/{id}", method = RequestMethod.POST)
  protected String editComputerPost(@ModelAttribute("computerDTO") @Valid ComputerDto computerDTO,
      BindingResult bindingResult) throws MapperException {

    Locale locale = LocaleContextHolder.getLocale();
    if (bindingResult.hasErrors()) {
      return "editcomputer";
    } else {
      Computer computer =
          MapperDtoComputer.dtoToComputer(computerDTO, companyService, locale.toString());
      System.out.println(computer.toString());
      return "redirect:/computers";
    }
  }

  // ***** ADD COMPUTER *****
  @RequestMapping(path = "/computers/new", method = RequestMethod.GET)
  protected String addComputerGet(Model model) {
    List<Company> companies = companyService.findAll();

    ComputerDto computerDTO = new ComputerDto();
    model.addAttribute("computerDTO", computerDTO);

    model.addAttribute("companies", companies);
    return "addcomputer";
  }

  @RequestMapping(path = "/computers/new", method = RequestMethod.POST)
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

      return "redirect:/computers";
    }
  }
  
  // ***** DELETE COMPUTER *****
  @RequestMapping(path = "/computers/delete", method = RequestMethod.POST)
  protected String doPost(@RequestParam(value = "selection", required = false) String selection) {
    String id = selection;
    String[] tab = id.split(",");
    for (String i : tab) {
      System.out.println("Deleting computer " + i);
    }
    return "redirect:/computers";
  }
}