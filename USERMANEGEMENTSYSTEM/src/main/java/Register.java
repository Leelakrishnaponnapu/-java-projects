

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
		 String name= request.getParameter("name");
		 String password= request.getParameter("pass");
		 String mail= request.getParameter("mail");
		 String gender= request.getParameter("gen");
		 String address= request.getParameter("add");
		 String State= request.getParameter("state");
		 String country= request.getParameter("cou");
		 String number= request.getParameter("no");
		 
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/qjavadb","root","0000");
			PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,password);
			ps.setString(3,mail);
			ps.setString(4,gender);
			ps.setString(5,address);
			ps.setString(6,State);
			ps.setString(7,country);
			ps.setString(8,number);
			int i=ps.executeUpdate();
			if(i>=0) {
				out.print("insertd sucess");
				response.sendRedirect("home.html");
			}
			else {
				out.print("not insertd");
			}
			con.close();
		}catch (Exception e) {
	out.print(e);
		}
		
		

}}
