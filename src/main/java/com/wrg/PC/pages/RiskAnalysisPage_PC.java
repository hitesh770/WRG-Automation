package com.wrg.PC.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class RiskAnalysisPage_PC extends AbstractTest{
	
	WebDriverWait wait = null;

	

	public void goToPolicyReviewPage() {
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("riskAnalysisScreenValidation")));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			sleep(3000);
			//WebElement nextButton = getWebElement("nextButton");
			click(getWebElement("nextButton"));
		} catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert data: " + alertText);
				alert.accept();
			} catch (NoAlertPresentException f) {
				f.printStackTrace();
			}
		}

	}
	
	public void approveIssues() {
		List<WebElement> numberOfUWIssues = new ArrayList<WebElement>();
		 numberOfUWIssues=driver.findElements(By.xpath("//div[@id='SubmissionWizard:Job_RiskAnalysisScreen:RiskAnalysisCV:RiskEvaluationPanelSet:0-body']//table//tr"));
		System.out.println("Total Under Writing Issues are: "+numberOfUWIssues.size());
		sleep(3000);
		 for(int i=1;i<=numberOfUWIssues.size();i++) {
			click(driver.findElement(By.xpath("//div[@id='SubmissionWizard:Job_RiskAnalysisScreen:RiskAnalysisCV:RiskEvaluationPanelSet:0-body']//table//tr["+i+"]/td[1]/div")));
		}
		 click(getWebElement("approveButton"));
		 if(isWebElementPresent("okButton")==true) {
		 click(getWebElement("okButton"));
		 }
		 if(isWebElementPresent("okButtonOnPopup")) {
			 clickUsingJS("okButtonOnPopup");
		 }
	}

}
