package com.wrg.PC.pages;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class ClassCodeSearchPage_PC extends AbstractTest {
	WebDriverWait wait = null;
	
	
	public void selectClassCode(String classCode) {
		wait = new WebDriverWait(driver, 10);
		clickUsingJS("addHazardGuideButton");
		type("classCodeTextBox",classCode);
		click("searchButton");
		sleep(2000);
		clickIfElementIsStale(getWebElement("firstResult"));
	}
}
