package com.wrg.PC.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class NewBuildingPage_PC extends AbstractTest {
	WebDriverWait wait = null;
	Wait fluentWait = null;

	public void clickClassCodeSearchButton() {
		clickUsingJS("classCodeDropdown");
		driver.findElement(By.xpath("//li[contains(text(),'"+getData("classCodeDropDownValue")+"')]")).click();
	}

	public void addBuildingDetails() {
		wait = new WebDriverWait(driver, 30);
		waitForPageLoaded();
		clickUsingJS("constructionDropdown");
		clickUsingJS(driver
				.findElement(By.xpath("//li[contains(text(),'" + getData("constructionTypeDropdownValue") + "')]")));
		sleep(3000);
		clickUsingJS("numberOfStoriesDropdown");
		clickUsingJS(driver
				.findElement(By.xpath("//li[contains(text(),'" + getData("numberOfStoriesDropdownValue") + "')]")));
		sleep(3000);
		type("yearBuildTextbox", getData("yearBuildValue"));
		getWebElement("yearBuildTextbox").sendKeys(Keys.TAB);
		sleep(3000);
		clickUsingJS("buidingCodeEffectiveGradeClassDropdown");
		click(driver.findElement(
				By.xpath("//li[contains(text(),'" + getData("buidingCodeEffectiveGradeClassDropdownValue") + "')]")));
		sleep(3000);
		clickUsingJS("typeOfBurglarAlarmSystemDropdown");
		click(driver.findElement(
				By.xpath("//li[contains(text(),'" + getData("typeOfBurglarAlarmSystemDropdownValue") + "')]")));
		sleep(3000);
		type("creditFactor", getData("creditFactorValue"));
		getWebElement("creditFactor").sendKeys(Keys.TAB);
		sleep(3000);
		clickUsingJS("typeOfWatchmanServiceDropdown");
		click(driver.findElement(
				By.xpath("//li[contains(text(),'" + getData("typeOfWatchmanServiceDropdownValue") + "')]")));
		sleep(3000);
		clickUsingJS("sprinklerSystemDropdown");
		click(driver.findElement(By.xpath("//li[contains(text(),'" + getData("sprinklerSystemDropdownValue") + "')]")));
		sleep(3000);
		type("limitTextBox", getData("limitValue"));
		getWebElement("limitTextBox").sendKeys(Keys.TAB);
		sleep(3000);
		clickUsingJS("causeOfLossDropdown");
		click(driver.findElement(By.xpath("//li[contains(text(),'" + getData("causeOfLossDropdownValue") + "')]")));
		sleep(3000);
		clickUsingJS("valuationDropdown");
		click(driver.findElement(By.xpath("//li[contains(text(),'" + getData("valuationDropdownValue") + "')]")));
		getWebElement("valuationDropdown").sendKeys(Keys.TAB);
		sleep(3000);
	}

	public void clickAddCoveragesButton() {
		click("addCoveragesButton");
	}

	public boolean validateCoverageIsPresent(String coverageName) {
		boolean result = false;
		List<WebElement> coverage = driver.findElements(By.xpath("//div[contains(text(),'" + coverageName + "')]"));
		if(coverage.size()>0) {
		if (coverage.get(0).getText().equalsIgnoreCase(coverageName)) {
			ExtentTestManager.getTest().log(Status.INFO, coverageName + " is Present");
			result = true;
		}
		}
		else {
			ExtentTestManager.getTest().log(Status.INFO, coverageName + " is not Present");
			result = false;
		}
		return result;
	}

	public void selectCoverage(String coverageName) {
		driver.findElement(By.xpath("//div[contains(text(),'" + coverageName + "')]/preceding::td[2]/div")).click();
		click("addSelectedCovergeButton");
	}

	public void validateLimitFieldInDebrisRemovalAdditionalInsuranceCoverage() {
		wait = new WebDriverWait(driver, 5);
		isWebElementPresent("limitLabel");
		isWebElementPresent("limitTextBox_Debris");
		type("limitTextBox_Debris", "a");
		getWebElement("limitTextBox_Debris").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("limitTextBox_Debris");
		sleep(1000);
		type("limitTextBox_Debris", "0");
		getWebElement("limitTextBox_Debris").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("limitTextBox_Debris");
		type("limitTextBox_Debris", "10000000000");
		getWebElement("limitTextBox_Debris").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("limitTextBox_Debris");
		getWebElement("limitTextBox_Debris").sendKeys(Keys.TAB);
		clickUsingJS("okButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("limitTextBox_Debris");
		type("limitTextBox_Debris", "26000.00");
		getWebElement("limitTextBox_Debris").sendKeys(Keys.TAB);
		Assert.assertEquals("26000", getWebElementText("limitTextBox_Debris"));
	}
	
	public void validateErrorMessagesForDischargeFromSewerDrainOrSumpAdditionalInsuranceCoverage() {
		wait = new WebDriverWait(driver, 5);
		isWebElementPresent("dischargeLimitLabel");
		isWebElementPresent("annualAggregateLimitAppliesLabel");
		isWebElementPresent("dischargeLimitextBox");
		isWebElementPresent("annualAggregateLimitationAppliesYES");
		isWebElementPresent("annualAggregateLimitationAppliesNO");
		type("dischargeLimitextBox", "a");
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("dischargeLimitextBox");
		sleep(2000);
		type("dischargeLimitextBox", "0");
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("dischargeLimitextBox");
		sleep(2000);
	}
	
	public void validateFieldsForDischargeFromSewerDrainOrSumpAdditionalInsuranceCoverage() {
		wait = new WebDriverWait(driver, 10);
		type("dischargeLimitextBox", getData("dischargeLimitValue"));
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		clickUsingJS("annualAggregateLimitationAppliesYES");
	}

	public void validateFieldsForOutdoorTreesShrubsAndPlantsCoverage() {
		isWebElementDisplayed("eachTreeLimitLabel");
		isWebElementDisplayed("eachShrubLimitLabel");
		isWebElementDisplayed("eachPlantLimitLabel");
		isWebElementDisplayed("allItemsTotalLimitLabel");
		isWebElementDisplayed("causeOfLossDropdown_Outdoor_Label");
		isWebElementPresentAfterWait("eachTreeLimitTextBox");
		isWebElementPresentAfterWait("eachShrubLimitTextBox");
		isWebElementPresentAfterWait("eachPlantLimitTextBox");
		isWebElementPresentAfterWait("allItemsTotalLimitTextBox");
		isWebElementPresentAfterWait("causeOfLossDropdown_Outdoor");
	}
	
	public void validateFieldsForNewlyAcquiredOrConstructedProperty() {
		isWebElementDisplayed("limitLabel");
		isWebElementDisplayed("limitTextBox_Debris");
		isWebElementDisplayed("manualPremiumLabel");
		isWebElementDisplayed("manualPremiumTextBox");
		isWebElementDisplayed("justificationLabel");
		isWebElementDisplayed("justificationTextBox");
	}
	
	public void validateErrorMessagesForNewlyAcquiredOrConstructedPropert() {
		wait = new WebDriverWait(driver, 5);
		type("limitTextBox_Debris", "a");
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("limitTextBox_Debris");
		sleep(2000);
		type("manualPremiumTextBox", "a");
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("manualPremiumTextBox");
		sleep(2000);
		type("limitTextBox_Debris", "10000");
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("limitTextBox_Debris");
		sleep(2000);
		type("manualPremiumTextBox", "-1");
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
		clear("manualPremiumTextBox");
		sleep(2000);
		type("limitTextBox_Debris", "260000");
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		sleep(2000);
		type("manualPremiumTextBox", "100");
		getWebElement("dischargeLimitextBox").sendKeys(Keys.TAB);
		sleep(2000);
		clickOkButton();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("errorMessage")));
		getErrorMessage();
	}

	public void validateErrorMessagesForOutdoorTreesShrubsAndPlantsCoverage() {
		wait = new WebDriverWait(driver, 5);
		sleep(2000);
		type("eachTreeLimitTextBox", "a");
		getWebElement("eachTreeLimitTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type(getWebElement("eachShrubLimitTextBox"), "a");
		getWebElement("eachShrubLimitTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type(getWebElement("eachPlantLimitTextBox"), "a");
		getWebElement("eachPlantLimitTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type(getWebElement("allItemsTotalLimitTextBox"), "a");
		getWebElement("allItemsTotalLimitTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		for (int i = 1; i <= 4; i++) {
			String errorMessage = driver.findElement(By.xpath("(//div[@class='message'])[" + i + "]")).getText();
			ExtentTestManager.getTest().log(Status.INFO, errorMessage);
		}
	}
	
	public void validateErrorMessagesForOrdinananceOrLawCoverage() {
		wait = new WebDriverWait(driver, 5);
		sleep(2000);
		isWebElementPresent("coverageTextBox_Ordinance");
		clickUsingJS("okButton");
		getErrorMessage();
		sleep(2000);
		clickUsingJS("coverageTextBox_Ordinance");
		driver.findElement(By.xpath("//li[contains(text(),'Coverage A Only')]")).click();;
		getWebElement("coverageTextBox_Ordinance").sendKeys(Keys.TAB);
		sleep(1000);
		clickUsingJS("coverageTextBox_Ordinance");
		driver.findElement(By.xpath("//li[contains(text(),'Coverage C Only')]")).click();;
		getWebElement("coverageTextBox_Ordinance").sendKeys(Keys.TAB);
		sleep(1000);
		isWebElementPresent("coverageIncreasedCostOfConstructionLimit");
		type(getWebElement("coverageIncreasedCostOfConstructionLimit"),"a");
		getWebElement("coverageIncreasedCostOfConstructionLimit").sendKeys(Keys.TAB);
		sleep(1000);
		getErrorMessage();
		clear("coverageIncreasedCostOfConstructionLimit");
		sleep(1000);
		type(getWebElement("coverageIncreasedCostOfConstructionLimit"),"0");
		getWebElement("coverageIncreasedCostOfConstructionLimit").sendKeys(Keys.TAB);
		sleep(1000);
		getErrorMessage();
	}

	public void goToAdditionalCoveragesTab() {
		clickUsingJS("additionalCoveragesTab");
	}

	public void clickOkButton() {
		clickUsingJS("okButton");
	}

	public void addValuesForOutdoorTreesShrubsAndPlantsCoverage() {
		wait = new WebDriverWait(driver, 5);
		sleep(1000);
		type(getWebElement("eachTreeLimitTextBox"), getData("eachTreeLimitValue"));
		getWebElement("eachTreeLimitTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type(getWebElement("eachShrubLimitTextBox"), getData("eachShrubLimitValue"));
		getWebElement("eachShrubLimitTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type(getWebElement("eachPlantLimitTextBox"), getData("eachPlantLimitValue"));
		getWebElement("eachPlantLimitTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type(getWebElement("allItemsTotalLimitTextBox"), getData("allItemsTotalLimitValue"));
		getWebElement("allItemsTotalLimitTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		click("causeOfLossDropdown_Outdoor");
		click(driver.findElement(
				By.xpath("//li[contains(text(),'" + getData("causeOfLossDropdown_Outdoor_Value") + "')]")));
	}

	public void addValuesForDebrisRemovalAdditionalInsuranceCoverage() {
		type(getWebElement("limitTextBox_Debris"), getData("limitValue"));
		getWebElement("limitTextBox_Debris").sendKeys(Keys.TAB);
	}

	public void getErrorMessage() {
		if (isWebElementPresent("errorMessage") == true) {
			ExtentTestManager.getTest().log(Status.INFO, getWebElementText("errorMessage"));
		}
	}

	public void presenceOfAdditionalCoverageAtBuildingLevel(String[] coverages,String additionalCoverageName) {
		for(int i=0;i<coverages.length;i++) {
			clickUsingJS("detailsTab");
			sleep(2000);
			clickUsingJS("coverageDropdown");
			click(driver.findElement(
					By.xpath("//li[contains(text(),'" + coverages[i] + "')]")));
			ExtentTestManager.getTest().log(Status.INFO, "selected "+coverages[i]);
			sleep(2000);
			goToAdditionalCoveragesTab();
			clickAddCoveragesButton();
			validateCoverageIsPresent(additionalCoverageName);
			clickUsingJS("returnToNewBuildingLink");
			
		}
	}
}
