package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelectQueryTest {
public static void main(String[] args) throws SQLException {
	Driver driverRef = new Driver();
	DriverManager.registerDriver(driverRef);
	
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation", "root", "root");
	Statement state= conn.createStatement();
	int result = state.executeUpdate("insert into people_list values(5,'Tom','Thomas','Rome');");
	System.out.println("Script execution successful");
	conn.close();
}
}
