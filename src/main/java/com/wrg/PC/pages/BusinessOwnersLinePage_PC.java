package com.wrg.PC.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class BusinessOwnersLinePage_PC extends AbstractTest {
	WebDriverWait wait = null;

	

	public void goToLocationsPage() {
		waitForPageLoaded();
		wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("businesOwnersScreenValidation")));
		try {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement nextButton = getWebElement("nextButton");
			click(nextButton);
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
