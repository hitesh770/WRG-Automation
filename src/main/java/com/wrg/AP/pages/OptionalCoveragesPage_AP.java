package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.STRING;

import org.apache.poi.poifs.crypt.dsig.KeyInfoKeySelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class OptionalCoveragesPage_AP extends AbstractTest {
	WebDriverWait wait = null;


	public int verifyOptionalCoveragePageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getOptionalCoverageUIElementList();
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
		
		public  List<String> getOptionalCoverageUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("optionalcoverageuilabels"));
			totalelements.add(getData("optionalcoverageuibuttons"));
			return totalelements;
		}
		
		
		public int getOptionalCoverageUIElementTotalCount() {
			return Integer.parseInt(getData("optionalcoverageuielements"));	
		}
	
	
	
	public void optionalCoverages() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("optionalCoveragesHeading")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("optionalCoveragesHeading")));
		clickUsingJS("coverage1");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("nextButton"));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("nextButton")));
		clickUsingJS("nextButton");
	}
	
	
	public void chooseFirstThreeOptionalCoverages() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		explicitwaitForElement(getWebElement("coverage1")); 
		clickUsingJS("coverage1");
		explicitwaitForElement(getWebElement("coverage1dropdown")); 
		selectByOption(getWebElement("coverage1dropdown"),getData("coverage1dropdownvalue"));  
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage1textfield"));
		type(getWebElement("coverage1textfield"),getData("coverage1textfieldvalue"));
		getWebElement("coverage1textfield").sendKeys(Keys.TAB); 
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage2"));
		clickUsingJS("coverage2");
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage2addbuton"));
		scrollToElement("coverage2addbuton");
		clickUsingJS("coverage2addbuton");
		type("coverage2textfield", getData("coverage2textfieldvalue")); 
		getWebElement("coverage2textfield").sendKeys(Keys.TAB); 
		waitForPageLoaded();
		clickUsingJS("coverage2savebutton"); 
		waitforrunningLoadingicon();
		clickUsingJS("coverage3");
	}
	
	
	
	
	
	public void chooseThreetoSixOptionalCoverages() {
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		explicitwaitForElement(getWebElement("coverage4")); 
		clickUsingJS("coverage4");
		waitforrunningLoadingicon();
		if(getData("scheduleinformationnow").equalsIgnoreCase("Yes")) {
			explicitwaitForElement(getWebElement("coverage4yesradiobtn")); 
			scrollToElement("coverage4yesradiobtn");
			clickUsingJS("coverage4yesradiobtn");
			waitforrunningLoadingicon();
			explicitwaitForElement(getWebElement("coverage4addbtn")); 
			clickUsingJS("coverage4addbtn"); 
			explicitwaitForElement(getWebElement("coverage4Yesoptiontextfield")); 
			type(getWebElement("coverage4Yesoptiontextfield"),getData("coverage4Yesoptiontextfieldvalue"));
			getWebElement("coverage4Yesoptiontextfield").sendKeys(Keys.TAB); 
			explicitwaitForElement(getWebElement("coverage4savebutton")); 
			clickUsingJS("coverage4savebutton");
			waitforrunningLoadingicon();
			explicitwaitForElement(getWebElement("coverage5"));
			clickUsingJS("coverage5");
			waitforrunningLoadingicon();
			explicitwaitForElement(getWebElement("coverage5dropdown"));
			scrollToElement("coverage5dropdown");
			selectByOption(getWebElement("coverage5dropdown"),getData("coverage5dropdownvalue")); 
			waitforrunningLoadingicon();
			//waitForPageLoaded();
			explicitwaitForElement(getWebElement("coverage5textfieldinyescase")); 
			type(getWebElement("coverage5textfieldinyescase"),getData("coverage5textfieldinyescasevalue"));
			getWebElement("coverage5textfieldinyescase").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
		}else {
			explicitwaitForElement(getWebElement("coverage4Nooptiontextfield")); 
			scrollToElement("coverage4Nooptiontextfield");
			type(getWebElement("coverage4Nooptiontextfield"),getData("coverage4Nooptiontextfieldvalue"));
			getWebElement("coverage4Nooptiontextfield").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
			explicitwaitForElement(getWebElement("coverage5"));
			clickUsingJS("coverage5");
			waitforrunningLoadingicon();
			explicitwaitForElement(getWebElement("coverage5dropdown"));
			scrollToElement("coverage5dropdown");
			selectByOption(getWebElement("coverage5dropdown"),getData("coverage5dropdownvalue")); 
			waitforrunningLoadingicon();
			//waitForPageLoaded();
			explicitwaitForElement(getWebElement("coverage5textfield")); 
			type(getWebElement("coverage5textfield"),getData("coverage5textfieldvalue"));
			getWebElement("coverage5textfield").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
		}

		//waitForPageLoaded();
		explicitwaitForElement(getWebElement("coverage6"));
		clickUsingJS("coverage6");
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage6dropdown"));
		scrollToElement("coverage6dropdown");
		selectByOption(getWebElement("coverage6dropdown"),getData("coverage6dropdownvalue")); 
		waitforrunningLoadingicon();
		waitForPageLoaded();
			explicitwaitForElement(getWebElement("coverage6textfield")); 
			type(getWebElement("coverage6textfield"),getData("coverage6textfieldvalue"));
			getWebElement("coverage6textfield").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
			//will handle later for coverage 6 field validation
		
		
		
	}
	
	
	
	
	public void chooseSeventoNineOptionalCoverages() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		explicitwaitForElement(getWebElement("coverage1")); 
		clickUsingJS("coverage1");
		explicitwaitForElement(getWebElement("coverage1dropdown")); 
		selectByOption(getWebElement("coverage1dropdown"),getData("coverage1dropdownvalue"));  
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage1textfield"));
		type(getWebElement("coverage1textfield"),getData("coverage1textfieldvalue"));
		getWebElement("coverage1textfield").sendKeys(Keys.TAB); 
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage2"));
		clickUsingJS("coverage2");
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage2addbuton"));
		scrollToElement("coverage2addbuton");
		clickUsingJS("coverage2addbuton");
		type("coverage2textfield", getData("coverage2textfieldvalue")); 
		getWebElement("coverage2textfield").sendKeys(Keys.TAB); 
		waitForPageLoaded();
		clickUsingJS("coverage2savebutton"); 
		waitforrunningLoadingicon();
		clickUsingJS("coverage3");
	}
	
	
	
	public String getQuotePageText() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String quotesubmitmessage="";
		explicitwaitForElement(getWebElement("optionalCoveragesHeading")); 
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("quoteButton"));
		explicitwaitForElement(getWebElement("quoteButton")); 
		clickUsingJS("quoteButton");
		waitForPageLoaded();
		if (isWebElementPresent("creatingQuoteLoader") == true) {
			explicitwaitForInvisibilityofElement(getWebElement("creatingQuoteLoader")); 
		}

		if (isWebElementPresentAfterWait("notesToUnderwriter")) { 
			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(getWebElement("notesToUnderwriter"), "testing");
				click("sendForUnderwritingReviewButton");
				waitForPageLoaded();
				explicitwaitForElement(getWebElement("quotesubmissionconfirmationmessage"));
				quotesubmitmessage=getWebElementText("quotesubmissionconfirmationmessage");  
				explicitwaitForElement(getWebElement("okButton"));
				clickUsingJS("okButton");
		
			}
			driver.switchTo().window(mainwindow);
		}
	
		return quotesubmitmessage;
	} 
	

	public void quote() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("optionalCoveragesHeading")));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("optionalCoveragesHeading")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("quoteButton"));
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("quoteButton")));
		clickUsingJS("quoteButton");
		sleep(1000);
		if (isWebElementPresent("creatingQuoteLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("creatingQuoteLoader")));
		}
		if (isWebElementPresentAfterWait("notesToUnderwriter")) {
			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(getWebElement("notesToUnderwriter"), "testing");

				click("sendForUnderwritingReviewButton");
				wait.until(ExpectedConditions.visibilityOf(getWebElement("okButton")));
				clickUsingJS("okButton");
			}
			driver.switchTo().window(mainwindow);
		}
	}
}
