package practice.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenshot {
	
	@Test
	public void amazonTest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("http://amazon.com");
		
		//Step-1: Create an object of EventFiringWebDriver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		//Step-2: Use getScreenshotAs() method to capture the File type screenshot in runtime
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		//Store screenshot
		File destFile = new File("./screenshot/test.png");
		FileUtils.copyFile(srcFile, destFile);
	}
}
