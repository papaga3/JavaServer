package huy.java.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Connection connection;
	PreparedStatement statement;
	
	@Override
	public void init() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mydb", "root", "root");
			statement = connection.prepareStatement("UPDATE users SET email=? WHERE lastName=?");
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		try {
			statement.setString(1,  email);
			statement.setString(2, lastName);
			
			int result = statement.executeUpdate();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<b> " + result + " users updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
