package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class UnderwritingGuidelinesPage_AP extends AbstractTest {
	WebDriverWait wait = null;
	protected static String quoteNumber;
	PolicyFormSelectionPage_AP policyFormSelectionPage = null;
	
	
	public boolean verifyUnderwritingInfoPageElementsPresence() {
		   if(isWebElementPresent("underwritingeuilabel1") && isWebElementPresent("underwritingeuilabel2") && isWebElementPresent("underwritingeuilabel3") && isWebElementPresent("underwritingeuilabel4") && isWebElementPresent("underwritingeuilabel5")
				   && isWebElementPresent("underwritingeuilabel6") && isWebElementPresent("underwritingeuilabel7") && isWebElementPresent("underwritingeuilabel8") && isWebElementPresent("underwritingeuilabel9") && isWebElementPresent("underwritingeuilabel10")
				   && isWebElementPresent("underwritingeuilabel11") && isWebElementPresent("underwritingeuilabel12") && isWebElementPresent("underwritingeuilabel13") && isWebElementPresent("underwritingeuilabel14") && isWebElementPresent("underwritingeuilabel15") && isWebElementPresent("underwritingeuilabel16")
				   && isWebElementPresent("underwritingeuilabel17")&& isWebElementPresent("underwritingeuilabel18") && isWebElementPresent("underwritinguiinputfield1") && isWebElementPresent("underwritinguiinputfield2") && isWebElementPresent("underwritinguiinputfield3") && isWebElementPresent("underwritinguiinputfield4")
				   && isWebElementPresent("underwritinguiinputfield5") && isWebElementPresent("underwritinguiinputfield6") && isWebElementPresent("underwritinguiinputfield7") && isWebElementPresent("underwritinguiinputfield8") && isWebElementPresent("underwritinguiinputfield9") && isWebElementPresent("underwritinguiinputfield10")
				   && isWebElementPresent("underwritinguiinputfield11") && isWebElementPresent("underwritinguiinputfield12") && isWebElementPresent("underwritinguibuttons1") && isWebElementPresent("underwritinguibuttons2")
				   && isWebElementPresent("underwritinguibuttons3") && isWebElementPresent("underwritinguibuttons4") && isWebElementPresent("underwritinguibuttons5") && isWebElementPresent("underwritinguibuttons6")) 
		   {
			   return true; 
			   
		   } 
			
			
		return false;
			
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

	public String guidelines() {
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (isWebElementPresentAfterWait("creatingQuoteLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("creatingQuoteLoader")));
		}
		if (isWebElementPresentAfterWait("savingQuoteDetailsLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("savingQuoteDetailsLoader")));
		}
		if (isWebElementPresentAfterWait("loader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
		}
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("underwritingGuidelinesHeading")));
		click("businessOwnedFor3YearsOrMoreYES");
		clickUsingJS("moreThan3LossesNO");
		quoteNumber = getWebElementText("quoteNumber").substring(7, 17);
		ExtentTestManager.getTest().log(Status.INFO, "Quote Number: " + quoteNumber);
		return quoteNumber;
	}

	public String goToPolicyFormSelectionPage() {
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (isWebElementPresentAfterWait("creatingQuoteLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("creatingQuoteLoader")));
		}
		if (isWebElementPresentAfterWait("savingQuoteDetailsLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("savingQuoteDetailsLoader")));
		}
		if (isWebElementPresentAfterWait("loader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
		}
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("underwritingGuidelinesHeading")));
		clickUsingJS("businessOwnedFor3YearsOrMoreYES");
		clickUsingJS("moreThan3LossesNO");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("nextButton"));
		click("nextButton");
		waitForPageLoaded();
		quoteNumber = getWebElementText("quoteNumber").substring(7, 17);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Quote Number: " + quoteNumber, ExtentColor.BLUE));
		return quoteNumber;
	}

	public void elpQuestions() {
		clickIfElementIsDisplayed("businessConductOperationsNO");
		clickIfElementIsDisplayed("bankruptcyInPastNO");
		clickIfElementIsDisplayed("policyDeclinedNO");
		clickIfElementIsDisplayed("manufacturingOperationsOrBusinessYES");
		clickIfElementIsDisplayed("qualityConrolYES");
		clickIfElementIsDisplayed("warningsAndLabelsCompliantWithRegulationsYES");
		clickIfElementIsDisplayed("applicantEngagedInExplosivesSalesNO");
		clickIfElementIsDisplayed("complianceUSDAandFDAstandardsYES");
		clickIfElementIsDisplayed("formalQualityConrolProgramYES");
		clickIfElementIsDisplayed("smokeDetectorsTestingProgramYES");
		clickIfElementIsDisplayed("hardwiredSmokeDetectorsPresentYES");
		clickIfElementIsDisplayed("leaseAgreementWithAllTenantsYES");
		clickIfElementIsDisplayed("insuranceCertificateWithMinimum$500000LiabilityLimitsYES");
		clickIfElementIsDisplayed("leaseProhibitUseOfGrillsYES");
		clickIfElementIsDisplayed("isDancingPremittedNO");
		clickIfElementIsDisplayed("isAlcoholServedAfterKitchenClosedNO");
		clickIfElementIsDisplayed("ownAcoholInPremisesNO");
		clickIfElementIsDisplayed("liveEntertainmentProvidedNO");
	}

	public String goToPolicyWideCoveragesPage(String classCodes) {
		wait = new WebDriverWait(driver, 30);
		if (isWebElementPresentAfterWait("savingQuoteDetailsLoader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("savingQuoteDetailsLoader")));
		}
		if (isWebElementPresentAfterWait("loader") == true) {
			wait.until(ExpectedConditions.invisibilityOf(getWebElement("loader")));
		}
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(getWebElement("underwritingGuidelinesHeading")));
		sleep(2000);
		quoteNumber = getWebElementText("quoteNumber").substring(7, 17);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Quote Number: " + quoteNumber, ExtentColor.BLUE));
		clickUsingJS("businessOwnedFor3YearsOrMoreYES");
		clickUsingJS("anyLossesInPastYearsNO");
		clickUsingJS("lossRunsProvidedNO");
		List<String> codes = new ArrayList<String>();
		if (classCodes.contains(",")) {
			String[] codesArray = classCodes.split(",");
			for (String code : codesArray) {
				codes.add(code);
			}
			for (int i = 0; i < elpClassCodeArray.length; i++) {
				for (String classCode : codes) {
					if (elpClassCodeArray[i].equalsIgnoreCase(classCode)) {
						elpQuestions();
						// break;
					} else {
						elpQuestions();
						// break;
					}
				}
			}
		} else {
			for (int i = 0; i < elpClassCodeArray.length; i++) {
				if (elpClassCodeArray[i].equalsIgnoreCase(classCodes)) {
					elpQuestions();
					// break;
				} else {
					elpQuestions();
					// break;
				}
			}
		}
		clickUsingJS("nextButton");
		waitForPageLoaded();
		return quoteNumber;
	}	

	// function to get portal wizard header name
	
			public String getPortalWizardheader() {  
				return getWebElementText("portalwizardtitle");
			}	
		
		
			//creating map for storing all the portal wizard options available on underwriting inf page
			 
			public HashMap<Integer, String> getWizardSideMenus() {
				HashMap<Integer, String> portalmenus=new HashMap<Integer, String>();
				wait = new WebDriverWait(driver, 20);
				waitForPageLoaded();
				List<WebElement> menulist=getWebElements("portalwizardsidemenus");
				for(int i=0;i<menulist.size();i++) { 
					portalmenus.put(i+1,menulist.get(i).getText());  
					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Menu Name is " +menulist.get(i).getText(),ExtentColor.BLUE));
				}
				
				return portalmenus;
				
			}
			
			//function to verify portal wizard menus 
			
			public boolean viewwizardmenusonUnderwritingInfoAndApplicationUI() {
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
				 
				
				 
				 if(getWebElementColor("portalfourthwizardmenu", "color").equals("#bbbdbf") && getWebElementColor("portalfifthwizardmenu", "color").equals("#bbbdbf")) {
					 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(getWebElementText("portalfourthwizardmenu")+ " and " +getWebElementText("portalfifthwizardmenu")+ " color is  grey and appear as disabled ",ExtentColor.BLUE)); 
					 flag=true;
				 }else {
					 return flag=false;   
				 }
				 
				 return flag;
			}
			
		// function to verify navigation of the visited wizard menus
			public boolean  navigatetoVisitedWizardMenus() {
				boolean flag=true;
				try {
					clickUsingJS("portalfistwizardmenu");
					waitForPageLoaded();
					clickUsingJS("nextButton");
					waitForPageLoaded();
					clickUsingJS("nextButton");
				}catch(Exception e) {
					return flag=false;
				}
                return flag;
			}
			
			// function to verify navigation of the unvisited wizard menu
					public boolean navigatetoUnVisitedWizardMenu() {
						boolean flag=true;
						try {
							waitForPageLoaded();
							clickUsingJS("portalfourthwizardmenu");
							getWebElementText("portalfourthwizardmenuHeading");
						}catch(Exception e) {
							return flag=false;
						}
		                return flag;
						
					
					
					}




}
