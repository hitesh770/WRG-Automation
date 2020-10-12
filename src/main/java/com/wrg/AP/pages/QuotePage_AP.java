package com.wrg.AP.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class QuotePage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void quote() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (isWebElementPresentAfterWait("continueButton") == true && isWebElementDisplayed("continueButton") == true) {
			clickUsingJS("continueButton");
		}
		if (isWebElementPresentAfterWait("startApplicationButton") == true) {
			clickUsingJS("startApplicationButton");
		}
	}

	
	public String getDisclaimerText() {
		String disclaimertext=null;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (isWebElementPresentAfterWait("disclaimertext") == true) {
			disclaimertext=getWebElementText("disclaimertext");
			 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(disclaimertext,ExtentColor.BLUE));
		}
		
		return disclaimertext;
	}
	
	
	public void clickonpreviousbtn(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (isWebElementPresentAfterWait("previousbtn") == true) {
			clickUsingJS("previousbtn");
		}
	}
	
	//function to verify portal wizard menus 
	
	public boolean viewwizardmenusonQuotePageUI() {
		boolean flag=true;
		
		 if(getWebElementColor("portalcurrentwizardmenu", "background-color").equals("#004ebd")) {
			 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(getWebElementText("portalcurrentwizardmenu")+ " color is blue ",ExtentColor.BLUE));
			  flag=true; 
		 }else {
			 return flag=false;  
		 }
		
		 
		 if(getWebElementColor("portalfistwizardmenu", "color").equals("#555555") && getWebElementColor("portalsecondwizardmenu", "color").equals("#555555")) {
			 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(getWebElementText("portalfistwizardmenu")+ " and " +getWebElementText("portalsecondwizardmenu")+ " color is dark grey and appear as enabled ",ExtentColor.BLUE)); 
			  flag=true;
		 }else {
			 return flag=false;   
		 }
		 
		
		 
		 if(getWebElementColor("portalninewizardmenu", "color").equals("#bbbdbf") && getWebElementColor("portaltenwizardmenu", "color").equals("#bbbdbf")) {
			 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(getWebElementText("portalninewizardmenu")+ " and " +getWebElementText("portaltenwizardmenu")+ " color is  grey and appear as disabled ",ExtentColor.BLUE)); 
			 flag=true;
		 }else {
			 return flag=false;   
		 }
		 
		 return flag;
	}

}
