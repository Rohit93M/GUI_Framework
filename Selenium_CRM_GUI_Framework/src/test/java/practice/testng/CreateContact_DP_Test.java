package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DP_Test {
	
	@Test(dataProvider="getData")
	public void createContact(String firstName, String lastName) {
		System.out.println("First Name: "+firstName+", LastName: "+lastName);

	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][2];
		
		objArr[0][0] = "Jerry";
		objArr[0][1] = "Henry";
		
		objArr[1][0] = "Tom";
		objArr[1][1] = "Thomas";
		
		objArr[2][0] = "Donald";
		objArr[2][1] = "Duck";
		
		return objArr;
	}
}
