package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class UnderwritingInfoAndApplicationPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	
	public int verifyUnderwritingInfoPageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getUnderwritingInfoUIElementList();
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
		
		public  List<String> getUnderwritingInfoUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("underwritingeuilabels"));
			totalelements.add(getData("underwritinguiinputfields"));
			totalelements.add(getData("underwritinguibuttons"));
			return totalelements;
		}
		
		
		public int getUnderwritingInfoUIElementTotalCount() {
			return Integer.parseInt(getData("underwritinguielements"));	
		}
		
	
	
	
	public boolean isWebElementPresentInSupplemental(String element) {
		driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);
		if (getWebElements(element).size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	public void clickIfElementIsDisplayed(String element) {
		if (isWebElementPresentInSupplemental(element) == true) {
			clickUsingJS(element);
		}
	}

	public void enterPhoneNumber() {
		wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("underwritingInfoPageValidation")));
		sleep(3000);
		if (isWebElementPresent("phoneNumber") == true) {
			type(("phoneNumber"), getData("phoneNumberValue"));
		}
	}

	public void answerQuestions() {
		wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("underwritingInfoPageValidation")));
		sleep(3000);
		if (isWebElementPresent("phoneNumber") == true) {
			clear("phoneNumber");
			type(("phoneNumber"), getData("phoneNumberValue"));
		}
		clickIfElementIsDisplayed("bankruptcyNO");
		clickIfElementIsDisplayed("policyDeclinedNO");
		clickIfElementIsDisplayed("otherBusinessNO");
		clickIfElementIsDisplayed("allegationsNO");
		clickIfElementIsDisplayed("continuousBusinessInsuranceYES");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement("currentOrMostRecentCarrier"));
		selectByOption(getWebElement("currentOrMostRecentCarrier"), getData("currentOrMostRecentCarrierValue"));
		clickIfElementIsDisplayed("lossesNO");
		clickIfElementIsDisplayed("businessInOtherStatesNO");
		clickIfElementIsDisplayed("buildingConvertedFromDwellingNO");
		clickIfElementIsDisplayed("75%AreaOccupiedYES");
		clickIfElementIsDisplayed("repairTypeOperationsNO");
		clickIfElementIsDisplayed("heatingDevicesNO");
		clickIfElementIsDisplayed("internetSalesNO");
		clickIfElementIsDisplayed("smokeDetectorPresentYES");
		clickIfElementIsDisplayed("buildingExceed2.5StoriesNO");
		clickIfElementIsDisplayed("moreThan12UnitsNO");
		clickIfElementIsDisplayed("distanceLessThan100FeetNO");
		clickIfElementIsDisplayed("multiFamilyOccupancyNO");
		clickIfElementIsDisplayed("75%AboveUnitsOccupiedYES");
		clickIfElementIsDisplayed("ownerResideNearPremisesYES");
		clickIfElementIsDisplayed("section8NO");
		clickIfElementIsDisplayed("recreationalFacilitiesNO");
		clickIfElementIsDisplayed("franchiseHotelNO");
		clickIfElementIsDisplayed("hotelClosedMoreThan30DaysNO");
		clickIfElementIsDisplayed("averageRoomRateAbove100$NO");
		clickIfElementIsDisplayed("exteriorEntryDoorsYES");
		clickIfElementIsDisplayed("managerOnDutyYES");
		clickIfElementIsDisplayed("averageOccupancyRateYES");
		clickIfElementIsDisplayed("hotelHaveBarNO");
		clickIfElementIsDisplayed("hotelHaveSwimmingPoolNO");
		clickIfElementIsDisplayed("hotelExceeding3StoriesYES");
		clickIfElementIsDisplayed("childrenPlayAreaNO");
		clickIfElementIsDisplayed("seatingAbove75NO");
		clickIfElementIsDisplayed("annualPayrollExceed50000NO");
		clickIfElementIsDisplayed("workCompletedOverThreeStoriesNO");
		clickIfElementIsDisplayed("equipmentRentedToOtherNO");
		clickIfElementIsDisplayed("salesFromRetailOperationsYES");
		clickIfElementIsDisplayed("floorAreaOpenToPublicNO");
		clickIfElementIsDisplayed("allSuppliersBasedInUSYES");
		clickIfElementIsDisplayed("applicantAlterGoodsNO");
		clickIfElementIsDisplayed("packageFoodUnderOwnLabelYES");
		clickIfElementIsDisplayed("explosiveSalesNO");
		clickIfElementIsDisplayed("treeTrimmingOrRemovalNO");
		clickIfElementIsDisplayed("applicantSubcontractNO");
		clickIfElementIsDisplayed("seatingAbove150NO");
		clickIfElementIsDisplayed("dancingPermittedNO");
		clickIfElementIsDisplayed("liveEntertainmentNO");
		clickIfElementIsDisplayed("UL300CompliantYES");
		clickIfElementIsDisplayed("semiAnnualContractForCleaningYES");
		clickIfElementIsDisplayed("fireSuspensionSystemInspectinoYES");
		clickIfElementIsDisplayed("businessClosedMoreThan30DaysNO");
		clickIfElementIsDisplayed("cateringServiceSalesYES");
		clickIfElementIsDisplayed("solidFuelCookingNO");
		clickIfElementIsDisplayed("liquorOtherThanBeerOrWineNO");
		clickIfElementIsDisplayed("alcoholServedAfterKitchenClosedNO");
	}

	public void isSeatingAbove75YES() {
		clickIfElementIsDisplayed("seatingAbove75YES");
	}

	public void verifyApplicationPrint() {
		wait = new WebDriverWait(driver, 120);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.urlContains("qnb"));
		clickUsingJS("printApplicationButton");
		sleep(10000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window(tabs.get(1));
		waitForPageLoaded();
		wait.until(ExpectedConditions.titleContains("printApplication"));
		System.out.println(driver.getTitle());
		driver.close();
		driver.switchTo().window(tabs.get(0));

	}

	public void glNonEplQuestions() {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		sleep(3000);
		if (isWebElementPresent("phoneNumber") == true) {
			clear("phoneNumber");
			type(("phoneNumber"), getData("phoneNumberValue"));
		}
		clickIfElementIsDisplayed("otherBusinessNO");
		if(isWebElementPresent("selectYearsInBusiness")==true) {
		selectByIndex(getWebElement("selectYearsInBusiness"), 2);
		}
		clickIfElementIsDisplayed("continuousBusinessInsuranceYES");
		if(isWebElementPresent("currentOrMostRecentCarrierForGL")==true) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement("currentOrMostRecentCarrierForGL"));
		selectByOption(getWebElement("currentOrMostRecentCarrierForGL"), getData("currentOrMostRecentCarrierValue"));
		}
		
		clickIfElementIsDisplayed("internetSalesNO");
		clickIfElementIsDisplayed("productsMarketedToYouthYES");
		clickIfElementIsDisplayed("productDesignNo");
		clickIfElementIsDisplayed("visitorsAllowedNO");
	}

	public void clickBeginSubmissionBtn() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement("beginSubmissionButton"));
		clickUsingJS("beginSubmissionButton");
		sleep(2000);
	}

	public void beginSubmission() {
		wait = new WebDriverWait(driver, 120);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (isWebElementPresentAfterWait("loader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
		}
		String mainwindow = driver.getWindowHandle(); // get parent(current) window name
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);

			click("sendForUnderwritingReviewButton");
			wait.until(ExpectedConditions.visibilityOf(getWebElement("okButton")));
			clickUsingJS("okButton");
		}
		driver.switchTo().window(mainwindow);
	}
}
