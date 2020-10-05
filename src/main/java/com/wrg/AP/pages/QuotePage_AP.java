package com.wrg.AP.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class QuotePage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void quote() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (isWebElementPresentAfterWait("continueButton") == true && isWebElementDisplayed("continueButton") == true) {
			clickUsingJS("continueButton");
		}
		if (isWebElementPresentAfterWait("startApplicationButton") == true) {
			clickUsingJS("startApplicationButton");
		}
	}
	public void scheduleRatingValidation() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (isWebElementPresentAfterWait("scheduleRating")) {
			ExtentTestManager.getTest().log(Status.INFO,
					MarkupHelper.createLabel(
							"Schedule Rating -> is present" ,
							ExtentColor.GREEN));
		}
		else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Schedule Rating -> is not present" ,
								ExtentColor.RED));
		}
		String scheduleRatingValue=null;
		scheduleRatingValue = getWebElementText("scheduleRating");
		if (scheduleRatingValue.contains("0%")) {
			ExtentTestManager.getTest().log(Status.INFO,
					MarkupHelper.createLabel(
							"Schedule Rating Default 0% ->  is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Schedule Rating Default 0% -> is present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("scheduleRatingWarningMessage")) {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Schedule Rating Warning Message -> is present" ,
							ExtentColor.RED));
		}
		else {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Schedule Rating Warning Message -> is not present" ,
							ExtentColor.GREEN));
		}
	}
	public void scheduleRating(String ratingVal) {
		//selectByValue("scheduleRating", ratingVal);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		selectByOption(getWebElement("scheduleRating"), ratingVal);
		if (isWebElementPresentAfterWait("reratingMessage")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Rerating Message -> is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Rerating Message -> is not present" ,
							ExtentColor.RED));
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2033")));
		//wait.until(ExpectedConditions.visibilityOf(getWebElement("startApplicationButton")));
		sleep(8000);
		if (isWebElementPresentAfterWait("scheduleRatingWarningMessage")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Schedule Rating Warning Message -> is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Schedule Rating Warning Message -> is not present" ,
							ExtentColor.RED));
		}
		
	}
}
