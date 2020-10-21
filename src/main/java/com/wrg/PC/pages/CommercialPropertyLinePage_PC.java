package com.wrg.PC.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class CommercialPropertyLinePage_PC extends AbstractTest {
	WebDriverWait wait = null;

	public void goToNextPage() {
		wait = new WebDriverWait(driver, 20);
		try {
			waitForPageLoaded();
			sleep(3000);
			wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
			if(isWebElementPresent("nextButton")==true) {
			click(getWebElement("nextButton"));
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

	public void goToAdditionalCoveragesTab() {
		waitForPageLoaded();
		if (isWebElementPresent("additionalCoveragesTab") == true) {
			clickUsingJS("additionalCoveragesTab");
		}
	}

	public void clickOnAddCoveragesButton() {
		wait = new WebDriverWait(driver, 5);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("addCoveragesButton")));
		clickUsingJS("addCoveragesButton");
	}

	public void clickOnAddSelectedCoveragesButton() {
		clickUsingJS("addSelectCoveragesButton");
	}

	public void verifyAdditionalCoverages() {
		String[] coverageNames = { "Cap On Losses From Certified Acts Of Terrorism",
				"Value Reporting Form General Information", "Disclosure Pursuant To Terrorism Risk Insurance Act",
				"Electronic Commerce (E-Commerce)" };
		clickOnAddCoveragesButton();
		for (int i = 1; i <= 4; i++) {
			Assert.assertEquals(coverageNames[i - 1], driver.findElement(By.xpath(
					"//div[@id='CoveragePatternSearchPopup:CoveragePatternSearchScreen:CoveragePatternSearchResultsLV']/div[3]/div/table/tbody/tr["
							+ i + "]/td[3]"))
					.getText());
		}
		Assert.assertEquals("CP 04 30", getWebElementText("electronicCommerceFormNumber"));
	}

	public void addElectronicCommerceCoverage() {
		click("electroniCommerceCoverageCheckbox");
		clickOnAddSelectedCoveragesButton();
		waitForPageLoaded();
	}
	
	public void enterDetailsForElectronicCommerceCoverage() {
		type("descriptionOfBusinessTextBox",getData("descriptionOfBusinessValue"));
		type("annualAggregateLimitOfInsuranceTextBox",getData("annualAggregateLimitOfInsuranceValue"));
		type("sectionIDeductibleTextBox",getData("sectionIDeductibleValue"));
		type("manualPremiumTextBox",getData("manualPremiumValue"));
	}
	
	public void validateErrorMessagesForElectronicCommerceCoverage() {
		wait = new WebDriverWait(driver,10);
		type("descriptionOfBusinessTextBox","Testingqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		getWebElement("descriptionOfBusinessTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type("locationOfBusinessTextBox","Testingqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		getWebElement("locationOfBusinessTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type("annualAggregateLimitOfInsuranceTextBox","-1");
		getWebElement("annualAggregateLimitOfInsuranceTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type("sectionIDeductibleTextBox","Test");
		getWebElement("sectionIDeductibleTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		type("manualPremiumTextBox","Test");
		getWebElement("manualPremiumTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		for(int i=1;i<=5;i++) {
			ExtentTestManager.getTest().log(Status.INFO, driver.findElement(By.xpath("(//div[@class='message'])["+i+"]")).getText());
		}
		clear("descriptionOfBusinessTextBox");
		sleep(1000);
		type("descriptionOfBusinessTextBox",getData("descriptionOfBusinessValue"));
		getWebElement("descriptionOfBusinessTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		clear("locationOfBusinessTextBox");
		sleep(1000);
		type("locationOfBusinessTextBox",getData("locationOfBusinessValue"));
		getWebElement("locationOfBusinessTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		clear("annualAggregateLimitOfInsuranceTextBox");
		sleep(1000);
		type("annualAggregateLimitOfInsuranceTextBox",getData("annualAggregateLimitOfInsuranceValue"));
		getWebElement("annualAggregateLimitOfInsuranceTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		clear("sectionIDeductibleTextBox");
		sleep(1000);
		type("sectionIDeductibleTextBox",getData("sectionIDeductibleValue"));
		getWebElement("sectionIDeductibleTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		clear("manualPremiumTextBox");
		sleep(1000);
		type("manualPremiumTextBox",getData("manualPremiumValue"));
		getWebElement("manualPremiumTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		click("nextButton");
		sleep(1000);
		ExtentTestManager.getTest().log(Status.INFO, driver.findElement(By.xpath("//div[@class='message']")).getText());
	}

	public boolean validatePolicyLevelCoveragesPresence() {
		boolean result = false;
		if (isWebElementPresent("policyLevelCoveragesHeading") == true
				&& isWebElementPresent("commercialPropertyEnhancementEndorsementTitle") == true
				&& isWebElementPresent("commercialPropertyEnhancementEndorsementCheckbox") == true
				&& isWebElementPresent("wil60Title") == true
				&& isWebElementPresent("wil60Checkbox") == true) {
			Assert.assertEquals(getWebElementText("policyLevelCoveragesHeading"), "Policy Level Coverages");
			Assert.assertEquals(getWebElementText("commercialPropertyEnhancementEndorsementTitle"), "Commercial Property Enhancement Endorsement");
			Assert.assertEquals(getWebElementText("wil60Title"), "WIL 60 - Cyber - Data Compromise Coverage");
			result = true;
		}
		return result;
	}
	
	public boolean validatePolicyDefaults() {
		boolean result = false;
		if(isWebElementPresent("policyDefaultsLabel")==true
				&& isWebElementPresent("deductibleDefaultsLabel")
				&& isWebElementPresent("deductibleLabel")
				&& isWebElementPresent("theftDeductibleLabel")
				&& isWebElementPresent("windstormOrHailDeductibleLabel")) {
			Assert.assertEquals(getWebElementText("policyDefaultsLabel"), "Policy Defaults");
			Assert.assertEquals(getWebElementText("deductibleDefaultsLabel"), "Deductible Defaults");
			Assert.assertEquals(getWebElementText("deductibleLabel"), "Deductible");
			Assert.assertEquals(getWebElementText("theftDeductibleLabel"), "Theft Deductible (if COL is Special)");
			Assert.assertEquals(getWebElementText("windstormOrHailDeductibleLabel"), "Windstorm or Hail Deductible");
			result=true;
		}
		return result;
	}
	
	public boolean validateElectronicCommerceLabels() {
		boolean result = false;
		if (isWebElementPresent("descriptionOfBusinessLabel") == true
				&& isWebElementPresent("locationOfBusinessLabel") == true
				&& isWebElementPresent("annualAggregateLimitOfInsuranceLabel") == true
				&& isWebElementPresent("antiVirusWaiverLabel") == true
				&& isWebElementPresent("sectionIDeductibleLabel") == true
				&& isWebElementPresent("manualPremiumLabel") == true
				&& isWebElementPresent("justificationLabel") == true) {
			result = true;
		}
		return result;
	}
	
	public boolean validateWIL60CoverageLabelsAndFields() {
		wait = new WebDriverWait(driver, 10);
		boolean result = false;
		//sleep(1000);
		waitForPageLoaded();
		click("wil60Checkbox");
		click("retentionDropdown");
		driver.findElement(By.xpath("//li[contains(text(),'Other')]")).click();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("otherRetentionLabel")));
		click("limitLabel");
		driver.findElement(By.xpath("//li[contains(text(),'"+getData("limitValue")+"')]")).click();
		if (isWebElementPresent("limitLabel") == true
				&& isWebElementPresent("limitDropdown") == true
				&& isWebElementPresent("retentionLabel") == true
				&& isWebElementPresent("retentionDropdown") == true
				&& isWebElementPresent("retroactiveDateLabel") == true
				&& isWebElementPresent("retroactiveDateValue") == true
				&& isWebElementPresent("otherRetentionLabel") == true
				&& isWebElementPresent("otherRetentionTextBox") == true) {
			Assert.assertEquals(getWebElementText("limitLabel"), "Limit");
			Assert.assertEquals(getWebElementText("retentionLabel"), "Retention");
			Assert.assertEquals(getWebElementText("retroactiveDateLabel"), "Retroactive Date");
			Assert.assertEquals(getWebElementText("otherRetentionLabel"), "Other Retention");
			result = true;
		}
		return result;
	}
	
}
