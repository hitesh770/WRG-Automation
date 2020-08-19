package com.wrg.PC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;


public class WrgHomePage_PC extends AbstractTest{
	WebDriverWait wait=null;


	
	public void searchAccount(String accountNumber) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		click(("searchButton"));
		click(("accountsTab"));
		type(("accountNumberSearchBox"),accountNumber);
		click(("accountSearchButton"));
		click(("searchedAccountNumberLink"));
	}
	
	public void searchExistingPolicy() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		click(("searchButton"));
		type(("policyNumberSearchBox"),getData("policyNumber"));
		click(("policySearchButton"));
		click(("searchedPolicyNumberLink"));
	}
	
	public void searchPolicy(String policyNumber) {
		wait = new WebDriverWait(driver, 50);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickUsingJS("searchButton");
		clickUsingJS("advancedTab");
		clickUsingJS("searchForDropdown");
		clickUsingJS("searchForPolicy");
		type(("policyNumberTextBox"),policyNumber);
		clickUsingJS("advancedSearchButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("searchedPolicyNumberLink")));
		clickUsingJS("searchedPolicyNumberLink");
	}
	
	public void searchSubmission(String submissionNumner) {
		wait = new WebDriverWait(driver, 10);
		clickUsingJS("searchButton");
		clickUsingJS("advancedTab");
		clickUsingJS("searchForDropdown");
		clickUsingJS("searchForSubmission");
		type(("submissionNumnerTextBox"),submissionNumner);
		clickUsingJS("advancedSearchButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("searchedSubmissionNumberLink")));
		clickUsingJS("searchedSubmissionNumberLink");
	}

	public void createNewAccount() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickUsingJS("actionsButton");
		clickUsingJS("newAccountButton");
	}
}
