package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteSelectQueryTest {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation", "root", "root");
			Statement state = conn.createStatement();
			ResultSet resultSet = state.executeQuery("select * from people_list");
			while (resultSet.next()) {
				System.out.println(
						resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4));
			}
		} catch (Exception e) {
			System.out.println("handle the exception");
		} finally {
			conn.close();
		}
	}
}