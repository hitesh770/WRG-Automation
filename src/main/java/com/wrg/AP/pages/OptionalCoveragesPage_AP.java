package com.wrg.AP.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.STRING;

import org.apache.poi.poifs.crypt.dsig.KeyInfoKeySelector;
import org.bson.codecs.ValueCodecProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class OptionalCoveragesPage_AP extends AbstractTest {
	WebDriverWait wait = null;
   

	public int verifyOptionalCoveragePageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getOptionalCoverageUIElementList();
		      
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
		explicitwaitForElementVisibility(getWebElement("coverage1")); 
		clickUsingJS("coverage1");
		explicitwaitForElementVisibility(getWebElement("coverage1dropdown")); 
		selectByOption(getWebElement("coverage1dropdown"),getData("coverage1dropdownvalue"));  
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage1textfield"));
		type(getWebElement("coverage1textfield"),getData("coverage1textfieldvalue"));
		getWebElement("coverage1textfield").sendKeys(Keys.TAB); 
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage2"));
		clickUsingJS("coverage2");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage2addbuton"));
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
		explicitwaitForElementVisibility(getWebElement("coverage4")); 
		clickUsingJS("coverage4");
		waitforrunningLoadingicon();
		if(getData("scheduleinformationnow").equalsIgnoreCase("Yes")) {
			explicitwaitForElementVisibility(getWebElement("coverage4yesradiobtn")); 
			scrollToElement("coverage4yesradiobtn");
			clickUsingJS("coverage4yesradiobtn");
			waitforrunningLoadingicon();
			explicitwaitForElementVisibility(getWebElement("coverage4addbtn")); 
			clickUsingJS("coverage4addbtn"); 
			explicitwaitForElementVisibility(getWebElement("coverage4Yesoptiontextfield")); 
			type(getWebElement("coverage4Yesoptiontextfield"),getData("coverage4Yesoptiontextfieldvalue"));
			getWebElement("coverage4Yesoptiontextfield").sendKeys(Keys.TAB); 
			explicitwaitForElementVisibility(getWebElement("coverage4savebutton")); 
			clickUsingJS("coverage4savebutton");
			waitforrunningLoadingicon();
			explicitwaitForElementVisibility(getWebElement("coverage5"));
			clickUsingJS("coverage5");
			waitforrunningLoadingicon();
			explicitwaitForElementVisibility(getWebElement("coverage5dropdown"));
			scrollToElement("coverage5dropdown");
			selectByOption(getWebElement("coverage5dropdown"),getData("coverage5dropdownvalue")); 
			waitforrunningLoadingicon();
			//waitForPageLoaded();
			explicitwaitForElementVisibility(getWebElement("coverage5textfieldinyescase")); 
			type(getWebElement("coverage5textfieldinyescase"),getData("coverage5textfieldinyescasevalue"));
			getWebElement("coverage5textfieldinyescase").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
		}else {
			explicitwaitForElementVisibility(getWebElement("coverage4Nooptiontextfield")); 
			scrollToElement("coverage4Nooptiontextfield");
			type(getWebElement("coverage4Nooptiontextfield"),getData("coverage4Nooptiontextfieldvalue"));
			getWebElement("coverage4Nooptiontextfield").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
			explicitwaitForElementVisibility(getWebElement("coverage5"));
			clickUsingJS("coverage5");
			waitforrunningLoadingicon();
			explicitwaitForElementVisibility(getWebElement("coverage5dropdown"));
			scrollToElement("coverage5dropdown");
			selectByOption(getWebElement("coverage5dropdown"),getData("coverage5dropdownvalue")); 
			waitforrunningLoadingicon();
			//waitForPageLoaded();
			explicitwaitForElementVisibility(getWebElement("coverage5textfield")); 
			type(getWebElement("coverage5textfield"),getData("coverage5textfieldvalue"));
			getWebElement("coverage5textfield").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
		}

		//waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("coverage6"));
		clickUsingJS("coverage6");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage6dropdown"));
		scrollToElement("coverage6dropdown");
		selectByOption(getWebElement("coverage6dropdown"),getData("coverage6dropdownvalue")); 
		waitforrunningLoadingicon();
		waitForPageLoaded();
			explicitwaitForElementVisibility(getWebElement("coverage6textfield")); 
			type(getWebElement("coverage6textfield"),getData("coverage6textfieldvalue"));
			getWebElement("coverage6textfield").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
			
		
		
		
	}
	
	
	
	
	public void chooseSeventoNineOptionalCoverages(String insuredName,String state,String addressLine1,String city,String zipcode) {
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("additionalinsuredsectionexpandbtn")); 
		clickUsingJS("additionalinsuredsectionexpandbtn");
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("coverage7"));  
		clickUsingJS("coverage7");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage7yesradiobtn")); 
		clickUsingJS("coverage7yesradiobtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage7addbtn")); 
		clickUsingJS("coverage7addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("additonalinsuredstatefield"));
		selectByOption(getWebElement("additonalinsuredstatefield"),state); 
		explicitwaitForElementVisibility(getWebElement("additonalinsuredlocationofcoveredoperationsfield")); 
		type("additonalinsuredlocationofcoveredoperationsfield", getData("additonalinsuredlocationofcoveredoperationsfieldvalue")); 
		getWebElement("additonalinsuredlocationofcoveredoperationsfield").sendKeys(Keys.TAB);
		submitaddtionalInsuredDetails();
		explicitwaitForElementVisibility(getWebElement("coverage8")); 
		clickUsingJS("coverage8");
		waitforrunningLoadingicon();
		scrollToElement("coverage8");  
		explicitwaitForElementVisibility(getWebElement("coverage8yesradiobtn")); 
		clickUsingJS("coverage8yesradiobtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage8addbtn")); 
		clickUsingJS("coverage8addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("coverage8statefield"));
		selectByOption(getWebElement("coverage8statefield"),state); 
		submitaddtionalInsuredDetails();
    	explicitwaitForElementVisibility(getWebElement("coverage9")); 
		clickUsingJS("coverage9");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage9yesradiobtn")); 
		clickUsingJS("coverage9yesradiobtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage9addbtn")); 
		clickUsingJS("coverage9addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("coverage9statefield"));
		selectByOption(getWebElement("coverage9statefield"),state); 
		submitaddtionalInsuredDetails();
	
	
	}
	
	
	
	public void chooseTentoTwelveOptionalCoverages(String insuredName,String state,String addressLine1,String city,String zipcode) {
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("additionalinsuredsectionexpandbtn")); 
		clickUsingJS("additionalinsuredsectionexpandbtn");
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("coverage10"));  
		clickUsingJS("coverage10");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage10yesbtn")); 
		clickUsingJS("coverage10yesbtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage10addbtn")); 
		clickUsingJS("coverage10addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("additonalinsuredstatefield"));
		selectByOption(getWebElement("additonalinsuredstatefield"),state); 
		explicitwaitForElementVisibility(getWebElement("additonalinsuredlocationofcoveredoperationsfield")); 
		type("additonalinsuredlocationofcoveredoperationsfield", getData("additonalinsuredlocationofcoveredoperationsfieldvalue")); 
		getWebElement("additonalinsuredlocationofcoveredoperationsfield").sendKeys(Keys.TAB);
		submitaddtionalInsuredDetails();
		explicitwaitForElementVisibility(getWebElement("coverage11")); 
		clickUsingJS("coverage11");
		waitforrunningLoadingicon();
		scrollToElement("coverage11");  
		explicitwaitForElementVisibility(getWebElement("coverage11yesbtn")); 
		clickUsingJS("coverage11yesbtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage11addbtn")); 
		clickUsingJS("coverage11addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("coverage11statefield"));
		selectByOption(getWebElement("coverage11statefield"),state); 
		submitaddtionalInsuredDetails();
    	explicitwaitForElementVisibility(getWebElement("coverage12")); 
		clickUsingJS("coverage12");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage12yesbtn")); 
		clickUsingJS("coverage12yesbtn");
		waitforrunningLoadingicon();
		sleep(4000); 
		explicitwaitForElementVisibility(getWebElement("coverage12addbtn")); 
		clickUsingJS("coverage12addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("coverage12statefield"));
		selectByOption(getWebElement("coverage12statefield"),state);   
		submitaddtionalInsuredDetails();
	
	
	}
	
	
	
	public void chooseThirteentoFifteenOptionalCoverages(String insuredName,String state,String addressLine1,String city,String zipcode) {
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("additionalinsuredsectionexpandbtn")); 
		clickUsingJS("additionalinsuredsectionexpandbtn");
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("coverage13")); 
		scrollToElement("coverage13");  
		clickUsingJS("coverage13");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage13yesbtn")); 
		clickUsingJS("coverage13yesbtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage13addbtn")); 
		clickUsingJS("coverage13addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("coverage13statefield"));
		selectByOption(getWebElement("coverage13statefield"),state); 
		submitaddtionalInsuredDetails();
		explicitwaitForElementVisibility(getWebElement("coverage14")); 
		clickUsingJS("coverage14");
		waitforrunningLoadingicon();
		scrollToElement("coverage14");  
		explicitwaitForElementVisibility(getWebElement("coverage14yesbtn")); 
		clickUsingJS("coverage14yesbtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage14addbtn")); 
		clickUsingJS("coverage14addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("coverage14statefield"));
		selectByOption(getWebElement("coverage14statefield"),state); 
		explicitwaitForElementVisibility(getWebElement("coverage14equipmentfield"));
		selectByOption(getWebElement("coverage14equipmentfield"),getData("coverage14equipmentfieldvalue")); 
		submitaddtionalInsuredDetails();
    	explicitwaitForElementVisibility(getWebElement("coverage15")); 
		clickUsingJS("coverage15");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage15yesbtn")); 
		clickUsingJS("coverage15yesbtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage15addbtn")); 
		clickUsingJS("coverage15addbtn");
		waitforrunningLoadingicon();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("coverage15statefield"));
		selectByOption(getWebElement("coverage15statefield"),state); 
		explicitwaitForElementVisibility(getWebElement("additonalinsuredokbtn")); 
		click("additonalinsuredokbtn");
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("additonalinsureduseverifiedbtn")); 
		click("additonalinsureduseverifiedbtn");
		waitForPageLoaded();
	
	}
	
	
	public void chooseSixteentoEighteenOptionalCoverages(String insuredName,String state,String addressLine1,String city,String zipcode) {
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("additionalinsuredsectionexpandbtn")); 
		clickUsingJS("additionalinsuredsectionexpandbtn");
		waitForPageLoaded();
		explicitwaitForElementVisibility(getWebElement("coverage16")); 
		scrollToElement("coverage16");  
		clickUsingJS("coverage16");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage17")); 
		clickUsingJS("coverage17");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage18")); 
		clickUsingJS("coverage18");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage18yesbtn")); 
		clickUsingJS("coverage18yesbtn");
		waitforrunningLoadingicon();
		explicitwaitForElementVisibility(getWebElement("coverage18addbtn")); 
		clickUsingJS("coverage18addbtn");
		waitForPageLoaded();
		enteraddtionalInsuredDetails(insuredName,addressLine1,city,zipcode);
		explicitwaitForElementVisibility(getWebElement("additonalinsuredstatefield"));
		selectByOption(getWebElement("additonalinsuredstatefield"),state); 
		explicitwaitForElementVisibility(getWebElement("additonalinsuredlocationofcoveredoperationsfield")); 
		type("additonalinsuredlocationofcoveredoperationsfield", getData("additonalinsuredlocationofcoveredoperationsfieldvalue")); 
		getWebElement("additonalinsuredlocationofcoveredoperationsfield").sendKeys(Keys.TAB);
		submitaddtionalInsuredDetails();
		
	}
	
	
	public void sendkeytofield(String addressLine1,String city,String zipcode) {
		try {
		Actions act=new Actions(driver);
		act.sendKeys(addressLine1)
		.sendKeys(Keys.TAB)
		.sendKeys(Keys.TAB)
		.sendKeys(city)
		.sendKeys(Keys.TAB) 
		.sendKeys(Keys.TAB)
		.sendKeys(zipcode) 
		.sendKeys(Keys.TAB).build().perform();

		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
	public void enteraddtionalInsuredDetails(String insuredName,String addressLine1,String city,String zipcode) {
		
		explicitwaitForElementVisibility(getWebElement("additonalinsurednametextfield")); 
		type("additonalinsurednametextfield", insuredName); 
		getWebElement("additonalinsurednametextfield").sendKeys(Keys.TAB); 
		sendkeytofield(addressLine1,city,zipcode);
		
	
		
	}
	
	
	public void submitaddtionalInsuredDetails() {
		
		explicitwaitForElementVisibility(getWebElement("additonalinsuredokbtn")); 
		clickUsingJS("additonalinsuredokbtn");
		waitForPageLoaded();
		if(isWebElementPresent("additionalinsuredfieldmessages")) {
			
			Iterator<WebElement> fieldmsgIterator=getaddtionalInsuredFieldMessages().iterator();
			while(fieldmsgIterator.hasNext()) {
				WebElement message=fieldmsgIterator.next();
				String msgtext=message.getText();
				if(msgtext.equalsIgnoreCase("This is a required field") || msgtext.contains("ZIP Code must be five or nine digits")) {
					 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Following validations appear for the given input, exiting from the system " +msgtext,ExtentColor.RED));
					
				}
				
				
			}// while end
			
			getaddtionalInsuredFieldNames();
		} // outer if end
		
		
		try {
		if(isWebElementPresent("additonalinsureduseverifiedbtn")) {
			explicitwaitForElementVisibility(getWebElement("additonalinsureduseverifiedbtn")); 
			clickUsingJS("additonalinsureduseverifiedbtn");
			
		}else {
			explicitwaitForElementVisibility(getWebElement("additonalinsureduseorginalbtn")); 
			clickUsingJS("additonalinsureduseorginalbtn");
		}
		}catch(Exception e) {
			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				String text=getWebElementText("additionalinsuredinvalidstatemessage");
				 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Following validations appear for the given input " +text,ExtentColor.RED));
				 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Test case failed due to validation error ",ExtentColor.RED));
				 explicitwaitForElementVisibility(getWebElement("mailverficationclosebtn")); 
				 clickUsingJS("mailverficationclosebtn");
			}
			driver.switchTo().window(mainwindow);
			
			
		}
		
		waitForPageLoaded();
		waitforrunningLoadingicon();
	 
	}
	
	public void getaddtionalInsuredFieldNames() {
		List<WebElement> fieldnames=getWebElements("additionalinsuredfieldnames");
		 for(WebElement field:fieldnames) {
			  String name=field.getText();
			  ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Field name is: '" +name+  "' ",ExtentColor.RED));
			 // System.out.print(" || ");
			  
		  }
		
		
	}
	
	public List<WebElement> getaddtionalInsuredFieldMessages() {
		List<WebElement> fieldmessages=getWebElements("additionalinsuredfieldmessages");
		return fieldmessages;
	}
	
	/*
	public String chooseState(String state) {
	String selectstate="";
		List<String> stateList=new ArrayList<String>();
	stateList.add("Indiana");
	stateList.add("Ohio");  
	for(String statename:stateList) {
		if(statename.equalsIgnoreCase(state)) {
			selectstate=statename;
			break;
		}
		
	}
		return selectstate;
	}
	*/
	
	
	
	
	public String getQuotePageText() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String quotesubmitmessage="";
		explicitwaitForElementVisibility(getWebElement("optionalCoveragesHeading")); 
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("quoteButton"));
		explicitwaitForElementVisibility(getWebElement("quoteButton")); 
		clickUsingJS("quoteButton");
		waitForPageLoaded();
		
		
		 if (isWebElementPresent("creatingQuoteLoader1") == false) {
			 Iterator<WebElement> fieldmsgIterator=getaddtionalInsuredFieldMessages().iterator();
				while(fieldmsgIterator.hasNext()) {
					WebElement message=fieldmsgIterator.next();
					String msgtext=message.getText();
					if(msgtext.equalsIgnoreCase("This is a required field") || msgtext.contains("ZIP Code must be five or nine digits") || msgtext.equalsIgnoreCase("Must be a Number")) {			
						ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Following validations appear for the given input: " +msgtext,ExtentColor.RED));
						
					}
	
				}// while end
				getaddtionalInsuredFieldNames();
				return  quotesubmitmessage="Test failed due to validation errors";		  
 }
		 

		if (isWebElementPresent("creatingQuoteLoader1") == true) {
			sleep(9000); 
		}

		if (isWebElementPresentAfterWait("notesToUnderwriter")) { 
			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(getWebElement("notesToUnderwriter"), "testing");
				click("sendForUnderwritingReviewButton");
				waitForPageLoaded();
				explicitwaitForElementVisibility(getWebElement("quotesubmissionconfirmationmessage"));
				quotesubmitmessage=getWebElementText("quotesubmissionconfirmationmessage");  
				explicitwaitForElementVisibility(getWebElement("okButton"));
				clickUsingJS("okButton");
		
			}
			driver.switchTo().window(mainwindow);
		}else {		
			Iterator<WebElement> fieldmsgIterator=getaddtionalInsuredFieldMessages().iterator();
			while(fieldmsgIterator.hasNext()) {
				WebElement message=fieldmsgIterator.next();
				String msgtext=message.getText();
				if(msgtext.equalsIgnoreCase("This is a required field") || msgtext.contains("ZIP Code must be five or nine digits") || msgtext.equalsIgnoreCase("Must be a Number")) {			
					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Following validations appear for the given input: " +msgtext,ExtentColor.RED));
					
				}

			}// while end
			getaddtionalInsuredFieldNames();
			return  quotesubmitmessage="Test failed due to validation errors";
	
			
		}
	
		return quotesubmitmessage;
	} 
	

	

	 public void waitforrunningLoadingicon() {  

		  int count=0;
	    	 if (isWebElementPresent("runningloadingicon") == true) { 
	    		  List<WebElement> runningiconlist=getWebElements("runningloadingicon");
	    			while(runningiconlist.size()!=0 && count<4) {
	    				sleep(1000); 
	    				count++;
	    			}  
	    			  
	    	 }
	    	 
			
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
	
	public void optionalCoveragesToolTipValidation(String classCodeNumber, String coverageNumber,String tooltipText) {
		waitForPageLoaded();
		wait = new WebDriverWait(driver, 30);
		String tooltip = null;
		
		if(coverageNumber =="CG2033") {
			clickUsingJS("AdditionalInsured");
			wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2033")));
			sleep(2000);
			scrollToElement("CG2033HelpIcon");
			actionClick("CG2033HelpIcon");
			//sleep(4000);
			wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2293Tooltip")));
			tooltip = getWebElementText("CG2293Tooltip");
			System.out.print(tooltip);
			if (tooltipText.contains(tooltip)) {					
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 33 Tooltip -> " + tooltip,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 33 Tooltip -> "+ tooltip,
								ExtentColor.RED));
			}
			actionClick("CG2033Coverages");
			if (isWebElementPresentAfterWait("CG2033HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 33 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 33 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			sleep(2000);
			clickUsingJS("CG2033Coverages");
			if (isWebElementPresentAfterWait("CG2033HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 33 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 33 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			quitDriver(driver);
		}
		else if(coverageNumber =="CG2034") {
			clickUsingJS("AdditionalInsured");
			wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2034")));
			sleep(2000);
			scrollToElement("CG2034HelpIcon");
			actionClick("CG2034HelpIcon");
			//sleep(4000);
			wait.until(ExpectedConditions.visibilityOf(getWebElement("CG2293Tooltip")));		
			tooltip = getWebElementText("CG2293Tooltip");
			System.out.print(tooltip);
			if (tooltipText.contains(tooltip)) {					
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 34 Tooltip -> " + tooltip,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 34 Tooltip -> "+ tooltip,
								ExtentColor.RED));
			}
			actionClick("CG2034Coverages");
			if (isWebElementPresentAfterWait("CG2034HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 34 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 34 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			sleep(2000);
			clickUsingJS("CG2034Coverages");
			if (isWebElementPresentAfterWait("CG2034HelpIcon")) {
				ExtentTestManager.getTest().log(Status.INFO,
						MarkupHelper.createLabel(
								"CG 20 34 Help Icon -> is present" ,
								ExtentColor.GREEN));
			}
			else {
				ExtentTestManager.getTest().log(Status.FAIL,
						MarkupHelper.createLabel(
								"CG 20 34 Help Icon -> is not present" ,
								ExtentColor.RED));
			}
			quitDriver(driver);
		}
	}
	
	
	//function to verify portal wizard menus 
	
			public boolean viewwizardmenusonOptionalCoverageUI() {
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
				 
				
				 
				 if(getWebElementColor("portaleightwizardmenu", "color").equals("#bbbdbf") && getWebElementColor("portalninewizardmenu", "color").equals("#bbbdbf")) {
					 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(getWebElementText("portaleightwizardmenu")+ " and " +getWebElementText("portalninewizardmenu")+ " color is  grey and appear as disabled ",ExtentColor.BLUE)); 
					 flag=true;
				 }else {
					 return flag=false;   
				 }
				 
				 return flag;
			}
		
	
}
