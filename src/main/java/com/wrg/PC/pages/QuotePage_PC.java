package com.wrg.PC.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class QuotePage_PC extends AbstractTest {
	WebDriverWait wait = null;

	public void goToFormsPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		releaseLockWithoutActivity();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickUsingJS("nextButton");
	}

	public String getFactorValue() {
		click(getWebElement("showRatingWorksheetButton"));
		click(getWebElement("buildingClassification"));
		String lcm = getWebElementText("factorValue");
		System.out.println(lcm);
		click(getWebElement("returnToQuoteButton"));
		return lcm;
	}

	public void goToFormsPageWithoutReleaseLock() {
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("issuePolicyButton")));
		clickUsingJS("nextButton");
	}

	public void releaseLockWithoutActivity() {
		wait = new WebDriverWait(driver, 20);
		sleep(8000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (isWebElementPresent("releaseLockDropdown")==true) {
			try {
				clickUsingJS("releaseLockDropdown");
			} catch (UnhandledAlertException e) {
				try {
					Alert alert = driver.switchTo().alert();
					String alertText = alert.getText();
					System.out.println("Alert data: " + alertText);
					alert.accept();
				} catch (NoAlertPresentException f) {
					f.printStackTrace();
				}
			}
			try {
				sleep(2000);
				clickUsingJS("releaseLockWithoutActivityButton");
			} catch (UnhandledAlertException e) {
				try {
					Alert alert = driver.switchTo().alert();
					String alertText = alert.getText();
					System.out.println("Alert data: " + alertText);
					alert.accept();
				} catch (NoAlertPresentException f) {
					f.printStackTrace();
				}
			}
		}

	}

	public void goToRiskAnalysisPage() {
		wait = new WebDriverWait(driver, 10);
		if (isWebElementPresent("riskAnalysisButton") == true) {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("riskAnalysisButton")));
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("riskAnalysisButton")));
			clickUsingJS("riskAnalysisButton");
		}
	}

	public void goToPolicyInfoPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickUsingJS("policyInfoPageButton");
	}

	public void issuePolicyWithoutMakingPayment() {
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		try {
			sleep(3000);
			releaseLockWithoutActivity();
			WebElement issuePolicyButton = getWebElement("issuePolicyButton");
			click(issuePolicyButton);
		} catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert data: " + alertText);
				alert.accept();
			} catch (NoAlertPresentException f) {
				f.printStackTrace();
			}
		}
		wait.until(ExpectedConditions.visibilityOf(getWebElement("okButton")));
		click(getWebElement("okButton"));
	}

	public String getPolicyNumber() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policyNumber")));
		String policyNumber = null;
		policyNumber = getWebElementText("policyNumber").substring(19, 29);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Policy Number: " + policyNumber, ExtentColor.BLUE));

		System.out.println("Policy Number: " + policyNumber);
		click(getWebElement("policyNumber"));
		return policyNumber;
	}

	public String getQuoteNumber() {
		waitForPageLoaded();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("quoteNumber")));
		String quoteNumber = getWebElementText("quoteNumber");
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Quote Number: " + quoteNumber, ExtentColor.BLUE));
		return quoteNumber;
	}

	public void clickReinstateButton() {
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("quotePageValidationAfterReinstatement")));
		clickUsingJS("reinstateButton");
		sleep(2000);
		click("okButton");
		/// wait.until(ExpectedConditions.visibilityOf(getWebElement("reinstatementIssuedTitle")));
	}

	public List<String> getUpdatedQuoteNumber() {
		List<String> updatedQuoteNumber = new ArrayList<String>();
		updatedQuoteNumber.add(getWebElementText("reinstatementMessage").substring(21, 31));
		ExtentTestManager.getTest().log(Status.INFO, MarkupHelper
				.createLabel("Reinstated Quote Number is : " + updatedQuoteNumber.get(0), ExtentColor.BLUE));
		return updatedQuoteNumber;
	}

}
