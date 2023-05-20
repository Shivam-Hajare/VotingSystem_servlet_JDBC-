package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.User_Dao_Implementation;
import pojo.User;

/**
 * Servlet implementation class Candidate_Servlet
 */
@WebServlet(value = "/candidate_list")
public class Candidate_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Candidate_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");
		// get PW
		try (PrintWriter pw = res.getWriter()) {
			HttpSession hs = req.getSession();
			User voter =(User) req.getAttribute("user_dtls");
			User_Dao_Implementation userDao=(User_Dao_Implementation)req.getAttribute("userDao");
			pw.print("<h4> in candidate list </h4>");
		}
	}
}
