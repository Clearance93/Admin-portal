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
	
	<div class="cotainer col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
					</c:if>
					<c:if test="${user == null}">
					<form action="insert" method="post">
					</c:if>
					
					<caption>
						<h2>
							<c:if test="${user != null}" >
							Edit User
							</c:if>
							<c:if test="${user == null}" >
							Add New User
							</c:if>
						</h2>
						</caption>
						
						<c:if test="${user != null}">
						<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
						</c:if>
						
						<fieldset class="form-group">
							<label>First Name</label>
							<input type="text" name="firstName" placeholder="Clearance" 
							value="<c:out value='${user.firstName}' />" 
							class="form-control" required="required" />
						</fieldset>
						<fieldset class="form-group">
							<label>Last Name</label>
							<input type="text" name="lastName" placeholder="Morumudi" 
							value="<c:out value='${user.lastName}' />" 
							class="form-control" required="required" />
						</fieldset>
						<fieldset class="form-group">
							<label>Email</label>
							<input type="email" name="email" placeholder="someone@example.com" 
							value="<c:out value='${user.email}' />" 
							class="form-control" required="required" />
						</fieldset>
						<fieldset class="form-group">
							<label>Country</label>
							<input type="text" name="country" placeholder="South Africa" 
							value="<c:out value='${user.country}' />" 
							class="form-control" required="required" />
						</fieldset>
						
						<button type="submit" class="btn btn-primary">Save</button>
					</form>
			</div>
		</div>
	</div>
	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
 integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" 
 crossorigin="anonymous"></script>
</body>
</html>