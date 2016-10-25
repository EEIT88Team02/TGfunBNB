<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" errorPage="error/ErrorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Update Table</h3>
<form action="<c:url value="/Login/Update.controller"/>" method="get">
<table>
	<tr>
		<td>帳號: </td>
		<td><input type="text" name="account" value="${user.account}"></td>
		<td><span class="error">${error.account}</span><span id="message"></span></td>
	</tr>
	<tr>
		<td>密碼: </td>
		<td><input type="text" name="pwd" value="${user.pwd}"></td>
		<td></td>
	</tr>
	<tr>
		<td>姓名 : </td>
		<td><input type="text" name="name" value="${user.name}"></td>
		<td><span class="error">${error.name}</span></td>
	</tr>
	<tr>
		<td>性別 : </td>
		<td><input type="text" name="Sex" value="${user.sex}"></td>
		<td><span class="error">${error.Email}</span></td>
	</tr>
	<tr>
		<td>生日: </td>
		<td><input type="text" name="Birthday" value="${user.birthday}"></td>
		<td><span class="error">${error.Birthday}</span></td>
	</tr>
	<tr>
		<td>ID : </td>
		<td><input type="text" name="ID" value="${user.ID}"></td>
		<td><span class="error">${error.ID}</span></td>
	</tr>
	
	<tr>
		<td>Email : </td>
		<td><input type="text" name="Email" value="${user.email}"></td>
		<td><span class="error">${error.Email}</span></td>
	</tr>
	<tr>
		<td>家電 : </td>
		<td><input type="text" name="Celphone" value="${user.celphone}"></td>
		<td><span class="error">${error.Celphone}</span></td>
	</tr>
	<tr>
		<td>手機 : </td>
		<td><input type="text" name="Telephone" value="${user.telephone}"></td>
		<td><span class="error">${error.Telephone}</span></td>
	</tr>
	<tr>
		<td>地址: </td>
		<td><input type="text" name="Address" value="${user.address}"></td>
		<td><span class="error">${error.Address}</span></td>
	</tr>
	<tr>
		<td>照片: </td>
		<td><input type="text" name="photo" value="${user.photo}"></td>
		<td><span class="error">${error.Address}</span></td>
	</tr>
	<tr>
		<td>vip: </td>
		<td><input type="text" name="vip" value="${user.vip}"></td>
		<td><span class="error">${error.Address}</span></td>
	</tr>
	<tr>
		<td>Bonus: </td>
		<td><input type="text" name="Bouns" value="${user.bonus}"></td>
		<td><span class="error">${error.Address}</span></td>
	</tr>
	<tr>
		<td>TotalBouns: </td>
		<td><input type="text" name="TotalBouns" value="${user.totalBonus}"></td>
		<td><span class="error">${error.Address}</span></td>
	</tr>

	<tr>
		<td>
			<input type="submit" name="prodaction" value="Update">
			<input type="button" value="Clear" onclick="clearForm()">
			
		</td>
	</tr>
</table>

</form>
</body>
</html>