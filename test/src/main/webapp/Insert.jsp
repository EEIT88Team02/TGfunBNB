<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"
	errorPage="error/ErrorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert</title>
</head>
<body>
	<h3>Welcome</h3>

	<h3>Insert Table</h3>

	<form action="<c:url value="/Insert.controller"/>" method="post">
		<table>
			<tr>
				<td>帳號:</td>
				<td><input type="text" name="account" value="${param.account}"></td>
				<td><span class="error">${error.account}</span><span
					id="message"></span></td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="text" name="pwd" value="${param.pwd}"></td>
				<td><span class="error">${error.pwd}</span></td>
				<td></td>
			</tr>
			<tr>
				<td>姓名 :</td>
				<td><input type="text" name="name" value="${param.name}"></td>
				<td><span class="error">${error.name}</span></td>
			</tr>
			<tr>
				<td>ID :</td>
				<td><input type="text" name="ID" value="${param.ID}"></td>
				<td><span class="error">${error.ID}</span></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><input type="text" name="Email" value="${param.Email}"></td>
				<td><span class="error">${error.Email}</span></td>
			</tr>
			<tr>
				<td>家電 :</td>
				<td><input type="text" name="Celphone"
					value="${param.Celphone}"></td>
				<td><span class="error">${error.Celphone}</span></td>
			</tr>
			<tr>
				<td>手機 :</td>
				<td><input type="text" name="Telephone"
					value="${param.Telephone}"></td>
				<td><span class="error">${error.Telephone}</span></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input type="text" name="Address" value="${param.Address}"></td>
				<td><span class="error">${error.Address}</span></td>
			</tr>
			<tr>
				<td>生日:</td>
				<td><input type="text" name="Birthday"
					value="${param.Birthday}"></td>
				<td><span class="error">${error.Birthday}</span></td>
			</tr>
			<tr>
				<td>驗證碼：</td>
				<td><img src="Kaptcha.jpg"></td>
				<td><input type="text" name="kaptchafield"></td>
				<td><span class="error">${error.parm}</span></td>
			</tr>
			<tr>
				<td><input type="submit" name="prodaction" value="Insert">
					<input type="button" value="Clear" onclick="clearForm()"></td>
			</tr>
		</table>


	</form>
	<h3>
		<span class="error">${error.action}</span>
	</h3>
</body>
</html>