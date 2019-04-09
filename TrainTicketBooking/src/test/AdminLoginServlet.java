package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AdminLoginServlet extends HttpServlet
{
	public Connection con;
	public void init() throws ServletException
	{
		con = DBConnection.getCon();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter pw =res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("uname");
		String pWord = req.getParameter("pword");
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from AdminTab17 where Username=? and Password=?");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				String fName = rs.getString(3);
				Cookie ck = new Cookie("fName", fName);
				res.addCookie(ck);
				RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
				rd.include(req, res);
				pw.println("Welcome "+fName);
			}
			else
			{
				pw.println("Invalid Credentials! Please try again...");
				RequestDispatcher rd = req.getRequestDispatcher("Login.html");
				rd.include(req, res);
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
