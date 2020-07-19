package com.yasser.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.yasser.pages.Page;

public abstract class TestHooks {

	protected static Page page;

	// This way if initializing ensured one chrome driver instance is used over all
	// and saves a lot of space and time
	@BeforeTest
	@Parameters({ "url" })
	public void setup(String url) {
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		//options.setBinary("./drivers/chromerdriver");
		//options.addArguments("window-size=1200x600");
		WebDriver driver = new ChromeDriver();
		page = new Page(driver);
		page.navigate(url);
	}

	@AfterTest
	public void afterTest() {
		page.tearDown();
	}
}
