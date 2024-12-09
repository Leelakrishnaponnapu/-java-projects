

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ElectricityBill
 */
public class ElectricityBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ElectricityBill() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String cid=request.getParameter("conusumerid");
		String cname=request.getParameter("conusumername");
		int PUnits=Integer.parseInt(request.getParameter("previousReading"));
		int CUnits=Integer.parseInt(request.getParameter("currentReading"));
		int units=CUnits-PUnits;
		double bill=0;
		if(units<500) {
			bill=units*1.75;
			}
		else if(units>=500&&units<700) {
			bill=units*5.25;
			}
		else if(units>=700) {
			bill=units*9.22;
		}

		out.print("consumerID  :"+cid+"<br><br>");
		out.print("consumername  :"+cname+"<br><br>");
		out.print("previous Units  :"+PUnits+"<br><br>");
		out.print("currentReading  :"+CUnits+"<br><br>");
		out.print("consumed Units  :"+units+"<br><br>");
		out.print("Bill  :  "+bill+"<br><br>");
		}

}
