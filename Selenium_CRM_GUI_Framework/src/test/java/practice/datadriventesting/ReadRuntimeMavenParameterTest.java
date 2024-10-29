package practice.datadriventesting;

import org.testng.annotations.Test;

public class ReadRuntimeMavenParameterTest {

	@Test
	public void runtimeParameterTest() {
		
		String url = System.getProperty("url");
		String browser = System.getProperty("browser");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
		
		System.out.println("Env Data or URL = "+url);
		System.out.println("Browser = "+browser);
		System.out.println("Username Data = "+username);
		System.out.println("Password Data = "+password);
	}
}
