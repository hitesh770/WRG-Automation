package com.wrg.AP.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class OptionalCoveragesPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void optionalCoverages() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("optionalCoveragesHeading")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("optionalCoveragesHeading")));
		clickUsingJS("coverage1");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("nextButton"));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("nextButton")));
		clickUsingJS("nextButton");
	}

	public void quote() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("optionalCoveragesHeading")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("optionalCoveragesHeading")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("quoteButton"));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("quoteButton")));
		clickUsingJS("quoteButton");
		sleep(1000);
		if (isWebElementPresent("creatingQuoteLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("creatingQuoteLoader")));
		}
		if (isWebElementPresentAfterWait("notesToUnderwriter")) {
			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(getWebElement("notesToUnderwriter"), "testing");

				click("sendForUnderwritingReviewButton");
				wait.until(ExpectedConditions.visibilityOf(getWebElement("okButton")));
				clickUsingJS("okButton");
			}
			driver.switchTo().window(mainwindow);
		}
	}
}
