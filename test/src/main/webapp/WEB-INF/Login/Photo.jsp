<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="<c:url value="/Login/Photo.controller"/>" enctype="multipart/form-data" name="from1">
Name:<input type="text" name="name"/>
Address:<input type="text" name="address"/>
image:<input type="file" name="file"/>
<input type="submit" value="go"/>



</form>
</body>
</html>