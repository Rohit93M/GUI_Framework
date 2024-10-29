package practice.datadriventesting;

//Basically in case of banking application, we can use this kind of program to generate alphanumeric random data
public class GenerateAlphaNumericRandomData {
	public static void main(String[] args) {
		int n = 20;

		// choose a character random from this String
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		
		//create StringBuffer size of alphanumeric String
		StringBuilder sb = new StringBuilder(n);
		
		//generate a random no between 0 to alphanumericString variable length
		for (int i = 0; i < n; i++) {
			int index = (int) (alphaNumericString.length() * Math.random());
			
		//add character one by one in end of sb
			sb.append(alphaNumericString.charAt(index));
		}
		System.out.println(sb);
	}
}