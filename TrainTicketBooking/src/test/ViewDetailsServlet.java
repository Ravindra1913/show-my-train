package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ViewDetailsServlet extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
	    PrintWriter pw = res.getWriter();
	    res.setContentType("text/html");
	    ServletContext sct = this.getServletContext();
	    UserRegBean urb = (UserRegBean)sct.getAttribute("regBean");
	    pw.println("<br><h1 align = 'center'>Online Book Store</h1><br>");
	    pw.println("<table align = 'center'>");
	    pw.println("<tr><td>Username:</td>");
	    pw.println("<td>"+urb.getuName()+"</td>");
	    pw.println("<tr><td>Password:</td>");
	    pw.println("<td>"+urb.getpWord()+"</td>");
	    pw.println("<tr><td>First Name:</td>");
	    pw.println("<td>"+urb.getfName()+"</td>");
	    pw.println("<tr><td>Last Name:</td>");
	    pw.println("<td>"+urb.getlName()+"</td>");
	    pw.println("<tr><td>Address:</td>");
	    pw.println("<td>"+urb.getAddr()+"</td>");
	    pw.println("<tr><td>Phone No:</td>");
	    pw.println("<td>"+urb.getPhNo()+"</td>");
	    pw.println("<tr><td>Mail id:</td>");
	    pw.println("<td>"+urb.getmId()+"</td>");
	    pw.println("</tr>");
	    pw.println("<tr><td></td><td></td></tr>");
	    pw.println("<tr>");
	    pw.println("<td align = 'right'>");
	    pw.println("<form action='reg' method='post'>");
	    pw.println("<input type='submit' value='Register'></form></td>");
	    pw.println("<td></td></tr></table>");
	    
	}

}
