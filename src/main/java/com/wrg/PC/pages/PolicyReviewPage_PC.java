package com.wrg.PC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class PolicyReviewPage_PC extends AbstractTest{
	WebDriverWait wait = null;

	
	public void quotePolicy() {
		waitForPageLoaded();
		driver.navigate().refresh();
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policyReviewScreenValidation")));
		try {
			sleep(2000);
			if(isWebElementPresentAfterWait("quoteButton")==true) {
			clickUsingJS("quoteButton");
			}
		} catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (NoAlertPresentException f) {
				f.printStackTrace();
			}
		}
	}
	
	public void goToRiskAnalysisPage()
	{
		if(getWebElements("warningMessage").size()>0) {
		click(getWebElement("riskAnalysisButton"));
		}
	}
	
	public void goToPolicyInfoPage() {
		clickUsingJS("policyInfoPageTab");
	}
	
	public void releaseLockWithoutActivity() {
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);;
		if(isWebElementPresent("releaseLockDropdown")==true) {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("releaseLockDropdown")));
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("releaseLockDropdown")));
			clickUsingJS("releaseLockDropdown");
			}
		try {
			if(isWebElementPresent("releaseLockWithoutActivityButton")==true) {
				wait.until(ExpectedConditions.visibilityOf(getWebElement("releaseLockWithoutActivityButton")));
				wait.until(ExpectedConditions.elementToBeClickable(getWebElement("releaseLockWithoutActivityButton")));
				clickUsingJS("releaseLockWithoutActivityButton");
				}
		} catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (NoAlertPresentException f) {
				f.printStackTrace();
			}
		}
		
	}
	
	public void goToQuotePage()
	{
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policyReviewScreenValidation")));
		try {
			if(isWebElementPresent("nextButton")==true) {
				clickUsingJS("nextButton");
				}
		} catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (NoAlertPresentException f) {
				f.printStackTrace();
			}
		}
		
	}
}
