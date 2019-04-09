package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Logout extends HttpServlet
{
	public Connection con;
	public void init() throws ServletException
	{
		con = DBConnection.getCon();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
	    Cookie c[] = req.getCookies();
	    c[0].setValue("");
	    c[0].setMaxAge(0);
	    res.addCookie(c[0]);
	    pw.println("Loggedout Successfully");
	    RequestDispatcher rd = req.getRequestDispatcher("Login.html");
	    rd.include(req, res);
	}
	public void destroy()
	{
		if(con!=null)
		{
			try {
			con.close();
			}
			catch(Exception e) {e.printStackTrace();}
		}
	}

}
