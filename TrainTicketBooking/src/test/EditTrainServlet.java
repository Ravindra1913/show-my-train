package test;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Predicate;

import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class EditTrainServlet extends HttpServlet
{
	public Connection con;
	public void init() throws ServletException
	{
		con = DBConnection.getCon();
	}
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Cookie c[] = req.getCookies();
		if(c == null)
		{
			pw.println("Session Expired!! Please Login again");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, res);
		}
		else
		{			
			RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
			rd.include(req, res);
			pw.println("Welcome "+c[0].getValue());
			String tNo = req.getParameter("tNo");
			pw.println("<table align = 'center'>");
			ServletContext sct = this.getServletContext();
			ArrayList<TrainBean> al = (ArrayList<TrainBean>)sct.getAttribute("aJcfRef");
			sct.setAttribute("edit", tNo);
			Predicate<TrainBean> p =(z)->z.gettNo().equals(tNo);
			al.forEach((k)->
			{
				TrainBean tb =(TrainBean) k;
				if(p.test(tb))
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
					pw.println("<br>");
					//pw.println("TrainNo: "+tb.gettNo()+"&nbsp&nbsp"+"Train Name: "+tb.gettName()+"&nbsp&nbsp"+"From Station: "+tb.getfStation()+"&nbsp&nbsp"+"To Station: "+tb.gettStation()+"&nbsp&nbsp"+"Avalilable Berths: "+tb.getAvl());
					//pw.println("<br>");
					RequestDispatcher rd1 = req.getRequestDispatcher("EditTrain.html");
					try {
						rd1.include(req, res);
					} catch (ServletException | IOException e) {e.printStackTrace();}
				}
			});
			
			
		}
		
	}

}
