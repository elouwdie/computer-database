package com.excilys.computerdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdb.service.impl.UserService;

@Controller
@RequestMapping("/login")
public class Login {

  public static final Logger LOG = LoggerFactory.getLogger(Login.class);
  @Autowired
  UserService userService;

  @RequestMapping(method = RequestMethod.GET)
  public String login(@RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout, Model model) {

    if (error != null) {
      model.addAttribute("error", "Invalid username and password!");
    }

    if (logout != null) {
      model.addAttribute("msg", "You've been logged out successfully.");
    }

    return "login";
  }
}
