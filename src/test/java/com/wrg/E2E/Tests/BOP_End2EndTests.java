package com.wrg.E2E.Tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
import com.wrg.BC.pages.ActivitiesCreatedPage_BC;
import com.wrg.BC.pages.InvoicesPage_BC;
import com.wrg.BC.pages.SummaryPage_BC;
import com.wrg.PC.Tests.BOP_PolicyCenterTests;
import com.wrg.PC.pages.AccountSummaryPage_PC;
import com.wrg.PC.pages.AddressVerificationPage_PC;
import com.wrg.PC.pages.BlanketsPage_PC;
import com.wrg.PC.pages.BuildingsAndClassificationsPage_PC;
import com.wrg.PC.pages.BusinessOwnersLinePage_PC;
import com.wrg.PC.pages.ClassCodeSearchPage_PC;
import com.wrg.PC.pages.EnterAccountInformationPage_PC;
import com.wrg.PC.pages.FormsPage_PC;
import com.wrg.PC.pages.HazardGradeSelectionPage_PC;
import com.wrg.PC.pages.LocationsPage_PC;
import com.wrg.PC.pages.NewSubmissionPage_PC;
import com.wrg.PC.pages.OfferingsPage_PC;
import com.wrg.PC.pages.OrganizationsPage_PC;
import com.wrg.PC.pages.PaymentPage_PC;
import com.wrg.PC.pages.PolicyInfoPage_PC;
import com.wrg.PC.pages.PolicyReviewPage_PC;
import com.wrg.PC.pages.QuotePage_PC;
import com.wrg.PC.pages.RiskAnalysisPage_PC;
import com.wrg.PC.pages.SummaryPage_PC;
import com.wrg.PC.pages.SupplementalPage_PC;
import com.wrg.PC.pages.WrgHomePage_PC;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class BOP_End2EndTests extends AbstractTest {

	WrgHomePage_AP homepage = null;
	QuoteSearchPage_AP quoteSearchPage = null;
	ApplicantInformationPage_AP applicantInfoPage_AP = null;
	UnderwritingGuidelinesPage_AP underwritingGuidelinesPage = null;
	PolicyFormSelectionPage_AP policyFormSelectionPage_AP = null;
	PolicywideCoveragesPage_AP policywideCoveragesPage_AP = null;
	OptionalCoveragesPage_AP optionalCoveragesPage_AP = null;
	LocationsAndBuildingsPage_AP locationsAndBuildingsPage_AP = null;
	QuotePage_AP quotePage_AP = null;
	QuotePage_PC quotePage_PC = null;
	UnderwritingInfoAndApplicationPage_AP underwritingQuestionsPage_AP = null;
	PolicySearchPage_AP policySearchPage_AP = null;
	BOP_PolicyCenterTests pctest = null;
	PaymentDetailsPage_AP paymentDetailsPage_AP = null;
	SummaryPage_PC summaryPage_PC = null;
	BOP_AgencyPortalTests apTests = null;
	EnterAccountInformationPage_PC enterAccountInfoPage = null;
	WrgHomePage_PC homepage_PC = null;
	OrganizationsPage_PC organizationsPage_PC = null;
	ClassCodeSearchPage_PC classCodeSearchPage_PC = null;
	HazardGradeSelectionPage_PC hazardGradeSelectionPage_PC = null;
	AddressVerificationPage_PC addressVerificationPage_PC = null;
	AccountSummaryPage_PC accountSummaryPage_PC = null;
	NewSubmissionPage_PC newSubmissionPage_PC = null;
	OfferingsPage_PC offeringsPage_PC = null;
	PolicyInfoPage_PC policyInfoPage = null;
	BusinessOwnersLinePage_PC businessOwnersLinePage_PC = null;
	LocationsPage_PC locationsPage_PC = null;
	BuildingsAndClassificationsPage_PC buildingsAndClassificationspage = null;
	BlanketsPage_PC blanketsPage_PC = null;
	SupplementalPage_PC supplementalPage = null;
	RiskAnalysisPage_PC riskAnalysispage = null;
	PolicyReviewPage_PC policyReviewPage = null;
	QuotePage_PC quotePage = null;
	FormsPage_PC formsPage_PC = null;
	PaymentPage_PC paymentPage = null;
	ActivitiesCreatedPage_BC activitiesPage = null;
	SummaryPage_BC summaryPage_BC = null;
	InvoicesPage_BC invoicesPage_BC = null;
	GL_AgencyPortalTests glTests = null;
	String buildNumber_AP=null;

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
		quotePage_PC = new QuotePage_PC();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		policySearchPage_AP = new PolicySearchPage_AP();
		summaryPage_PC = new SummaryPage_PC();
		pctest = new BOP_PolicyCenterTests();
		apTests = new BOP_AgencyPortalTests();
		glTests = new GL_AgencyPortalTests();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		paymentDetailsPage_AP = new PaymentDetailsPage_AP();
		newSubmissionPage_PC = new NewSubmissionPage_PC();
		offeringsPage_PC = new OfferingsPage_PC();
		policyInfoPage = new PolicyInfoPage_PC();
		businessOwnersLinePage_PC = new BusinessOwnersLinePage_PC();
		locationsPage_PC = new LocationsPage_PC();
		buildingsAndClassificationspage = new BuildingsAndClassificationsPage_PC();
		hazardGradeSelectionPage_PC = new HazardGradeSelectionPage_PC();
		classCodeSearchPage_PC = new ClassCodeSearchPage_PC();
		addressVerificationPage_PC = new AddressVerificationPage_PC();
		organizationsPage_PC = new OrganizationsPage_PC();
		blanketsPage_PC = new BlanketsPage_PC();
		supplementalPage = new SupplementalPage_PC();
		riskAnalysispage = new RiskAnalysisPage_PC();
		policyReviewPage = new PolicyReviewPage_PC();
		quotePage = new QuotePage_PC();
		formsPage_PC = new FormsPage_PC();
		paymentPage = new PaymentPage_PC();
		activitiesPage = new ActivitiesCreatedPage_BC();
		summaryPage_BC = new SummaryPage_BC();
		invoicesPage_BC = new InvoicesPage_BC();
		homepage_PC = new WrgHomePage_PC();
		enterAccountInfoPage = new EnterAccountInformationPage_PC();
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		quoteSearchPage = new QuoteSearchPage_AP();
		policySearchPage_AP = new PolicySearchPage_AP();
	}

	/*
	 * Following Steps Performed with this Function:
	 * 
	 * Create New Submission via Agent Portal Go to Policy Center And Change The
	 * Term Approve Underwriter Issues(If Any) and Quote the Submission Go to Agent
	 * Portal And Make The Payment Go to Policy Center And Issue the Policy
	 */
	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "term", "classCodeNumber", "formType", "percentageOwnerOccupiedValue",
			"effectiveDate" })
	@Test
	public void verifyNewEndToEndPolicyIsCreated(String pcUsers, String testCaseID, String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String term, String classCodeNumber, String formType,
			String percentageOwnerOccupiedValue, String effectiveDate) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Parameters are-> PC USers: " + pcUsers + ", TestCaseID: " + testCaseID
						+ ", Insured Name: " + insuredName + ", State: " + state + ", Number of Locations: "
						+ numberOfLocations + ", Organization Code: " + organizationCode + ", Password: " + password
						+ ", Insurance Type: " + insuranceType + ", Business Entity: " + businessEntity + ", Term: "
						+ term + ", Class Codes: " + classCodeNumber + ", Effective Date: " + effectiveDate
						+ ", FormType: " + formType + ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		apTests = new BOP_AgencyPortalTests();
		String applicantName = null;
		String quoteNumber = null;
		glTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = glTests.searchQuote(insuredName);
			quoteNumber = glTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
		}
		underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
		underwritingQuestionsPage_AP.beginSubmission();
		policySearchPage_AP.searchViaQuoteNumberUnderViewEditQuote(quoteNumber, applicantName, "Under UW Review");
		login(usersList.get(0).toString());
		homepage_PC.searchSubmission(quoteNumber);
		quotePage.goToPolicyInfoPage();
		policyInfoPage.editPolicyTransaction();
		policyInfoPage.setCustomTerm(term, effectiveDate);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyInfoPage.goToRiskAnalysisPage();
		// approveUnderwriterIssues(quoteNumber,underwritingCompany);
		riskAnalysispage.approveIssues();
		riskAnalysispage.goToPolicyReviewPage();
		policyReviewPage.quotePolicy();
		quotePage.releaseLockWithoutActivity();
		apTests.makePaymentOnAP(quoteNumber, organizationCode, password);
		apTests.completePayment();
		try {
			pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(1).toString());
		} catch (Exception e) {
			pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(0).toString());
		}
	}

	/*
	 * Following Steps Performed with this Function:
	 * 
	 * Create New Submission via Agent Portal with Multiple Locations Go to Policy
	 * Center And Change the Approve Underwrites Issues (If Any) and Quote the
	 * Submission via Policy Center Go to Agent Portal And Make The Payment Go to
	 * Policy Center And Issue The Policy
	 */
	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "term", "classCodeNumber", "formType", "percentageOwnerOccupiedValue",
			"effectiveDate"

	})

	@Test
	public void verifyNewEndToEndPolicyIsCreatedHavingMultipleLocations(String pcUsers, String testCaseID,
			String insuredName, String state, String numberOfLocations, String organizationCode, String password,
			String insuranceType, String businessEntity, String term, String classCodeNumber, String formType,
			String percentageOwnerOccupiedValue, String effectiveDate) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Parameters are-> PC USers: " + pcUsers + ", TestCaseID: " + testCaseID
						+ ", Insured Name: " + insuredName + ", State: " + state + ", Number of Locations: "
						+ numberOfLocations + ", Organization Code: " + organizationCode + ", Password :" + password
						+ ", Insurance Type: " + insuranceType + ", Business Entity: " + businessEntity + ", Term: "
						+ term + "," + " Class Codes: " + classCodeNumber + ", Effective Date: " + effectiveDate
						+ ", FormType: " + formType + ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		apTests = new BOP_AgencyPortalTests();
		String applicantName = null;
		String quoteNumber = null;
		glTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = glTests.searchQuote(insuredName);
			quoteNumber = glTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
		}
		underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
		underwritingQuestionsPage_AP.beginSubmission();
		policySearchPage_AP.searchViaQuoteNumberUnderViewEditQuote(quoteNumber, applicantName, "Under UW Review");
		login(usersList.get(0).toString());
		homepage_PC.searchSubmission(quoteNumber);
		quotePage.goToPolicyInfoPage();
		policyInfoPage.editPolicyTransaction();
		policyInfoPage.setCustomTerm(term, effectiveDate);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyInfoPage.goToRiskAnalysisPage();
		// approveUnderwriterIssues(quoteNumber,underwritingCompany);
		riskAnalysispage.approveIssues();
		riskAnalysispage.goToPolicyReviewPage();
		policyReviewPage.quotePolicy();
		sleep(10000);
		quotePage.releaseLockWithoutActivity();
		apTests.makePaymentOnAP(quoteNumber, organizationCode, password);
		apTests.completePayment();
		try {
			pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(1).toString());
		} catch (Exception e) {
			pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(0).toString());
		}
	}

	/*
	 * Following Steps Performed with this Function:
	 * 
	 * Create New Submission via Agent Portal Go to Policy Center And Change the
	 * Term Approve Underwriter Issues(If Any) and Quote the Submission via Policy
	 * Center
	 */
	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "organizationCode", "password",
			"insuranceType", "businessEntity", "term", "classCodeNumber", "formType", "percentageOwnerOccupiedValue",
			"effectiveDate" })
	@Test
	public void verifyNewQuoteIsCreated(String pcUsers, String testCaseID, String insuredName, String state,
			String numberOfLocations, String organizationCode, String password, String insuranceType,
			String businessEntity, String term, String classCodeNumber, String formType,
			String percentageOwnerOccupiedValue, String effectiveDate) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Parameters are-> PC USers: " + pcUsers + ", TestCaseID: " + testCaseID
						+ ", Insured Name: " + insuredName + ", State: " + state + ", Number of Locations: "
						+ numberOfLocations + ", Organization Code: " + organizationCode + ", Password: " + password
						+ ", Insurance Type: " + insuranceType + ", Business Entity: " + businessEntity + ", Term: "
						+ term + "," + " Class Codes: " + classCodeNumber + ", Effective Date: " + effectiveDate
						+ ", FormType: " + formType + ", Percentage Owner Occupied: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		apTests = new BOP_AgencyPortalTests();
		String applicantName = null;
		String quoteNumber = null;
		glTests.agentPortalLogin(organizationCode,password);
		buildNumber_AP=getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = glTests.searchQuote(insuredName);
			quoteNumber = glTests.newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber,
					formType, percentageOwnerOccupiedValue);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
		}
		underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
		underwritingQuestionsPage_AP.beginSubmission();
		policySearchPage_AP.searchViaQuoteNumberUnderViewEditQuote(quoteNumber, applicantName, "Under UW Review");
		login(usersList.get(0).toString());
		homepage_PC.searchSubmission(quoteNumber);
		quotePage.goToPolicyInfoPage();
		policyInfoPage.editPolicyTransaction();
		policyInfoPage.setCustomTerm(term, effectiveDate);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		policyInfoPage.goToRiskAnalysisPage();
		riskAnalysispage.approveIssues();
		riskAnalysispage.goToPolicyReviewPage();
		policyReviewPage.quotePolicy();
		quotePage.releaseLockWithoutActivity();
	}

	/*
	 * Following Steps Performed with this Function:
	 * 
	 * Begin New Submission via Policy Center with any term and approve Underwriter
	 * Issues (If Any) Go to Agent Portal and Make the Payment Go to Policy Center
	 * and Issue the Policy
	 */
	@Parameters({ "pcUsers", "insuredName", "state", "businessEntity", "organizationCode", "password",
			"classCodeNumber", "numberOfLocations", "numberOfBuildings", "term", "accountType", "effectiveDate",
			"formType", "percentageOwnerOccupiedValue" })
	@Test
	public void verifyNewPolicyIsCreatedViaPolicyCenter(String pcUsers, String insuredName, String state,
			String businessEntity, String organizationCode, String password, String classCodeNumber,
			String numberOfLocations, String numberOfBuildings, String term, String accountType, String effectiveDate,
			String formType, String percentageOwnerOccupiedValue) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO, MarkupHelper.createLabel(
				"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
						+ ", Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
						+ ", Password: " + password + ", Class Codes: " + classCodeNumber + ", Number Of Locations: "
						+ numberOfLocations + ", Number of Buildings: " + numberOfBuildings + ", Term: " + term
						+ ", Account Type: " + accountType + ", Effective Date: " + effectiveDate + ", FormType: "
						+ formType + ", Percentage Owner Occupied Value: " + percentageOwnerOccupiedValue,
				ExtentColor.PURPLE));
		pctest.createAccount(usersList.get(0).toString(), insuredName, state, businessEntity, organizationCode,
				classCodeNumber, accountType);
		String quoteNumber = pctest.newCustomTermQuote(term, effectiveDate, formType, classCodeNumber, state,
				numberOfLocations, numberOfBuildings,insuredName);
		quotePage.goToPolicyInfoPage();
		quotePage.goToRiskAnalysisPage();
		try {
			riskAnalysispage.approveIssues();
			riskAnalysispage.goToPolicyReviewPage();
		} catch (Exception e) {
			riskAnalysispage.goToPolicyReviewPage();
		}
		policyReviewPage.goToQuotePage();
		quotePage.releaseLockWithoutActivity();
		apTests.makePaymentOnAP(quoteNumber, organizationCode, password);
		underwritingGuidelinesPage.goToPolicyFormSelectionPage();
		policyFormSelectionPage_AP.policyForm(formType);
		policywideCoveragesPage_AP.coverages();
		optionalCoveragesPage_AP.optionalCoverages();
		locationsAndBuildingsPage_AP.clickQuoteButton();
		quotePage_AP.quote();
		underwritingQuestionsPage_AP.enterPhoneNumber();
		apTests.completePayment();
//		try {
//			pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(1).toString());
//		} catch (Exception e) {
//			pctest.issuePolicyFromPCWithoutPayment(quoteNumber, usersList.get(0).toString());
//		}
	}

//	@AfterTest
//	public void afterTest() throws IOException {
//		if (browser.equalsIgnoreCase("firefox")) {
//			Runtime.getRuntime().exec("taskkill /IM geckodriver.exe /T");
//		} else if (browser.equalsIgnoreCase("ie")) {
//			Runtime.getRuntime().exec("taskkill /IM IEDriverServer32.exe /T");
//		} else if (browser.equalsIgnoreCase("chrome")) {
//			Runtime.getRuntime().exec("taskkill /IM chromedriver.exe /T");
//		}
//	}

}
