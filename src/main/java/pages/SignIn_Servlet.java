package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.User_Dao_Implementation;
import pojo.User;

@WebServlet(value="/signin",loadOnStartup = 1)
public class SignIn_Servlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User_Dao_Implementation uDao = null;

	@Override
	public void init() throws ServletException {
		System.out.println("in init " + getClass());
		try {
			uDao = new User_Dao_Implementation();
			
		}catch(Exception e)
		{
			throw new ServletException("err in init " + e.getMessage(), e);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
		User u=	uDao.signIn(email, password);
		
		if (u == null)
			pw.print("<h5> Invalid Login , Please <a href='login.html'>Retry</a></h5>");
		else //login successful
		{
			HttpSession hs = req.getSession();
			hs.setAttribute("user_dtls", u);
			hs.setAttribute("userDao", uDao);
			hs.setAttribute("candidateDao", u);
			if(u.getRole().equals("admin"))
				res.sendRedirect("admin_main");
			if(u.getRole().equals("voter"))
				res.sendRedirect("candidate_list");
		}
			pw.print("<h5>Successful Login , User Details "+u+"</h5>");
		
		} catch (SQLException e) {
			throw new ServletException("err in do-post" + getClass(), e);
		}

		pw.close();

	}

	@Override
	public void destroy() {
		System.out.println("in destroy " + Thread.currentThread());
		try {
			uDao.clanUp();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
