package com.wrg.PC.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class OfferingsPage_PC extends AbstractTest{
	WebDriverWait wait=null;

	
	public void selectOffering(String formType) {
		wait = new WebDriverWait(driver, 10);
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("offeringScreenValidation")));
		//wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		click(getWebElement("offeringSelectionDropdown"));
		click(driver.findElement(By.xpath("//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'"+formType+"')]")));
		if(isWebElementPresent("okButton")==true) {
			clickUsingJS("okButton");
		}
		clickUsingJS(getWebElement("nextButton"));
	}

}
