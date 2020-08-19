package com.wrg.PC.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class SupplementalPage_PC extends AbstractTest{
	WebDriverWait wait=null;

	

	public void goToRiskAnalysisPage() {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", getWebElement("nextButton"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("supplementalScreenValidation")));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		try {
			sleep(3000);
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
	
	public boolean isWebElementPresentInSupplemental(String element) {
		driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);
		if (getWebElements(element).size() > 0) {
			return true;
		} else {
			return false;
		}

	}
	
	public void clickIfElementIsDisplayed(String element) {
		if(isWebElementPresentAfterWait(element)==true) {
			clickUsingJS(element);
		}
	}
	
	public void generalQuestions() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("supplementalScreenValidation")));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		clickIfElementIsDisplayed("bankruptcyNO");
		clickIfElementIsDisplayed("policyDeclinedNO");
		clickIfElementIsDisplayed("controllingInterestNO");
		sleep(2000);
		clickIfElementIsDisplayed("allegationsNO");
		sleep(2000);
		clickIfElementIsDisplayed("continuousInsuranceYES");
		sleep(3000);
		clickUsingJS("mostRecentCarrierDropdown");
		clickUsingJS(driver.findElement(By.xpath("//li[contains(text(),'Allstate')]")));
		sleep(2000);
		clickIfElementIsDisplayed("lossesNO");
		sleep(2000);
		clickIfElementIsDisplayed("operationsInOtherStatesNO");
		clickIfElementIsDisplayed("singleFamilyDwellingNO");
		clickIfElementIsDisplayed("areaOccupiedYES");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", getWebElement("otherOperationsNO"));
		clickIfElementIsDisplayed("otherOperationsNO");
		clickIfElementIsDisplayed("heatingDevicesNO");
		clickIfElementIsDisplayed("annualGrossSalesNO");
//		click(getWebElement("wiringImprovementYearTextBox"));
//		type(getWebElement("wiringImprovementYearTextBox"),getData("wiringYear"));
//		click(getWebElement("heatingImprovementYearTextBox"));
//		type(getWebElement("heatingImprovementYearTextBox"),getData("heatingYear"));
//		click(getWebElement("plumbingImprovementYearTextBox"));
//		type(getWebElement("plumbingImprovementYearTextBox"),getData("plumbingYear"));
//		click(getWebElement("roofImprovementYearTextBox"));
//		type(getWebElement("roofImprovementYearTextBox"),getData("roofYear"));
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		clickIfElementIsDisplayed("seatingCapacityOver150NO");
		clickIfElementIsDisplayed("seatingCapacityGreaterThan75NO");
		clickIfElementIsDisplayed("businessClosedMoreThan30DaysNO");
		clickIfElementIsDisplayed("businessCloseMoreThan30DaysNO");
		clickIfElementIsDisplayed("cateringServiceYES");
		clickIfElementIsDisplayed("cateringExceed10%SalesYES");
		clickIfElementIsDisplayed("anySolidFuelCookingNO");
		clickIfElementIsDisplayed("anyOtherLiquorSalesNO");
		clickIfElementIsDisplayed("alcoholServedAfterKitchenClosedNO");
		clickIfElementIsDisplayed("dancingPermittedNO");
		clickIfElementIsDisplayed("playAreaNO");
		clickIfElementIsDisplayed("liveEntertainmentaNO");
		clickIfElementIsDisplayed("compliantWithUL300NO");
		clickIfElementIsDisplayed("cleaningContractActiveNO");
		clickIfElementIsDisplayed("fireSupressionsSystemInspectedNO");
		clickIfElementIsDisplayed("liquorSalesNO");
	}

}
