package com.wrg.PC.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class NewSubmissionPage_PC extends AbstractTest{
	private WebDriverWait wait=null;
	
	
	public void selectBOPClassCode() {
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("selectBOPButton")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("selectBOPButton")));
		click(getWebElement("selectBOPButton"));
		sleep(1000);
		if(isWebElementPresent("okButton")==true) {
			clickUsingJS("okButton");
		}
	}
	
	public void selectCommercialPropertyClassCode() {
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("selectCommercialPropertyButton")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("selectCommercialPropertyButton")));
		click(getWebElement("selectCommercialPropertyButton"));
		sleep(1000);
		if(isWebElementPresent("okButton")==true) {
			clickUsingJS("okButton");
		}
	}
}
