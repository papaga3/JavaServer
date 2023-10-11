package huy.java.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private String jdbcURL = "jdbc:mariadb://localhost:3306/mydb";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";

	// Constructors
	public UserDAO(String url, String userName, String password) {
		this.jdbcURL = url;
		this.jdbcUserName = userName;
		this.jdbcPassword = password;
	}

	public UserDAO() {
	}

	private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM users";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE userID=?";
	private static final String INSERT_USER_QUERY = "INSERT INTO users (firstName, lastName, email) VALUES (?, ?, ?)";
	private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE userID=?";

	protected Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void insertUser(User user) {
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_USER_QUERY);) {
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<User>();

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS_QUERY);) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);

				users.add(new User(id, firstName, lastName, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public User selectUserByID(int id) {
		User user = null;

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_ID);) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");

				user = new User(id, firstName, lastName, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean deleteUser(int id) {
		boolean rowDeleted = false;
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_USER_QUERY);) {
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}
}
