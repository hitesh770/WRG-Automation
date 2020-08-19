package com.wrg.PC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class PaymentPage_PC extends AbstractTest {
	WebDriverWait wait = null;

	public void IssuePolicy(String paymentMethod,String downPaymntMethod,String downPaymentAmount) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("paymentScreenValidation")));
		makePayment(paymentMethod,downPaymntMethod,downPaymentAmount);
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("issuePolicyButton")));
		clickUsingJS("issuePolicyButton");
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("okButton")));
		clickUsingJS("okButton");
	}

	public void IssuePolicyWithoutMakingPayment() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("paymentScreenValidation")));
		WebElement issuePolicyButton = getWebElement("issuePolicyButton");
		wait.until(ExpectedConditions.elementToBeClickable(issuePolicyButton));
		clickUsingJS(issuePolicyButton);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("okButton")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("okButton")));
		clickUsingJS(getWebElement("okButton"));
	}

	public void billToSameAccount() {
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 10);
		click("accountBillYES");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("billToDifferentAccountNO")));
		click("billToDifferentAccountNO");
	}

	public void makePayment(String paymentMethod, String downPaymentMethod, String downPaymentAmount) {
		wait = new WebDriverWait(driver, 10);
		WebElement mailPolicyDropdown = getWebElement("mailPolicyDropdown");
		click(mailPolicyDropdown);
		WebElement sendingPolicyToWhom = getWebElement("sendingPolicyToWhom");
		click(sendingPolicyToWhom);
		sleep(2000);
		
		if (paymentMethod.equalsIgnoreCase("DirectBill")) {
			if (downPaymentMethod.equalsIgnoreCase("Cash")) {
				
				if(downPaymentAmount.equalsIgnoreCase("0")) {
					clear("downpaymentAmountTextbox");
					sleep(1000);
					type("downpaymentAmountTextbox", downPaymentAmount);
				}else {
				clickUsingJS("downpaymentMethodDropdown");
				clickUsingJS("downpaymentMethodCash");
				}
			} else if (downPaymentMethod.equalsIgnoreCase("Check")) {
				clickUsingJS("downpaymentMethodDropdown");
				clickUsingJS("downpaymentMethodCheck");
				wait.until(ExpectedConditions.visibilityOf(getWebElement("checkNumberField")));
				type("checkNumberField", getData("checkNumber"));
			} else if (downPaymentMethod.equalsIgnoreCase("Credit Debit or eCheck")) {
				clickUsingJS("downpaymentMethodDropdown");
				clickUsingJS("downpaymentMethodCredit");
			}
			sleep(2000);
		}else if(paymentMethod.equalsIgnoreCase("EFT")) {
			clickUsingJS("eftCheckbox");
			wait.until(ExpectedConditions.visibilityOf(getWebElement("addPaymentButton")));
			clickUsingJS("addPaymentButton");
			type("routingNumberField",getData("routingNumber"));
			type("bankAccountNumberField",getData("bankAccountNumber"));
			clickUsingJS("bankAccountType");
			clickUsingJS("bankAccountTypeSavings");
			clickUsingJS("institutionType");
			clickUsingJS("institutionTypeBank");
			clickUsingJS("acknowledgementCheckbox");
			type("eftFormAuthorizedBy","test");
			type("bankNameField",getData("bankName"));
			clickUsingJS("okButton");
			sleep(3000);
			click("selectPayment");
			if (downPaymentMethod.equalsIgnoreCase("Cash")) {
				clickUsingJS("downpaymentMethodDropdown");
				clickUsingJS("downpaymentMethodCash");
			} else if (downPaymentMethod.equalsIgnoreCase("Check")) {
				clickUsingJS("downpaymentMethodDropdown");
				clickUsingJS("downpaymentMethodCheck");
				wait.until(ExpectedConditions.visibilityOf(getWebElement("checkNumberField")));
				type("checkNumberField", getData("checkNumber"));
			} else if (downPaymentMethod.equalsIgnoreCase("Credit Debit or eCheck")) {
				clickUsingJS("downpaymentMethodDropdown");
				clickUsingJS("downpaymentMethodCredit");
			}
			sleep(2000);
		}
//		clickUsingJS("downpaymentAmountTextbox");
//		clear("downpaymentAmountTextbox");
//		sleep(2000);
//		type("downpaymentAmountTextbox",getData("downpaymentAmount"));
//		getWebElement("downpaymentAmountTextbox").sendKeys(Keys.TAB);
//		sleep(1000);
	}

	public String getPolicyNumber() {
		waitForPageLoaded();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// wait.until(ExpectedConditions.visibilityOf(getWebElement("policyNumber")));
		String policyNumber = null;
		policyNumber = getWebElementText("policyNumber").substring(19, 29);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Policy Number: " + policyNumber, ExtentColor.BLUE));
		System.out.println(policyNumber);
		click("policyNumber");
		return policyNumber;
	}

}
