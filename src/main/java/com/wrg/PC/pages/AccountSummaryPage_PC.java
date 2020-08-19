package com.wrg.PC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class AccountSummaryPage_PC extends AbstractTest {
	WebDriverWait wait = null;

	
	
	public boolean isAccountCreationSuccessful() {
		boolean isAccountCreated=verifyAccountCreation();
		Assert.assertEquals(true, isAccountCreated);
		return isAccountCreated;
	}
	
	public boolean verifyAccountCreation() {
		boolean isAccountCreated=false;
		if(isWebElementDisplayed(("accountFileSummaryTitle"))==true) {
			isAccountCreated=true;
		}else {
			isAccountCreated=false;
		}
		return isAccountCreated;
	}

	public void createNewSubmission() {
		click(getWebElement("actionsButton"));
		click(getWebElement("newSubmissionsButton"));
	}
	
	public String getAccountNumber() {
		String accountNumber=getWebElementText("accountNumber");
		ExtentTestManager.getTest().log(Status.INFO, MarkupHelper
				.createLabel("Account Number is : "+accountNumber, ExtentColor.BLUE));
		return accountNumber;
	}
	
	public void clickEditAccountButton() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("editAccountButton")));
		clickUsingJS("editAccountButton");
	}
	
}
