package com.wrg.PC.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class SummaryPage_PC extends AbstractTest{

	WebDriverWait wait=null;
	
	public void startPolicyChange() {
		click("actionsButton");
		click("changePolicyButton");
	}
	
	public void cancelPolicy() {
		driver.navigate().refresh();
		wait = new WebDriverWait(driver,10);
		click("actionsButtonNew");
		click("cancelPolicyButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("cancellationSourceDropdown")));
		click("cancellationSourceDropdown");
		click(driver.findElement(By.xpath("//li[contains(text(),'Insured')]")));
		sleep(2000);
		click("cancellationReasonDropdown");
		click(driver.findElement(By.xpath("//li[contains(text(),'Insured request')]")));
		//wait.until(ExpectedConditions.elementToBeClickable(getWebElement("startCancellationButton")));
		sleep(2000);
		clickUsingJS("startCancellationButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("issueDropdown")));
		click("issueDropdown");
		click("cancelNowButton");
		sleep(1000);
		click("okButton");
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("cancellationMessage")));
	}
	
	public void reinstatePolicy(String reinstatementReason) {
		waitForPageLoaded();
		wait = new WebDriverWait(driver,10);
		click("actionsButtonNew");
		click("reinstatePolicyButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("startReinstatementTitle")));
		click("reinstatementReasonDropdown");
		click(driver.findElement(By.xpath("//li[contains(text(),'"+reinstatementReason+"')]")));
		sleep(2000);
		click("quoteButton");		
	}
	
	
	
	public List<String> renewPolicy() {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		List<String> renewedPolicyNumber=new ArrayList<String>();
		waitForPageLoaded();
		wait = new WebDriverWait(driver,100);
		click("actionsButtonNew");
		click("renewPolicyButton");
		click("okButton");
		wait.until(ExpectedConditions.visibilityOf(getWebElement("viewQuoteTitle")));
		click("issueRenewalDropdown");
		click("issueNowButton");
		click("okButton");
		sleep(20000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("renewalMessage")));
		renewedPolicyNumber.add(getWebElementText("renewalMessage").substring(15, 25));
		renewedPolicyNumber.add(getWebElementText("policyNumber").substring(19,29));
		System.out.println("Renewed Quote Number: "+ renewedPolicyNumber.get(0));
		System.out.println("Renewed Policy Number: "+ renewedPolicyNumber.get(1));
		ExtentTestManager.getTest().log(Status.INFO, MarkupHelper
				.createLabel("Renewed Quote Number is : "+renewedPolicyNumber.get(0), ExtentColor.BLUE));
		ExtentTestManager.getTest().log(Status.INFO, MarkupHelper
				.createLabel("Renewed Policy Number is : "+renewedPolicyNumber.get(1), ExtentColor.BLUE));
		return renewedPolicyNumber;
	}
	
	public String getAccountNumber() {
		String accountNumber=getWebElementText("accountNumber");
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Account Number: " + accountNumber, ExtentColor.BLUE));
		return accountNumber;
	}
	
	public void clickAccountNumber() {
		clickUsingJS("accountNumber");
	}
	
	public List getPolicyNumbers() {
		List<WebElement> policiesAssociatedToAccount = new ArrayList<WebElement>();
		List<String> policyNumberAssociatedToAccount = new ArrayList<String>();
		policiesAssociatedToAccount=getWebElements("policyNumberList");
		for(int i=1;i<=policiesAssociatedToAccount.size();i++) {
			String t=driver.findElement(By.xpath("(//div[@id='AccountFile_Summary:AccountFile_SummaryScreen:AccountFile_Summary_PolicyTermsLV']//table)[3]//tr["+i+"]/td[1]/div/a")).getText();
			//String t = getWebElementText(we.getText());
			policyNumberAssociatedToAccount.add(t);
		}
		System.out.println(policyNumberAssociatedToAccount);
		return policyNumberAssociatedToAccount;
	}
	

}
