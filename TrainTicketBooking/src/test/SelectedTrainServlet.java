package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.function.*;

@SuppressWarnings("serial")
public class SelectedTrainServlet  extends HttpServlet
{
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Cookie c[] = req.getCookies();
		if(c == null)
		{
			pw.println("Perform Login process");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, res);	
		}
		else
		{
			String fName = c[0].getValue();			
			RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
			rd.include(req, res);
			pw.println("Welcome "+fName+"<br>");			
			ServletContext sct = this.getServletContext();
			ArrayList<TrainBean> al = (ArrayList<TrainBean>)sct.getAttribute("JcfRef");
			pw.println("<h4 align = 'center'>Train Details</h4>");
			pw.println("<table align = 'center'>");
			String tNo = req.getParameter("tNo");
			Predicate<TrainBean> p1 = (z)->z.gettNo().equals(tNo);
			al.forEach((k)->
			{
				TrainBean tb = (TrainBean)k;
				if(p1.test(tb))
				{
					pw.println("<tr><td>Train No </td>");
					pw.println("<td>:  "+tb.gettNo()+"</td>");
					pw.println("<tr><td>Train Name </td>");
					pw.println("<td>:  "+tb.gettName()+"</td>");
					pw.println("<tr><td>From Station </td>");
					pw.println("<td>:  "+tb.getfStation()+"</td>");
					pw.println("<tr><td>To Station </td>");
					pw.println("<td>:  "+tb.gettStation()+"</td>");
					pw.println("<tr><td>Available Seats </td>");
					pw.println("<td>:  "+tb.getAvl()+"</td>");
					pw.println("</tr></table>");					
				}
			});
			
		}
		
	}

}
