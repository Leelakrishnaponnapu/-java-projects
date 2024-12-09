

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
import java.sql.ResultSet;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String accno=request.getParameter("accno");
		String username=request.getParameter("name");
		String password=request.getParameter("psw");
		//jdbc code
         try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qjavadb","root","0000");
			PreparedStatement ps=con.prepareStatement("select * from bank where account_number=? And (name=? or password=?)");
			ps.setString(1,accno);
			ps.setString(2,username);
            ps.setString(3, password);
            ResultSet rs=ps.executeQuery();
           
            if(rs.next()) {
                  response.sendRedirect("userLogin.html");
            }
            else {
            	  response.sendRedirect("wrongLogin.html");
            }
            con.close();
         } catch (Exception e) {
			out.print(e);
		}	
		
		
	}

}
