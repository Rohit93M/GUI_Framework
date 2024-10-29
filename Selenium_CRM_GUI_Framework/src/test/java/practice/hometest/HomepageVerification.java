package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomepageVerification {

	@Test
	public void homePageTest(Method mtd) {
		
		System.out.println(mtd.getName()+" Test Start");
		String expectedTitle = "Home Page";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://www.localhost:8888");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String actualTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		//Hard Assert
		Assert.assertEquals(actualTitle, expectedTitle);

		driver.quit();
		System.out.println(mtd.getName()+" Test End");
	}
	
	@Test
	public void verifyLogoHomepageTest(Method mtd) {
		
		System.out.println(mtd.getName()+" Test Start");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://www.localhost:8888");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		
		//Hard Assert
		//Assert.assertEquals(true, status);
		Assert.assertTrue(status); //assertTrue() method will always look for boolean data

		driver.quit();
		System.out.println(mtd.getName()+" Test End");
		
	}
}
