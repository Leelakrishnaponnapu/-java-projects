

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginCode
 */
public class loginCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginCode() {
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
			String name=request.getParameter("username");
			String password=request.getParameter("psw");
			 Class.forName("com.mysql.cj.jdbc.Driver");
			  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qjavadb","root","0000");
			  PreparedStatement ps=con.prepareStatement("select * from users where  name=? and password=?");
			  ps.setString(1, name);
			  ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				response.sendRedirect("userhome.html");
			}
			else {
				response.sendRedirect("wrong.html");	
			}
			con.close();
			
		} catch (Exception e) {
			out.print(e);
		}
	}

}
