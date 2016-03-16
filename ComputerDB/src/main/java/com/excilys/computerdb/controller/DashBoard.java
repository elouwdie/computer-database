package com.excilys.computerdb.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdb.enumeration.EnumSearch;
import com.excilys.computerdb.mapper.MapperDtoComputer;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.CompanyService;
import com.excilys.computerdb.service.ComputerService;

/**
 * The main menu, here the user can check the list of computers,
 * add/delete/update a computer, and search a computer by name.
 */
@Controller
@RequestMapping("/computerdb")
public class DashBoard {

  public static final Logger LOG = LoggerFactory.getLogger(DashBoard.class);
  @Autowired
  CompanyService companyService;
  @Autowired
  ComputerService computerService;

  @RequestMapping(method = RequestMethod.GET)
  protected String doGet(@RequestParam(value = "page", required = false) Integer pageNb,
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

  @RequestMapping(method = RequestMethod.POST)
  protected String doPost(@RequestParam(value = "selection", required = false) String selection,
      Model model) {
    String id = selection;
    String[] tab = id.split(",");
    for (String i : tab) {
      System.out.println("Deleting computer " + i);
    }
    return doGet(null, null, null, null, model);
  }
}