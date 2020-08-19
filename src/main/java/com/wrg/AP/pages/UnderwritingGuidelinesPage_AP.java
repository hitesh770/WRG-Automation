package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class UnderwritingGuidelinesPage_AP extends AbstractTest {
	WebDriverWait wait = null;
	protected static String quoteNumber;
	PolicyFormSelectionPage_AP policyFormSelectionPage = null;

	public boolean isWebElementPresentInSupplemental(String element) {
		driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);
		if (getWebElements(element).size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	public void clickIfElementIsDisplayed(String element) {
		if (isWebElementPresentInSupplemental(element) == true) {
			clickUsingJS(element);
		}
	}

	public String guidelines() {
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (isWebElementPresentAfterWait("creatingQuoteLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("creatingQuoteLoader")));
		}
		if (isWebElementPresentAfterWait("savingQuoteDetailsLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("savingQuoteDetailsLoader")));
		}
		if (isWebElementPresentAfterWait("loader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
		}
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("underwritingGuidelinesHeading")));
		click("businessOwnedFor3YearsOrMoreYES");
		clickUsingJS("moreThan3LossesNO");
		quoteNumber = getWebElementText("quoteNumber").substring(7, 17);
		ExtentTestManager.getTest().log(Status.INFO, "Quote Number: " + quoteNumber);
		return quoteNumber;
	}

	public String goToPolicyFormSelectionPage() {
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (isWebElementPresentAfterWait("creatingQuoteLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("creatingQuoteLoader")));
		}
		if (isWebElementPresentAfterWait("savingQuoteDetailsLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("savingQuoteDetailsLoader")));
		}
		if (isWebElementPresentAfterWait("loader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
		}
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("underwritingGuidelinesHeading")));
		clickUsingJS("businessOwnedFor3YearsOrMoreYES");
		clickUsingJS("moreThan3LossesNO");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("nextButton"));
		click("nextButton");
		waitForPageLoaded();
		quoteNumber = getWebElementText("quoteNumber").substring(7, 17);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Quote Number: " + quoteNumber, ExtentColor.BLUE));
		return quoteNumber;
	}

	public void elpQuestions() {
		clickIfElementIsDisplayed("businessConductOperationsNO");
		clickIfElementIsDisplayed("bankruptcyInPastNO");
		clickIfElementIsDisplayed("policyDeclinedNO");
		clickIfElementIsDisplayed("manufacturingOperationsOrBusinessYES");
		clickIfElementIsDisplayed("qualityConrolYES");
		clickIfElementIsDisplayed("warningsAndLabelsCompliantWithRegulationsYES");
	}

	public String goToPolicyWideCoveragesPage(String classCodes) {
		wait = new WebDriverWait(driver, 30);
		if (isWebElementPresentAfterWait("savingQuoteDetailsLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("savingQuoteDetailsLoader")));
		}
		if (isWebElementPresentAfterWait("loader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
		}
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("underwritingGuidelinesHeading")));
		sleep(2000);
		quoteNumber = getWebElementText("quoteNumber").substring(7, 17);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Quote Number: " + quoteNumber, ExtentColor.BLUE));
		clickUsingJS("businessOwnedFor3YearsOrMoreYES");
		clickUsingJS("anyLossesInPastYearsNO");
		List<String> codes = new ArrayList<String>();
		if (classCodes.contains(",")) {
			String[] codesArray = classCodes.split(",");
			for (String code : codesArray) {
				codes.add(code);
			}
			for (int i = 0; i < elpClassCodeArray.length; i++) {
				for (String classCode : codes) {
					if (elpClassCodeArray[i].equalsIgnoreCase(classCode)) {
						elpQuestions();
						// break;
					} else {
						elpQuestions();
						// break;
					}
				}
			}
		} else {
			for (int i = 0; i < elpClassCodeArray.length; i++) {
				if (elpClassCodeArray[i].equalsIgnoreCase(classCodes)) {
					elpQuestions();
					// break;
				} else {
					elpQuestions();
					// break;
				}
			}
		}
		clickUsingJS("nextButton");
		waitForPageLoaded();
		return quoteNumber;
	}
}
