package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class DeleteTrainServlet extends HttpServlet
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
		if(c == null)
		{
			pw.println("Session Expired Login again");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, res);
		}
		else
		{
			pw.println("Welcome "+c[0].getValue());
			RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
			rd.include(req, res);
			String tNo = req.getParameter("tNo");
			
			try {
				PreparedStatement ps = con.prepareStatement("delete from Train17 where tNo=?");
				ps.setString(1, tNo);
				int k = ps.executeUpdate();
				if(k>0)
				{
					pw.println("Train data deleted successfully");
				}
				else
				{
					pw.println("Failed to delete train. Try again.");
				}
			}catch(Exception e) {e.printStackTrace();}
		}
	}

}
