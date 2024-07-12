package runtimeDDT_cmdline;

import org.testng.annotations.Test;

public class ReadRunTimeMavenParameterTest {

	@Test
	public void runtimeParameterTest()
	{
//		System.out.println("testng test");
		String url = System.getProperty("url");
		String browser = System.getProperty("browser");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
		System.out.println("env data => URL => "+url);
		System.out.println("env data => browser => "+browser);
		System.out.println("env data => username => "+username);
		System.out.println("env data => password => "+password);
	}
}
