package test;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class UserLoginServlet extends HttpServlet
{
	public Connection con;
	public void init() throws ServletException
	{
		con = DBConnection.getCon();	
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		String uName = req.getParameter("uname");
		String pWord = req.getParameter("pword");
		try
		{
			PreparedStatement ps = con.prepareStatement("select * from UserTab17 where Username=? and Password =?");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{				
				Cookie ck = new Cookie("fName", rs.getString(3));
				res.addCookie(ck);
				RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
				rd.include(req, res);
				pw.println("Welcome "+rs.getString(3));
			}
			else
			{
				pw.println("Invalid Credentials. Please Login again");
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
			}
			
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
