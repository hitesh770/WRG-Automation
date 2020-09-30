package com.wrg.AP.pages;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class PolicySearchPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void searchQuote(String quoteNumber) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlContains("policies"));
		if (isWebElementPresent("okButton") == true && isWebElementDisplayed("okButton") == true) {
			click("okButton");
		}
		wait.until(ExpectedConditions.visibilityOf(getWebElement("viewOrEditQuoteButton")));
		// sleep(3000);
		click("viewOrEditQuoteButton");
		click("allOtherPoliciesLink");
		selectByOption(getWebElement("searchTypeDropdown"), "Quote Number");
		type(("quoteNumberInput"), quoteNumber);
		click("searchButton");
		sleep(2000);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//a[contains(text(),'" + quoteNumber + "')]")));
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to locate element " + e.getStackTrace());
		}
	}

	public void searchValuesUnderViewEditQuoteSection(String searchBy, String quoteNumber, String insuredName,
			String expectedQuoteStatus) {
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		clickUsingJS("homeButton");
		if (isWebElementPresentAfterWait("yesButton") == true) {
			click("yesButton");
		}
		wait.until(ExpectedConditions.urlContains("home"));
		click("policiesOrQuoteLink");
		wait.until(ExpectedConditions.urlContains("policies"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("viewOrEditQuoteButton")));
		sleep(3000);
		click("viewOrEditQuoteButton");
		click("allOtherPoliciesLink");
		selectByOption(getWebElement("searchTypeDropdown"), searchBy);
		if (searchBy.contentEquals("Quote Number")) {
			type(("quoteNumberInput"), quoteNumber);
		} else {
			type(("insuredNameInput"), insuredName);
		}
		if (isWebElementEnabled("searchButton") == true) {
			click("searchButton");
		}
		sleep(2000);

		if (isWebElementPresent("insuredName") == true
				&& (expectedQuoteStatus.equalsIgnoreCase("Draft") || expectedQuoteStatus.equalsIgnoreCase("Quoted")
						|| expectedQuoteStatus.equalsIgnoreCase("Under UW Review")
						//|| expectedQuoteStatus.equalsIgnoreCase("In Force")
						//|| expectedQuoteStatus.equalsIgnoreCase("Cancelled")
						)) {
			if (getWebElementText("insuredName").equals(insuredName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						getWebElement("quoteStatus"));
				Assert.assertEquals(getWebElementText("quoteStatus"), expectedQuoteStatus);
				ExtentTestManager.getTest().log(Status.INFO, insuredName + ": found as expected");
			} else if (isWebElementPresent("insuredName1") == true
					&& getWebElementText("insuredName1").equals(insuredName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						getWebElement("quoteStatus1"));
				if (getWebElementText("quoteStatus1").equals(expectedQuoteStatus)) {
					ExtentTestManager.getTest().log(Status.INFO, insuredName + ": found under Recently Viewed");
				} else {
					ExtentTestManager.getTest().log(Status.FAIL,
							MarkupHelper.createLabel(
									insuredName + ": found under Recently Viewed But Expected Status "
											+ expectedQuoteStatus + " does not match current Status "
											+ getWebElementText("quoteStatus1") + ", Defect should be reported",
									ExtentColor.RED));
					Assert.assertEquals(getWebElementText("quoteStatus1"), expectedQuoteStatus);
				}
			} else {
				ExtentTestManager.getTest().log(Status.INFO, insuredName + ": not found");
			}
		}else if (isWebElementPresent("insuredName") == true 
				&& !getWebElementText("insuredName").equals(insuredName)
				&& (expectedQuoteStatus.equalsIgnoreCase("Cancelled")
				|| expectedQuoteStatus.equalsIgnoreCase("In Force")
				|| expectedQuoteStatus.equalsIgnoreCase("Scheduled")))
		{
			ExtentTestManager.getTest()
			.log(Status.PASS,
					MarkupHelper.createLabel("The search is working as Expected for" + " "
							+ expectedQuoteStatus + " status when searched by " + searchBy,
							ExtentColor.GREEN));
		}
		else {
			if (isWebElementPresent("noResultMessage") == true && isWebElementDisplayed("noResultMessage") == true
					&& expectedQuoteStatus.equalsIgnoreCase("Cancelled")
					|| expectedQuoteStatus.equalsIgnoreCase("In Force")
					|| expectedQuoteStatus.equalsIgnoreCase("Scheduled")) {
				ExtentTestManager.getTest()
				.log(Status.PASS,
						MarkupHelper.createLabel("The search is working as Expected for" + " "
								+ expectedQuoteStatus + " status when searched by " + searchBy,
								ExtentColor.BLUE));
			}
			 else {
				ExtentTestManager.getTest()
						.log(Status.FAIL,
								MarkupHelper.createLabel("The search is not working as Expected for" + " "
										+ expectedQuoteStatus + " status when searched by " + searchBy,
										ExtentColor.RED));
				Assert.fail("The search is not working as Expected for " + expectedQuoteStatus
						+ " status when searched by " + searchBy);

			}
		}

	}

	public void searchValuesUnderPolicyDetailsSection(String searchBy, String policyNumber, String insuredName,
			String expectedQuoteStatus) {
		wait = new WebDriverWait(driver, 20);
		clickUsingJS("homeButton");
		if (isWebElementPresent("yesButton") == true) {
			click("yesButton");
		}
		wait.until(ExpectedConditions.urlContains("home"));
		click("policiesOrQuoteLink");
		wait.until(ExpectedConditions.urlContains("policies"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policyDetailsButton")));
		sleep(3000);
		click("policyDetailsButton");
		click("businessownersItem");
		selectByOption(getWebElement("searchTypeDropdown"), searchBy);
		if (searchBy.contentEquals("Policy Number")) {
			type(("quoteNumberInput"), policyNumber);
		} else {
			type(("insuredNameInput"), insuredName);
		}
		if (isWebElementEnabled("searchButton") == true) {
			click("searchButton");
		}
		sleep(2000);
//		if (isWebElementPresentAfterWait("insuredName") == true && (expectedQuoteStatus.equalsIgnoreCase("Draft")
//				|| expectedQuoteStatus.equalsIgnoreCase("Quoted") || expectedQuoteStatus.equalsIgnoreCase("Cancelled")
//				|| expectedQuoteStatus.equalsIgnoreCase("Under UW Review"))) {
//			ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper
//					.createLabel("Record shouldn't have displayed, Defect should be reported", ExtentColor.RED));
//			Assert.fail("Search Under Policy Details Section working as expected");
//		} else if ((isWebElementPresentAfterWait("insuredName") == true
//				&& (expectedQuoteStatus.equalsIgnoreCase("In Force")))
//				|| (!isWebElementPresentAfterWait("insuredName") == true
//						&& (expectedQuoteStatus.equalsIgnoreCase("Draft")
//								|| expectedQuoteStatus.equalsIgnoreCase("Quoted")
//								|| expectedQuoteStatus.equalsIgnoreCase("Cancelled")
//								|| expectedQuoteStatus.equalsIgnoreCase("Under UW Review")))) {
//			ExtentTestManager.getTest().log(Status.INFO, "Search Under Policy Details Section working as expected");
//		}

		if (isWebElementPresent("insuredName") == true
				&& (expectedQuoteStatus.equalsIgnoreCase("Draft") || expectedQuoteStatus.equalsIgnoreCase("Quoted")
						|| expectedQuoteStatus.equalsIgnoreCase("Under UW Review")
						|| expectedQuoteStatus.equalsIgnoreCase("Cancelled"))) {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("insuredName")));
			ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper
					.createLabel("Record shouldn't have displayed, Defect should be reported", ExtentColor.RED));
			Assert.fail("Search Under Policy Details Section working as expected");
		}

		else if (isWebElementPresent("insuredName") == true && (expectedQuoteStatus.equalsIgnoreCase("In Force"))) {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("insuredName")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getWebElement("quoteStatus"));
			ExtentTestManager.getTest().log(Status.INFO, getWebElementText("insuredName") + ": found as expected");
			Assert.assertEquals(getWebElementText("quoteStatus"), expectedQuoteStatus);
		}
		if (isWebElementPresent("insuredName1") == true && getWebElementText("insuredName1").equals(insuredName)) {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("insuredName1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
					getWebElement("quoteStatus1"));
			if (getWebElementText("quoteStatus1").equals(expectedQuoteStatus)) {
				ExtentTestManager.getTest().log(Status.INFO,
						getWebElementText("insuredName1") + ": found under Recently Viewed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								getWebElementText("insuredName1") + ": found under Recently Viewed But Expected Status "
										+ expectedQuoteStatus + " does not match current Status "
										+ getWebElementText("quoteStatus1") + ", Defect should be reported",
								ExtentColor.RED));
				Assert.assertEquals(getWebElementText("quoteStatus1"), expectedQuoteStatus);
			}
		}

	}

	public void searchValuesUnderBillingSection(String searchBy, String policyNumber, String insuredName,
			String accountNumber, String expectedQuoteStatus) {
		wait = new WebDriverWait(driver, 20);
		clickUsingJS("homeButton");
		if (isWebElementPresent("yesButton") == true) {
			click("yesButton");
		}
		waitForPageLoaded();
		wait.until(ExpectedConditions.urlContains("home"));
		clickUsingJS("billingLink");
		wait.until(ExpectedConditions.urlContains("billing"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("billingDetailsButton")));
		sleep(3000);
		click("billingDetailsButton");
		click("businessownersItem");
		selectByOption(getWebElement("searchTypeDropdownBilling"), searchBy);
		if (searchBy.contentEquals("Policy Number")) {
			type(("quoteNumberInput"), policyNumber);
		} else if (searchBy.contentEquals("Insured Name")) {
			type(("insuredNameInput"), insuredName);
		} else if (searchBy.contentEquals("Account Number")) {
			type(("accountNumberInput"), accountNumber);
		}
		if (isWebElementEnabled("searchButtonBilling") == true) {
			click("searchButtonBilling");
		}
		sleep(2000);
		if (isWebElementPresentAfterWait("insuredNameInAccount") == true
				&& (expectedQuoteStatus.equalsIgnoreCase("Draft") || expectedQuoteStatus.equalsIgnoreCase("Quoted")
						|| expectedQuoteStatus.equalsIgnoreCase("Under UW Review"))) {
			ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper
					.createLabel("Record shouldn't have displayed, Defect should be reported", ExtentColor.RED));
		}
