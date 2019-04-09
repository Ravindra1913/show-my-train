package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.PreparedStatement;

@SuppressWarnings("serial")
public class FEditTrainServlet extends HttpServlet 
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
			String fAvail = req.getParameter("favail");
			ServletContext sct = this.getServletContext();
			String tNo = (String)sct.getAttribute("edit");
			try {
				PreparedStatement ps = con.prepareStatement("update Train17 set avl=? where tNo=?");
				ps.setString(1, fAvail);
				ps.setString(2, tNo);
				int k = ps.executeUpdate();
				if(k>0)
				{
					pw.println("<br>Train Data Updated Succesfully!!!");
					
				}
				else
				{
					pw.println("Failed to update..Try again.");
					pw.println("<hr>");
				}
			}
			catch(Exception e) {e.printStackTrace();}
		}
		
	}
	

}
