<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OrderRoom</title>
</head>
<body>
	<H3>Hello!</H3>

<form action="<c:url value="/Order/OrderRoom.Controller" />" method="post">
		<table>	
			<tr>
				<td>會員編號:</td>
				<td><input type="text" name="memberID" value="${param.memberID}"></td>
				<td><span class="error">${error.memberID}</span></td>
			</tr>
			<tr>
				<td>房間編號:</td>
				<td><input type="text" name="roomCode" value="${param.roomCode}"></td>
				<td><span class="error">${error.roomCode}</span></td>
			</tr>	
			<tr>
				<td>入住日期:</td>
				<td><input type="text" name="inDate" value="${param.inDate}"></td>
				<td><span class="error">${error.inDate}</span></td>
			</tr>
			<tr>
				<td>退房日期:</td>
				<td><input type="text" name="outDate" value="${param.outDate}"></td>
				<td><span class="error">${error.outDate}</span></td>
			</tr>
		
			<tr>
				<td><input type="hidden" name="roomCode"/></td>
				<td><span class="error">${error.insertResult}</span></td>
			</tr>
			
			<tr>
				<td><input type="submit" name="submit" value="訂房">
					<input type="reset" value="清除"></td>
			</tr>
		</table>

	</form>

</body>
</html>