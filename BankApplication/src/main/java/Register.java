

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
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
		String accno=request.getParameter("accno");
		String username=request.getParameter("uname");
		String password=request.getParameter("psw");
		String amount=request.getParameter("ob");
		String address=request.getParameter("address");
		String mobileno=request.getParameter("mno");
		String status="0";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qjavadb","root","0000");
			PreparedStatement ps=con.prepareStatement("insert into bank values(?,?,?,?,?,?,?)");
			ps.setString(1,accno);
			ps.setString(2,username); 
			ps.setString(3,password); 
			ps.setString(4,amount); 
			ps.setString(5,address); 
			ps.setString(6,mobileno);
			ps.setString(7,status);
			int i=ps.executeUpdate();
			if(i>=0) {
				out.print("insertd sucess");
              response.sendRedirect("home.html");
			}
			else {
				out.print("not insertd");
			}
			con.close();

		} catch (Exception e) {

			out.print(e);
		}

	}

}
