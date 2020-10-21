package com.wrg.PC.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class ClassCodeSearchPage_PC extends AbstractTest {
	WebDriverWait wait = null;
	
	public void clickHazardCodeButton() {
		clickUsingJS("addHazardGuideButton");
	}
	
	public void selectClassCode(String classCode, String selectionType) {
		wait = new WebDriverWait(driver, 10);
		
		clickUsingJS("addHazardGuideButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("GL")));
		clickUsingJS(selectionType);
		sleep(2000);
		type("classCodeTextBox",classCode);
		click("searchButton");
		sleep(2000);
		clickIfElementIsStale(getWebElement("firstResult"));
	}
	
	public void selectClassCode(String classCode) {
		wait = new WebDriverWait(driver, 10);
		
		clickUsingJS("addHazardGuideButton");
		sleep(2000);
		type("classCodeTextBox",classCode);
		click("searchButton");
		sleep(2000);
		clickIfElementIsStale(getWebElement("firstResult"));
	}
}
