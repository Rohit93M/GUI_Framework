package practice.testng;

import org.testng.annotations.Test;

public class ContactTest2 {
	
//	@Test(priority = 1)
//	public void createContactTest() {
//		System.out.println("execute createContactTest");
//	}
//	
//	@Test(priority = 2)
//	public void modifyContactTest() {
//		System.out.println("execute modifyContactTest");
//	}
//	
//	@Test(priority = 3)
//	public void deleteContactTest() {
//		System.out.println("execute deleteContactTest");
//	}
	
	@Test
	public void createContactTest() {
		System.out.println("execute createContactTest");
	}
	
	@Test(dependsOnMethods="createContactTest")
	public void modifyContactTest() {
		System.out.println("execute modifyContactTest");
	}
	
	@Test(dependsOnMethods="modifyContactTest")
	public void deleteContactTest() {
		System.out.println("execute deleteContactTest");
	}
}
