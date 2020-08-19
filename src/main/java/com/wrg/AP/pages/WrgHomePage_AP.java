package com.wrg.AP.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class WrgHomePage_AP extends AbstractTest{
	WebDriverWait wait=null;

	
	
	public void createNewQuote(String quote) {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("createNewQuoteButton")));
		clickUsingJS("createNewQuoteButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("quoteDropdown")));
		click(driver.findElement(By.xpath("//span[contains(text(),'"+quote+"')]")));
	}

}
