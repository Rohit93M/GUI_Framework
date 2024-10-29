package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomepageSampleTest {

	@Test
	public void homePageTest(Method mtd) {
		
		Reporter.log(mtd.getName()+" Test Start");
//		SoftAssert softAssert = new SoftAssert();
		Reporter.log("step-1");
		Reporter.log("step-2");
//		Assert.assertEquals("Home","Home");
//		softAssert.assertEquals("Title","Title-1");
		Reporter.log("step-3");
		Reporter.log("step-4");
//		softAssert.assertAll();
		Reporter.log(mtd.getName()+" Test End");
	}
	
	@Test
	public void verifyLogoHomepageTest(Method mtd) {
		
		Reporter.log(mtd.getName()+" Test Start");
//		SoftAssert softAssert = new SoftAssert();
		Reporter.log("step-1");
		Reporter.log("step-2");
//		softAssert.assertTrue(true);	
		Reporter.log("step-3");
		Reporter.log("step-4");
//		softAssert.assertAll();
		Reporter.log(mtd.getName()+" Test End");
		
	}
}
