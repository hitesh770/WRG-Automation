package com.wrg.AP.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class WrgHomePage_AP extends AbstractTest{
	WebDriverWait wait=null;

	
	
	public void createNewQuote(String quote) {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("createNewQuoteButton")));
		clickUsingJS("createNewQuoteButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("quoteDropdown")));
		click(driver.findElement(By.xpath("//span[contains(text(),'"+quote+"')]")));
	}

	
	
	public HashMap<Integer, String> getCreateNewButtonOptions() {
		HashMap<Integer, String> createquoteMap=new HashMap<Integer, String>();
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("createNewQuoteButton")));
		clickUsingJS("createNewQuoteButton");
		waitForPageLoaded();
		List<WebElement> quoteoptions=getWebElements("createnewquoteoptions");
		
		for(int i=0;i<quoteoptions.size();i++) { 
			createquoteMap.put(i+1,quoteoptions.get(i).getText());  
			ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(quoteoptions.get(i).getText(),ExtentColor.BLUE));
		}
		
		return createquoteMap;
		
	}
	
}
