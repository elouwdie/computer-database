<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="views/dashboard.html"> Application
				- Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="addcomputer" id="add" method="POST"
						novalidate="novalidate">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									name="computerName" type="text" class="form-control"
									id="computerName" placeholder="Computer name">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									name="introduced" type="date" class="form-control"
									id="introduced" placeholder="Introduced date">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									name="discontinued" type="date" class="form-control"
									id="discontinued" placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select name="companyId"
									class="form-control" id="companyId">
									<option value="0">--</option>
									<c:forEach var="i" begin="1" end="${companies.size()}" step="1">
										<option value="${i}">${companies.get(i - 1).getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="computerdb" class="btn btn-default">Cancel</a>
						</div>
					</form>
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