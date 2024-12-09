

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
 * Servlet implementation class wirhdraw
 */
public class wirhdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wirhdraw() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String accno=request.getParameter("accno");
		String username=request.getParameter("name");
		String password=request.getParameter("psw");
		double amnt= Double.parseDouble(request.getParameter("wa"));
		
		//jdbc code
         try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qjavadb","root","0000");
			PreparedStatement ps=con.prepareStatement("select amount from bank where account_number=? And (name=? or password=?)");
			ps.setString(1,accno);
			ps.setString(2,username);
            ps.setString(3, password);
            ResultSet rs1=ps.executeQuery();          
              Double ubalance = null;
              Double balance = null;
              
            if(rs1.next()) {
             balance= Double.parseDouble(rs1.getString(1));    
            }
            ubalance=balance-amnt;
           
        	PreparedStatement ps2=con.prepareStatement("update  bank set amount=? where account_number=? ");
    		ps2.setDouble(1,ubalance);
    		ps2.setString(2,accno);
    		 int i=ps2.executeUpdate();
    		 if(i>=0) {
    			 out.print(ubalance);
    		 }
            con.close();
         } catch (Exception e) {
			out.print(e);
		}		
		
		
	}

}
