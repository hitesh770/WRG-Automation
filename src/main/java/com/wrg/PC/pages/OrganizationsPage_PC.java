package com.wrg.PC.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wrg.abstestbase.AbstractTest;

public class OrganizationsPage_PC extends AbstractTest{
	
	
	public void selectOrganization(String organizationCode) throws IOException {
		clickUsingJS("organizationCodeTextBox");
		sleep(1000);
		type(("organizationCodeTextBox"),organizationCode);
		clickUsingJS("searchButton");
		clickUsingJS(driver.findElement(By.xpath("//div[contains(text(),'"+organizationCode+"')]//ancestor::tr[starts-with(@id,'gridview')]/td/div/a")));
		clickUsingJS("producerCodeDropDown");
		clickUsingJS("producerCodeDropdownValue");
	}

}