//		else if (!isWebElementPresentAfterWait("insuredNameInAccount") == true
//				&& (expectedQuoteStatus.equalsIgnoreCase("In Force")
//						|| expectedQuoteStatus.equalsIgnoreCase("Cancelled"))) {
//			if (!getWebElement("activeOnlyCheckbox").isSelected()) {
//				// click("activeOnlyCheckbox");
//				Assert.assertTrue(!isWebElementPresentAfterWait("insuredName"), "Records not displayed as Expected");
//
//			}
//		} 
		else if (!isWebElementPresentAfterWait("insuredNameInAccount") == true
				&& (expectedQuoteStatus.equalsIgnoreCase("Draft") || expectedQuoteStatus.equalsIgnoreCase("Quoted")
						|| expectedQuoteStatus.equalsIgnoreCase("Under UW Review"))) {
			ExtentTestManager.getTest().log(Status.INFO, "Search under Billing section is working as Expected");
		} else if (isWebElementPresent("insuredNameInAccount") == true
				&& (expectedQuoteStatus.equalsIgnoreCase("In Force")
						|| expectedQuoteStatus.equalsIgnoreCase("Scheduled"))) {
			ExtentTestManager.getTest().log(Status.INFO, "Search under Billing section is working as Expected");
		}
		if (expectedQuoteStatus.equalsIgnoreCase("Cancelled")
				&& getWebElement("activeOnlyCheckbox").getAttribute("class").contains("ng-not-empty")) {
			click("activeOnlyCheckbox");
			sleep(3000);
			if (isWebElementPresentAfterWait("insuredNameInAccount") == true) {
				ExtentTestManager.getTest().log(Status.INFO, "Search under Billing section is working as Expected");
			} else {
				ExtentTestManager.getTest().log(Status.INFO, "Search under Billing section isnot  working as Expected");
			}
		}

	}

	public void verifyNoResultsDisplayed(String searchBy, String quoteNumber, String insuredName) {
		wait = new WebDriverWait(driver, 20);
		clickUsingJS("homeButton");
		if (isWebElementPresent("yesButton") == true) {
			click("yesButton");
		}
		wait.until(ExpectedConditions.urlContains("home"));
		click("policiesOrQuoteLink");
		wait.until(ExpectedConditions.urlContains("policies"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("viewOrEditQuoteButton")));
		sleep(3000);
		click("viewOrEditQuoteButton");
		click("allOtherPoliciesLink");
		selectByOption(getWebElement("searchTypeDropdown"), searchBy);
		if (searchBy.contentEquals("Quote Number")) {
			type(("quoteNumberInput"), quoteNumber);
			if (isWebElementEnabled("searchButton") == true) {
				click("searchButton");
			}
			sleep(2000);
			Assert.assertEquals(getWebElementText("noResultMessage"), "The search returned zero results.");
		} else {
			type(("insuredNameInput"), insuredName);
			if (isWebElementEnabled("searchButton") == true) {
				click("searchButton");
			}
			sleep(2000);
			if (isWebElementPresent("noResultMessage") == true && isWebElementDisplayed("noResultMessage") == true) {
				Assert.assertEquals(getWebElementText("noResultMessage"), "The search returned zero results.");
			} else if ((isWebElementPresent("insuredName") == true
					&& getWebElementText("insuredName").contains(insuredName))
					|| (isWebElementPresent("insuredName1") == true
							&& getWebElementText("insuredName1").contains(insuredName))) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						getWebElement("insuredName1"));
				Assert.fail("Insured Name: " + getWebElementText("insuredName1") + "is searchable");
			} else {
				Assert.assertTrue(isWebElementPresent("insuredName") == false,
						"Results are displayed but Record is not searchable by Insured Name: "
								+ getWebElementText("insuredName"));

			}
		}
