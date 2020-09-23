package com.wrg.AP.pages;

import java.util.ArrayList;
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

public class LocationsPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	

	public int verifyLocationPageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getLocationUIElementList();
		      
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
		
		public  List<String> getLocationUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("locationuibuttons"));
			return totalelements;
		}
		
		
		public int getLocationUIElementTotalCount() {
			return Integer.parseInt(getData("locationuielements"));	
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
	public void addLocationScreenValidation(String state, String numberOfLocations) {
		waitForPageLoaded();
		clickUsingJS("addNewLocationButton");
		String mainwindow = driver.getWindowHandle();
		for (String popup : driver.getWindowHandles()) {
			driver.switchTo().window(popup);
			//wait.until(ExpectedConditions.visibilityOf(getWebElement("addressLine1")));
			String addressLineText;
			addressLineText = getWebElementText("addressLine1");
			
			if (isWebElementPresentAfterWait("addressLine1")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Address Line 1 -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Address Line 1 -> is not present" ,
								ExtentColor.RED));
			}
			if (addressLineText.isEmpty()) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Address Line 1 -> is blank by default" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Address Line 1 -> is not blank by default" ,
								ExtentColor.RED));
			}
			
			if (isWebElementPresentAfterWait("addressLine2")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Address Line 2 -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Address Line 2 -> is not present" ,
								ExtentColor.RED));
			}
			addressLineText = getWebElementText("addressLine2");
			if (addressLineText.isEmpty()) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Address Line 2 -> is blank by default" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Address Line 2 -> is not blank by default" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("city")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"City -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"City -> is not present" ,
								ExtentColor.RED));
			}
			addressLineText = getWebElementText("city");
			if (addressLineText.isEmpty()) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"City -> is blank by default" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"City -> is not blank by default" ,
								ExtentColor.RED));
			}
			if (isWebElementPresentAfterWait("zipCode")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Zip Code -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Zip Code -> is not present" ,
								ExtentColor.RED));
			}
			addressLineText = getWebElementText("zipCode");
			if (addressLineText.isEmpty()) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Zip Code -> is blank by default" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Zip Code -> is not blank by default" ,
								ExtentColor.RED));
			}
			actionClick("saveButton");
			if (getWebElements("requiredFieldCount").size()>0) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Required Field Label -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Required Field Label -> is not present" ,
								ExtentColor.RED));
			}
			
			type("addressLine1", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm123456789");
			actionClick("saveButton");
			if (isWebElementPresentAfterWait("addressLine1ErrorText")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Value must be between 0 and 60 characters -> address line 1 error text is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Value must be between 0 and 60 characters -> address line 1 error text is not present" ,
								ExtentColor.RED));
			}
			actionClick("cancel");
			driver.switchTo().window(mainwindow);
			clickUsingJS("addNewLocationButton");
			driver.switchTo().window(popup);
			type("addressLine1", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			//actionClick("saveButton");
			//actionClick("addressLine1");
			//typeUsingJS("addressLine2", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			//actionClick("saveButton");
		
			type("addressLine2", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm123456789");
			sleep(3000);
			actionClick("saveButton");
			if (isWebElementPresentAfterWait("addressLine1ErrorText")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Value must be between 0 and 60 characters -> address line 2 error text is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Value must be between 0 and 60 characters -> address line 2 error text is not present" ,
								ExtentColor.RED));
			}
			type("addressLine2", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			actionClick("addressLine2");
			type("city", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm123456789");
			actionClick("addressLine2");
			actionClick("saveButton");
			actionClick("addressLine2");
			if (isWebElementPresentAfterWait("addressLine1ErrorText")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Value must be between 0 and 60 characters -> city error text is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Value must be between 0 and 60 characters -> city error text is not present" ,
								ExtentColor.RED));
			}
			type("city", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			actionClick("saveButton");
			type("zipCode", "44281&1234");
			clickUsingJS("saveButton");
			actionClick("city");
			if (isWebElementPresentAfterWait("zipCodeErrorText")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Zip code is not formatted -> zip code error text is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Zip code is not formatted -> zip code error text is not present" ,
								ExtentColor.RED));
			}
			actionClick("cancel");
			driver.switchTo().window(mainwindow);
			clickUsingJS("addNewLocationButton");
			driver.switchTo().window(popup);
			type("addressLine1", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			type("addressLine2", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			type("city", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			type("zipCode", "1");
			actionClick("saveButton");
		
			if (isWebElementPresentAfterWait("zipCodeErrorText")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Zip code is not formatted -> zip code error text is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Zip code is not formatted -> zip code error text is not present" ,
								ExtentColor.RED));
			}
			//typeUsingJS("zipCode", "");
			//actionClick("zipCode");
			type("zipCode", "1234567890");
			actionClick("saveButton");
		
			if (isWebElementPresentAfterWait("zipCodeErrorText")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Zip code is not formatted -> zip code error text is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Zip code is not formatted -> zip code error text is not present" ,
								ExtentColor.RED));
			}
			actionClick("cancel");
			driver.switchTo().window(mainwindow);
			clickUsingJS("addNewLocationButton");
			driver.switchTo().window(popup);
			type("addressLine1", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			type("addressLine2", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			type("city", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm12345678");
			type("zipCode", "44692");
			actionClick("saveButton");
			if (isWebElementPresentAfterWait("invalidZipCodeErrorText")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Zip code is not valid for state -> zip code error text is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Zip code is not valid for state -> zip code error text is not present" ,
								ExtentColor.RED));
			}
			actionClick("close");
			actionClick("cancel");
			if (isWebElementPresentAfterWait("locationsPageHeading")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Location Page Heading -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Location Page Heading -> is not present" ,
								ExtentColor.RED));
			}
			
		}
		driver.switchTo().window(mainwindow);
		quitDriver(driver);
		
	}
	public void editLocationScreenValidation(String state, String numberOfLocations) {
		waitForPageLoaded(); 
	
		for (int i = 1; i < Integer.parseInt(numberOfLocations); i++) {
			clickUsingJS("editLocationButton");
			String mainwindow = driver.getWindowHandle();
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				if (state.equalsIgnoreCase("Indiana")) {
					clear("addressLine1");
					clear("city");
					clear("zipCode");
					type("addressLine1", getData("indianaAddress" + i));
					type("city", getData("indianaCityName" + i));
					type("zipCode", getData("indianaPincodeValue" + i));
				} else {
					clear("addressLine1");
					clear("city");
					clear("zipCode");
					type("addressLine1", getData("ohioAddress" + i));
					type("city", getData("ohioCityName" + i));
					type("zipCode", getData("ohioPincodeValue" + i));
				}
				actionClick("addressLine1");
				actionClick("saveButton");
		
				try {
					clickUsingJS("useVerifiedButton");
				} catch (Exception e) {
					clickUsingJS("useOriginalButton");
				}

			}
			driver.switchTo().window(mainwindow);
			if(isWebElementPresentAfterWait("verifyEditAddress")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"Address is editted successfully" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"Address is not editted successfully" ,
								ExtentColor.RED));
			}
			quitDriver(driver);
			
		}
	}
	
	public void deleteLocation(String state, String numberOfLocations) {
		waitForPageLoaded();
		clickUsingJS("secondLocationDeleteIcon");
		if (isWebElementPresentAfterWait("deletionMessage")) {
			ExtentTestManager.getTest().log(Status.INFO,
					MarkupHelper.createLabel(
							"Deletion Message is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Deletion Message is not present" ,
							ExtentColor.RED));
		}
		clickUsingJS("delete");
		sleep(2000);
		if (isWebElementPresentAfterWait("secondLocationText")) {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Location is not deleted successfully" ,
							ExtentColor.RED));
		}else {
			ExtentTestManager.getTest().log(Status.INFO,
					MarkupHelper.createLabel(
							"Location is deleted successfully" ,
							ExtentColor.GREEN));
		}
		quitDriver(driver);
		
	}
}
