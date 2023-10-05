package huy.java.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.Tag;
import jakarta.servlet.jsp.tagext.TagSupport;

public class UserResultHandler extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn;
	PreparedStatement ps;
	
	// Constructor
	public UserResultHandler() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mydb", "root", "root");
			ps = conn.prepareStatement("SELECT * FROM users WHERE email=?");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	// doStartTag
	// Will be invoke by the container as soon as we hit the tag in the JSP page.
	// Return a value which tell the container what it should do next
	@Override
	public int doStartTag() throws JspException {
		
		// pageContext object is used to access JSP implicit objects
		ServletRequest request = pageContext.getRequest();
		
		String email = request.getParameter("email");
		
		try {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			JspWriter out = pageContext.getOut();
			if(rs.next()) {
				out.println("first name: " + rs.getString(2) + "<br />");
				out.println("last name: " + rs.getString(3) + "<br />");
			} else {
				out.println("Users not found");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return Tag.SKIP_BODY; // Skip the tag body	
	}
	
	// Release the state of current tag
	@Override
	public void release() {
		try {
			ps.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
