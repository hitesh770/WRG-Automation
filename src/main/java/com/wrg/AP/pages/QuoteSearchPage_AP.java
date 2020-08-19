package com.wrg.AP.pages;

import java.text.SimpleDateFormat;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class QuoteSearchPage_AP extends AbstractTest {
	java.util.Date date = new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	String timestamp = sdf.format(date);
	WebDriverWait wait = null;

	public String searchQuote(String applicantName) {
		wait = new WebDriverWait(driver, 10);
		if (applicantName==null) {
			applicantName = "Automation_Applicant_" + timestamp;
		} else {
			applicantName = applicantName +"_"+ timestamp;
		}
		System.out.println(applicantName);
		sleep(3000);
		wait.until((ExpectedConditions.visibilityOf(getWebElement("applicantNameTextBox"))));
		type(("applicantNameTextBox"), applicantName);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Insured Name is: " + applicantName, ExtentColor.BLUE));
		click("nextButton");
		return applicantName;
	}

}
