package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String sub = req.getParameter("sub");
		if(sub.equals("ADMIN"))
		{
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.forward(req, res);
		}
		else
		{
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.forward(req, res);
		}
	}

}
