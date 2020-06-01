<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
response.setHeader("Cache-Control","no-cache,no-store,must revalidate");
if(session.getAttribute("adminusername")==null)
{
	response.sendRedirect("main.jsp");
}
%>

Welcome ${adminusername}

<H2>Create New User</H2>
<form:form action="addAdmin" modelAttribute="newAdmin" method="POST">
	<c:if test="${success != null}">
		<div style="color:blue">
			New User created!
		</div>						
	</c:if>
	<c:if test="${error != null}">
		<div style="color:red">
			Username exists!
		</div>						
	</c:if>
	<br>
	First Name : <input name="firstname" type="text" placeholder="Enter first name" required value=${firstname}>
	<br>
	<br>
	Last Name : <input name="lastname" type="text" placeholder="Enter last name" required value=${lastname}>
	<br>
	<br>
	UserId : <input name="username" type="text" placeholder="Enter username" required value=${username} >
	<br>
	<br>
	Password : <input name="password" type="password" placeholder="Enter password" required value=${password}>
	<br>
	<br>
	<input type="submit" value="Create User"/>
</form:form>

<br>
<input type="button" value="logout" onclick="window.location.href='logout'">

</body>
</html>