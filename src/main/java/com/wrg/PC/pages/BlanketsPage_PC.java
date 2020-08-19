package com.wrg.PC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class BlanketsPage_PC extends AbstractTest {
	WebDriverWait wait = null;

	

	public void goToSupplementalPage() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("blanketsScreenValidation")));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		WebElement nextButton = getWebElement("nextButton");
		click(nextButton);

	}

}
