<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" errorPage="error/ErrorPage.jsp"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChangePwd</title>
</head>
<body>
<form action="<c:url value="/Login/ChangePwd.controller"/>" method="get">
<table>
	<tr>
		<td>帳號: </td>
		<td><input type="text" name="account" value="${param.account}"></td>
		<td><span class="error">${error.account}</span></td>
	</tr>
	<tr>
		<td>舊密碼 : </td>
		<td><input type="text" name="oldpwd" value="${param.oldpwd}"></td>
		<td><span class="error">${error.oldpwd}</span></td>
	</tr>
	<tr>
		<td>新密碼 : </td>
		<td><input type="text" name="newpwd" value="${param.newpwd}"></td>
		<td><span class="error">${error.newpwd}</span></td>
	</tr>

	<tr>
		<td>
			<input type="submit" name="prodaction" value="ChangePwd">
			
		</td>
	</tr>
	
</table>
<h3><span class="error">${error.action}</span></h3>
</form>
</body>
</html>