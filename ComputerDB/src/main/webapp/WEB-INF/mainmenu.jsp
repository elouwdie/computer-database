<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html tp://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="edit" var="edit" />
<spring:message code="confirm.delete" var="confirmDelete" />
<spring:message code="filter.name" var="filterName" />
<spring:message code="filter.company" var="filterCompany" />
<spring:message code="view" var="view" />

<script>
	var confirmDelete = '${confirmDelete}';
</script>
<script>
	var edit = '${edit}';
</script>
<script>
	var view = '${view}';
</script>

<title><spring:message code="main.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
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

	<section id="main">
		<div class="container">

			<h1 id="homeTitle">${size}

				<c:choose>
					<c:when test="${size > 1}">
						<spring:message code="computers.found" />
					</c:when>
					<c:otherwise>
						<spring:message code="computers.found.sing" />
					</c:otherwise>
				</c:choose>
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" method="GET" class="form-inline">
						<input type="hidden" name="page" value="${currentPage}" /> <input
							type="hidden" name="records" value="${records}" /> <input
							type="search" id="searchbox" name="search" value="${search}"
							class="form-control" placeholder=<spring:message code="search" /> />
						<input type="submit" id="searchsubmit" value="${filterName}"
							class="btn btn-primary" /><input type="submit"
							id="searchCompanysubmit" name="searchCompany"
							value="${filterCompany}" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addcomputer">
						<spring:message code="add" />
					</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"> ${edit} </a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="computerdb" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a
								href="javascript:$.fn.deleteSelected();" id="deleteSelected">
									<i class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="computer.name" /></th>
						<th><spring:message code="computer.intro" /></th>
						<th><spring:message code="computer.disc" /></th>
						<th><spring:message code="computer.company" /></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${computers}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="editcomputer?computerid=${computer.id}"
								onclick=""> ${computer.name} </a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.getCompanyName()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">

		<div class="btn-group btn-group-sm pull-right" role="group">
			<c:forEach var="record" items="10,50,100">
				<a type="button" class="btn btn-default"
					href=<tags:link target="computerdb" page="${currentPage}" limit="${record}"
				search="${search }" searchCompany="${searchCompany }" />>${record}</a>
			</c:forEach>
		</div>

		<div class="container text-center">
			<ul class="pagination">
				<tags:pagination currentPage="${currentPage}"
					noOfPages="${noOfPages}" />
			</ul>
		</div>

	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>