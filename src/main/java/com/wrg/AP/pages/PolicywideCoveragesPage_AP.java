package com.wrg.AP.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class PolicywideCoveragesPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	

	public void coverages() {
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policywideCoveragesHeading")));
		if (isWebElementPresentAfterWait("coverage") == true) {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					getWebElement("coverage"));
			click("coverage");
		}
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("nextButton")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("nextButton"));
		sleep(1000);
		click("nextButton");
	}
}
