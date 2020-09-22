package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.util.ContinuedFraction;
import org.checkerframework.common.value.qual.IntRange;
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

public class ApplicantInformationPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void fillApplicantDetails(String state, String businessEntity, String classCodeNumber) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		enterAddress(state,businessEntity);
		if(classCodeNumber.contains(",")) {
			String spiltClassCodes[] = classCodeNumber.split(",");
			List<String> classCodes = new ArrayList<String>();
			for(String classCode:spiltClassCodes) {
			classCodes.add(classCode);
			
		click("addClassificationButton");

		String mainwindow = driver.getWindowHandle(); // get parent(current) window name
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			type(("classCodeNumber"), classCode);
			click("classCode");

			clickUsingJS("okButton");
			if (isWebElementPresent("hazardCode") == true) {
				clickUsingJS("hazardCode");
				clickUsingJS("okButtonForHazardCode");
			}
		}
		driver.switchTo().window(mainwindow);
			}
		}else {
			click("addClassificationButton");

			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(("classCodeNumber"), classCodeNumber);
				click("classCode");

				clickUsingJS("okButton");
				if (isWebElementPresent("hazardCode") == true) {
					clickUsingJS("hazardCode");
					clickUsingJS("okButtonForHazardCode");
				}
			}
			driver.switchTo().window(mainwindow);
		}
		clickUsingJS("nextButton");
		sleep(2000);
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			try {
				clickUsingJS("useverifiedButton");
				}catch(Exception e) {
					e.getMessage();
				}
		}

	}
	
	public void enterAddress(String state,String businessEntity) {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		wait.until(ExpectedConditions.urlContains("qnb-flow"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("producerCodeDropdown")));
		if (isWebElementPresent("effectiveDate") == true) {
			String effectveDateValue = getWebElementText("effectiveDate");
			type(("effectiveDate"), effectveDateValue);
		}
		selectByOption(getWebElement("producerCodeDropdown"), "FORWARD AGENCIES INC (000)");
		if (state.equalsIgnoreCase("Indiana")) {
			
			type(("addressLine1"), getData("indianaAddress"));
			type(("city"), getData("indianaCityName"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("state"));
			selectByOption(getWebElement("state"), state);
			type(("zipCode"), getData("indianaPincodeValue"));
		} else if (state.equalsIgnoreCase("Ohio")) {
			type(("addressLine1"), getData("ohioAddress"));
			type(("city"), getData("ohioCityName"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("state"));
			selectByOption(getWebElement("state"), state);
			type(("zipCode"), getData("ohioPincodeValue"));
		}
		selectByOption(getWebElement("businessEntityType"), businessEntity);
		type(("description"), getData("descriptionValue"));

	}
	public void enterAddress(String state) {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		wait.until(ExpectedConditions.urlContains("qnb-flow"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("producerCodeDropdown")));
		/*if (isWebElementPresent("effectiveDate") == true) {
			String effectveDateValue = getWebElementText("effectiveDate");
			type(("effectiveDate"), effectveDateValue);
		}
		selectByOption(getWebElement("producerCodeDropdown"), "FORWARD AGENCIES INC (000)");*/
		if (state.equalsIgnoreCase("Indiana")) {
			
			type(("isaddressLine1"), getData("indianaAddress"));
			type(("iscity"), getData("indianaCityName"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("state"));
			//selectByOption(getWebElement("state"), state);
			type(("iszipCode"), getData("indianaPincodeValue"));
		} else if (state.equalsIgnoreCase("Ohio")) {
			type(("isaddressLine1"), getData("ohioAddress"));
			type(("iscity"), getData("ohioCityName"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("state"));
			//selectByOption(getWebElement("state"), state);
			type(("iszipCode"), getData("ohioPincodeValue"));
		}
		//selectByOption(getWebElement("businessEntityType"), businessEntity);
		//type(("description"), getData("descriptionValue"));

	}
	public void mailingAddress(String isMailingAddress, String state) {
		if (isMailingAddress=="No") {
			clickUsingJS("isMailingAddressSameNo");
			enterAddress(state);
		}
	}
	
	
	public int verifyApplicationInformationPageElementsPresence() {
	int totalcount=0;
	 int currentelementcount=0;
		List<String> totalPageElements=getApplicantUIElementList();
	      
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
	
	public  List<String> getApplicantUIElementList() {
		waitForPageLoaded();
		List<String> totalelements=new ArrayList<String>();
		totalelements.add(getData("applicantuilabels"));
		totalelements.add(getData("applicantuidropdown"));
		totalelements.add(getData("applicantuiinputfields"));
		totalelements.add(getData("applicantuibuttons"));
		return totalelements;
	}
	
	
	public int getApplicantUIElementTotalCount() {
		return Integer.parseInt(getData("totalapplicantuielements"));	
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
	
	
	public void selectInsuranceType(String insuranceType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("nextButton"));
		clickUsingJS(driver.findElement(By.xpath("//label[contains(text(),'"+insuranceType+"')]/parent::div[1]/input")));
	}
	
	public void clickNextButton() {
		
		actionClick("nextButton");
		//sleep(1000);
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			try {
				clickUsingJS("useverifiedButton");
				if (isWebElementDisplayed("useverifiedButton")) {
					actionClick("useverifiedButton");
				}
			}catch(Exception e) {
				clickUsingJS("useOriginalButton");
			}
		}
	}

}
