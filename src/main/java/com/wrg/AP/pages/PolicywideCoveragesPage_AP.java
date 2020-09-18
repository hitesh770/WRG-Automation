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
	public void additionalCoveragesToolTipValidation(String classCodeNumber, String coverageNumber,String tooltipText) {
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policywideCoveragesHeading")));

		String tooltip = null;
		
		if(coverageNumber =="WGL12") {
			scrollToElement("WGL12HelpIcon");
			actionClick("WGL12HelpIcon");
			sleep(4000);
			tooltip = getWebElementText("CG2293Tooltip");
			System.out.print(tooltip);
			if (tooltipText.contains(tooltip)) {					
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"WGL 12 Tooltip -> " + tooltip,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"WGL 12 Tooltip -> "+ tooltip,
								ExtentColor.RED));
			}
			clickUsingJS("GeneralLiabilityEnhancementEndorsement");
			actionClick("WGL12Coverages");
			if (isWebElementPresentAfterWait("WGL12HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"WGL 12 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"WGL 12 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			sleep(2000);
			clickUsingJS("WGL12Coverages");
			if (isWebElementPresentAfterWait("WGL12HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"WGL 12 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"WGL 12 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			quitDriver(driver);
		}
	}
}
