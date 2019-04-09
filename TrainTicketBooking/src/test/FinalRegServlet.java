package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class FinalRegServlet extends HttpServlet
{
	public Connection con;
	public void init() throws ServletException
	{
		con = DBConnection.getCon();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		ServletContext sct = this.getServletContext();
		UserRegBean urb = (UserRegBean)sct.getAttribute("regBean");
		try {
		PreparedStatement ps = con.prepareStatement("insert into UserTab17 values(?,?,?,?,?,?,?)");
		ps.setString(1, urb.getuName());
		ps.setString(2, urb.getpWord());
		ps.setString(3, urb.getfName());
		ps.setString(4, urb.getlName());
		ps.setString(5, urb.getAddr());
		ps.setLong(6, urb.getPhNo());
		ps.setString(7, urb.getmId());
		int k = ps.executeUpdate();
		if(k>0)
		{
			pw.println("User Registration Successful");
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req,res);
		}
		}
		catch(Exception e) {}
		
	}

}
