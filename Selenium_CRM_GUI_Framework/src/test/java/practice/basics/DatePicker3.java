package practice.basics;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatePicker3 {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.goibibo.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String monthAndYear = "November 2024";
		String date = "30";
		driver.findElement(By.xpath("//span[@role='presentation']")).click();
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		String actual = "//div[text()='" + monthAndYear
				+ "']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='" + date + "']";
		for (;;) {
			try {
				driver.findElement(By.xpath(actual));
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
		}
	}
}