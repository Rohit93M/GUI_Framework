package practice.testng;

import org.testng.annotations.Test;

public class CreateContact_DP_Test2 {

	@Test(dataProviderClass = DP_TestData.class, dataProvider = "getData")
	public void createContact(String firstName, String lastName, long phoneNo) {
		System.out.println("First Name: " + firstName + ", LastName: " + lastName + ", PhoneNo: " + phoneNo);

	}
}
