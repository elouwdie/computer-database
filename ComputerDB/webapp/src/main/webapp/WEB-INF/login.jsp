<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page session="true"%>
<html>
<head>
<title><spring:message code="main.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body onload='document.loginForm.username.focus();'>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#"> <spring:message
					code="main.title" />
			</a>
			<div align="right">
				<a href="?lang=en" class="btn btn-default"> En </a> <a
					href="?lang=fr" class="btn btn-default"> Fr </a>
			</div>
		</div>
	</header>

	<div align=center>
		<h1>Authentication</h1>


		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">

				<c:if test="${not empty error}">
					<div class="error" style="color: #ff0000;">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>

				<form name='loginForm' action="<c:url value='/login' />"
					method='POST'>
					<div class="form-group" >

						<table>
							<tr>
								<td>User:</td>
							</tr>
							<tr>
								<td><input class="form-control" type='text' name='username'></td>
							</tr>
							<tr>
								<td>Password:</td>
							</tr>
							<tr>
								<td><input class="form-control" type='password'
									name='password' /></td>
							</tr>
							<tr>
								<td align=center><input name="submit" class="btn btn-primary" type="submit"
									value="submit" /></td>
							</tr>
						</table>
					</div>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
			</div>
		</div>
	</div>
</body>
</html>