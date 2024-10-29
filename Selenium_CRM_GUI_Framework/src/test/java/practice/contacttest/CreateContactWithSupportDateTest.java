package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		Sheet sheet = workbook.getSheet("contact");
		Row row = sheet.getRow(4);
		Cell cell = row.getCell(2);
		String lastName = cell.getStringCellValue()+randomInt;

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

		// Step 2: Navigate to Contacts module
		driver.findElement(By.linkText("Contacts")).click();

		// Step 3: Click on "Create Contact" button
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// Step 4: Create new Contact
		
		Date dateObj = new Date();
		System.out.println(dateObj.toString());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(dateObj);

		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String endDate = sdf.format(cal.getTime());
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.name("button")).click();
		
//      Verify start date

		String actualStartDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actualStartDate.equals(startDate)) {
			System.out.println(startDate+" is created == PASS");
		} else {
			System.out.println(startDate+" is not created == FAIL");
		}	
		
//		Verify end date
		String actualEndDate = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actualEndDate.equals(endDate)) {
			System.out.println(endDate+" is created == PASS");
		} else {
			System.out.println(endDate+" is not created == FAIL");
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
