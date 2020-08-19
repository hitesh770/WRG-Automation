package com.wrg.AP.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class PaymentDetailsPage_AP extends AbstractTest{
	WebDriverWait wait=null;
	
	
	public void downPayment() {
		wait = new WebDriverWait(driver, 40);
		waitForPageLoaded();
		sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("mailPolicyTo"));
		selectByOption(getWebElement("mailPolicyTo"), "Directly to Insured");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("downPaymentMethodDropdown"));
		selectByValue(getWebElement("downPaymentMethodDropdown"), "cash");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("submitButton"));
		click("submitButton");
		String mainwindow = driver.getWindowHandle(); // get parent(current) window name
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			click("yesButton");
		}
		driver.switchTo().window(mainwindow);
		wait.until(ExpectedConditions.urlContains("qnbconfirmation"));
	}

}
