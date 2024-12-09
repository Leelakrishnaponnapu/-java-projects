<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>close account</title>
</head>
<body>
<%

String accountno=request.getParameter("accno");
String name=request.getParameter("uname");
String password=request.getParameter("psw");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qjavadb","root","0000");
PreparedStatement ps= con.prepareStatement("update bank set status=1 where accountno=? or( name=? and password=?)");
ps.setString(1, accountno);
ps.setString(2, name);
ps.setString(3, password);

int i=ps.executeUpdate();
 if(i>0)
 {
	 out.print("Succesfully Closed....");
 }

%>
</body>
</html>