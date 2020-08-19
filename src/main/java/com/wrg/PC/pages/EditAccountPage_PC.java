package com.wrg.PC.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class EditAccountPage_PC extends AbstractTest{
	WebDriverWait wait = null;
	
	public void addClassCode(String classCodeNumber) {
		if(classCodeNumber.contains(",")) {
			String spiltClassCodes[] = classCodeNumber.split(",");
			List<String> classCodes = new ArrayList<String>();
			for(String classCode:spiltClassCodes) {
			classCodes.add(classCode);
			selectClassCode(classCode);
			selecthazardGrade();
			clickUsingJS("updateButton");
			}
		}else {
			selectClassCode(classCodeNumber);
			selecthazardGrade();
			clickUsingJS("updateButton");
		}
	}
	
	public void selecthazardGrade() {
		clickUsingJS("firstResultOfHazard");
	}
	
	public void updateAccount() {
		clickUsingJS("updateButton");
	}
	
	public void selectClassCode(String classCode) {
		wait = new WebDriverWait(driver, 10);
		clickUsingJS("addHazardGuideButton");
		type("classCodeTextBox",classCode);
		click("searchButton");
		sleep(2000);
		clickIfElementIsStale(getWebElement("firstResult"));
	}

}
