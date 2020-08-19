package com.wrg.PC.pages;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class EnterAccountInformationPage_PC extends AbstractTest {

	public static HashMap<String, String> environment = new HashMap<String, String>();
	WebDriverWait wait = null;
	java.util.Date date = new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	String timestamp = sdf.format(date);

	public void searchForAccount() {
		type(("companyName"), "Blue_Agency");
		clickUsingJS("searchButton");
		setObjectWaitTimeout(3);
		clickUsingJS("createNewAccountButton");
		clickUsingJS("companyLink");
		setObjectWaitTimeout(3);
	}

	public void selectAccountType(String accountType) {
		waitForPageLoaded();
		clickUsingJS("accountTypeDropdown");
		clickUsingJS(driver.findElement(By.xpath("//li[contains(text(),'" + accountType + "')]")));
		sleep(2000);
	}

	public String enterInsuredName(String insuredName) {
		wait = new WebDriverWait(driver, 10);
		clear("insuredNameTextBox");
		if (insuredName.isEmpty()) {
			insuredName = "Automation_Applicant_" + timestamp;
		} else {
			insuredName = insuredName + "_" + timestamp;
		}
		sleep(2000);
		type("insuredNameTextBox", insuredName);
		getWebElement("insuredNameTextBox").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.textToBe(By.id("CreateAccount:CreateAccountScreen:CreateAccountContactInputSet:GlobalContactNameInputSet:NameMedium-inputEl"),
				insuredName));
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Insured Name is: " + insuredName, ExtentColor.BLUE));
		return insuredName;
	}

	public void enterAddress(String state, String businessEntity) {
		if (state.equalsIgnoreCase("Indiana")) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			type(("addressLine1"), getData("indianaAddress"));
			sleep(2000);
			clickUsingJS("stateDropdown");
			clickUsingJS(driver.findElement(
					By.xpath("//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'" + state + "')]")));
			sleep(1000);
			type(("city"), getData("indianaCityName"));
			setObjectWaitTimeout(3);
			getWebElement("city").sendKeys(Keys.TAB);
			sleep(1000);
			type(("description"), getData("descriptionValue"));
			type(("pincode"), getData("indianaPincodeValue"));
			getWebElement("pincode").sendKeys(Keys.TAB);
			sleep(1000);
		} else if (state.equalsIgnoreCase("Ohio")) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			type(("addressLine1"), getData("ohioAddress"));
			sleep(2000);
			clickUsingJS("stateDropdown");
			clickUsingJS(driver.findElement(By.xpath(
					"//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'" + getData("ohioState") + "')]")));
			sleep(1000);
			type(("city"), getData("ohioCityName"));
			sleep(1000);
			getWebElement("city").sendKeys(Keys.TAB);
			sleep(1000);
			type(("description"), getData("descriptionValue"));
			type(("pincode"), getData("ohioPincodeValue"));
			getWebElement("pincode").sendKeys(Keys.TAB);
			sleep(1000);

		}
		clickUsingJS("businessEntityTypeDropdown");
		clickUsingJS(driver.findElement(
				By.xpath("//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'" + businessEntity + "')]")));
		type(("organizationName"), getData("organizationNameValue"));
		clickUsingJS("searchOrganization");
	}

	public void updateAccount() {
		clickUsingJS("updateButton");
	}

}
