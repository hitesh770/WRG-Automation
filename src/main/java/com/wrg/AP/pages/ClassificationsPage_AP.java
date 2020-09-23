package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class ClassificationsPage_AP extends AbstractTest {

	WebDriverWait wait = null;
	

	public int verifyClassificationPageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getClassificationUIElementList();
		      
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
		
		public  List<String> getClassificationUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("classificaionuibuttons"));
			return totalelements;
		}
		
		
		public int getClassificationUIElementTotalCount() {
			return Integer.parseInt(getData("classificaionuielements"));	
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
	public void addClassificationsToolTipValidation(String classCodeNumber, String coverageNumber,String tooltipText) {
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("classificationPageHeading")));
		sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("addNewClassificationButton")));
		clickUsingJS("addNewClassificationButton");
		String tooltip = null;
		String mainwindow = driver.getWindowHandle();
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			wait.until(ExpectedConditions.visibilityOf(getWebElement("addNewClassificationButton")));
			if (coverageNumber =="CG2293"){
				//sleep(8000);
				//typeUsingJS("Exposure", "10");
				if (isWebElementPresentAfterWait("IfAnyBasis")) {
					clickUsingJS("IfAnyBasis");
				}			
				//sleep(8000);
				if (isWebElementPresentAfterWait("PesticideLicenceYes")) {
					clickUsingJS("PesticideLicenceYes");
				}							
				sleep(8000);			
				scrollToElement("CG2293HelpIcon");
				actionClick("CG2293HelpIcon");
				//sleep(4000);
				wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2293Tooltip")));		
				tooltip = getWebElementText("CG2293Tooltip");
				if (tooltipText.contains(tooltip)) {					
					ExtentTestManager.getTest().log(Status.INFO,
							MarkupHelper.createLabel(
									"CG 22 93 Tooltip -> " + tooltip,
									ExtentColor.GREEN));
				}
				else {
					ExtentTestManager.getTest().log(Status.FAIL,
							MarkupHelper.createLabel(
									"CG 22 93 Tooltip -> "+ tooltip,
									ExtentColor.RED));
				}
				actionClick("CG2293Coverages");
				if (isWebElementPresentAfterWait("CG2293HelpIcon")) {
					ExtentTestManager.getTest().log(Status.INFO,
							MarkupHelper.createLabel(
									"CG 22 93 Help Icon -> is present" ,
									ExtentColor.GREEN));
				}
				else {
					ExtentTestManager.getTest().log(Status.FAIL,
							MarkupHelper.createLabel(
									"CG 22 93 Help Icon -> is not present" ,
									ExtentColor.RED));
				}
				sleep(2000);
				clickUsingJS("CG2293Coverages");
				if (isWebElementPresentAfterWait("CG2293HelpIcon")) {
					ExtentTestManager.getTest().log(Status.INFO,
							MarkupHelper.createLabel(
									"CG 22 93 Help Icon -> is present" ,
									ExtentColor.GREEN));
				}
				else {
					ExtentTestManager.getTest().log(Status.FAIL,
							MarkupHelper.createLabel(
									"CG 22 93 Help Icon -> is not present" ,
									ExtentColor.RED));
				}
			}
			else if(coverageNumber =="WCG2687") {
				if (isWebElementPresentAfterWait("IfAnyBasis")) {
					clickUsingJS("IfAnyBasis");
				}			
				//sleep(8000);
				if (isWebElementPresentAfterWait("PesticideLicenceYes")) {
					clickUsingJS("PesticideLicenceYes");
				}							
				sleep(8000);
				scrollToElement("WCG2687HelpIcon");
				actionClick("WCG2687HelpIcon");
				//sleep(4000);
				wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2293Tooltip")));
				tooltip = getWebElementText("CG2293Tooltip");
				//System.out.print(tooltip);
				if (tooltipText.contains(tooltip)) {					
					ExtentTestManager.getTest().log(Status.INFO,
							MarkupHelper.createLabel(
									"WCG 26 87 Tooltip -> " + tooltip,
									ExtentColor.GREEN));
				}
				else {
					ExtentTestManager.getTest().log(Status.FAIL,
							MarkupHelper.createLabel(
									"WCG 26 87 Tooltip -> "+ tooltip,
									ExtentColor.RED));
				}
				actionClick("WCG2687Coverages");
				if (isWebElementPresentAfterWait("WCG2687HelpIcon")) {
					ExtentTestManager.getTest().log(Status.INFO,
							MarkupHelper.createLabel(
									"WCG 26 87 Help Icon -> is present" ,
									ExtentColor.GREEN));
				}
				else {
					ExtentTestManager.getTest().log(Status.FAIL,
							MarkupHelper.createLabel(
									"WCG 26 87 Help Icon -> is not present" ,
									ExtentColor.RED));
				}
				sleep(2000);
				clickUsingJS("WCG2687Coverages");
				if (isWebElementPresentAfterWait("WCG2687HelpIcon")) {
					ExtentTestManager.getTest().log(Status.INFO,
							MarkupHelper.createLabel(
									"WCG 26 87 Help Icon -> is present" ,
									ExtentColor.GREEN));
				}
				else {
					ExtentTestManager.getTest().log(Status.FAIL,
							MarkupHelper.createLabel(
									"WCG 26 87 Help Icon -> is not present" ,
									ExtentColor.RED));
				}
			}
			quitDriver(driver);
			
		}
	}
	public void editClassifications(String classCodeNumber, String exposureAmount, String classficationsNumber,String numberOfLocations) {
		waitForPageLoaded();
		clickUsingJS("classificationNavigation");
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("classificationPageHeading")));
		clickUsingJS(classficationsNumber);
		String mainwindow = driver.getWindowHandle();
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			/*Select selectLocation = new Select(getWebElement("locationDropdown"));
			if (Integer.parseInt(numberOfLocations) > 1) {
				selectLocation.selectByIndex(2);
			} else {
				selectLocation.selectByIndex(1);
			}*/
			clear("exposureTextBox");
			type(getWebElement("exposureTextBox"), exposureAmount);
			getWebElement("exposureTextBox").sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
			// clickUsingJS("addNewClassificationButton");
			wait.until(ExpectedConditions.visibilityOf(getWebElement("saveClassificationButton")));
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
	public void deleteClassifications(String classCodeNumber, String exposureAmount, String classficationsNumber,String numberOfLocations) {
		waitForPageLoaded();
		clickUsingJS("classificationNavigation");
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("classificationPageHeading")));
		clickUsingJS("deleteClassification");
		if (isWebElementPresentAfterWait("deleteText")) {
			ExtentTestManager.getTest().log(Status.INFO,
					MarkupHelper.createLabel(
							"Delete Confirmation Text -> is present" ,
							ExtentColor.GREEN));
		}else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Delete Confirmation Text -> is present" ,
							ExtentColor.RED));
		}
		clickUsingJS("delete");
	}
}
