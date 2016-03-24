package com.excilys.computerdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class MainMenu {

  // ***** MAIN MENU *****
  public String welcome(Model model) {
    return "redirect:/computerdb";
  }
}