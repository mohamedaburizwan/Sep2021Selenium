package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constants;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.id("form-login");
	private By registerLink = By.linkText("Register");
	private By forgotPassword = By.linkText("Forgotten Password");
	private By loginErrorMesg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(constants.LOGINPAGETITLE, constants.INT_TIMEOUT);
		
	}
	
	public boolean getLoginPageUrl( ) {
		return eleUtil.waitForURLToContain(constants.LOGIN_PAGE_URL_FRACTION, constants.INT_TIMEOUT);
	}
	
	public boolean isForgotPwdLink() {
		return eleUtil.doIsDisplayed(forgotPassword);
	}
	
	public boolean isRegisterLink() {
		return eleUtil.doIsDisplayed(registerLink);
	} 
	
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login with : " + un + " : " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("try to login with wrong credentials: " + un + " : " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMesg = eleUtil.doGetText(loginErrorMesg);
		System.out.println(errorMesg);
		if(errorMesg.contains(constants.LOGIN_ERROR_MESSG)) {
			System.out.println("login is not done....");
			return false;
		}
		return true;
	}

	
	}
	
	