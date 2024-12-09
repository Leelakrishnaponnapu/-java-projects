<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>acc trancefer</title>
</head>
<body>
<%
//part 1
String accno=request.getParameter("accno");
String uname=request.getParameter("uname");
String psw=request.getParameter("psw");
String tcac=request.getParameter("tcac");
double tamnt= Double.parseDouble(request.getParameter("tamnt"));
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qjavadb","root","0000");
PreparedStatement ps=con.prepareStatement("select amount   from bank where account_number=? And (name=? or password=?)");
ps.setString(1,accno);	
ps.setString(2,uname);
ps.setString(3, psw);

ResultSet rs=ps.executeQuery();
double balance=0;
if(rs.next()) {
    balance= Double.parseDouble(rs.getString(1));    
   } 
out.print( "user balance"+balance);
out.print("<br>");
double userbal=balance-tamnt;
PreparedStatement ps3=con.prepareStatement("update  bank set amount=? where account_number=? ");
ps3.setDouble(1,userbal);
ps3.setString(2,accno);
 int i=ps3.executeUpdate();
 if(i>0) {
	 out.print("user balance after trancefer"+userbal);
	 out.print("<br>");
	 out.print("<br>");
	 out.print("<br>");
 }
//part 2
PreparedStatement ps2=con.prepareStatement("select amount from bank where account_number=?");
ps2.setString(1,tcac);
ResultSet rs2=ps2.executeQuery();
double tbalance=0;
if(rs2.next()) {
   tbalance= Double.parseDouble(rs2.getString(1));    
   } 
out.print( " target account balance"+tbalance);
out.print("<br>");
double uuserbal=tbalance+tamnt;
PreparedStatement ps4=con.prepareStatement("update  bank set amount=? where account_number=? ");
ps4.setDouble(1,uuserbal);
ps4.setString(2,tcac);
 int j=ps4.executeUpdate();
 if(j>=0) {
	 out.print( " after tarancefer taraget  balanace is"+uuserbal);
	 out.print("<br>");
 }

%>
</body>
</html>