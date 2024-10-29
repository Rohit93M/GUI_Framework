package practice.selenium_grid;

import java.net.URI;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class NewClass {
	
	@Test
	public void sampleTest() throws Throwable {
        URL url = new URL("http://localhost:4444");
		//URL url = new URI("https://localhost:4444").toURL();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);
		driver.get("https://www.fb.com");

	}
}
