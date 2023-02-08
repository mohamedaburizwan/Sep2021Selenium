package com.qa.opencart.factory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriveFactory {
	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	//public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initilizeDriver(Properties prop) {
		// TODO Auto-generated method stub
			String browserName = prop.getProperty("browser").trim();
			//optionsManager = new OptionsManager(prop);
			if(browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver());
				
			}
			
			else if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				if (Boolean.parseBoolean(prop.getProperty("remote"))) {
					// remote code
					//init_remoteDriver("firefox", browserVersion);
				} else {
					tlDriver.set(new FirefoxDriver());
				}
			}
			getDriver().manage().window().fullscreen();
			getDriver().manage().deleteAllCookies();
			// openUrl(prop.getProperty("url"));

			URL url;
			try {
				url = new URL(prop.getProperty("url"));
				openUrl(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			return getDriver();
		}

	
	/**
	 * getdriver(): it will return a thread local copy of the webdriver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * launch url
	 * 
	 * @param url
	 */
	public void openUrl(String url) {
		try {
			if (url == null) {
				throw new Exception("url is null");
			}
		} catch (Exception e) {

		}
		getDriver().get(url);
	}

	public void openUrl(URL url) {
		try {
			if (url == null) {
				throw new Exception("url is null");
			}
		} catch (Exception e) {

		}
		getDriver().navigate().to(url);
	}

	public void openUrl(String baseUrl, String path) {
		try {
			if (baseUrl == null) {
				throw new Exception("baseUrl is null");
			}
		} catch (Exception e) {

		}
		// http://amazon.com/accpage/users.html
		getDriver().get(baseUrl + "/" + path);
	}

	public void openUrl(String baseUrl, String path, String queryParam) {
		try {
			if (baseUrl == null) {
				throw new Exception("baseUrl is null");
			}
		} catch (Exception e) {

		}
		// http://amazon.com/accpage/users.html?route=account/login
		getDriver().get(baseUrl + "/" + path + "?" + queryParam);
	}
	
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");// qa/dev/stage/uat

		if (envName == null) {
			System.out.println("Running on PROD env: ");
			try {
				ip = new FileInputStream("./src/test/resource/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on environment: " + envName);
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resource/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resource/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
					break;

				default:
					System.out.println("please pass the right environment.....");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}
	

}
