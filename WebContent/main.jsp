<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<h2>Login Form</h2>
<form:form action="loginValidation" modelAttribute="theadmin" method="POST">

	<c:if test="${loginerror != null}">
		<div style="color:red">
			Invalid credentials!
		</div>				
	</c:if>
	<br>
	UserId : <input name="username" type="text" placeholder="Enter username" required>
	<br>
	<br>
	Password : <input name="password" type="password" placeholder="Enter password" required>
	<br>
	<br>
	<input type="submit" value="Login"/>
</form:form>

</body>
</html>