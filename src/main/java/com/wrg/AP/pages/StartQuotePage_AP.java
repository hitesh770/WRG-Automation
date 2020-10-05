package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class StartQuotePage_AP extends AbstractTest {
	WebDriverWait wait = null;

	
	public int verifyStartQuotePageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getStartQuoteUIElementList();
		      
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
		
		public  List<String> getStartQuoteUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("startquoteuilabels"));
			totalelements.add(getData("startquoteuiinputfields"));
			totalelements.add(getData("startquoteuibuttons"));
			return totalelements;
		}
		
		
		public int getStartQuoteUIElementTotalCount() {
			return Integer.parseInt(getData("startquoteuielements"));	
		}
		
	
		public List<WebElement> getCurrentList(String data, boolean... optional) {
			List<WebElement> we = null;
			try {
				we = driver.findElements(By.xpath(data));
			} catch (Exception e) {
				e.printStackTrace();
				if (optional[0] != true) {
					throwElementNotPresentException(e, data, optional[0]);
				}
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
		
	
	
	
	public void addClassification(String classCodeNumber) {
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (classCodeNumber.contains(",")) {
			String spiltClassCodes[] = classCodeNumber.split(",");
			List<String> classCodes = new ArrayList<String>();
			for (String classCode : spiltClassCodes) {
				classCodes.add(classCode);
				Actions action = new Actions(driver);
				action.moveToElement(getWebElement("addClassificationButton")).build().perform();;
				click("addClassificationButton");

				String mainwindow = driver.getWindowHandle(); // get parent(current) window name
				for (String popup : driver.getWindowHandles()) // iterating on child windows
				{
					driver.switchTo().window(popup);
					type(("classCodeNumber"), classCode);
					clickUsingJS("classCode");

					clickUsingJS("okButton");
					if (isWebElementPresent("hazardCode") == true) {
						clickUsingJS("hazardCode");
						clickUsingJS("okButtonForHazardCode");
					}
				}
				driver.switchTo().window(mainwindow);

			}
		} else {
			clickUsingJS("addClassificationButton");

			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(("classCodeNumber"), classCodeNumber);
				clickUsingJS("classCode");

				clickUsingJS("okButton");
				if (isWebElementPresent("hazardCode") == true) {
					clickUsingJS("hazardCode");
					clickUsingJS("okButtonForHazardCode");
				}
			}

			driver.switchTo().window(mainwindow);
		}
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		clickUsingJS("nextButton");
		if(isWebElementPresent("okButtonForUnderwritingReferralRequired")==true) {
			clickUsingJS("okButtonForUnderwritingReferralRequired");
		}
	}
	public void addClassificationScreenvalidation() {
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForPageLoaded();
		clickUsingJS("previousButton");
		sleep(2000);
		if (isWebElementPresentAfterWait("addClassificationButton")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Add Classification button is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Add Classification button is present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("glClassCodeLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"GL Class Code Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"GL Class Code Label is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("classificationLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Classification Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Classification Label is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("propertyLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Property Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Property Label is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("premopsLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Prem Ops Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Prem ops Label is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("prodscompletedLabel")){
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Prods Completed Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Prods Completed Label is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("wcLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"WC Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"WC Label is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("autoLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
 					MarkupHelper.createLabel(
							"Auto Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Auto Label is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("generallyAcceptableLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Generally Acceptable Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Generally Acceptable Label is not present" ,
							ExtentColor.RED));
		}
		if(isWebElementPresentAfterWait("generallyUnacceptableLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Generally Unacceptable Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Generally Unacceptable Label is not present" ,
							ExtentColor.RED));
		}
		if (isWebElementPresentAfterWait("notAcceptableLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"No Acceptable Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"No Acceptable Label is not present" ,
							ExtentColor.RED));
		}
	}
	public void deleteClassifications() {
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForPageLoaded();
		int count = 0;
		count = getWebElements("classificationcount").size();
		clickUsingJS("deleteClassficationButton");
		sleep(2000);
		if(isWebElementPresentAfterWait("removeClassifcationLabel")) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Remove Classification Label is present" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Remove Classification Label is not present" ,
							ExtentColor.RED));
		}
		clickUsingJS("deleteButton");
		sleep(2000);
		if (count>getWebElements("classificationcount").size()) {
			ExtentTestManager.getTest().log(Status.PASS,
					MarkupHelper.createLabel(
							"Classification is deleted successfully" ,
							ExtentColor.GREEN));
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MarkupHelper.createLabel(
							"Classification is not deleted successfully" ,
							ExtentColor.RED));
		}
		
	}

}
