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

}
