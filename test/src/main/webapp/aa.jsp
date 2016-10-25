<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
        <form name="register" action="bb" method="post">
            <tr>
                <td>姓名</td>
                <td><input name="userName" type="text"></input>${userNameGot}</td>
            </tr>        
            <tr>
                <td>年齡</td>
                <td><input name="age" type="text"></input></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"></input></td>
            </tr>
        </form>
    <table>
</body>
</html>