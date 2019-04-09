package test;
import java.io.*;
import java.util.*;
import java.util.function.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AdminSelectedTrainServlet extends HttpServlet
{
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Cookie c[] = req.getCookies();
		if(c == null)
		{
			pw.println("Session Expired! Login again.");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, res);
		}
		else
		{			
			RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
			rd.include(req, res);
			pw.println("Welcome "+c[0].getValue());
			pw.println("<table align = 'center'>");
			String tNo = req.getParameter("tNo");
			ServletContext sct = this.getServletContext();
			ArrayList<TrainBean> al = (ArrayList<TrainBean>)sct.getAttribute("aJcfRef");
			Predicate<TrainBean> p =(z)->z.gettNo().equals(tNo);
			al.forEach((k)->
			{
				TrainBean tb =(TrainBean) k;
				if(p.test(tb))
				{
					pw.println("<tr><td>Train No </td>");
					pw.println("<td>:  "+tb.gettNo()+"</td>");
					pw.println("</tr><tr><td>Train Name </td>");
					pw.println("<td>:  "+tb.gettName()+"</td>");
					pw.println("</tr><tr><td>From Station </td>");
					pw.println("<td>:  "+tb.getfStation()+"</td>");
					pw.println("</tr><tr><td>To Station </td>");
					pw.println("<td>:  "+tb.gettStation()+"</td>");
					pw.println("</tr><tr><td>Available Seats </td>");
					pw.println("<td>:  "+tb.getAvl()+"</td>");
					pw.println("</tr><tr align = 'center'><td>");
					pw.println("<a href='edit?tNo="+tb.gettNo()+"'><input type = 'button' value = 'Edit'></a></td>");
					pw.println("<td>");
					pw.println("<a href='delete?tNo="+tb.gettNo()+"'><input type = 'button' value = 'Delete'></a></td>");
					pw.println("</tr></table>");
					
					
					//pw.println("TrainNo: "+tb.gettNo()+"&nbsp&nbsp"+"Train Name: "+tb.gettName()+"&nbsp&nbsp"+"From Station: "+tb.getfStation()+"&nbsp&nbsp"+"To Station: "+tb.gettStation()+"&nbsp&nbsp"+"Avalilable Berths: "+tb.getAvl());
					//pw.println("<br>");
					//pw.println("<a href='edit?tNo="+tb.gettNo()+"'>Edit</a>");
				//	pw.println("&nbsp&nbsp");
					//pw.println("<a href='delete?tNo="+tb.gettNo()+"'>Delete</a>");
				//	pw.println("<hr>");
				}
			});
			
		}
		
		
		
		
	}

}
