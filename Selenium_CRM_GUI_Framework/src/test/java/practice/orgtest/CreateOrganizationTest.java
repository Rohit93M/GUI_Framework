package practice.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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

/*This is a smoke scenario. We want to check whether critical functionalities like Create Organization functionality
and Organization module is working or not*/

public class CreateOrganizationTest {
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {

		// read common data from property file
		FileInputStream fis = new FileInputStream("C:\\Users\\ROHIT M\\OneDrive\\Desktop\\data\\commondata.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		String browser = properties.getProperty("browser");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		// generate a random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read test script data from excel file
		FileInputStream fis1 = new FileInputStream(
				"C:\\Users\\ROHIT M\\OneDrive\\Desktop\\data\\testScriptdata.xlsx");
		Workbook workbook = WorkbookFactory.create(fis1);
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
		
//      Verify Header msg Expected Result
		String headerInfo= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName+" is created == PASS");
		} else {
			System.out.println(orgName+" is not created == FAIL");
		}
		
//		Verify orgName info Expected Result
		String actualOrgName = driver.findElement(By.id("submitButton")).getText();
		if(actualOrgName.equals(orgName)) {
			System.out.println(orgName+" is created == PASS");
		} else {
			System.out.println(orgName+" is not created == FAIL");
		}
		
		// Step 5: Logout
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		actions.moveToElement(driver.findElement(By.linkText("Sign Out"))).click().perform();
		System.out.println("script execution successful");
		driver.quit();
	}
}
