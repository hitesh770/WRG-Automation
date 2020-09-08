package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class ClassificationsPage_AP extends AbstractTest {

	WebDriverWait wait = null;
	

	public int verifyClassificationPageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getClassificationUIElementList();
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
		
		public  List<String> getClassificationUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("classificaionuibuttons"));
			return totalelements;
		}
		
		
		public int getClassificationUIElementTotalCount() {
			return Integer.parseInt(getData("classificaionuielements"));	
		}

	public void addClassifications(String classCodeNumber, String exposureAmount, String numberOfLocations) {
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("classificationPageHeading")));
//		if(Integer.parseInt(numberOfLocations>1) {
//			wait.until(ExpectedConditions.visibilityOf(getWebElement("addNewClassificationButton")));
//			Select selectLocation = new Select(getWebElement("locationDropdown"));
//			selectLocation.selectByIndex(1);
//		}
		if (Integer.parseInt(numberOfLocations) >= 1) {
//			
			for (int k = 1; k <= Integer.parseInt(numberOfLocations); k++) {
				if (classCodeNumber.contains(",")) {
					String spiltClassCodes[] = classCodeNumber.split(",");
					List<String> classCodes = new ArrayList<String>();
					for (int i = 0; i < spiltClassCodes.length; i++) {
						classCodes.add(spiltClassCodes[i]);
						sleep(1000);
						wait.until(ExpectedConditions.visibilityOf(getWebElement("addNewClassificationButton")));
						clickUsingJS("addNewClassificationButton");
						String mainwindow = driver.getWindowHandle();
						for (String popup : driver.getWindowHandles()) // iterating on child windows
						{
							driver.switchTo().window(popup);
							wait.until(ExpectedConditions.visibilityOf(getWebElement("addNewClassificationButton")));
							Select selectLocation = new Select(getWebElement("locationDropdown"));
							if (Integer.parseInt(numberOfLocations) > 1) {
								selectLocation.selectByIndex(i + 1);
							} else {
								selectLocation.selectByIndex(1);
							}

							Select select = new Select(getWebElement("classificationDropdown"));
							String expectedDescription = spiltClassCodes[i];
							List<WebElement> allClassCodes = select.getOptions();
							for (WebElement option : allClassCodes) {
								String currentClassCode = option.getText();
								if (currentClassCode.contains(expectedDescription)) {
									select.selectByVisibleText(currentClassCode);
								}
							}
							type(getWebElement("exposureTextBox"), exposureAmount);
							getWebElement("exposureTextBox").sendKeys(Keys.TAB);
							wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
							// clickUsingJS("addNewClassificationButton");
							wait.until(ExpectedConditions.visibilityOf(getWebElement("saveClassificationButton")));
							wait.until(
									ExpectedConditions.elementToBeClickable(getWebElement("saveClassificationButton")));
							try {
								sleep(6000);
								clickUsingJS("saveClassificationButton");
								// wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
								sleep(2000);
							} catch (Exception e) {
								try {
									clickUsingJS("closeButton");
								} catch (NoAlertPresentException f) {
									f.printStackTrace();
								}
							}
						}
						driver.switchTo().window(mainwindow);
					}
					break;
				} else {
					wait.until(ExpectedConditions.visibilityOf(getWebElement("addNewClassificationButton")));
					clickUsingJS("addNewClassificationButton");
					String mainwindow = driver.getWindowHandle();
					for (String popup : driver.getWindowHandles()) // iterating on child windows
					{
						driver.switchTo().window(popup);
						wait.until(ExpectedConditions.visibilityOf(getWebElement("saveClassificationButton")));
						if (Integer.parseInt(numberOfLocations) > 1) {
							Select selectLocation = new Select(getWebElement("locationDropdown"));
							selectLocation.selectByIndex(k);
							Select select = new Select(getWebElement("classificationDropdown"));
							String expectedDescription = classCodeNumber;
							List<WebElement> allClassCodes = select.getOptions();
							for (WebElement option : allClassCodes) {
								String currentClassCode = option.getText();
								if (currentClassCode.contains(expectedDescription)) {
									select.selectByVisibleText(currentClassCode);
								}
							}
						}
						type(getWebElement("exposureTextBox"), exposureAmount);
						getWebElement("exposureTextBox").sendKeys(Keys.TAB);
						wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
						wait.until(ExpectedConditions.visibilityOf(getWebElement("saveClassificationButton")));
						wait.until(ExpectedConditions.elementToBeClickable(getWebElement("saveClassificationButton")));
						try {
							sleep(8000);
							clickUsingJS("saveClassificationButton");
							sleep(2000);
						} catch (Exception e) {
							try {
								clickUsingJS("closeButton");
							} catch (NoAlertPresentException f) {
								f.printStackTrace();
							}
						}
						if (isWebElementPresent("loader")) {
							wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
						}
					}
					driver.switchTo().window(mainwindow);
				}
			}
		}
		clickUsingJS("nextButtonBottom");
	}

}
