package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class FAddTrainServlet extends HttpServlet
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
		Cookie c[] = req.getCookies();
		if(c == null)
		{
			pw.println("Session Expired!!");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, res);
		}
		else
		{			
			RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
			rd.include(req, res);
			pw.println("Welcome "+c[0].getValue());
			
		    TrainBean tb = (TrainBean)req.getAttribute("tbean");
		    String tNo = tb.gettNo();
		    String tName = tb.gettName();
		    String fStation = tb.getfStation();
		    String tStation = tb.gettStation();
		    int avail = tb.getAvl();
			try {
				PreparedStatement ps = con.prepareStatement("insert into Train17 values(?,?,?,?,?)");
				ps.setString(1, tNo);
				ps.setString(2, tName);
				ps.setString(3, fStation);
				ps.setString(4, tStation);
				ps.setInt(5, avail);
				int k = ps.executeUpdate();
				if(k>0)
				{
					pw.println("<br>Train Data added Successfully");
				}
				}
			catch(Exception e) {e.printStackTrace();}
		}
	}

}
