<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
<h2>Hello World!</h2>
<body>
  <table>
   <tr>
    <td><img src="Kaptcha.jpg"></td>
    <td valign="top">
     <form method="POST">
      <br>請輸入驗證碼：<input type="text" name="kaptchafield"><br />
      <input type="submit" name="submit">
     </form>
    </td>
   </tr>
  </table> 
  

  <%
   String c = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
   String parm = (String) request.getParameter("kaptchafield");
   
   out.println("Parameter: " + parm + " ? Session Key: " + c + " : ");
   
   if (c != null && parm != null) {
    if (c.equals(parm)) {
     out.println("<b>true</b>");
    } else {
     out.println("<b>false</b>");
    }
   }
  %>

 </body>
</body>
</html>
