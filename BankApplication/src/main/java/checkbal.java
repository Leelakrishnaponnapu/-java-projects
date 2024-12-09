

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class checkbal
 */
public class checkbal extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public checkbal() {
		super();

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	
		PrintWriter out=response.getWriter();
		try {
			String accno= request.getParameter("accno");
			String name= request.getParameter("uname");
			String pswd= request.getParameter("psw");

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qjavadb","root","0000");
			PreparedStatement ps=con.prepareStatement("select account_number,name,amount  from bank where account_number=? And (name=? or password=?)");
			ps.setString(1,accno);	
			ps.setString(2,name);
			ps.setString(3, pswd);
			ResultSet rs=ps.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            int n=rss.getColumnCount();
        	out.print("<html><table border=2  color=black>");
			for (int i = 1; i<=n; i++) {
				out.print("<td><font color=blue size=3>"+"<br>"+rss.getColumnName(i));
			}
			out.print("<tr>");
			if(rs.next()) {
				for (int i = 1; i<=n; i++) {
					out.print("<td><br>"+rs.getString(i));
				}	
			}
			out.print("<tr>");
			out.print("</table></html>");
			con.close();

		} catch (Exception e) {
			out.print(e);
		}

	}

}
