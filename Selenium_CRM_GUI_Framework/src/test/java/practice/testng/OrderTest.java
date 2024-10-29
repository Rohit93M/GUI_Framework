package practice.testng;

import org.testng.annotations.Test;

public class OrderTest {
	
//	@Test(invocationCount = 10)
//	public void createOrderTest() {
//		System.out.println("Execute createOrderTest==>123");
//	}
//	
//	@Test (enabled = false)
//	public void billingAndOrderTest() {
//		System.out.println("Execute billingAndOrderTest==>123");
//	}
	@Test
	public void createOrderTest() {
		System.out.println("Execute createOrderTest==>123");
	}
	
	@Test(dependsOnMethods="createOrderTest")
	public void billingAndOrderTest() {
		System.out.println("Execute billingAndOrderTest==>123");
	}
}
