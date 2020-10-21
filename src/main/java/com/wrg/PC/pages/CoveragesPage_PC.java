package com.wrg.PC.pages;

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
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class CoveragesPage_PC extends AbstractTest{
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

	public void addBuilding() {
		wait = new WebDriverWait(driver, 5);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("coveragesHeader")));
		clickUsingJS("addBuildingButton");
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("newBuildingLabel")));
	}
	public void addBuildingDetails(String coverage, String causeofloss, String limit, String yearBuilt, String construction, String classcode, String classcodedescription, String valuation) {
		//addBuildingClassCode("0844");
		addBuildingClassCode(classcode);
		//addBuildingClassCodeDescription("Amusement Centers");
		/*if (! classcode.contains("1150")) {
			addBuildingClassCodeDescription(classcodedescription);
		}*/
		addBuildingClassCodeDescription(classcodedescription);
		addBuildingConstruction(construction);
		addBuildingNoOfStories("1-3");
		
		addBuildingYearBuilt1(yearBuilt);
		addBuildingCoverage(coverage);
		
		if (! coverage.contains("Leasehold Interest")) {
			addBuildingLimit(limit);
		}else {
			actionClick("clearButton");
			sleep(2000);
		}
		
		
		
		addBuildingCauseOfLoss(causeofloss);
		//addBuildingYearBuilt(yearBuilt);
		if (coverage.contains("Building")) {
			addBuildingValuation(valuation);
		}
	}
	public void addBuildingValuation(String valuation) {
		sleep(2000);
		guidewireDropDownSelection("valuation", valuation);
		getWebElement("valuation").sendKeys(Keys.TAB);
	};
	public void addBuildingClassCodeDescription(String classCodeDescription) {
		sleep(2000);
		if (isWebElementPresentAfterWait("classCodeDescription")) {
			guidewireDropDownSelection("classCodeDescription", classCodeDescription);
		}
		
	}
	
	public void addBuildingClassCode(String classCode) {
		sleep(2000);
		clear("classCode");
		sleep(1000);
		type("classCode", classCode);
		getWebElement("classCode").sendKeys(Keys.TAB);
		sleep(5000);
	}
	public void addBuildingNoOfStories(String numberOfStories) {
		sleep(1000);
		guidewireDropDownSelection("numberOfStories", numberOfStories);
	}
	public void addBuildingConstruction(String construction) {
		sleep(1000);
		
		guidewireDropDownSelection("construction", construction);
	}
	public void addBuildingLimit(String limit) {
		if (isWebElementPresentAfterWait("clearButton")){
			actionClick("clearButton");
			sleep(2000);
		}
		clear("limit");
		sleep(2000);
		
		type("limit", limit);
		getWebElement("limit").sendKeys(Keys.TAB);
		sleep(2000);
	}
	public void addBuildingYearBuilt(String yearBuilt) {
		//type("yearBuilt", yearBuilt);
		clear("yearBuilt");
		sleep(2000);
		
		//typeUsingJS("yearBuilt", yearBuilt);
		type("yearBuilt", yearBuilt);
	}
	public void addBuildingYearBuilt1(String yearBuilt) {
		//type("yearBuilt", yearBuilt);
		clear("yearBuilt");
		sleep(2000);
		
		//typeUsingJS("yearBuilt", yearBuilt);
		type("yearBuilt", yearBuilt);
		getWebElement("yearBuilt").sendKeys(Keys.TAB);
	}
	public void addBuildingCoverage(String coverages) {
		guidewireDropDownSelection("coverages", coverages);
	}
	public void addBuildingCauseOfLoss(String lossvalue) {
		if (isWebElementPresentAfterWait("clearButton")){
			actionClick("clearButton");
			sleep(2000);
		}
		guidewireDropDownSelection("causeOfLoss", lossvalue);
		getWebElement("causeOfLoss").sendKeys(Keys.TAB);
	}
	public void goToAdditionalCoveragesTab() {
		waitForPageLoaded();
		if (isWebElementPresent("additionalCoveragesTab") == true) {
			clickUsingJS("additionalCoveragesTab");
		}
	}
	public void goToDetailsCoveragesTab() {
		waitForPageLoaded();
		if (isWebElementPresent("detailsCoveragesTab") == true) {
			clickUsingJS("detailsCoveragesTab");
		}
	}
	
	public void verifyAdditionalCoverages(String coverageNumber, int count) {
		sleep(5000);
		wait = new WebDriverWait(driver, 50);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("addCoveragesButton")));
		clickUsingJS("addCoveragesButton");
		waitForPageLoaded();
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("keywords")));
	
		type("keywords", coverageNumber);
		sleep(1000);
		clickUsingJS("searchButton");
		sleep(1000);
		
		
		int additionalCoveragesCount =  driver.findElements(By.xpath("//div[text()='"+coverageNumber+"']")).size();
		if (additionalCoveragesCount==count) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							coverageNumber+" is verified successfully" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							coverageNumber +" is not verified successfully" ,
							ExtentColor.RED));
		}
		clickUsingJS("returnToNewBuilding");
	}
	public void addAdditionalCoverages(String coverageNumber) {
		sleep(5000);
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("addCoveragesButton")));
		clickUsingJS("addCoveragesButton");
		
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("keywords")));
		type("keywords", coverageNumber);
		sleep(1000);
		clickUsingJS("searchButton");
		sleep(1000);
		actionClick("coveragesSelection");
		sleep(1000);
		clickUsingJS("addSelectedCoveragesButton");
		sleep(2000);
		if (coverageNumber.contains("CP 14 15")) {
			if (isWebElementPresentAfterWait("cp1415Coverageslabel")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"CP 14 15 Coverages is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CP 14 15 Coverages is not present" ,
								ExtentColor.RED));
			}
		}
		
	}
	public boolean validateCP1415Coverages() {
		sleep(2000);
		//boolean val1 = 
		if (isWebElementPresentAfterWait("removeButtonLink")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Remove Button is disabled" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Remove Button is not disabled" ,
							ExtentColor.RED));
		}
		
		clickUsingJS("addButton");
		sleep(2000);
		boolean result = false;
		if (isWebElementPresent("scheduleNoLabel") == true
				&& isWebElementPresent("descriptionLabel") == true
				&& isWebElementPresent("listOfScheduledItemsLabel") == true
				&& isWebElementPresent("oneLabel") == true) {
			result = true;
		}		
		return result;
	}
	public void validateCP1070Coverages() {
		sleep(2000);
		if (isWebElementPresentAfterWait("CP1070Label")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"CP1070Label Field is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"CP1070Label Field is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("CP1070Construction")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"CP1070Construction Field is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"CP1070Construction Field is not present" ,
							ExtentColor.RED));
		}
	}
	public void validateGreenUpgradesCoverages() {
		sleep(2000);
		if (isWebElementPresentAfterWait("increasedCostOflossPercentage")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"increasedCostOflossPercentage Field is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"increasedCostOflossPercentage Field is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("greenUpgradeslimit")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"greenUpgradeslimit Field is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"greenUpgradeslimit Field is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("relatedExpensesLimit")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"relatedExpensesLimit Field is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"relatedExpensesLimit Field is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("descrption")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"descrption Field is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"descrption Field is not present" ,
							ExtentColor.RED));
		}
		
		
	}
	public void validateCP1113Coverages(String state) {
		sleep(3000);
		if (isWebElementPresentAfterWait("CP1113Limit")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Limit Field is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"limit Field is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("CP1113ScheduleNo")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Schedule No is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Schedule No is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("CP1113LossPayee")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Loss Payee is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Loss Payee is not present" ,
							ExtentColor.RED));
		}
		actionClick("addEditButton");
		
	}
	public void validateCP1121Coverages() {
		sleep(3000);
		if(isWebElementPresentAfterWait("CP1121TheftLimit")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"CP1121TheftLimit is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"CP1121TheftLimit is not present" ,
							ExtentColor.RED));
		}
		if(isWebElementPresentAfterWait("CP1121TheftDeductible")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"CP1121TheftDeductible is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"CP1121TheftDeductible is not present" ,
							ExtentColor.RED));
		}
	}
	public void validateCP1115Coverages() {
		sleep(3000);
		if (isWebElementPresentAfterWait("CP1115ScheduleNoLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"CP1115ScheduleNoLabel is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"CP1115ScheduleNoLabel is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("CP1115ContractorLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"CP1115ContractorLabel is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"CP1115ContractorLabel is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("CP1115InstallationLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"CP1115InstallationLabel is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"CP1115InstallationLabel is not present" ,
							ExtentColor.RED));
		}
	}
	
	public void additionalPropertyNotCoveredCoverages() {
		sleep(3000);
		if (isWebElementPresentAfterWait("additionalPropertyYesField1")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Awnings or Canopies of Fabric or Slat Construction Including Their Supports is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Awnings or Canopies of Fabric or Slat Construction Including Their Supports is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField2")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Brick, Metal, Stone or Concrete Chimneys or Stacks Not Forming Part of a Building or Metal Smokestacks is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Brick, Metal, Stone or Concrete Chimneys or Stacks Not Forming Part of a Building or Metal Smokestacks is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField3")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Crop Silos is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Crop Silos is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField4")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Swimming Pools, Diving Towers or Platforms is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Swimming Pools, Diving Towers or Platforms is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField5")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Waterwheels, Windmills, Wind Pumps or Their Towers is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Waterwheels, Windmills, Wind Pumps or Their Towers is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField6")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"The Value of Improvements, Alterations or Repairs Including Real Property Being Demolished is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"The Value of Improvements, Alterations or Repairs Including Real Property Being Demolished is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField7")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Performed by is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Performed by is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField8")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Personal Property Contained in Safes or Vaults is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Personal Property Contained in Safes or Vaults is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField9")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Contents of Crop Silos is not present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Contents of Crop Silos is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField9")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Glass Which Is Not Part of a Building or Structure is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Glass Which Is Not Part of a Building or Structure is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField11")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Metals in Ingots, Pigs, Billets, or Scraps is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Metals in Ingots, Pigs, Billets, or Scraps is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField12")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Ores, Gravels, Clay, or Sand is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Ores, Gravels, Clay, or Sand is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField13")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Property of Others is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Property of Others is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField14")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Property Stored in Open Yards is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Property Stored in Open Yards is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField15")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Signs Inside the Premises is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Signs Inside the Premises is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField16")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Vending Machines or Their Contents is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Vending Machines or Their Contents is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField17")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Tenants Improvements and Betterments is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Tenants Improvements and Betterments is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField18")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"\"Stock\" is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"\"Stock\" is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField19")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Description of Excluded Stock is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Description of Excluded Stock is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField20")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Buildings or Their Contents in Which Any Cooking Is Done, Including Restaurants and Lunch Rooms is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Buildings or Their Contents in Which Any Cooking Is Done, Including Restaurants and Lunch Rooms is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField21")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Buildings or Their Contents Used to Repair Tires or Electric Batteries is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Buildings or Their Contents Used to Repair Tires or Electric Batteries is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField22")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Buildings or Their Contents Used to Store or Repair Motor Vehicles Not Owned is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Buildings or Their Contents Used to Store or Repair Motor Vehicles Not Owned is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField23")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Buildings Used Principally for Storage, Sales or Repairing of Appliances or Heating Devices or Their Parts is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Buildings Used Principally for Storage, Sales or Repairing of Appliances or Heating Devices or Their Parts is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField24")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Petroleum Products and Other Merchandise, Including the Contents of Above or Below Ground Tanks is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Petroleum Products and Other Merchandise, Including the Contents of Above or Below Ground Tanks is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("additionalPropertyYesField25")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Description of Additional Property Not Covered is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Description of Additional Property Not Covered is not present" ,
							ExtentColor.RED));
		}
	}
	
	public void addNewPerson(String state) {
		actionClick("addPersonButton");
		actionClick("newPersonLink");
		if (state.equalsIgnoreCase("Indiana")) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			type(("firstName"), "testfirstname");
			type(("lastName"), "testlastname");
			type(("address1"), getData("indianaAddress"));
			sleep(2000);
			clickUsingJS("state");
			clickUsingJS(driver.findElement(
					By.xpath("//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'" + state + "')]")));
			sleep(1000);
			type(("city"), getData("indianaCityName"));
			setObjectWaitTimeout(3);
			getWebElement("city").sendKeys(Keys.TAB);
			sleep(1000);
			type(("zipCode"), getData("indianaPincodeValue"));
			getWebElement("zipCode").sendKeys(Keys.TAB);
			sleep(1000);
		} else if (state.equalsIgnoreCase("Ohio")) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			type(("firstName"), "testfirstname");
			type(("lastName"), "testlastname");
			type(("address1"), getData("ohioAddress"));
			sleep(2000);
			clickUsingJS("state");
			clickUsingJS(driver.findElement(By.xpath(
					"//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'" + getData("ohioState") + "')]")));
			sleep(1000);
			type(("city"), getData("ohioCityName"));
			sleep(1000);
			getWebElement("city").sendKeys(Keys.TAB);
			sleep(1000);
			type(("description"), getData("descriptionValue"));
			type(("zipCode"), getData("ohioPincodeValue"));
			getWebElement("zipCode").sendKeys(Keys.TAB);
			sleep(1000);
		}
	}
	
	public void clickAddBuilding() {
		clickUsingJS("addBuildingButton");
	}
	public void addNewCompany(String state) {
		actionClick("addPersonButton");
		actionClick("newCompanylink");
		if (state.equalsIgnoreCase("Indiana")) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			type(("companyName"), "testcompanyname");
			sleep(1000);
			getWebElement("companyName").sendKeys(Keys.TAB);
			sleep(1000);
			type(("address1"), getData("indianaAddress"));
			sleep(2000);
			clickUsingJS("state");
			clickUsingJS(driver.findElement(
					By.xpath("//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'" + state + "')]")));
			sleep(1000);
			type(("city"), getData("indianaCityName"));
			setObjectWaitTimeout(3);
			getWebElement("city").sendKeys(Keys.TAB);
			sleep(1000);
			type(("zipCode"), getData("indianaPincodeValue"));
			getWebElement("zipCode").sendKeys(Keys.TAB);
			sleep(1000);
		} else if (state.equalsIgnoreCase("Ohio")) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			type(("companyName"), "testcompanyname");
			sleep(1000);
			getWebElement("companyName").sendKeys(Keys.TAB);
			type(("address1"), getData("ohioAddress"));
			sleep(2000);
			clickUsingJS("state");
			clickUsingJS(driver.findElement(By.xpath(
					"//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'" + getData("ohioState") + "')]")));
			sleep(1000);
			type(("city"), getData("ohioCityName"));
			sleep(1000);
			getWebElement("city").sendKeys(Keys.TAB);
			sleep(1000);
			type(("description"), getData("descriptionValue"));
			type(("zipCode"), getData("ohioPincodeValue"));
			getWebElement("zipCode").sendKeys(Keys.TAB);
			sleep(1000);

		}
		clickUsingJS("okButton");
		sleep(2000);
		clickUsingJS("updateButton");
		sleep(2000);
	}
	public void  validateCP1120Coverages() {
		sleep(3000);
	
		if(isWebElementPresentAfterWait("optionCP1120Feild")){
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Option Field is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Option Field is not present" ,
							ExtentColor.RED));
		}
		String optiondefaultvalue = getWebElement("optionCP1120Feild").getAttribute("value");
		if (optiondefaultvalue.contentEquals("Architect, Engineer or Building Trades Contractor is Named as the Insured or as an Additional Insured")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Option Field default value is validated successfully" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Option Field default value is not validated successfully" ,
							ExtentColor.RED));
		}
	}
	public void CP1415Coverages(String data) {
		actionClick("cp1415Description");
		sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(data).build().perform();
		action.sendKeys(Keys.TAB).build().perform();
		sleep(2000);
	}
	public void CP1115Coverages(String contractor, String installation) {
		sleep(2000);
		clear("CP1115Contractor");
		getWebElement("CP1115Contractor").sendKeys(Keys.TAB);
		sleep(1000);
		clear("CP1115Installation");
		getWebElement("CP1115Installation").sendKeys(Keys.TAB);
		sleep(1000);
		type("CP1115Contractor", contractor);
		getWebElement("CP1115Contractor").sendKeys(Keys.TAB);
		sleep(1000);
		type("CP1115Installation", installation);
		getWebElement("CP1115Installation").sendKeys(Keys.TAB);
		sleep(1000);
		clickUsingJS("okButton");
		sleep(2000);
		
		
	}
	public void removeItems(String coverages) {
		int count = 0;
		
		sleep(2000);
		count = getWebElements("elementCount").size();
		String weblementtext="//div[contains(text(),'"+coverages+"')]//following::img[1]";
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(weblementtext))).click().build().perform();
		sleep(3000);
		if (isWebElementPresentAfterWait("removeButtonLink")) {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Remove Button is disabled" ,
							ExtentColor.RED));
		}
		clickUsingJS("removeButton");
		sleep(3000);
		if (count>getWebElements("elementCount").size()) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Element is removed successfully" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Element is not removed successfully" ,
							ExtentColor.RED));
		}
		
	}
	public void validateCoveragesErrorMessage(String errorText) {
		sleep(1000);
		if (errorText.contains("Please fill in all required fields.")) {
			clickUsingJS("addButton");
			sleep(1000);
			clickUsingJS("okButton");
			sleep(3000);
			if (isWebElementPresentAfterWait("errorMessage1")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Please fill in all required fields. error is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Please fill in all required fields. error is not present" ,
								ExtentColor.RED));
			}
		}
		else if (errorText.contains("You must enter at least 1 row of Additional Building Property")) {
			clickUsingJS("okButton");
			sleep(3000);
			if (isWebElementPresentAfterWait("errorMessage")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"You must enter at least 1 row of Additional Building Property error is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"You must enter at least 1 row of Additional Building Property error is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Builders Risk Renovations : Cannot have same Loss Payee more than once")) {
			clickUsingJS("okButton");
			sleep(3000);
			if (isWebElementPresentAfterWait("errorMessage2")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Builders Risk Renovations : Cannot have same Loss Payee more than once error is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Builders Risk Renovations : Cannot have same Loss Payee more than once error is not present" ,
								ExtentColor.RED));
			}
		}	
	}
	public void validatesCP1115ErrorMessages(String errorText) {

		if (errorText.contains("You must enter at least 1 row of Builders Risk - Separate Or Sub-Contractors Coverage")) {
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("CP1115Error1")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"You must enter at least 1 row of Builders Risk - Separate Or Sub-Contractors Coverage is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"You must enter at least 1 row of Builders Risk - Separate Or Sub-Contractors Coverage is not present" ,
								ExtentColor.RED));
			}
		}
		else if (errorText.contains("Contractor : Missing required field \"Contractor\"")){
			clickUsingJS("editButton");
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("CP1115Error2")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Contractor : Missing required field \"Contractor\" is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Contractor : Missing required field \"Contractor\" is not present" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("CP1115Error3")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Installation : Missing required field \"Installation\" is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Installation : Missing required field \"Installation\" is not present" ,
								ExtentColor.RED));
			}
		}else if(errorText.contains("Contractor : Exceeds the maximum length of 255 (344)")) {
			type("CP1115Contractor", getData("largeString2"));
			getWebElement("CP1115Contractor").sendKeys(Keys.TAB);
			sleep(1000);
			type("CP1115Installation", getData("largeString2"));
			getWebElement("CP1115Installation").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("CP1115Error4")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Installation : Missing required field \"Installation\" is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Installation : Missing required field \"Installation\" is not present" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("CP1115Error5")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Installation : Missing required field \"Installation\" is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Installation : Missing required field \"Installation\" is not present" ,
								ExtentColor.RED));
			}
		}
	}
	public void validatesCP1121CovergaesErrorMessages(String errorText) {
		if (errorText.contains("Theft Limit : Missing required field \"Theft Limit\"")) {
			clickUsingJS("okButton");
			sleep(3000);
			if (isWebElementPresentAfterWait("CP1121Error1")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Theft Limit : Missing required field \"Theft Limit\" is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Theft Limit : Missing required field \"Theft Limit\" is not present" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("CP1121Error2")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Theft Deductible : Missing required field \"Theft Deductible\" is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Theft Deductible : Missing required field \"Theft Deductible\" is not present" ,
								ExtentColor.RED));
			}
		}else if(errorText.contains("Theft Limit : Must not be less than $1.00")) {
			//String data = "0";
			type("CP1121TheftLimit", getData("zeroValue"));
			getWebElement("CP1121TheftLimit").sendKeys(Keys.TAB);
			sleep(1000);
			type("CP1121TheftDeductible", getData("zeroValue"));
			getWebElement("CP1121TheftDeductible").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(3000);
			if (isWebElementPresentAfterWait("CP1121Error3")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Theft Limit : Must not be less than $1.00 is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Theft Limit : Must not be less than $1.00 is not present" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("CP1121Error4")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Theft Deductible : Must not be less than $1,000.00 is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Theft Deductible : Must not be less than $1,000.00 is not present" ,
								ExtentColor.RED));
			}
		}else if(errorText.contains("Theft Limit : Must be a numeric value")) {
			//String data = "abc";
			clear("CP1121TheftLimit");
			sleep(1000);
			clear("CP1121TheftDeductible");
			sleep(1000);
			type("CP1121TheftLimit", getData("alphaNumericValue"));
			getWebElement("CP1121TheftLimit").sendKeys(Keys.TAB);
			sleep(1000);
			type("CP1121TheftDeductible", getData("alphaNumericValue"));
			getWebElement("CP1121TheftDeductible").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(3000);
			if (isWebElementPresentAfterWait("CP1121Error5")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Theft Limit : Must be a numeric value is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Theft Limit : Must be a numeric value is not present" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("CP1121Error6")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Theft Deductible : Must be a numeric value is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Theft Deductible : Must be a numeric value is not present" ,
								ExtentColor.RED));
			}
		}else if(errorText.contains("Theft Limit : Must be no greater than $999,999,999.00")) {
			//String data = "999999999999";
			clear("CP1121TheftLimit");
			sleep(1000);
			clear("CP1121TheftDeductible");
			sleep(1000);
			type("CP1121TheftLimit", getData("numberData"));
			getWebElement("CP1121TheftLimit").sendKeys(Keys.TAB);
			sleep(1000);
			type("CP1121TheftDeductible", getData("numberData"));
			getWebElement("CP1121TheftDeductible").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(3000);
			if (isWebElementPresentAfterWait("CP1121Error7")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Theft Limit : Must be no greater than $999,999,999.00 is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Theft Limit : Must be no greater than $999,999,999.00 is not present" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("CP1121Error8")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Theft Deductible : Must be no greater than $99,999.00 is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Theft Deductible : Must be no greater than $99,999.00 is not present" ,
								ExtentColor.RED));
			}
		}
	}
	public void validatesGreenCoveragesErrorMessages(String errorText) {
		
		if (errorText.contains("Increased Cost Of Loss Percentage : Missing required field \"Increased Cost Of Loss Percentage\"")) {
			clickUsingJS("okButton");
			sleep(3000);
			if (isWebElementPresentAfterWait("GreenUpgradeError1")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Increased Cost Of Loss Percentage : Missing required field \"Increased Cost Of Loss Percentage\" is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Increased Cost Of Loss Percentage : Missing required field \"Increased Cost Of Loss Percentage\" is not present" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("GreenUpgradeError2")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Green Upgrades Limit : Missing required field \"Green Upgrades Limit\" is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Green Upgrades Limit : Missing required field \"Green Upgrades Limit\" is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Green Upgrades Limit : Must not be less than $1.00")) {
			clear("greenUpgradeslimit");
			sleep(1000);
			type("greenUpgradeslimit", getData("zeroValue"));
			getWebElement("greenUpgradeslimit").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("GreenUpgradeError3")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Green Upgrades Limit : Must not be less than $1.00 is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Green Upgrades Limit : Must not be less than $1.00 is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Green Upgrades Limit : Must be a numeric value")) {
			clear("greenUpgradeslimit");
			sleep(1000);
			type("greenUpgradeslimit", getData("alphaNumericValue"));
			getWebElement("greenUpgradeslimit").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("GreenUpgradeError4")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Green Upgrades Limit : Must be a numeric value is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Green Upgrades Limit : Must be a numeric value is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Green Upgrades Limit : Must be no greater than $999,999,999.00")) {
			clear("greenUpgradeslimit");
			sleep(1000);
			type("greenUpgradeslimit", getData("numberData"));
			getWebElement("greenUpgradeslimit").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("GreenUpgradeError5")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Green Upgrades Limit : Must be no greater than $999,999,999.00 is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Green Upgrades Limit : Must be no greater than $999,999,999.00 is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Description : Exceeds the maximum length of 255 (363)")) {			
			clear("descrption");
			sleep(1000);
			type("descrption", getData("largeString2"));
			getWebElement("descrption").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(1000);
			if (isWebElementPresentAfterWait("GreenUpgradeError6")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Description : Exceeds the maximum length of 255 (363) is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Description : Exceeds the maximum length of 255 (363) is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Related Expenses Limit : Must not be less than $0.00")) {
			clear("relatedExpensesLimit");
			sleep(1000);
			type("relatedExpensesLimit", getData("negativeValue"));
			getWebElement("relatedExpensesLimit").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("GreenUpgradeError7")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Related Expenses Limit : Must not be less than $0.00 is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Related Expenses Limit : Must not be less than $0.00 is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Related Expenses Limit : Must be no greater than $999,999,999.00")) {
			clear("relatedExpensesLimit");
			sleep(1000);
			type("relatedExpensesLimit", getData("numberData"));
			getWebElement("relatedExpensesLimit").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("GreenUpgradeError8")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Related Expenses Limit : Must be no greater than $999,999,999.00 is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Related Expenses Limit : Must be no greater than $999,999,999.00 is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Performed by : Exceeds the maximum length of 255 (263)")) {
			clear("additionalPropertyYesField7");
			sleep(1000);
			type("additionalPropertyYesField7", getData("largeString"));
			getWebElement("additionalPropertyYesField7").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("additionalPropertyError1")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Performed by : Exceeds the maximum length of 255 (263) is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Performed by : Exceeds the maximum length of 255 (263) is not present" ,
								ExtentColor.RED));
			}
		}else if (errorText.contains("Description of Excluded Stock : Exceeds the maximum length of 255 (263)")) {
			
			clear("additionalPropertyYesField19");
			sleep(1000);
			type("additionalPropertyYesField19", getData("largeString"));
			getWebElement("additionalPropertyYesField19").sendKeys(Keys.TAB);
			sleep(1000);
			clickUsingJS("okButton");
			sleep(2000);
			if (isWebElementPresentAfterWait("additionalPropertyError2")) {
				ExtentTestManager.getTest().log(Status.PASS,
						MarkupHelper.createLabel(
								"Description of Excluded Stock : Exceeds the maximum length of 255 (263) is present" ,
								ExtentColor.GREEN));
			}else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Description of Excluded Stock : Exceeds the maximum length of 255 (263) is not present" ,
								ExtentColor.RED));
			}
		}	
	}
	public void validatesCP1070CoveragesErrorMessages(String errorText) {
		sleep(1000);
		clickUsingJS("okButton");
		sleep(2000);
		if (isWebElementPresentAfterWait("CP1070ErrorMessage")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Construction : Missing required field \"Construction\" is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Construction : Missing required field \"Construction\" is not present" ,
							ExtentColor.RED));
		}
	}
	public void validatesCoverageMenu(String menuName, String menuLocator) {
		clickUsingJS("CP1070Construction");
		sleep(1000);
		if (isWebElementPresentAfterWait(menuLocator)) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							menuName+" is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							menuName+" is not present" ,
							ExtentColor.RED));
		}
		clickUsingJS("CP1070Construction");
		sleep(1000);
	}
	public void guidewireDropDownSelection(String WebElement, String data) {
		sleep(2000);
		//clickUsingJS(WebElement);
		actionClick(WebElement);
		sleep(2000);
		String weblementtext="//li[contains(text(),'"+data+"')]";
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(weblementtext))).click().build().perform();
		sleep(3000);
		//type(WebElement, data);
		getWebElement(WebElement).sendKeys(Keys.TAB);
		sleep(2000);
	}
}
