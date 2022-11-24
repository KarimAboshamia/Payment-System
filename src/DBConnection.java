import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	
	public static void main(String[]args) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();	
			statement.executeUpdate("create table person (id integer, name string)");
			statement.executeUpdate("insert into person values(1, 'leo')");
			ResultSet rs = statement.executeQuery("select * from person");

			while(rs.next())
			{
				// read the result set
				System.out.println("name = " + rs.getString("name"));
	            System.out.println("id = " + rs.getInt("id"));
			}

		}catch(SQLException e) {
            System.out.println(e);

				
		}
	}
    


}
