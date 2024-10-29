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
import org.openqa.selenium.support.ui.Select;

//every test case you have to verify the expected result.
//This test case is a regression test case

public class CreateOrganizationWithIndustriesTest {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {

		// read common data from
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
		Row row = sheet.getRow(4);
		Cell cell = row.getCell(2);
		String orgName = cell.getStringCellValue() + randomInt;
		cell = row.getCell(3);
		String industry = cell.getStringCellValue(); //expected data from excel
		cell = row.getCell(4);
		String type = cell.getStringCellValue();
		workbook.close();

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

		// Step 4: Enter all the details and Create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		Select industrySelect = new Select(driver.findElement(By.name("industry")));
		industrySelect.selectByValue("Energy");
		
		Select typeSelect = new Select(driver.findElement(By.name("accounttype")));
		typeSelect.selectByValue("Press");
		
		driver.findElement(By.name("button")).click();
		
//		You have to take care of end result validation
//		Verify the industries info
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actualIndustry.equals(industry)) {
			System.out.println(industry+" information is verified == PASS");
		} else {
			System.out.println(industry+" information is not verified == FAIL");
		}
		
//		Verify the type info
		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actualType.equals(type)) {
			System.out.println(type+" information is verified == PASS");
		} else {
			System.out.println(type+" information is not verified == FAIL");
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

