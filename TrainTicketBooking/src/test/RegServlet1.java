package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RegServlet1 extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{
		String uName = req.getParameter("uname");
		String pWord = req.getParameter("pword");
		String fName = req.getParameter("fname");
		String lName = req.getParameter("lname");
		
		UserRegBean urb = new UserRegBean();
		urb.setuName(uName);
		urb.setpWord(pWord);
		urb.setfName(fName);
		urb.setlName(lName);
		
		ServletContext sct = this.getServletContext();
		sct.setAttribute("regBean",urb);
		RequestDispatcher rd = req.getRequestDispatcher("Register2.html");
		rd.forward(req,res);
	}

}
