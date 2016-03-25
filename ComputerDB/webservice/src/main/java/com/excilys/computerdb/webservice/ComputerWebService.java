package com.excilys.computerdb.webservice;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.excilys.computerdb.dto.ComputerDto;
import com.excilys.computerdb.mapper.MapperDtoComputer;
import com.excilys.computerdb.mapper.exception.MapperException;
import com.excilys.computerdb.page.Page;
import com.excilys.computerdb.service.CompanyService;
import com.excilys.computerdb.service.ComputerService;

@Path("/computers")
@Service
public class ComputerWebService {

  @Autowired
  CompanyService companyService;
  @Autowired
  ComputerService computerService;

  Page page = new Page(1, 30);

  Locale locale = LocaleContextHolder.getLocale();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ComputerDto> getAllComputers(
      @QueryParam(value = "page") Integer pageNb,
      @QueryParam(value = "records") Integer records) {
    if (pageNb != null) {
      page.setNumber(pageNb);
    }
    if (records != null) {
      page.setLimit(records);
    }
    computerService.findAll(page);
    return MapperDtoComputer.computersToDtos(page.getComputers(),
        companyService,
        locale.toString());
  }

  @Path("/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ComputerDto getComputer(
      @PathParam(value = "id") Integer id) {
    return MapperDtoComputer.computerToDto(computerService.findById(id),
        companyService,
        locale.toString());
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateUser(ComputerDto computerDto) throws IOException, MapperException{
     computerService.create(MapperDtoComputer.dtoToComputer(computerDto, companyService, locale.toString()));
  }
}