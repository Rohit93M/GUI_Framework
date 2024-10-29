package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import com.mysql.cj.jdbc.Driver;

//Capture the data from the GUI and as per the test case verify the data is available in back end
//This is an example of front end and back end testing

public class CreateProjectAndVerifyDataInDBWithGUI {
	
	public static void main(String[] args) throws SQLException, InterruptedException {

		// Create Project in GUI using Selenium code
		
		String projectName = "FB_"+new Random().nextInt(1000);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("http://106.51.90.215:8084");
		driver.manage().window().maximize();

		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();

		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys("Rohit");
		
		WebElement status = driver.findElement(By.xpath("//label[text()='Project Status ']/following-sibling::select"));
		Select select = new Select(status);
		select.selectByVisibleText("Created");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.quit();
		
		

		// Verify the project in DB using JDBC
		
		Connection conn = null;

		boolean flag = false;
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);

		conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/Projects", "root@%", "root");
		System.out.println("======DONE======");
		Statement state = conn.createStatement();
		ResultSet resultSet = state.executeQuery("select * from project;");
		
		while (resultSet.next()) {
			String actualProjectName = resultSet.getString(4);
	//		System.out.println(actualProjectName);
			if (projectName.equals(actualProjectName)) {
				flag = true;
				System.out.println(projectName + " is available = Pass");
			}
		}
		if (flag == false) {
			System.out.println(projectName + " is not available = Fail");
		}
		conn.close();
	}
}
