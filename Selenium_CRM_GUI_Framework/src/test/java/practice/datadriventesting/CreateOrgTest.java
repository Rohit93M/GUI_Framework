package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class CreateOrgTest {

	@Test
	public void creatOrgTest(XmlTest test) throws IOException, InterruptedException {

		
		  // read common data from xml file
		  String url = test.getParameter("url");
		  String browser = test.getParameter("browser"); 
		  String username = test.getParameter("username"); 
		  String password = test.getParameter("password");
		 
		
			/*
			 * // read common data from command line String url = System.getProperty("url");
			 * String browser = System.getProperty("browser"); String username =
			 * System.getProperty("username"); String password =
			 * System.getProperty("password");
			 * 
			 */
		// generate a random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read test script data from excel file
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\ROHIT M\\OneDrive\\Desktop\\New folder\\testScriptdata01.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("org");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(2);
		String orgName = cell.getStringCellValue() + randomInt;

		WebDriver driver = null;
		if (browser.equals("chrome")) {
			driver = new ChromeDriver(); // This decision of initialization is happening at runtime
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		// Step 1: Login to app
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// Step 2: Navigate to Organizations module
		driver.findElement(By.linkText("Organizations")).click();

		// Step 3: Click on "Create Organization" button
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 4: Create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("button")).click();
//		driver.quit();

		// Step 5: Logout
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		actions.moveToElement(driver.findElement(By.linkText("Sign Out"))).click().perform();
		System.out.println("script execution successful");
		driver.quit();
	}
}
