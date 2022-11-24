package test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import util.DataUtil;
import util.MyXLSReader;

public class Login extends Base {
	
	WebDriver driver;
	MyXLSReader execelReader;
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(dataProvider="dataSupplier")
	public void testLogin(HashMap<String,String>hMap)
	{
		if(!DataUtil.isRunnable(execelReader,"LoginTest","Testcases")||hMap.get("Runmode").equals("N")) {
			
			throw new SkipException("Run mode is set to N, hence not executed");
			
		}

		driver = openBrowser(hMap.get("Browser"));
		
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.id("email")).sendKeys(hMap.get("Username"));
		driver.findElement(By.id("pass")).sendKeys(hMap.get("Password"));
		driver.findElement(By.name("login")).click();
		
		String expectedResult = hMap.get("ExpectedResult");
		boolean expectedConvertedResult;
		
		if(expectedResult.equalsIgnoreCase("Success")) {
			
			expectedConvertedResult = true;
			
		}else if(expectedResult.equalsIgnoreCase("Failure")) {
			
			expectedConvertedResult = false;
			
		}
		
//		boolean actualResult = false;
//		
//		try {
//			
//        actualResult = driver.findElement(By.linkText("Add Account"));
//		
//		}catch(Throwable e) {
//			actualResult = false;
//		}
//		Assert.assertEquals(expectedConvertedResult, expectedConvertedResult);
	}
  
	@DataProvider
	public Object[][] dataSupplier() {
    	
    	Object[][] data = null;
    	
    	try {
        execelReader = new MyXLSReader("C:\\Users\\manju\\eclipse-workspace\\Data_Drivan_Framework_Fb\\srs\\test\\resources\\ddfqa.xlsx");
    	data = DataUtil.getTestData(execelReader,"LoginTest","Data");
    	
    	}catch(Throwable e) {
    		e.printStackTrace();
    	}
    	
    	return data;
	}
}
