package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class AddTrainServlet extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Cookie c[] = req.getCookies();
		if(c == null)
		{
			pw.println("Session Expired!!");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, res);
		}
		else
		{
			String tNo = req.getParameter("tno");
			String tName = req.getParameter("tname");
			String fStation = req.getParameter("fstation");
			String tStation = req.getParameter("tstation");
			int avail = Integer.parseInt(req.getParameter("avail"));
			
			TrainBean tb = new TrainBean();
			tb.settNo(tNo);
			tb.settName(tName);
			tb.setfStation(fStation);
			tb.settStation(tStation);
			tb.setAvl(avail);
			req.setAttribute("tbean",tb);
			RequestDispatcher rd = req.getRequestDispatcher("fadd");
			rd.include(req, res);
		}
		
	}

}
