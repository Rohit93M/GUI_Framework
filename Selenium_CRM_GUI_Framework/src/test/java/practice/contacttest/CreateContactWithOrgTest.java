package practice.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

//Its an integration test case as data flow is happening from Organization module to Contacts module
//As when we get a new build in Testing env, data is always empty, so here we should also try to automate the precondition.
//xpath also getting created in runtime, is called dynamic xpath

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Throwable {
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
		FileInputStream fis1 = new FileInputStream("C:\\Users\\ROHIT M\\OneDrive\\Desktop\\data\\testScriptdata.xlsx");
		Workbook workbook = WorkbookFactory.create(fis1);
		Sheet sheet = workbook.getSheet("contact");
		Row row = sheet.getRow(7);
		Cell cell = row.getCell(2);
		String orgName = cell.getStringCellValue() + randomInt;
		cell = row.getCell(2);
		String contactLastName = cell.getStringCellValue() + randomInt;
		cell = row.getCell(3);
		String lastName = cell.getStringCellValue() + randomInt;
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

		// Step 4: Create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("button")).click();

//      Verify Header msg Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is created == PASS");
		} else {
			System.out.println(orgName + " is not created == FAIL");
		}

//		Verify orgName info Expected Result
		String actualOrgName = driver.findElement(By.id("submitButton")).getText();
		if (actualOrgName.equals(orgName)) {
			System.out.println(orgName + " is created == PASS");
		} else {
			System.out.println(orgName + " is not created == FAIL");

			// Step 5: Navigate to Contact module
			driver.findElement(By.linkText("Contacts")).click();

			// Step 6: Click on "Create Contact" button
			driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

			// Step 7: Create new Contact
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

			// switch to child window
			Set<String> set = driver.getWindowHandles();
			Iterator<String> iterator = set.iterator();

			while (iterator.hasNext()) {
				String windowID = iterator.next();
				driver.switchTo().window(windowID);
				String actualUrl = driver.getCurrentUrl();
				if (actualUrl.contains("module=Accounts")) {
					break;
				}
			}

			driver.findElement(By.id("search_txt")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='" + orgName + "]"));

			// switch to parent window
			Set<String> set1 = driver.getWindowHandles();
			Iterator<String> iterator1 = set1.iterator();

			while (iterator.hasNext()) {
				String windowID = iterator1.next();
				driver.switchTo().window(windowID);
				String actualUrl = driver.getCurrentUrl();
				if (actualUrl.contains("Contacts&action")) {
					break;
				}
			}

			driver.findElement(By.name("button")).click();

//	      Verify Header msg Expected Result
			headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if (headerInfo.contains(lastName)) {
				System.out.println(lastName + " is created == PASS");
			} else {
				System.out.println(lastName + " is not created == FAIL");
			}

//			Verify lastName info Expected Result
			String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
			if (actualLastName.equals(lastName)) {
				System.out.println(lastName + " is created == PASS");
			} else {
				System.out.println(lastName + " is not created == FAIL");
			}

//			Verify orgName info Expected Result
			actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if (actualOrgName.trim().equals(orgName)) {
				System.out.println(orgName + " is created == PASS");
			} else {
				System.out.println(orgName + " is not created == FAIL");
			}

		}
	}
}
