import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {
	public static void main(String args[]) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mydb", "root", "root");
			System.out.println(connection);
			Statement statement = connection.createStatement();
			
			// int results = statement.executeUpdate("INSERT INTO student VALUE(1, 'huy', 'nguyen')");
			
			// int results = statement.executeUpdate("UPDATE student SET firstName='Tim' WHERE studentNumber=1");
			
			// int results = statement.executeUpdate("DELETE FROM student WHERE studentNumber=1");
			
			// System.out.println(results + " rows updated");
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
			while(resultSet.next()) {
				System.out.println("Student Number: " + resultSet.getString(1));
				System.out.println("Last name: " + resultSet.getString(2));
				System.out.println("First name: " + resultSet.getString(3));
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
