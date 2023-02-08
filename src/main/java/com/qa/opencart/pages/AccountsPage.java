package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constants;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By header = By.cssSelector("div#content h2");
	private By searchText = By.cssSelector("div#search input");
	private By searchButton = By.cssSelector("div#search button");
	private By transaction = By.linkText("Transactions");
	private By phone = By.xpath("/html/body/main/div[1]/nav/div[2]/ul/li[6]/a");
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);	
	}
	
	public boolean getAccountPageTitle() {
		return eleUtil.waitForTitleContains(constants.LOGINPAGETITLE, constants.INT_TIMEOUT);
		
	}
	
	public String getAccountPageHeader( ) {
		return eleUtil.doGetText(header);	
	}
	
	public boolean isTransactionLinkExist( ) {
		 return eleUtil.doIsDisplayed(transaction);
		
	}
	
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(searchText);
	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("searching the product: " + productName);
		eleUtil.doSendKeys(searchText, productName);
		eleUtil.doClick(phone);
		return new SearchResultsPage(driver);
	}
	
	 
}


