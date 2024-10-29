package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*Lazy initialization: PageFactory.initElements will load all elements with current address. 
  Using those elements if you are trying to perform an action at that time in runtime
 it will take the latest address to perform an action*/

public class SampleTestWithPom {

	@FindBy(name = "user_name")
	WebElement ele1;

	@FindBy(name = "user_password")
	WebElement ele2;

	@FindBy(id = "submitButton")
	WebElement ele3;

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.localhost:8888");

		SampleTestWithPom s = PageFactory.initElements(driver, SampleTestWithPom.class);
		
		// First argument is browser session, 2nd argument is the page class for which you
		// are creating an object
		// All these elements are being loaded in this variable s representing the
		// class. By taking help of this variable,
		// i am going to locate the latest address of these elements.
		// This initElements method will load all the elements inside this class

		s.ele1.sendKeys("admin");
		System.out.println(s.ele1);
		// At runtime take the latest address and perform action
		s.ele2.sendKeys("admin");

		driver.navigate().refresh();

		// s is reference variable of this page class. Even though page is getting
		// refreshed, go to this page, taking the help of this object or reference,
		// take the latest address of this element identify the element and perform action
		
		System.out.println(s.ele1);
		s.ele1.sendKeys("admin");
		s.ele1.sendKeys("manager");

		s.ele3.click();
	}

}
