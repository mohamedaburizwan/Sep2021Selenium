package com.qa.opencart.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriveFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	DriveFactory df;
	WebDriver driver;
	LoginPage loginPage ;
	Properties prop;
	AccountsPage accountsPage;
	SearchResultsPage searchResultsPage;
	ProductInfoPage productInfoPage;
	SoftAssert softAssert;
	
	
	@BeforeTest 
	public void setup() {
		df = new DriveFactory();
		prop = df.init_prop();
		driver = df.initilizeDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() { 
		driver.quit();
	}

}
