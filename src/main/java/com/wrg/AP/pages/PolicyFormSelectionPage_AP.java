package com.wrg.AP.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class PolicyFormSelectionPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void policyForm(String formType) {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();

		wait.until(ExpectedConditions.visibilityOf(getWebElement("broadedningRadioButton")));
		if (formType.equalsIgnoreCase("broadening")) {
			clickUsingJS("broadedningRadioButton");
		} else if (formType.equalsIgnoreCase("enhancement")) {
			clickUsingJS("enhancementRadioButton");
		} else if (formType.equalsIgnoreCase("advantage")) {
			clickUsingJS("advantageRadioButton");
		}
		clickUsingJS("nextButton");
	}

	public String getQuoteNumber() {
		return getWebElementText("quoteNumber").substring(7, 17);
	}

}
