package com.excilys.computerdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {

  public static final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

  @RequestMapping(path = "/403")
  @ResponseBody
  public String error403() {
    LOG.info("custom error handler32");
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
