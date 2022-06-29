<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management Application</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
 integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" 
 crossorigin="anonymous" />
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark text-light" style="background-color: orangered;">
			<div>
				<a href="http://www.xadmin.net" class="navbar-brand">
					User Management Application
				</a>
			</div>
			<ul class="navbar-nav">
				<li>
					<a href="<%=request.getContextPath()%>/list" class="nav-link">
						User List
					</a>
				</li>
			</ul>
		</nav>
	</header>
	<br />
	
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		
		<div class="container">
			<h3 class="text-center">
				List of Users
			</h3>
			<hr />
			
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">
					Add new user
				</a>
			</div>
			<br />
			
			<table class="table table-hover table-stiped table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Country</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${ListUser}">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.firstName}" /></td>
							<td><c:out value="${user.lastName}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.country}" /></td>
							<td>
								<a href="edit?id=<c:out value='${user.id}' />"> Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="delete?id=<c:out value='${user.id}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
 integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" 
 crossorigin="anonymous"></script>
</body>
</html>