package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCaseToCheckProjectInBackEnd {

	
	//unit testing to test the back end
	//program to check whether expected value is available in db
	
	@Test
	public void projectTestTest() throws SQLException {

		String expectedLocName = "new york";
		boolean flag = false;
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation", "root", "root");
		Statement state = conn.createStatement();
		ResultSet resultSet = state.executeQuery("select * from people_list");
		while (resultSet.next()) {
			String actualLocName = resultSet.getString(4);
			if (expectedLocName.equals(actualLocName)) {
				flag = true;
				System.out.println(expectedLocName + "is available = Pass");
			}
		}
		if (flag == false) {
			System.out.println(expectedLocName + "is not available = Not Pass");
			Assert.fail();
		}
		conn.close();
	}
}