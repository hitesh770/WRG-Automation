package com.wrg.Regression.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.AP.Tests.BOP_AgencyPortalTests;
import com.wrg.AP.Tests.GL_AgencyPortalTests;
import com.wrg.AP.pages.ApplicantInformationPage_AP;
import com.wrg.AP.pages.PolicySearchPage_AP;
import com.wrg.AP.pages.QuoteSearchPage_AP;
import com.wrg.AP.pages.StartQuotePage_AP;
import com.wrg.AP.pages.UnderwritingGuidelinesPage_AP;
import com.wrg.AP.pages.UnderwritingInfoAndApplicationPage_AP;
import com.wrg.AP.pages.WrgHomePage_AP;
import com.wrg.PC.Tests.BOP_PolicyCenterTests;
import com.wrg.PC.pages.PolicyInfoPage_PC;
import com.wrg.PC.pages.PolicyReviewPage_PC;
import com.wrg.PC.pages.QuotePage_PC;
import com.wrg.PC.pages.RiskAnalysisPage_PC;
import com.wrg.PC.pages.SummaryPage_PC;
import com.wrg.PC.pages.WrgHomePage_PC;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class BOP_RegressionTests extends AbstractTest {
	WrgHomePage_AP homepage = null;
	QuoteSearchPage_AP quoteSearchPage = null;
	ApplicantInformationPage_AP applicantInfoPage_AP = null;
	PolicySearchPage_AP policySearchPage_AP = null;
	UnderwritingGuidelinesPage_AP underwritingGuidelinesPage = null;
	UnderwritingInfoAndApplicationPage_AP underwritingQuestionsPage_AP = null;
	BOP_AgencyPortalTests apTests = null;
	WrgHomePage_PC homepage_PC = null;
	QuotePage_PC quotePage_PC = null;
	PolicyInfoPage_PC policyInfoPage_PC = null;
	RiskAnalysisPage_PC riskAnalysispage_PC = null;
	PolicyReviewPage_PC policyReviewPage_PC = null;
	BOP_PolicyCenterTests pctest = null;
	SummaryPage_PC summaryPage_PC = null;
	StartQuotePage_AP startQuotePage_AP = null;
	GL_AgencyPortalTests gl_APTests = null;
	String buildNumber_AP=null;

	@BeforeMethod
	public void beforeMethod() {
		homepage = new WrgHomePage_AP();
		quoteSearchPage = new QuoteSearchPage_AP();
		applicantInfoPage_AP = new ApplicantInformationPage_AP();
		policySearchPage_AP = new PolicySearchPage_AP();
		underwritingGuidelinesPage = new UnderwritingGuidelinesPage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		apTests = new BOP_AgencyPortalTests();
		pctest = new BOP_PolicyCenterTests();
		homepage_PC = new WrgHomePage_PC();
		quotePage_PC = new QuotePage_PC();
		policyInfoPage_PC = new PolicyInfoPage_PC();
		riskAnalysispage_PC = new RiskAnalysisPage_PC();
		policyReviewPage_PC = new PolicyReviewPage_PC();
		summaryPage_PC = new SummaryPage_PC();
		gl_APTests = new GL_AgencyPortalTests();
		startQuotePage_AP = new StartQuotePage_AP();
	}

	@Parameters({ "testCaseID", "insuredName", "state", "organizationCode", "password", "insuranceType",
			"businessEntity", "classCodeNumber", "percentageOwnerOccupiedValue" })
	@Test
	public void draftQuoteAndPerformSearch(String testCaseID, String insuredName, String state, String organizationCode,
			String password, String insuranceType, String businessEntity, String classCodeNumber,
			String percentageOwnerOccupiedValue) throws IOException {
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Parameters are-> TestCaseID: " + testCaseID + "Insured Name: " + insuredName
						+ ", State: " + state + ", Organization Code: " + organizationCode + ", Password: " + password
						+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
						+ ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue, ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		gl_APTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.startsWith("R3")) {
			applicantName = gl_APTests.searchQuote(applicantName);
			applicantInfoPage_AP.enterAddress(state, businessEntity);
			applicantInfoPage_AP.selectInsuranceType(insuranceType);
			applicantInfoPage_AP.clickNextButton();
			sleep(2000);
			startQuotePage_AP.addClassification(classCodeNumber);
			quoteNumber = underwritingGuidelinesPage.guidelines();
		} else if (buildNumber_AP.contains("R2")){
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			applicantInfoPage_AP.fillApplicantDetails(state, businessEntity, classCodeNumber);
			quoteNumber = underwritingGuidelinesPage.guidelines();
		}
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Quote Number", quoteNumber, applicantName, "Draft");
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Insured Name", quoteNumber, applicantName, "Draft");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Insured Name", "", applicantName, "Draft");
		policySearchPage_AP.searchValuesUnderBillingSection("Insured Name", "", applicantName, "", "Draft");
	}

	@Parameters({ "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void createQuoteWithUnderwriterIssuesAndPerformSearch(String testCaseID, String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue)
			throws IOException {
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Parameters are-> TestCaseID: " + testCaseID + ", State: " + state
						+ ", Number of Locations: " + numberOfLocations + ", Organization Code: " + organizationCode
						+ ", Password: " + password + ", Insurance Type: " + insuranceType + ", Business Entity: "
						+ businessEntity + ", Class Codes: " + classCodeNumber + ", FormType: " + formType
						+ ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue, ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		gl_APTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = gl_APTests.searchQuote(insuredName);
			quoteNumber = gl_APTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.isSeatingAbove75YES();
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.isSeatingAbove75YES();
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		}
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Quote Number", quoteNumber, applicantName,
				"Under UW Review");
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Insured Name", quoteNumber, applicantName,
				"Under UW Review");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Insured Name", "", applicantName, "Under UW Review");
		policySearchPage_AP.searchValuesUnderBillingSection("Insured Name", "", applicantName, "", "Under UW Review");
	}

	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void createQuoteAndPerformSearch(String pcUsers, String testCaseID, String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue)
			throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Parameters are-> PC Users: " + pcUsers + ", TestCase ID: " + testCaseID
						+ ", State: " + state + ", Number of Locations: " + numberOfLocations + ", Organization Code: "
						+ organizationCode + ", Password: " + password + " , Insurance Type: " + insuranceType
						+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber + ", FormType: "
						+ formType + ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		gl_APTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = gl_APTests.searchQuote(insuredName);
			quoteNumber = gl_APTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		}
		login(usersList.get(0).toString());
		homepage_PC.searchSubmission(quoteNumber);
		quotePage_PC.goToPolicyInfoPage();
		policyInfoPage_PC.editPolicyTransaction();
		// policyInfoPage_PC.setCustomTerm(days, effectiveDate);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyInfoPage_PC.goToRiskAnalysisPage();
		riskAnalysispage_PC.approveIssues();
		riskAnalysispage_PC.goToPolicyReviewPage();
		policyReviewPage_PC.quotePolicy();
		quotePage_PC.releaseLockWithoutActivity();
		agencyPortalLogin(organizationCode, password);
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
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Quote Number", quoteNumber, applicantName, "Quoted");
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Insured Name", quoteNumber, applicantName, "Quoted");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Insured Name", "", applicantName, "Quoted");
		policySearchPage_AP.searchValuesUnderBillingSection("Insured Name", "", applicantName, "", "Quoted");
	}

	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void issuePolicyAndPerformSearch(String pcUsers, String testCaseID, String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue)
			throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Parameters are-> PCU Users: " + pcUsers + "TestCase ID: " + testCaseID
						+ ", State: " + state + ", Number of Locations: " + numberOfLocations + ", Organization Code: "
						+ organizationCode + ", Password: " + password + ", Insurance type: " + insuranceType
						+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber + ", FormType: "
						+ formType + ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		gl_APTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = gl_APTests.searchQuote(insuredName);
			quoteNumber = gl_APTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		}
		login(usersList.get(0).toString());
		homepage_PC.searchSubmission(quoteNumber);
		quotePage_PC.goToPolicyInfoPage();
		policyInfoPage_PC.editPolicyTransaction();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyInfoPage_PC.goToRiskAnalysisPage();
		riskAnalysispage_PC.approveIssues();
		riskAnalysispage_PC.goToPolicyReviewPage();
		policyReviewPage_PC.quotePolicy();
		quotePage_PC.releaseLockWithoutActivity();
		apTests.makePaymentOnAP(quoteNumber, organizationCode, password);
		apTests.completePayment();
		String policyNumber = null;
		try {
			policyNumber = pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(1).toString());
		} catch (Exception e) {
			policyNumber = pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(0).toString());
		}
		String accountNumber = summaryPage_PC.getAccountNumber();
		agencyPortalLogin(organizationCode, password);
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
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Quote Number", quoteNumber, applicantName,
				"In Force");
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Insured Name", quoteNumber, applicantName,
				"In Force");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Insured Name", "", applicantName, "In Force");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Policy Number", policyNumber, "", "In Force");
		policySearchPage_AP.searchValuesUnderBillingSection("Insured Name", "", applicantName, "", "In Force");
		policySearchPage_AP.searchValuesUnderBillingSection("Policy Number", policyNumber, "", "", "In Force");
		policySearchPage_AP.searchValuesUnderBillingSection("Account Number", "", "", accountNumber, "In Force");
	}

	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void cancelPolicyAndPerformSearch(String pcUsers, String testCaseID, String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue)
			throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createLabel(
				"Parameters are-> PC Users: " + pcUsers + ", TestCase ID: " + testCaseID + "Insured Name: "
						+ insuredName + ", State: " + state + ", Number of Locations: " + numberOfLocations
						+ ", Organization Code: " + organizationCode + ", Password: " + password + ", Insurance Type: "
						+ insuranceType + ", Business Entity: " + businessEntity + ", Class Code: " + classCodeNumber
						+ ", FormType: " + formType + ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue,
				ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		gl_APTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = gl_APTests.searchQuote(insuredName);
			quoteNumber = gl_APTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		}
		login(usersList.get(0).toString());
		homepage_PC.searchSubmission(quoteNumber);
		quotePage_PC.goToPolicyInfoPage();
		policyInfoPage_PC.editPolicyTransaction();
		// policyInfoPage_PC.setCustomTerm(days, effectiveDate);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyInfoPage_PC.goToRiskAnalysisPage();
		riskAnalysispage_PC.approveIssues();
		riskAnalysispage_PC.goToPolicyReviewPage();
		policyReviewPage_PC.quotePolicy();
		quotePage_PC.releaseLockWithoutActivity();
		apTests.makePaymentOnAP(quoteNumber, organizationCode, password);
		apTests.completePayment();
		String policyNumber = null;
		try {
			policyNumber = pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(1).toString());
		} catch (Exception e) {
			policyNumber = pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(0).toString());
		}
		try {
			login(usersList.get(2).toString());
		} catch (Exception e) {
			login(usersList.get(0).toString());
		}
		pctest.searchPolicyInPC(policyNumber);
		sleep(3000);
		String accountNumber = summaryPage_PC.getAccountNumber();
		summaryPage_PC.cancelPolicy();
		agencyPortalLogin(organizationCode, password);
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
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Quote Number", quoteNumber, applicantName,
				"Cancelled");
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Insured Name", quoteNumber, applicantName,
				"Cancelled");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Insured Name", "", applicantName, "Cancelled");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Policy Number", policyNumber, "", "Cancelled");
		policySearchPage_AP.searchValuesUnderBillingSection("Insured Name", "", applicantName, "", "Cancelled");
		policySearchPage_AP.searchValuesUnderBillingSection("Policy Number", policyNumber, "", "", "Cancelled");
	}

	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void reinstatePolicyAndPerformSearch(String pcUsers, String testCaseID, String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue)
			throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createLabel(
				"Parameters are-> PC Users: " + pcUsers + ", TestCaseID: " + testCaseID + ", Insured Name: "
						+ insuredName + ", State: " + state + ", Number of Locations: " + numberOfLocations
						+ ", Organization Code: " + organizationCode + ", Password: " + password + ", Insurance Type: "
						+ insuranceType + ", Business Entity: " + businessEntity + ", Class Code: " + classCodeNumber
						+ ", FormType: " + formType + ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue,
				ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		gl_APTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = gl_APTests.searchQuote(insuredName);
			quoteNumber = gl_APTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		}
		login(usersList.get(0).toString());
		homepage_PC.searchSubmission(quoteNumber);
		quotePage_PC.goToPolicyInfoPage();
		policyInfoPage_PC.editPolicyTransaction();
		// policyInfoPage_PC.setCustomTerm(days, effectiveDate);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		policyInfoPage_PC.goToRiskAnalysisPage();
		riskAnalysispage_PC.approveIssues();
		riskAnalysispage_PC.goToPolicyReviewPage();
		policyReviewPage_PC.quotePolicy();
		quotePage_PC.releaseLockWithoutActivity();
		apTests.makePaymentOnAP(quoteNumber, organizationCode, password);
		apTests.completePayment();
		String policyNumber = null;
		try {
			policyNumber = pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(1).toString());
		} catch (Exception e) {
			policyNumber = pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(0).toString());
		}
		try {
			login(usersList.get(2).toString());
		} catch (Exception e) {
			login(usersList.get(0).toString());
		}
		pctest.searchPolicyInPC(policyNumber);
		sleep(3000);
		summaryPage_PC.cancelPolicy();
		// login();
		pctest.searchPolicyInPC(policyNumber);
		sleep(3000);
		String accountNumber = summaryPage_PC.getAccountNumber();
		List<String> updatedQuoteNumber = new ArrayList<String>();
		summaryPage_PC.reinstatePolicy("Agency Accommodation");
		quotePage_PC.clickReinstateButton();
		updatedQuoteNumber = quotePage_PC.getUpdatedQuoteNumber();
		agencyPortalLogin(organizationCode, password);
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
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Quote Number", updatedQuoteNumber.get(0),
				applicantName, "In Force");
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Insured Name", quoteNumber, applicantName,
				"In Force");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Insured Name", "", applicantName, "In Force");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Policy Number", policyNumber, "", "In Force");
		policySearchPage_AP.searchValuesUnderBillingSection("Insured Name", "", applicantName, "", "In Force");
		policySearchPage_AP.searchValuesUnderBillingSection("Policy Number", policyNumber, "", "", "In Force");
		policySearchPage_AP.searchValuesUnderBillingSection("Account Number", "", "", accountNumber, "In Force");
	}

	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void renewPolicyAndPerformSearch(String pcUsers, String testCaseID, String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue)
			throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createLabel(
				"Parameters are-> PC Users: " + pcUsers + ", TestCaseID: " + testCaseID + "Insured Name: " + insuredName
						+ ", State: " + state + ", Number of Locations: " + numberOfLocations + ", Organization Code: "
						+ organizationCode + ", Password: " + password + ", Insurance Type: " + insuranceType
						+ ", Business Entity: " + businessEntity + ", Class Code: " + classCodeNumber + ", FormType: "
						+ formType + ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue,
				ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		gl_APTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = gl_APTests.searchQuote(insuredName);
			quoteNumber = gl_APTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
			underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
			underwritingQuestionsPage_AP.beginSubmission();
		}
		login(usersList.get(0).toString());
		homepage_PC.searchSubmission(quoteNumber);
		quotePage_PC.goToPolicyInfoPage();
		policyInfoPage_PC.editPolicyTransaction();
		policyInfoPage_PC.setCustomTerm("60", "NA");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyInfoPage_PC.goToRiskAnalysisPage();
		riskAnalysispage_PC.approveIssues();
		riskAnalysispage_PC.goToPolicyReviewPage();
		policyReviewPage_PC.quotePolicy();
		quotePage_PC.releaseLockWithoutActivity();
		apTests.makePaymentOnAP(quoteNumber, organizationCode, password);
		apTests.completePayment();
		String policyNumber = null;
		try {
			policyNumber = pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(1).toString());
		} catch (Exception e) {
			policyNumber = pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(0).toString());
		}
		try {
			login(usersList.get(2).toString());
		} catch (Exception e) {
			login(usersList.get(0).toString());
		}
		pctest.searchPolicyInPC(policyNumber);
		sleep(3000);
		List<String> updatedPolicyAndQuoteNumber = new ArrayList<String>();
		String accountNumber = summaryPage_PC.getAccountNumber();
		updatedPolicyAndQuoteNumber = summaryPage_PC.renewPolicy();
		sleep(3000);
		agencyPortalLogin(organizationCode, password);
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
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Quote Number", updatedPolicyAndQuoteNumber.get(0),
				applicantName, "Scheduled");
		policySearchPage_AP.searchValuesUnderViewEditQuoteSection("Insured Name", updatedPolicyAndQuoteNumber.get(0),
				applicantName, "Scheduled");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Insured Name", "", applicantName, "Scheduled");
		policySearchPage_AP.searchValuesUnderPolicyDetailsSection("Policy Number", updatedPolicyAndQuoteNumber.get(1),
				"", "Scheduled");
		policySearchPage_AP.searchValuesUnderBillingSection("Insured Name", "", applicantName, "", "Scheduled");
		policySearchPage_AP.searchValuesUnderBillingSection("Policy Number", updatedPolicyAndQuoteNumber.get(1), "", "",
				"Scheduled");
	}

	@Parameters({ "insuredName", "state", "numberOfLocations", "organizationCode", "password", "insuranceType",
			"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue"

	})

	@Test
	public void verifyPrintApplicationButton(String insuredName, String state, String numberOfLocations,
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
								+ organizationCode + ", Password: " + password + ", Insurance Type" + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ",FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String applicantName = null;
		String quoteNumber = null;
		gl_APTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = gl_APTests.searchQuote(insuredName);
			quoteNumber = gl_APTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
		}
		underwritingQuestionsPage_AP.verifyApplicationPrint();
		apTests.completePayment();
		policySearchPage_AP.searchViaQuoteNumberUnderViewEditQuote(quoteNumber, applicantName, "Submitted");
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
