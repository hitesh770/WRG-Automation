package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class PolicywideCoveragesPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	
	public int verifyPolicywideCoveragePageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getPolicywideCoverageUIElementList();
			System.out.println(totalPageElements.size());
		      
		     for(int i=0;i<totalPageElements.size();i++) {
		    	  currentelementcount=0;
		    	  String curlocater=totalPageElements.get(i);
		    	 
		    	  List<WebElement> curloclist=getWebElementsBydata(totalPageElements.get(i));
		    	  inner:for(int j=1;j<=curloclist.size();j++) {
		    		  WebElement curElement=null;
		    		  try { 
		    		   curElement=getWebElementBydata(curlocater+"["+j+"]"); 
		    	          }catch(Exception e) {
		    	        	  continue inner;
			              }
		    		  if(curElement.isDisplayed()==true) { 
			    			 ExtentTestManager.getTest().log(Status.INFO, curElement.getText() + " Page Element is Present");
			    			currentelementcount++;
			    			continue inner;
			    		}
			    		 
			    	 } //inner for loop close here
		    	  totalcount=totalcount+currentelementcount;
		    	  
		      } //outer  for loop close here
		      
		     
			
	System.out.println(totalcount); 
			
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
}
