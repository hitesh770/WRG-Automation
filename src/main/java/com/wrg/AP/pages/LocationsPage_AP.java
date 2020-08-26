package com.wrg.AP.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class LocationsPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void goToClassificationsPage(String state,String numberOfLocations) {
		wait = new WebDriverWait(driver, 10);
		waitForPageLoaded();
		addLocation(state, numberOfLocations);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButtonBottom")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("nextButtonBottom")));
		clickUsingJS("nextButtonBottom");
	}

	public void addLocation(String state, String numberOfLocations) {
		waitForPageLoaded();
		for (int i = 1; i < Integer.parseInt(numberOfLocations); i++) {
			clickUsingJS("addNewLocationButton");
			String mainwindow = driver.getWindowHandle();
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				if (state.equalsIgnoreCase("Indiana")) {
					type("addressLine1", getData("indianaAddress" + i));
					type("city", getData("indianaCityName" + i));
					type("zipCode", getData("indianaPincodeValue" + i));
				} else {
					type("addressLine1", getData("ohioAddress" + i));
					type("city", getData("ohioCityName" + i));
					type("zipCode", getData("ohioPincodeValue" + i));
				}
				clickUsingJS("saveButton");
				try { 
					sleep(4000);
					clickUsingJS("useVerifiedButton");
				} catch (Exception e) {
					clickUsingJS("useOriginalButton");
				}

			}
			driver.switchTo().window(mainwindow);

		}
	}

}
