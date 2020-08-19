package com.wrg.PC.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class FormsPage_PC extends AbstractTest{
	WebDriverWait wait = null;

	
	
	public void goToPaymentPage() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("formsScreenValidation")));
		click(getWebElement("nextButton"));
		//return new FormsPage_PC(driver);
	}
}
