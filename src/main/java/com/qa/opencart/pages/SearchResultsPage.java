package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	String SAP;
	private By header = By.cssSelector("div#content h4");
	private By newElement;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);	
	}

	public int getProductListCount( ) {
		int resultCount = eleUtil.waitForElementsToBeVisible(header, 10).size();
		System.out.println("the search product count: " + resultCount);
		return resultCount;	
	}
	
	public ProductInfoPage selectProduct(String mainProductName) throws InterruptedException {
		List<WebElement> searchElement = eleUtil.waitForElementsToBeVisible(header, 10000);	
		((JavascriptExecutor) driver).executeScript("scroll(0,350);");
		for(WebElement e : searchElement) {
			String text = e.getText();
			//System.out.println(text +"Test");
			if(text.equals(mainProductName)) {
				newElement = By.xpath("//*[@alt='"+text+"' and @title='"+text+"']");
				//newElement = By.xpath("//a[text()='"+text+"']");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,250)", "");
				//eleUtil.wait(100000);
				//eleUtil.doActionMoveToElementClick(newElement);
				driver.findElement(newElement).click();
				//eleUtil.clickElementWhenReady(newElement, 100000);
				break;	
			}
		}
		return new ProductInfoPage(driver);
	}
	
}
