package practice.basics;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DatePickerDemo {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoapps.qspiders.com/ui/datePick?sublist=0");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5 ));
		String monthAndYear = "November2024";
		int time = 30;
		driver.findElement(By.xpath("(//*[local-name()='svg'])[1]")).click();
		String actual = "//div[text()='"+monthAndYear+"']/ancestor::div[@class='react-datepicker__month-container']/desecendant::div[text()='"+time+"']";
		for (;;) {
			try {
				driver.findElement(By.xpath(actual)).click();
				break;
			} catch (Exception e) {
				Actions actions = new Actions(driver);
				actions.moveToElement(driver.findElement(By.xpath("//span[text()='Next Month']"))).click().perform();
			}
		}
	}
}
