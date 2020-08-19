package com.wrg.PC.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class BuildingsAndClassificationsPage_PC extends AbstractTest {
	WebDriverWait wait = null;
	java.util.Date date = new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	String timestamp = sdf.format(date);

	public void goToBlanketsPage() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("buildingsAndClassificationScreenValidation")));
		WebElement nextButton = getWebElement("nextButton");
		try {
			clickUsingJS(nextButton);
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

	public void addBuilding(String classCodeNumber, String state,String numberOfBuildings,String insuredName) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		for (int i = 1; i <= Integer.parseInt(numberOfBuildings); i++) {
			
			if (Integer.parseInt(numberOfBuildings) > 1) {
				click(getWebElement("addBuildingButtonForMultipleBuildings"));
				driver.findElement(By.xpath("(//div[contains(@id,'BuildingsEdit_DP_tb')]/a)[" + i + "]")).click();
			} else {
				click(getWebElement("addBuildingButtonForSingleBuilding"));
			}
			sleep(2000);
			clickUsingJS("detailsTab");
			fillBuildingInformation(classCodeNumber);
			addAdditionalInsured(state,numberOfBuildings,i,insuredName);
			addAdditionalInterest(state,numberOfBuildings,i);
		}
	}

	public void fillBuildingInformation(String classCodeNumber) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("constructionTypeDropdown")));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("constructionTypeDropdown")));
		sleep(2000);
		click("constructionTypeDropdown");
		click(driver.findElement(By.xpath("//li[contains(text(),'" + getData("constructionTypeValue") + "')]")));
		type(("yearBuiltTextBox"), getData("yearValue"));
		sleep(4000);
		clickUsingJS("buildingCoverageCheckbox");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("percentageOwnerOccupiedDropdown")));
		WebElement percentageOwnerOccupiedDropdown = getWebElement("percentageOwnerOccupiedDropdown");
		click(percentageOwnerOccupiedDropdown);
		click(driver.findElement(By.xpath("//li[contains(text(),'" + getData("percentageOwnerOccupiedValue") + "')]")));
		sleep(2000);
		type(("limitTextBox"), getData("limitValue"));
		addClassificationData(classCodeNumber);

	}
	
	public String enterInsuredName(String insuredName) {
		wait = new WebDriverWait(driver, 10);
		clear("name");
		if (insuredName.isEmpty()) {
			insuredName = "Additional_Insured_" + timestamp;
		} else {
			insuredName = insuredName + "_" + timestamp;
		}
		sleep(2000);
		type("name", insuredName);
		getWebElement("name").sendKeys(Keys.TAB);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Additional Insured Name is: " + insuredName, ExtentColor.BLUE));
		return insuredName;
	}
	
	public void addNewLocation(String state, String numberOfLocations, int i) {
		wait = new WebDriverWait(driver, 10);
		//for (int i = 1; i <= Integer.parseInt(numberOfLocations); i++) {
			clickUsingJS("addAdditionalInterestButton");
			clickUsingJS("newCompanyLink");
			enterInsuredName("test");
			sleep(1000);
			type("address1", getData(state.toLowerCase() + "Address" + i));
			getWebElement("address1").sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("city")));
			type("city", getData(state.toLowerCase() + "CityName" + i));
			getWebElement("city").sendKeys(Keys.TAB);
			sleep(2000);
			type("zipCode", getData(state.toLowerCase() + "PincodeValue" + i));
			getWebElement("zipCode").sendKeys(Keys.TAB);
			sleep(1000);
			//wait.until(ExpectedConditions.elementToBeClickable(getWebElement("county")));
			click("state");
			clickUsingJS(driver.findElement(By.xpath(
					"//li[contains(text(),'" + state + "')]")));
			//wait.until(ExpectedConditions.elementToBeClickable(getWebElement("updateButton")));
			try {
				clickUsingJS("okButton");
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
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("okButtonMortgageHolder")));
			try {
				clickUsingJS("okButtonMortgageHolder");
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
		//}
	}
	
	public void addNewBuildingOwner(String state, String numberOfLocations, int i,String insuredName) {
		wait = new WebDriverWait(driver, 10);
			//wait.until(ExpectedConditions.visibilityOf(getWebElement("addBuildingOwnerButton")));
			//clickUsingJS("addBuildingOwnerButton");
			wait.until(ExpectedConditions.visibilityOf(getWebElement("addContactButton")));
			clickUsingJS("addContactButton");
			clickUsingJS("newCompanyLink_AdditionlInsured");
			clear("name_AdditionlInsured");
			if (insuredName.isEmpty()) {
				insuredName = "Additional_Insured_" + timestamp;
			} else {
				insuredName = insuredName + "_" + timestamp;
			}
			sleep(2000);
			type("name_AdditionlInsured", insuredName);
			getWebElement("name_AdditionlInsured").sendKeys(Keys.TAB);
			ExtentTestManager.getTest().log(Status.INFO,
					MarkupHelper.createLabel("Additional Insured Name is: " + insuredName, ExtentColor.BLUE));
			sleep(1000);
			type("address1_AdditionlInsured", getData(state.toLowerCase() + "Address" + i));
			getWebElement("address1_AdditionlInsured").sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("city_AdditionlInsured")));
			type("city_AdditionlInsured", getData(state.toLowerCase() + "CityName" + i));
			getWebElement("city_AdditionlInsured").sendKeys(Keys.TAB);
			sleep(2000);
			type("zipCode_AdditionlInsured", getData(state.toLowerCase() + "PincodeValue" + i));
			getWebElement("zipCode_AdditionlInsured").sendKeys(Keys.TAB);
			sleep(1000);
			click("state_AdditionlInsured");
			clickUsingJS(driver.findElement(By.xpath(
					"//li[contains(text(),'" + state + "')]")));
			try {
				clickUsingJS("okButton_AdditionalInsured");
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
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement("okButton_BuildingOwner")));
			try {
				clickUsingJS("okButton_BuildingOwner");
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
	
	public void addAdditionalInterest(String state,String numberOfLocations,int i) {
		click("mortgageHoldersTab");
		click("mortgageHoldersCheckbox");
		click("addMortgageHoldersButton");
		addNewLocation(state,numberOfLocations,i);
	}
	
	public void addAdditionalInsured(String state,String numberOfLocations,int i,String insuredName) {
		wait = new WebDriverWait(driver, 10);
		click("additionalInsuredsTab");
		click("addCoveragesButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("firstCoverageCheckbox")));
		click("firstCoverageCheckbox");
		click("addSelectedCoverageButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("addBuildingOwnerButton")));
		click("addBuildingOwnerButton");
		addNewBuildingOwner(state,numberOfLocations,i,insuredName);
	}

	public void addClassificationData(String classCodeNumber) {
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if (classCodeNumber.contains(",")) {
			String spiltClassCodes[] = classCodeNumber.split(",");
			List<String> classCodes = new ArrayList<String>();
			for (String classCode : spiltClassCodes) {
				classCodes.add(classCode);
				clickUsingJS("addClassificationButton");
				sleep(5000);
				scrollToElement("classificationSquareFootageTextBox");
				type(("classificationSquareFootageTextBox"), getData("classificationSquareFootageValue"));
				getWebElement("classificationSquareFootageTextBox").sendKeys(Keys.TAB);
				sleep(2000);
				click("classificationDropdown");
				clickUsingJS(driver.findElement(By.xpath("//li[contains(text(),'" + classCode + "')]")));
			}
		} else {
			clickUsingJS("addClassificationButton");
			scrollToElement("classificationSquareFootageTextBox");
			sleep(2000);
			type(("classificationSquareFootageTextBox"), getData("classificationSquareFootageValue"));
			getWebElement("classificationSquareFootageTextBox").sendKeys(Keys.TAB);
			sleep(2000);
		}
	}

}
