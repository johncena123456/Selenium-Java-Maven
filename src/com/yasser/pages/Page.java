package com.yasser.pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Page {

	@FindBy(how = How.ID, id = "search-input")
	WebElement searchInput;

	@FindBy(how = How.ID, id = "search-button")
	WebElement searchButton;

	@FindBy(how = How.ID, id = "error-empty-query")
	WebElement emptyQueryError;

	@FindBy(how = How.ID, id = "output-container")
	WebElement outputContainer;

	@FindBy(how = How.ID, using = "error-no-results")
	WebElement noResults;

	@FindBy(how = How.ID, using = "search-results")
	WebElement results;

	@FindBys({ @FindBy(how = How.TAG_NAME, using = "li") })
	List<WebElement> searchResult;

	public WebElement getSearchInput() {
		return searchInput;
	}

	public WebElement getOutputContainer() {
		return outputContainer;
	}

	public WebElement getResults() {
		return results;
	}

	public WebElement getEmptyQueryError() {
		return emptyQueryError;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getNoResults() {
		return noResults;
	}

	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigate(String url) {
		driver.navigate().to(url);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public Page enterSearchParameter(String searchQuery) {
		searchInput.clear();
		searchInput.sendKeys(searchQuery);
		return this;
	}

	public Page clickSearchButton() {
		searchButton.click();
		return this;
	}

	public List<WebElement> getSearchResult() {
		WebDriverWait wait = new WebDriverWait(driver, 5);

		try {
			wait.until(ExpectedConditions.visibilityOf(results));
			return searchResult;
		} catch (NoSuchElementException e) {
			Assert.assertTrue(noResults.isDisplayed());
		}
		return searchResult;

	}

	public void getPlaces() {
		if (this.getSearchResult().size() > 0) {
			this.getSearchResult().forEach(place -> System.out.println("" + place.getText()));
		} else
			System.out.println("There are " + this.getSearchResult().size() + " Results");

	}

	public void tearDown() {
		try {
			this.driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
