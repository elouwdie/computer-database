<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="AlphaNum" var="nameAlphaError" />
<spring:message code="computer.intro" var="computerIntro" />
<spring:message code="computer.name" var="computerName" />
<spring:message code="computer.disc" var="computerDisc" />
<spring:message code="Date" var="datePattern" />
<spring:message code="GreaterThan" var="datesCompError" />
<spring:message code="Name" var="nameError" />
<spring:message code="ValidDate" var="dateError" />

<script>var datesCompError = '${datesCompError}';</script>
<script>var dateError = '${dateError}';</script>
<script>var nameAlphaError = '${nameAlphaError}';</script>
<script>var nameError = '${nameError}';</script>
<script>var pattern = '${datePattern}';</script>

<title><spring:message code="main.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="computerdb"> <spring:message
					code="main.title" />
			</a>
            <div align="right">
                <a href="#&lang=en" class="btn btn-default"> En </a> <a
                    href="#&lang=fr" class="btn btn-default"> Fr </a>
            </div>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computerDTO.id}</div>
					<h1>
						<spring:message code="edit.computer" />
					</h1>

					<form:form action="editcomputer" modelAttribute="computerDTO" id="add"
						method="POST" novalidate="novalidate">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message
										code="computer.name" /></label>
								<form:input name="computerName" type="text" class="form-control"
									id="computerName" path="name" placeholder="${computerName}" value="${computerDTO.name}" />
								<form:errors path="name" cssClass="error" cssStyle="color: #ff0000;"/>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message
										code="computer.intro" /></label>
								<form:input name="introduced" type="date" class="form-control"
									id="introduced" path="introduced"
									placeholder="${computerIntro} (${datePattern })"  value="${computerDTO.introduced}" />
								<form:errors path="introduced" cssClass="error" cssStyle="color: #ff0000;"/>
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message
										code="computer.disc" /></label>
								<form:input name="discontinued" type="date" class="form-control"
									id="discontinued" path="discontinued"
									placeholder="${computerDisc} (${datePattern })" value="${computerDTO.discontinued}"  />
								<form:errors path="discontinued" cssClass="error" cssStyle="color: #ff0000;"/>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="computer.company" /></label>
								<form:select name="companyId" class="form-control"
									path="companyId" id="companyId">
									<option value="0">--</option>
									<c:forEach var="company" items="${companies}">
										<c:choose>
											<c:when test="${company.id == computerDTO.companyId}">
												<option value="${company.id}" selected="selected">${company.getName()}</option>
											</c:when>
											<c:otherwise>
												<option value="${company.id}">${company.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value=<spring:message code="edit" />
								class="btn btn-primary">
							<spring:message code="or" />
							<a href="computerdb" class="btn btn-default"><spring:message
									code="cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/additional-methods.js"></script>
	<script src="js/verify-computer.js"></script>
</body>
</html>