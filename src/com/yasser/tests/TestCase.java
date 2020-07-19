package com.yasser.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase extends TestHooks {

	@Test(priority = 1)
	public void verifyTitle() {
		Assert.assertEquals(page.getTitle(), "ARR", "Testing if title is ARR");
		Assert.assertTrue(page.getSearchInput().isDisplayed());
		Assert.assertTrue(page.getSearchButton().isDisplayed());
	}

	@Test(priority = 2)
	public void verifyEmptyQuerySearch() {
		Assert.assertEquals(page.getTitle(), "ARR", "Testing if title is ARR");
		Assert.assertTrue(page.getSearchInput().isDisplayed());
		Assert.assertTrue(page.getSearchButton().isDisplayed());
		page.clickSearchButton();
		Assert.assertTrue(page.getEmptyQueryError().isDisplayed());
		Assert.assertEquals(page.getEmptyQueryError().getText(), "Provide some query");

	}

	@Test(priority = 3)
	public void verifyValidInpputSearch() {
		page.enterSearchParameter("isla").clickSearchButton().getPlaces();
		Assert.assertTrue(page.getResults().isDisplayed());

	}

	@Test(priority = 4)
	public void verifyInValidInpputSearch() {
		page.enterSearchParameter("@#$%^&*(98765").clickSearchButton();
		Assert.assertTrue(page.getOutputContainer().isDisplayed());
		Assert.assertEquals(page.getOutputContainer().getText(), "Results:");
	}
}
