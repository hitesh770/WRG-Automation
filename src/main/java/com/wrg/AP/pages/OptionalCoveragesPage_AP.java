package com.wrg.AP.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

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
	
	public void optionalCoveragesToolTipValidation(String classCodeNumber, String coverageNumber,String tooltipText) {
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 30);
		String tooltip = null;
		
		if(coverageNumber =="CG2033") {
			clickUsingJS("AdditionalInsured");
			wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2033")));
			sleep(2000);
			scrollToElement("CG2033HelpIcon");
			actionClick("CG2033HelpIcon");
			sleep(4000);
			tooltip = getWebElementText("CG2293Tooltip");
			System.out.print(tooltip);
			if (tooltipText.contains(tooltip)) {					
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 33 Tooltip -> " + tooltip,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 33 Tooltip -> "+ tooltip,
								ExtentColor.RED));
			}
			actionClick("CG2033Coverages");
			if (isWebElementPresentAfterWait("CG2033HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 33 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 33 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			sleep(2000);
			clickUsingJS("CG2033Coverages");
			if (isWebElementPresentAfterWait("CG2033HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 33 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 33 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			closeDriver(driver);
		}
		else if(coverageNumber =="CG2034") {
			clickUsingJS("AdditionalInsured");
			wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2034")));
			sleep(2000);
			scrollToElement("CG2034HelpIcon");
			actionClick("CG2034HelpIcon");
			sleep(4000);
			tooltip = getWebElementText("CG2293Tooltip");
			System.out.print(tooltip);
			if (tooltipText.contains(tooltip)) {					
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 34 Tooltip -> " + tooltip,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 34 Tooltip -> "+ tooltip,
								ExtentColor.RED));
			}
			actionClick("CG2034Coverages");
			if (isWebElementPresentAfterWait("CG2034HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 34 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 34 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			sleep(2000);
			clickUsingJS("CG2034Coverages");
			if (isWebElementPresentAfterWait("CG2034HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 34 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 34 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			closeDriver(driver);
		}
	}
}
