package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class LocationsPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	

	public int verifyLocationPageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getLocationUIElementList();
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
		
		public  List<String> getLocationUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("locationuibuttons"));
			return totalelements;
		}
		
		
		public int getLocationUIElementTotalCount() {
			return Integer.parseInt(getData("locationuielements"));	
		}
		
	
	
	public void goToClassificationsPage(String state,String numberOfLocations) {
		wait = new WebDriverWait(driver, 10);
		waitForPageLoaded();
		addLocation(state, numberOfLocations);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButtonBottom")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("nextButtonBottom")));
		clickUsingJS("nextButtonBottom");
	}

	public void addLocation(String state, String numberOfLocations) {
		waitForPageLoaded();
		for (int i = 1; i < Integer.parseInt(numberOfLocations); i++) {
			clickUsingJS("addNewLocationButton");
			String mainwindow = driver.getWindowHandle();
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				if (state.equalsIgnoreCase("Indiana")) {
					type("addressLine1", getData("indianaAddress" + i));
					type("city", getData("indianaCityName" + i));
					type("zipCode", getData("indianaPincodeValue" + i));
				} else {
					type("addressLine1", getData("ohioAddress" + i));
					type("city", getData("ohioCityName" + i));
					type("zipCode", getData("ohioPincodeValue" + i));
				}
				clickUsingJS("saveButton");
				try {
					clickUsingJS("useVerifiedButton");
				} catch (Exception e) {
					clickUsingJS("useOriginalButton");
				}

			}
			driver.switchTo().window(mainwindow);

		}
	}

}
