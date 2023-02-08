package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.qa.opencart.utils.constants;

public class ProductInfoPageTest extends BaseTest{

	@BeforeClass
	public void productInfoSetup( ) {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test(priority = 2)
	public void productHeaderTest() throws InterruptedException {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("HTC Touch HD");
		Assert.assertEquals(productInfoPage.getProductHeader(), "HTC Touch HD");
	}
	
	@Test(priority = 1)
	public void productImagesCountTest() throws InterruptedException  {
		searchResultsPage = accountsPage.doSearch("iMac");
		productInfoPage = searchResultsPage.selectProduct("iPhone");
		Assert.assertEquals(productInfoPage.getProductImagesCount(),constants.IMAC_IMAGE_COUNT);
	}
	
	@Test(priority = 3)
	public void productInfoTest() throws InterruptedException {
		searchResultsPage = accountsPage.doSearch("Palm Treo Pro");
		productInfoPage = searchResultsPage.selectProduct("Palm Treo Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		//softAssert.assertEquals(actProductInfoMap.get("name"), "Palm Treo Pro");
		softAssert.assertEquals(actProductInfoMap.get("name"), "Palm Treo Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Palm");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$337.99");
		softAssert.assertAll();
	}
	
}
