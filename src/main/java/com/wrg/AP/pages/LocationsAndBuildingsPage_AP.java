package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class LocationsAndBuildingsPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	
	public void clickQuoteButton() {
		Actions action = new Actions(driver);
		wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("locationsAndBuildingsHeading")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement("quoteButton1"));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("quoteButton1")));
		sleep(3000);
		action.moveToElement(getWebElement("quoteButton1")).click().build().perform();
		sleep(3000);
		if (isWebElementPresent("calculatingQuoteLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("calculatingQuoteLoader")));
		}
	}

	public LocationsAndBuildingsPage_AP addMultipleLocations(String state, String percentageOwnerOccupiedValue, String numberOfLocations,
			String classCodeNumber) {
		Actions action = new Actions(driver);
		for (int i = 1; i <= Integer.parseInt(numberOfLocations); i++) {

			wait = new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.visibilityOf(getWebElement("locationsAndBuildingsHeading")));
			if (isWebElementPresent("addressLine1") && isWebElementEnabled("addressLine1")) {
				clear("addressLine1");
				type("addressLine1", getData(state.toLowerCase() + "Address" + i));
			}
			if (isWebElementPresent("city") && isWebElementEnabled("city")) {
				clear("city");
				type("city", getData(state.toLowerCase() + "CityName" + i));
			}
			if (isWebElementPresent("zipCode") && isWebElementEnabled("zipCode")) {
				clear("zipCode");
				type("zipCode", getData(state.toLowerCase() + "PincodeValue" + i));
			}
			getWebElement("zipCode").sendKeys(Keys.TAB);
			if (isWebElementPresent("addressVerificationModal") == true) {
				String mainwindow = driver.getWindowHandle();
				for (String popup : driver.getWindowHandles()) // iterating on child windows
				{
					driver.switchTo().window(popup);
					click("useVerifiedButton");
				}
				driver.switchTo().window(mainwindow);
			}
			sleep(2000);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");

			clickUsingJS("continueToBuildingButton");
			if (isWebElementPresent("addressVerificationModal") == true) {
				String mainwindow = driver.getWindowHandle();
				for (String popup : driver.getWindowHandles()) // iterating on child windows
				{
					driver.switchTo().window(popup);
					click("useVerifiedButton");
				}
				driver.switchTo().window(mainwindow);
			}
			sleep(2000);
			type(("yearBuilt"), getData("year"));
			selectByOption(getWebElement("constructionTypeDropdown"), "Frame");
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
			clickUsingJS("automaticSprinklerSystemYES");
			if (isWebElementPresent("buildingCheckBox") == true) {
				wait.until(ExpectedConditions.elementToBeClickable(getWebElement("buildingCheckBox")));
				wait.until(ExpectedConditions.visibilityOf(getWebElement("buildingCheckBox")));
				clickUsingJS("buildingCheckBox");
			}
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
			wait.until(ExpectedConditions.visibilityOf(getWebElement("limit")));
			sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("limit"));
			type(("limit"), getData("limitValue"));
			getWebElement("limit").sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
			selectByOption(getWebElement("automaticIncreasePercentageDropdown"), "2%");
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
			selectByOption(getWebElement("percentageOwnerOccupiedDropdown"), percentageOwnerOccupiedValue);
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("continueToClassificationButton")));
			clickUsingJS("continueToClassificationButton");
			//Select select = new Select(getWebElement("descriptionDropdown"));
			// String expectedDescription = classCode;
			// select.selectByIndex(i);

			if (classCodeNumber.contains(",")) {
				String spiltClassCodes[] = classCodeNumber.split(",");
				List<String> classCodes = new ArrayList<String>();
				for (String classCode : spiltClassCodes) {
					classCodes.add(classCode);
					Select select = new Select(getWebElement("descriptionDropdown"));
					String expectedDescription = classCode;
					List<WebElement> allClassCodes = select.getOptions();
					for (WebElement option : allClassCodes) {
						String currentClassCode = option.getText();
						if (currentClassCode.contains(expectedDescription)) {
							select.selectByVisibleText(currentClassCode);
						}
					}
					type(getWebElement("classificationSquareFootage"), getData("squareFootageValue"));
					getWebElement("classificationSquareFootage").sendKeys(Keys.TAB);
					wait.until(ExpectedConditions.elementToBeClickable(getWebElement("addNewClassificationButton")));
					clickUsingJS("addNewClassificationButton");
				}
				clickUsingJS("deleteNewClassificationButton");
				clickUsingJS("deleteButton");
			} else {
				type(getWebElement("classificationSquareFootage"), getData("squareFootageValue"));
				getWebElement("classificationSquareFootage").sendKeys(Keys.TAB);
				sleep(8000);
				wait.until(ExpectedConditions.elementToBeClickable(getWebElement("quoteButton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						getWebElement("quoteButton"));
				sleep(3000);
				action.moveToElement(getWebElement("quoteButton")).click().build().perform();
				sleep(3000);
			}

//			type(getWebElement("classificationSquareFootage"), getData("squareFootageValue"));
//			getWebElement("classificationSquareFootage").sendKeys(Keys.TAB);
//			sleep(3000);
			if (Integer.parseInt(numberOfLocations) > 1) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						getWebElement("addNewLocationButton"));
				sleep(1000);
				action.moveToElement(getWebElement("addNewLocationButton")).click().build().perform();
				sleep(1000);
			}
		}
//		if(isWebElementPresent("deleteLocationButton")) {
//		clickUsingJS("deleteLocationButton");
//		clickUsingJS("deletebutton");
//		}
		// scrollToElement("continueToBuildingButton");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement("deleteLocationButton"));
		clickUsingJS("deleteLocationButton");
		click("deletebutton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("locationsAndBuildingsHeading")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("quoteButton"));
		action.moveToElement(getWebElement("quoteButton")).click().build().perform();
		sleep(3000);
		if (isWebElementPresent("calculatingQuoteLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("calculatingQuoteLoader")));
		}
		return new LocationsAndBuildingsPage_AP();
	}
}
