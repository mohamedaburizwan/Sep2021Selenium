package com.qa.opencart.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.constants;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
	@Test
	public void accPageTitleExist() {
		//AccountsPage accountsPage;
		String title = accountsPage.getAccountPageHeader();
		Assert.assertEquals(title, constants.LOGIN_ERROR_MESSG);	
	}
	
	
	@Test
	public void searchExist() {
		Assert.assertTrue(accountsPage.isSearchExist());	
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"HTC Touch HD"},
			{"iPhone"},
			{"Palm Treo Pro"},
		};
		}
	
	@Test(priority = 1, dataProvider = "productData")
	public void searchText(String productName) {
		searchResultsPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductListCount() > 0);
	}
	
	
	@DataProvider 
	public Object[][] productSelectData() {
		return new Object[][] {
			{"HTC","HTC Touch HD"},
			{"Apple","iPhone"},
			{"Pro","Palm Treo Pro"},
		};
		}
	
	@Test(priority =2, dataProvider = "productSelectData")
	public void selectData(String productName, String mainProductname) throws InterruptedException {

		searchResultsPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProduct(mainProductname);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductname);
	}	
}
