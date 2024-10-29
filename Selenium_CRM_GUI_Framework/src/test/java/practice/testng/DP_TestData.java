package practice.testng;

import org.testng.annotations.DataProvider;

public class DP_TestData {
	@DataProvider
	public Object[][] getData() {

		// Create a 2D array
		Object[][] objArr = new Object[3][3];

		objArr[0][0] = "Bruce";
		objArr[0][1] = "Wayne";
		objArr[0][2] = 9876543210l;

		objArr[1][0] = "Tony";
		objArr[1][1] = "Stark";
		objArr[1][2] = 8967452301l;

		objArr[2][0] = "Peter";
		objArr[2][1] = "Parker";
		objArr[2][2] = 7894561230l;

		return objArr;
	}
	// return type is objArr coz in object array we can store any type of data
}
