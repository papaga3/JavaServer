package huy.java.server;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/targetServlet")
public class targetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {		
		HttpSession session = req.getSession(false);
		PrintWriter out = res.getWriter();
		Cookie[] cookies = req.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			out.println(cookies[i].getName());
			out.println(cookies[i].getValue());
		}
		if(session != null) {
			String userName = (String) session.getAttribute("user");
			out.println("Hello " + userName);
		} else {
			out.println("Session has ended");
		}
	}
}
