
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<c:url value="/Login/CheckArticle.controller"/>" method="get">
<table>
	<tr>
		<td>文章編號: </td>
		<td><input type="text" name="ArtCode" value="${Art.ArtCode}"readonly></td>
	</tr>
	<tr>
		<td>會員編號: </td>
		<td><input type="text" name="memberID" value="${Art.MemberID}"readonly></td>
		
	</tr>
	<tr>
	
		<td>檢舉次數 : </td>
		<td><input type="text" name="ReportCount" value="${Art.ReportCount}"readonly></td>

	</tr>
	<tr>
		<td>標題: </td>
		<td><input type="text" name="ArtTopic" value="${Art.ArtTopic}"readonly></td>

	</tr>
	<tr>
		<td>留言內容: </td>
		<td><input type="text" name="ArtContent" value="${Art.ArtContent}"readonly></td>
	
	</tr>
	<tr>
		<td>發問日期: </td>
		<td><input type="text" name="artDate" value="${Art.artDate}"readonly></td>

	</tr>
	<tr>
		<td>是否申訴: </td>
		<td><input type="text" name="haveAppeal" value="${Art.HaveAppeal}"readonly></td>

	</tr>
	<tr>
		<td>申訴日期 : </td>
		<td><input type="text" name="appealDate" value="${Art.appealDate}"readonly></td>

	</tr>
	<tr>
		<td>申訴內容: </td>
		<td><input type="text" name="AppealContent" value="${Art.AppealContent}"readonly></td>

	</tr>
<tr>
		<td>是否處理: </td>
		<td><input type="text" name="haveprocess" value="${Art.haveprocess}"readonly></td>

	</tr>
<tr>
		<td>處理日期: </td>
		<td><input type="text" name="processDate" value="${Art.processDate}"readonly></td>

	</tr>
<tr>
		<td>管理者回覆: </td>
		<td><input type="text" name="reportReply" value="${Art.reportReply}"readonly></td>
	
	<tr>
		<td>
				
			<input type="submit" name="prodaction" value="Article">
			<input type="button" value="Clear" onclick="clearForm()">
			
		</td>
	</tr>
</table>

</form>
</body>
</html>