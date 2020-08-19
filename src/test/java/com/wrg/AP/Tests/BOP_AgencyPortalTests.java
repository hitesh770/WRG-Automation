package com.wrg.AP.Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.AP.pages.ApplicantInformationPage_AP;
import com.wrg.AP.pages.LocationsAndBuildingsPage_AP;
import com.wrg.AP.pages.OptionalCoveragesPage_AP;
import com.wrg.AP.pages.PaymentDetailsPage_AP;
import com.wrg.AP.pages.PolicyFormSelectionPage_AP;
import com.wrg.AP.pages.PolicySearchPage_AP;
import com.wrg.AP.pages.PolicywideCoveragesPage_AP;
import com.wrg.AP.pages.QuotePage_AP;
import com.wrg.AP.pages.QuoteSearchPage_AP;
import com.wrg.AP.pages.UnderwritingGuidelinesPage_AP;
import com.wrg.AP.pages.UnderwritingInfoAndApplicationPage_AP;
import com.wrg.AP.pages.WrgHomePage_AP;
import com.wrg.PC.Tests.BOP_PolicyCenterTests;
import com.wrg.PC.pages.SummaryPage_PC;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class BOP_AgencyPortalTests extends AbstractTest {

	WrgHomePage_AP homepage = null;
	QuoteSearchPage_AP quoteSearchPage = null;
	ApplicantInformationPage_AP applicantInfoPage_AP = null;
	UnderwritingGuidelinesPage_AP underwritingGuidelinesPage = null;
	PolicyFormSelectionPage_AP policyFormSelectionPage_AP = null;
	PolicywideCoveragesPage_AP policywideCoveragesPage_AP = null;
	OptionalCoveragesPage_AP optionalCoveragesPage_AP = null;
	LocationsAndBuildingsPage_AP locationsAndBuildingsPage_AP = null;
	QuotePage_AP quotePage_AP = null;
	UnderwritingInfoAndApplicationPage_AP underwritingQuestionsPage_AP = null;
	PolicySearchPage_AP policySearchPage_AP = null;
	BOP_PolicyCenterTests pctest = null;
	PaymentDetailsPage_AP paymentDetailsPage_AP = null;
	SummaryPage_PC summaryPage_PC = null;
	GL_AgencyPortalTests glTests = null;
	String buildNumber_AP = null;

	@BeforeMethod
	public void beforeMethod() {
		homepage = new WrgHomePage_AP();
		quoteSearchPage = new QuoteSearchPage_AP();
		applicantInfoPage_AP = new ApplicantInformationPage_AP();
		underwritingGuidelinesPage = new UnderwritingGuidelinesPage_AP();
		policyFormSelectionPage_AP = new PolicyFormSelectionPage_AP();
		policywideCoveragesPage_AP = new PolicywideCoveragesPage_AP();
		optionalCoveragesPage_AP = new OptionalCoveragesPage_AP();
		locationsAndBuildingsPage_AP = new LocationsAndBuildingsPage_AP();
		quotePage_AP = new QuotePage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		paymentDetailsPage_AP = new PaymentDetailsPage_AP();
		policySearchPage_AP = new PolicySearchPage_AP();
		glTests = new GL_AgencyPortalTests();
	}

	/* Create New Submission via Agent Portal */
	@Parameters({ "insuredName", "state", "numberOfLocations", "organizationCode", "password", "insuranceType",
			"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void verifyNewSubmissionIsCreatedViaAgentPortal(String insuredName, String state, String numberOfLocations,
			String organizationCode, String password, String insuranceType, String businessEntity,
			String classCodeNumber, String formType, String percentageOwnerOccupiedValue) throws IOException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state
								+ ", Number of Locations: " + numberOfLocations + ", Organization Code: "
								+ organizationCode + ", Password: " + password + ", Insurance Type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;

		glTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = glTests.searchQuote(insuredName);
			quoteNumber = glTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = searchQuote(insuredName, organizationCode, password);
			quoteNumber = newQuote(state, businessEntity, classCodeNumber, formType, percentageOwnerOccupiedValue);
		}

		underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
		underwritingQuestionsPage_AP.beginSubmission();
		policySearchPage_AP.searchViaQuoteNumberUnderViewEditQuote(quoteNumber, applicantName, "Under UW Review");
	}

	/* Create New Submission with Multiple Locations */
	@Parameters({ "insuredName", "state", "numberOfLocations", "organizationCode", "password", "insuranceType",
			"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void verifyNewSubmissionIsCreatedWithMultipleLocationsViaAgentPortal(String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue)
			throws IOException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state
								+ ", Number of Locations: " + numberOfLocations + ", Organization Code: "
								+ organizationCode + ", Password: " + password + ", Insurance Type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		glTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = glTests.searchQuote(insuredName);
			quoteNumber = glTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = searchQuote(insuredName, organizationCode, password);
			quoteNumber = newQuoteWithMultipleLocations(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);
		}
		underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
		underwritingQuestionsPage_AP.beginSubmission();
		policySearchPage_AP.searchViaQuoteNumberUnderViewEditQuote(quoteNumber, applicantName, "Under UW Review");
	}

	public String searchQuote(String applicantname, String organizationCode, String password) {
		homepage = new WrgHomePage_AP();
		quoteSearchPage = new QuoteSearchPage_AP();
		homepage.createNewQuote("Businessowners");
		applicantname = quoteSearchPage.searchQuote(applicantname);
		return applicantname;
	}

	public String newQuote(String state, String businessEntity, String classCodeNumber, String formType,
			String percentageOwnerOccupiedValue) {
		applicantInfoPage_AP = new ApplicantInformationPage_AP();
		underwritingGuidelinesPage = new UnderwritingGuidelinesPage_AP();
		policyFormSelectionPage_AP = new PolicyFormSelectionPage_AP();
		policywideCoveragesPage_AP = new PolicywideCoveragesPage_AP();
		optionalCoveragesPage_AP = new OptionalCoveragesPage_AP();
		locationsAndBuildingsPage_AP = new LocationsAndBuildingsPage_AP();
		quotePage_AP = new QuotePage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		applicantInfoPage_AP.fillApplicantDetails(state, businessEntity, classCodeNumber);
		String quoteNumber = underwritingGuidelinesPage.goToPolicyFormSelectionPage();
		policyFormSelectionPage_AP.policyForm(formType);
		policywideCoveragesPage_AP.coverages();
		optionalCoveragesPage_AP.optionalCoverages();
		locationsAndBuildingsPage_AP.addBuilding(percentageOwnerOccupiedValue, classCodeNumber);

		quotePage_AP.quote();
		underwritingQuestionsPage_AP.answerQuestions();
		return quoteNumber;
	}

	public String newQuoteWithMultipleLocations(String state, String businessEntity, String classCodeNumber,
			String formType, String percentageOwnerOccupiedValue, String numberOfLocations) {
		applicantInfoPage_AP = new ApplicantInformationPage_AP();
		underwritingGuidelinesPage = new UnderwritingGuidelinesPage_AP();
		policyFormSelectionPage_AP = new PolicyFormSelectionPage_AP();
		policywideCoveragesPage_AP = new PolicywideCoveragesPage_AP();
		optionalCoveragesPage_AP = new OptionalCoveragesPage_AP();
		locationsAndBuildingsPage_AP = new LocationsAndBuildingsPage_AP();
		quotePage_AP = new QuotePage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		applicantInfoPage_AP.fillApplicantDetails(state, businessEntity, classCodeNumber);
		String quoteNumber = underwritingGuidelinesPage.goToPolicyFormSelectionPage();
		policyFormSelectionPage_AP.policyForm(formType);
		policywideCoveragesPage_AP.coverages();
		optionalCoveragesPage_AP.optionalCoverages();
		locationsAndBuildingsPage_AP.addMultipleLocations(state, percentageOwnerOccupiedValue, numberOfLocations,
				classCodeNumber);
		quotePage_AP.quote();
		underwritingQuestionsPage_AP.answerQuestions();
		return quoteNumber;
	}

	// Make Payment After Underwrite Issues are resolved
	public void makePaymentOnAP(String quoteNumber, String organizationCode, String password) {
		policySearchPage_AP = new PolicySearchPage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		paymentDetailsPage_AP = new PaymentDetailsPage_AP();
		driver = agencyPortalLogin(organizationCode, password);
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
		policySearchPage_AP.goToSearchQuotePage();
		policySearchPage_AP.searchQuote(quoteNumber);
		policySearchPage_AP.clickBeginSubmissionButton();
	}

	public void completePayment() {
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		paymentDetailsPage_AP = new PaymentDetailsPage_AP();
		underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
		paymentDetailsPage_AP.downPayment();
	}

	@AfterTest
	public void afterTest() throws IOException {
		if (browser.equalsIgnoreCase("firefox")) {
			Runtime.getRuntime().exec("taskkill /IM geckodriver.exe /T");
		} else if (browser.equalsIgnoreCase("ie")) {
			Runtime.getRuntime().exec("taskkill /IM IEDriverServer32.exe /T");
		} else if (browser.equalsIgnoreCase("chrome")) {
			Runtime.getRuntime().exec("taskkill /IM chromedriver.exe /T");
		}
	}

}
