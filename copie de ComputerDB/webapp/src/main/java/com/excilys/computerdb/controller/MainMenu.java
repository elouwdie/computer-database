package com.excilys.computerdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainMenu {

  @RequestMapping(method = RequestMethod.GET)
  public String welcome(Model model) {
    return "redirect:/login";
  }

}
