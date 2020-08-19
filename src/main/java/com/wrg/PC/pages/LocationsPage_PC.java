package com.wrg.PC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.wrg.abstestbase.AbstractTest;

public class LocationsPage_PC extends AbstractTest {
	WebDriverWait wait = null;

	public void goToNextPage() {
		waitForPageLoaded();
		//wait = new WebDriverWait(driver, 20);
		try {
			//wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
			if(isWebElementPresent("nextButton")==true) {
				actionClick("nextButton");
			}
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

	public void addNewLocation(String state, String numberOfLocations) {
		wait = new WebDriverWait(driver, 10);
		for (int i = 1; i < Integer.parseInt(numberOfLocations); i++) {
			click("newLocationButton");
			type("address1", getData(state.toLowerCase() + "Address" + i));
			getWebElement("address1").sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("city")));
			type("city", getData(state.toLowerCase() + "CityName" + i));
			getWebElement("city").sendKeys(Keys.TAB);
			sleep(2000);
			type("zipCode", getData(state.toLowerCase() + "PincodeValue" + i));
			getWebElement("zipCode").sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("county")));
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("updateButton")));
			try {
				clickUsingJS("updateButton");
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
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("verifyAddressButton")));
			try {
				clickUsingJS("verifyAddressButton");
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

}
