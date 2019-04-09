package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class RegServlet2 extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String addr = req.getParameter("addr");
		long phNo = Long.parseLong(req.getParameter("phno"));
		String mId = req.getParameter("mid");
		
		ServletContext sct = this.getServletContext();
		UserRegBean urb = (UserRegBean)sct.getAttribute("regBean");
		urb.setAddr(addr);
		urb.setPhNo(phNo);
		urb.setmId(mId);
		RequestDispatcher rd = req.getRequestDispatcher("vdetails");
		rd.forward(req, res);
		
	}

}
