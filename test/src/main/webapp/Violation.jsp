<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>您覺得這則文章違規原因是:</h1>


<form action="violationcheck" method="post">
<input type="hidden" name="artCode" value=${artCode}>
<input type="hidden" name="ProsecutorID" value=${ProsecutorID}>
<input type="hidden" name="By_prosecutorID" value=${By_prosecutorID}>
<!-- 
<p>文章編號(隱藏欄位)</p>
<P>檢舉人會員編號(隱藏欄位)</P>
<p>被檢舉人會員編號(隱藏欄位)</p>      
  -->  
     檢舉內容    
    <textarea  rows="10" cols="30" name="textvalue">
    </textarea> 
   <input type="submit" value="送出">
  </form>
</body>
</html>