package test;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AdminViewTrainsServlet extends HttpServlet
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
			pw.println("Session Expired!! Please Login again.");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, res);
		}
		else
		{
			String fName = c[0].getValue();
			RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
			rd.include(req, res);
			pw.println("Welcome "+fName+"<br>");			
			try {
				PreparedStatement ps = con.prepareStatement("select * from Train17");
				ResultSet rs = ps.executeQuery();
				pw.println("<table align = 'center' width = '30%' border = '1'>");
		    	pw.println("<tr>");
		    	pw.println("<th>Train No</th>");
		    	pw.println("<th>Train Name</th>");
		    	ArrayList<TrainBean> al = new ArrayList<TrainBean>();
				while(rs.next())
				{
					TrainBean tb = new TrainBean();
					tb.settNo(rs.getString(1));
					tb.settName(rs.getString(2));
					tb.setfStation(rs.getString(3));
					tb.settStation(rs.getString(4));
					tb.setAvl(rs.getInt(5));
					al.add(tb);
					
					pw.println("<tr align = 'center'>");
		    		pw.println("<td><a href='aselect?tNo="+rs.getString(1)+"'>"+rs.getString(1)+"</a></td>");
		    		pw.println("<td>"+rs.getString(2)+"</td>");
		    		pw.println("</tr>");
				}
				pw.println("</table>");
				ServletContext sct = this.getServletContext();
				sct.setAttribute("aJcfRef",al);
			}
			
			catch(Exception e) {e.printStackTrace();}
		}
		
	}

}
