package com.wrg.PC.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class AddressVerificationPage_PC extends AbstractTest{
	WebDriverWait wait=null;
	
	
	
	public void addressUpdate() {
		sleep(2000);
		wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("updateButton")));
		click(getWebElement("updateButton"));
	}

}
