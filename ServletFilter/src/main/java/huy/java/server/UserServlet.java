package huy.java.server;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/users/*")
public class UserServlet extends HttpServlet {
private Gson gson;
	
	private String keyString = "mrufzDPgBTA6IFx5nPnOEfBqlre8I5PX87ckbFt+OE93z3iEbW+h09tP1rtdpNqur4aVOY6mgck6Gmr5fci3nA==";
	
	public void init() {
		gson = new Gson();
	}
	
	private void sendAsJSON(HttpServletResponse response, Object obj) throws ServletException, IOException {
		response.setContentType("application/json");
		String result = gson.toJson(obj);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = (String) request.getAttribute("userName");
		User user = new User(userName);
		sendAsJSON(response, user);
	}
}
