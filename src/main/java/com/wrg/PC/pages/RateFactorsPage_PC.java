package com.wrg.PC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class RateFactorsPage_PC extends AbstractTest{
	WebDriverWait wait = null;
	
	public void updateEstimatedOverridePotential() {
		waitForPageLoaded();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickUsingJS("estimatedLossPotentialOverride");
		type("estimatedLossPotentialOverride","2");
	}
	
	public void goToNextPage() {
		wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("nextButton")));
			actionClick("nextButton");
		} catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert data: " + alertText);
				alert.dismiss();
			} catch (NoAlertPresentException f) {
				f.printStackTrace();
			}
		}
	}
	
}
