<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" errorPage="error/ErrorPage.jsp"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<form action="<c:url value="/Login/Login.controller"/>" method="get">
<table>
	<tr>
		<td>ID : </td>
		<td><input type="text" name="account" value="${param.account}"></td>
		<td><span class="error">${error.account}</span></td>
	</tr>
	<tr>
		<td>PWD : </td>
		<td><input type="text" name="pwd" value="${param.pwd}"></td>
		<td><span class="error">${error.pwd}</span></td>
	</tr>
	<tr>	
		<td align="right"><input type="submit" value="Login"></td>
	</tr>

</table>
</form>
</body>
</html>