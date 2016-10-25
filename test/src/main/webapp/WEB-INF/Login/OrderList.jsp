<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/OrderList.controller"/>" method="post">
		<table>
			<c:forEach var="h" items="${list}">
				<tr>
					<td>訂單編號:</td>
					<td><span>${h.orderID}</span></td>
				</tr>
				<tr>
					<td>會員編號:</td>
					<td><span>${h.memberID}</span></td>
				</tr>
				<tr>
					<td>會員訂單日期:</td>
					<td><span>${h.memberDate}</span></td>
				</tr>
				<tr>
					<td>房間名稱:</td>
					<td><span>${h.orderRoomInfos.roomInfos.roomName}</span></td>
				</tr>
				<tr>
					<td>入住日期:</td>
					<td><span>${h.orderRoomInfos.inDate}</span></td>
				</tr>
				<tr>
					<td>退房日期:</td>
					<td><span>${h.orderRoomInfos.outDate}</span></td>
				</tr>
				<tr>
					<td>房間個別金額:</td>
					<td><span>${h.orderRoomInfos.roomSum}</span></td>
				</tr>
				<tr>
					<td>房間總金額:</td>
					<td><span>${h.roomTotalSum}</span></td>
				</tr>
				<tr>
					<td>烤肉區編號:</td>
					<td><span>${h.bbqOrders.bbqOrderID}</span></td>
				</tr>
				<tr>
					<td>烤肉日期:</td>
					<td><span>${h.bbqOrders.bbqDate}</span></td>
				</tr>
				<tr>
					<td>訂單編號:</td>
					<td><span>${h.bbqOrders.foodorderbean.foodOrderID}</span></td>
				</tr>
				<tr>
					<td>訂單日期:</td>
					<td><span>${h.bbqOrders.foodorderbean.foodDate}</span></td>
				</tr>
				<tr>
					<td>訂單金額:</td>
					<td><span>${h.bbqOrders.foodorderbean.totalSum}</span></td>
				</tr>
				<tr>
					<td>會員總金額:</td>
					<td><span>${h.memberSum}</span></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>商品名稱:</td> -->
<%-- 					<td><span>${h.bbqOrders.foodorderbean.foodOrderInfo.productInfo.ProdName}</span></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>數量:</td> -->
<%-- 					<td><span>${h.bbqOrders.foodorderbean.foodOrderInfo.FoodCount}</span></td> --%>
<!-- 				</tr> -->

			</c:forEach>
			<tr>
				<td><input type="submit" name="prodaction" value="List">
					<input type="button" value="Clear"></td>
			</tr>
		</table>
	</form>
</body>
</html>




