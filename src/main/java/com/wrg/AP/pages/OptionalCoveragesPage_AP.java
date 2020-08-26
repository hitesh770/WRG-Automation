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
	
	
	public void chooseFirstThreeOptionalCoverages() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage1")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("coverage1")));
		clickUsingJS("coverage1");
	    sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage1dropdown")));
		selectByOption(getWebElement("coverage1dropdown"),"100,000/300,000");
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage1textfield")));
		type(getWebElement("coverage1textfield"),"1000");
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage2")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("coverage2")));
		clickUsingJS("coverage2");
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage2addbuton")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("coverage2addbuton")));
		sleep(2000);
		clickUsingJS("coverage2addbuton");
		scrollToElement("coverage2textfield");
	    wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage2textfield")));
		sleep(2000);
		typeUsingJS("coverage2textfield","test789");
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage2savebutton")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("coverage2savebutton")));
		clickUsingJS("coverage2savebutton");
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage3")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("coverage3")));
		clickUsingJS("coverage3");
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
