package com.excilys.computerdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {

  public static final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

  // for 403 access denied page
  @RequestMapping(value = "/403", method = RequestMethod.GET)
  @ResponseBody
  public String accesssDenied(Model model) {

    // check if user is login
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (!(auth instanceof AnonymousAuthenticationToken)) {
      UserDetails userDetail = (UserDetails) auth.getPrincipal();
      System.out.println(userDetail);

      model.addAttribute("username", userDetail.getUsername());

    }

    return "403";

  }

  @RequestMapping(path = "/404")
  @ResponseBody
  public String error404() {
    LOG.info("custom error handler4");
    return "404";
  }

  @RequestMapping(path = "/500")
  @ResponseBody
  public String error500() {
    LOG.info("custom error handler5");
    return "500";
  }

}
