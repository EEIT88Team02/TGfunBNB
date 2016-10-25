<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>我要發文</h1>
<form name="register" action="insert" method="post" >
<label>主題:<input type="text" name="theme"></label>${errortheme}<br>
內文:<br> &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp; <label><textarea rows="10" cols="30" name="context"></textarea></label>${errorcontext}
<input type="submit" value="送出">
</form>
<h1>文章留言</h1>
<form name="mesg"  action="remessage" method="post">
<label>留言文章編號<input type="text" name="articl"></label>${errorarticl}<br>
留言內容:<br> &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp; <label><textarea rows="10" cols="30" name="leave"></textarea></label>${errorleave}
<input type="submit" value="送出">
</form>


<%
HttpSession ssio=request.getSession();
ssio.setAttribute("mumber",1 ); //會員編號
System.out.print(ssio.getAttribute("rr"));
%>

<table border="0" >

<c:forEach  var="mo" items="${ArticleBean}" >
<br/><br/>
<tr>
文章編號:${mo[0]}<br/><br/>
主題:${mo[2]}
<td>作者:${mo[12]} </td><br/>
<td>日期:${mo[4] }</td>
<td>
<form action="violation" method="post">
<input type="hidden" name="artCode" value=${mo[0]}>
<input type="hidden" name="memberID" value=${mo[1]}>
<input type="submit" value="檢舉"></td>
</form>
</tr>
<tr>
<td>
內容:
</td>
<td>
${mo[3]}
</td>

</tr>
</table>
<table border="0" >
<br/>
<h2>主題回復</h2>
<c:forEach  var="mo1" items="${MessageBean}" varStatus="Loop">
<c:if test="${mo1[2]==mo[0]}">

<br/><br/>
作者:${mo1[7]}<br/>
日期:${mo1[3]}<br/>
<tr>
內容:${mo1[4]}<button id="bgood${Loop.count}"  value="${mo1[0]}">讚<div id="a${Loop.count}"  >${mo1[5]}</div></button>
<br/><br/>
</c:if>

</c:forEach>

</table>
</tr>
-----------------------------------------------------
<br/>
</c:forEach>
</table>
</body>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	var i=1;
	
	while(true){ 
	   
		var s=document.getElementById("bgood"+i);
		if(s==null)
	    break;
		 s.addEventListener("click",function(){
			 $.post("Like_taking",{msgCode:this.value,gg:this.id},function(data){
				 
 		         var dw=data.gg.split("d");
 		        var s=document.getElementById("a"+dw[1]);
 		         s.innerHTML="<div id="+dw[1]+">"+data.good+"</div>";
 		        
			 });   
	       
	   },false); 
	  i++;
	}
	});

</script>

</html>