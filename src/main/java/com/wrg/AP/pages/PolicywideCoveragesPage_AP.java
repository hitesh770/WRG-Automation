package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class PolicywideCoveragesPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	
	public int verifyPolicywideCoveragePageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getPolicywideCoverageUIElementList();
		      
		     for(int i=0;i<totalPageElements.size();i++) {
		    	  currentelementcount=0;
		    	  String curlocater=totalPageElements.get(i);
		    	 
		    	  List<WebElement> curloclist=getCurrentList(totalPageElements.get(i));
		    	  inner:for(int j=1;j<=curloclist.size();j++) {
		    		  WebElement curElement=null;
		    		  try { 
		    		   curElement=getCurrentElement(curlocater+"["+j+"]"); 
		    	          }catch(Exception e) {
		    	        	  continue inner;
			              }
		    		  if(curElement.isDisplayed()==true) { 
		    			  ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(curElement.getText() + " Page Element is Present",ExtentColor.BLUE));
			    			currentelementcount++;
			    			continue inner;
			    		}
			    		 
			    	 } //inner for loop close here
		    	  totalcount=totalcount+currentelementcount;
		    	  
		      } //outer  for loop close here
		      
	
			
			return totalcount;
		}
		
		public  List<String> getPolicywideCoverageUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("policywideuilabels"));
			totalelements.add(getData("policywideuidropdownfields"));
			totalelements.add(getData("policywideuibuttons"));
			return totalelements;
		}
		
		
		public int getPolicywideCoverageUIElementTotalCount() {
			return Integer.parseInt(getData("policywideuielements"));	
		}
		
	
		public List<WebElement> getCurrentList(String data) {
			List<WebElement> we = null;
			try {
				we = driver.findElements(By.xpath(data));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return we;
		}
		
		
		
		
		public WebElement getCurrentElement(String data) {
			WebElement we = null; 
			try {
				 we = driver.findElement(By.xpath(data));
			} catch (Exception e) {
				e.printStackTrace();
				
			}

			return we;
		}
		
		
	public void coverages() {
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policywideCoveragesHeading")));
		if (isWebElementPresentAfterWait("coverage") == true) {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("coverage")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					getWebElement("coverage"));
			click("coverage");
		}
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("nextButton")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("nextButton"));
		sleep(1000);
		click("nextButton");
	}
	public void additionalCoveragesToolTipValidation(String classCodeNumber, String coverageNumber,String tooltipText) {
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policywideCoveragesHeading")));

		String tooltip = null;
		
		if(coverageNumber =="WGL12") {
			scrollToElement("WGL12HelpIcon");
			actionClick("WGL12HelpIcon");
			//sleep(4000);
			wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2293Tooltip")));
			tooltip = getWebElementText("CG2293Tooltip");
			System.out.print(tooltip);
			if (tooltipText.contains(tooltip)) {					
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"WGL 12 Tooltip -> " + tooltip,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"WGL 12 Tooltip -> "+ tooltip,
								ExtentColor.RED));
			}
			clickUsingJS("GeneralLiabilityEnhancementEndorsement");
			actionClick("WGL12Coverages");
			if (isWebElementPresentAfterWait("WGL12HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"WGL 12 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"WGL 12 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			sleep(2000);
			clickUsingJS("WGL12Coverages");
			if (isWebElementPresentAfterWait("WGL12HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"WGL 12 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"WGL 12 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			quitDriver(driver);
		}
	}
	
	//function to verify portal wizard menus 
	
	public boolean viewwizardmenusonPolicyWideCoverageUI() {
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
		 
		
		 
		 if(getWebElementColor("portalffithwizardmenu", "color").equals("#bbbdbf") && getWebElementColor("portalsixwizardmenu", "color").equals("#bbbdbf")) {
			 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(getWebElementText("portalffithwizardmenu")+ " and " +getWebElementText("portalsixwizardmenu")+ " color is  grey and appear as disabled ",ExtentColor.BLUE)); 
			 flag=true;
		 }else {
			 return flag=false;   
		 }
		 
		 return flag;
	}
	
	
	
	
}