//		click("searchButton");
//		sleep(2000);
//		Assert.assertEquals(getWebElementText("noResultMessage"), "The search returned zero results.");
//		Assert.assertTrue(isWebElementPresent("insuredName"));

	}

	public void searchViaQuoteNumberUnderViewEditQuote(String quoteNumber, String insuredName,
			String expectedQuoteStatus) {
		wait = new WebDriverWait(driver, 20);
		clickUsingJS("homeButton");
		if (isWebElementPresent("yesButton") == true) {
			click("yesButton");
		}
		wait.until(ExpectedConditions.urlContains("home"));
		click("policiesOrQuoteLink");
		wait.until(ExpectedConditions.urlContains("policies"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("viewOrEditQuoteButton")));
		sleep(3000);
		click("viewOrEditQuoteButton");
		click("allOtherPoliciesLink");
		selectByOption(getWebElement("searchTypeDropdown"), "Quote Number");
		type(("quoteNumberInput"), quoteNumber);
		if (isWebElementEnabled("searchButton") == true) {
			click("searchButton");
		}
		sleep(2000);
		if (isWebElementPresent("insuredName") == true) {
			Assert.assertTrue(true, "Record displayed");
			if (getWebElementText("insuredName").equals(insuredName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						getWebElement("quoteStatus"));
				Assert.assertEquals(getWebElementText("quoteStatus"), expectedQuoteStatus);
				ExtentTestManager.getTest().log(Status.INFO, insuredName + ": found");
			} else if (getWebElementText("insuredName1").equals(insuredName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						getWebElement("quoteStatus"));
				if (getWebElementText("quoteStatus1").equals(expectedQuoteStatus)) {
					ExtentTestManager.getTest().log(Status.INFO, insuredName + ": found under Recently Viewed");
				} else {
					ExtentTestManager.getTest().log(Status.FAIL, insuredName
							+ ": found under Recently Viewed But Expected" + " Status does not match current Status");
				}
				Assert.assertEquals(getWebElementText("quoteStatus1"), expectedQuoteStatus);
			} else {
				ExtentTestManager.getTest().log(Status.INFO, insuredName + ": not found");
			}
		} else {
			if (isWebElementPresent("noResultMessage") == true && isWebElementDisplayed("noResultMessage") == true) {
				Assert.assertEquals(getWebElementText("noResultMessage"), "The search returned zero results.");
			}
		}

	}

	public void searchViaInsuredNameUnderViewEditQuote(String quoteNumber, String insuredName,
			String expectedQuoteStatus) {
		wait = new WebDriverWait(driver, 20);
		clickUsingJS("homeButton");
		if (isWebElementPresent("yesButton") == true) {
			click("yesButton");
		}
		wait.until(ExpectedConditions.urlContains("home"));
		click("policiesOrQuoteLink");
		wait.until(ExpectedConditions.urlContains("policies"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("viewOrEditQuoteButton")));
		sleep(3000);
		click("viewOrEditQuoteButton");
		click("allOtherPoliciesLink");
		selectByOption(getWebElement("searchTypeDropdown"), "Insured Name");
		type(getWebElement("insuredNameInput"), insuredName);
		if (isWebElementEnabled("searchButton") == true) {
			click("searchButton");
		}
		sleep(2000);
		if (isWebElementPresent("insuredName") == true) {
			Assert.assertTrue(true, "Record displayed");
			if (getWebElementText("insuredName").equals(insuredName)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						getWebElement("quoteStatus"));
				Assert.assertEquals(getWebElementText("quoteStatus"), expectedQuoteStatus);
				ExtentTestManager.getTest().log(Status.INFO, insuredName + ": found");
			}
//			else if (getWebElementText("insuredName1").equals(insuredName)){
//				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
//						getWebElement("quoteStatus"));
//				Assert.assertEquals(getWebElementText("quoteStatus1"), expectedQuoteStatus);
//				ExtentTestManager.getTest().log(Status.INFO, insuredName+": found");
//			}
			else {
				ExtentTestManager.getTest().log(Status.INFO, insuredName + ": not found");
			}
		} else {
			if (isWebElementPresent("noResultMessage") == true && isWebElementDisplayed("noResultMessage") == true) {
				Assert.assertEquals(getWebElementText("noResultMessage"), "The search returned zero results.");
			}
		}

	}

	public void goToSearchQuotePage() {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("policiesOrQuoteLink")));
		WebElement policiesOrQuoteLink = getWebElement("policiesOrQuoteLink");
		Actions action = new Actions(driver);
		action.moveToElement(policiesOrQuoteLink);
		action.click();
		action.perform();
	}

	public void clickBeginSubmissionButton() {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		//wait.until(ExpectedConditions.elementToBeClickable(getWebElement("beginSubmissionButton")));
		if(isWebElementPresent("beginSubmissionButton")==true) {
			clickUsingJS("beginSubmissionButton");
		}else {
			clickUsingJS("continueQuoteButton");
		}
	}

	public void clickContinueQuoteButton() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlContains("summary"));
		clickUsingJS("continueQuoteButton");
	}
	
	public HashMap<Integer, String> getCreateNewButtonOptions() {
		HashMap<Integer, String> createquoteMap=new HashMap<Integer, String>();
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policiesOrQuoteLink")));
		clickUsingJS("policiesOrQuoteLink");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("createNewQuoteButton")));
		clickUsingJS("createNewQuoteButton");
		waitForPageLoaded();
		List<WebElement> quoteoptions=getWebElements("createnewquoteoptions");
		
		for(int i=0;i<quoteoptions.size();i++) { 
			createquoteMap.put(i+1,quoteoptions.get(i).getText());  
			ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(quoteoptions.get(i).getText(),ExtentColor.BLUE));
		}
		
		return createquoteMap;
		
	}
	
}
