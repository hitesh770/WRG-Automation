package com.wrg.AP.pages;

import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.STRING;

import org.apache.poi.poifs.crypt.dsig.KeyInfoKeySelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class OptionalCoveragesPage_AP extends AbstractTest {
	WebDriverWait wait = null;

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
		waitForPageLoaded();
		typeUsingJS("coverage2textfield",getData("coverage2textfieldvalue"));
		sleep(3000);
		getWebElement("coverage2textfield").sendKeys(Keys.TAB); 
		sleep(3000);
		waitForPageLoaded();
		clickUsingJS("coverage2savebutton"); 
		waitForPageLoaded();
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
		}else {
			explicitwaitForElement(getWebElement("coverage4Nooptiontextfield")); 
			scrollToElement("coverage4Nooptiontextfield");
			type(getWebElement("coverage4Nooptiontextfield"),getData("coverage4Nooptiontextfieldvalue"));
			getWebElement("coverage4Nooptiontextfield").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
		}
		
		explicitwaitForElement(getWebElement("coverage5"));
		clickUsingJS("coverage5");
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage5dropdown"));
		scrollToElement("coverage5dropdown");
		selectByOption(getWebElement("coverage5dropdown"),getData("coverage5dropdownvalue")); 
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage5textfield")); 
		type(getWebElement("coverage5textfield"),getData("coverage5textfieldvalue"));
		getWebElement("coverage5textfield").sendKeys(Keys.TAB); 
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage6"));
		clickUsingJS("coverage6");
		waitforrunningLoadingicon();
		explicitwaitForElement(getWebElement("coverage6dropdown"));
		scrollToElement("coverage6dropdown");
		selectByOption(getWebElement("coverage6dropdown"),getData("coverage6dropdownvalue")); 
		waitforrunningLoadingicon();
		
		if(getData("riskpayroll").equalsIgnoreCase("Yes")) {
			explicitwaitForElement(getWebElement("coverage6textfield1")); 
			type(getWebElement("coverage6textfield1"),getData("coverage6textfield1value"));
			getWebElement("coverage6textfield1").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
			
		}else {
			
			explicitwaitForElement(getWebElement("coverage6textfield2")); 
			type(getWebElement("coverage6textfield2"),getData("coverage6textfield2value"));
			getWebElement("coverage6textfield2").sendKeys(Keys.TAB); 
			waitforrunningLoadingicon();
		}
		
		
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
			//check this thing on monday
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
		

			//quotepagelabel=getWebElementText("quotepagelabel");
			//quotepagelabel="quote page not available";
		

		
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
