package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.constants;

public class LoginPageTest extends BaseTest {

	
	@Test
	public void loginPageTitle( ) {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, constants.LOGINPAGETITLE);	
	}
	
	@Test
	public void loginPageUrl( ) {
		 Assert.assertTrue(loginPage.getLoginPageUrl()); 
		}
	
	@Test
	public void isForgotPwd( ) {
		Assert.assertTrue(loginPage.isForgotPwdLink());
	}
	
	@Test
	public void registerLink( ) {
		Assert.assertTrue(loginPage.isRegisterLink());
	}
	
	@Test(enabled=false)
	public void login() throws InterruptedException {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountPageTitle(), constants.LOGINPAGETITLE);
		//Assert.assertEquals(accountsPage.getAccountPageTitle(), constants.LOGINPAGETITLE);
		
			
	}
}