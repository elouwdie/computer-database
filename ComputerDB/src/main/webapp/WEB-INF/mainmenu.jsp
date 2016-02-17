<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html tp://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Computer Database</title>
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
			<a class="navbar-brand" href="dashboard.html"> Application -
				Computer Database </a>
		</div>
		offset sql
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${size} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="hidden" name="page" value="${currentPage}" /> <input
							type="hidden" name="records" value="${records}" /> <input
							type="search" id="searchbox" name="search" value = "${search}" class="form-control"
							placeholder="Search name" /> <input type="submit"
							id="searchsubmit" value="Filter by name" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="addcomputer">Add Computer</a> <a
						class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
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
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<th>Discontinued date</th>
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach var="computer" items="${employeeList}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="views/editComputer.html" onclick="">
									${computer.name} </a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>

							<td>${computer.getCompany().getName()}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">

		<div class="btn-group btn-group-sm pull-right" role="group">
			<a type="button" class="btn btn-default"
				href="computerdb?page=${currentPage}&records=10&search=${search}">10</a>
			<a type="button" class="btn btn-default"
				href="computerdb?page=${currentPage}&records=50&search=${search}">50</a>
			<a type="button" class="btn btn-default"
				href="computerdb?page=${currentPage}&records=100&search=${search}">100</a>
		</div>

		<div class="container text-center">
			<ul class="pagination">
				<li><a
					href="computerdb?page=${currentPage - 1}&records=${records}&search=${search}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:choose>
					<c:when test="${noOfPages - currentPage < 5}">
						<c:set var="begin" value="${currentPage}" scope="page" />
						<c:set var="end" value="${noOfPages}" scope="page" />

					</c:when>
					<c:when test="${currentPage < 6}">
						<c:set var="begin" value="1" scope="page" />
						<c:set var="end" value="9" scope="page" />

					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${currentPage - 4}" scope="page" />
						<c:set var="end" value="${currentPage + 4}" scope="page" />

					</c:otherwise>
				</c:choose>

				<c:forEach var="i" begin="${begin}" end="${end}" step="1">
					<c:choose>
						<c:when test="${currentPage eq i }">
							<li><a href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="computerdb?page=${i}&records=${records}&search=${search}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt noOfPages}">
					<li><a
						href="computerdb?page=${currentPage + 1}&records=${records}&search=${search}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>
		</div>

	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>