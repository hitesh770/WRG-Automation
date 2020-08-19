package com.wrg.Regression.Tests;

import java.io.IOException;
import java.util.List;

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
import com.wrg.BC.pages.InvoiceStreamsPage_BC;
import com.wrg.BC.pages.InvoicesPage_BC;
import com.wrg.BC.pages.SummaryPage_BC;
import com.wrg.PC.Tests.BOP_PolicyCenterTests;
import com.wrg.PC.Tests.Property_PolicyCenterTests;
import com.wrg.PC.pages.AccountSummaryPage_PC;
import com.wrg.PC.pages.AddressVerificationPage_PC;
import com.wrg.PC.pages.BlanketsPage_PC;
import com.wrg.PC.pages.BuildingsAndClassificationsPage_PC;
import com.wrg.PC.pages.BusinessOwnersLinePage_PC;
import com.wrg.PC.pages.CGLExposuresPage_PC;
import com.wrg.PC.pages.ClassCodeSearchPage_PC;
import com.wrg.PC.pages.CoveragePartselectionPage_PC;
import com.wrg.PC.pages.CoveragesPage_PC;
import com.wrg.PC.pages.EditAccountPage_PC;
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
import com.wrg.PC.pages.RateFactorsPage_PC;
import com.wrg.PC.pages.RiskAnalysisPage_PC;
import com.wrg.PC.pages.SummaryPage_PC;
import com.wrg.PC.pages.SupplementalPage_PC;
import com.wrg.PC.pages.WrgHomePage_PC;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class BC_RegressionTests extends AbstractTest {

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
	PolicyInfoPage_PC policyInfoPage_PC = null;
	BusinessOwnersLinePage_PC businessOwnersLinePage_PC = null;
	LocationsPage_PC locationsPage_PC = null;
	BuildingsAndClassificationsPage_PC buildingsAndClassificationspage = null;
	BlanketsPage_PC blanketsPage_PC = null;
	SupplementalPage_PC supplementalPage = null;
	RiskAnalysisPage_PC riskAnalysispage_PC = null;
	PolicyReviewPage_PC policyReviewPage_PC = null;
	EditAccountPage_PC editAccountPage_PC = null;
	FormsPage_PC formsPage_PC = null;
	PaymentPage_PC paymentPage = null;
	ActivitiesCreatedPage_BC activitiesPage = null;
	SummaryPage_BC summaryPage_BC = null;
	InvoicesPage_BC invoicesPage_BC = null;
	InvoiceStreamsPage_BC invoiceStreamsPag_BC = null;
	CoveragePartselectionPage_PC coveragePartsSelectionPage_PC = null;
	CoveragesPage_PC coveragesPage_PC = null;
	CGLExposuresPage_PC cglExposuresPage_PC = null;
	RateFactorsPage_PC rateFactorsPage_PC = null;
	ActivitiesCreatedPage_BC activitiesCreatedPage_BC = null;
	GL_AgencyPortalTests glTests = null;
	BOP_PolicyCenterTests bop_PCTests = null;
	Property_PolicyCenterTests property_PCTests = null;
	GL_AgencyPortalTests gl_APTests = null;
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
		policyInfoPage_PC = new PolicyInfoPage_PC();
		businessOwnersLinePage_PC = new BusinessOwnersLinePage_PC();
		locationsPage_PC = new LocationsPage_PC();
		buildingsAndClassificationspage = new BuildingsAndClassificationsPage_PC();
		hazardGradeSelectionPage_PC = new HazardGradeSelectionPage_PC();
		classCodeSearchPage_PC = new ClassCodeSearchPage_PC();
		addressVerificationPage_PC = new AddressVerificationPage_PC();
		organizationsPage_PC = new OrganizationsPage_PC();
		blanketsPage_PC = new BlanketsPage_PC();
		supplementalPage = new SupplementalPage_PC();
		riskAnalysispage_PC = new RiskAnalysisPage_PC();
		policyReviewPage_PC = new PolicyReviewPage_PC();
		formsPage_PC = new FormsPage_PC();
		paymentPage = new PaymentPage_PC();
		activitiesPage = new ActivitiesCreatedPage_BC();
		summaryPage_BC = new SummaryPage_BC();
		invoicesPage_BC = new InvoicesPage_BC();
		invoiceStreamsPag_BC = new InvoiceStreamsPage_BC();
		homepage_PC = new WrgHomePage_PC();
		editAccountPage_PC = new EditAccountPage_PC();
		enterAccountInfoPage = new EnterAccountInformationPage_PC();
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		coveragePartsSelectionPage_PC = new CoveragePartselectionPage_PC();
		coveragesPage_PC = new CoveragesPage_PC();
		cglExposuresPage_PC = new CGLExposuresPage_PC();
		rateFactorsPage_PC = new RateFactorsPage_PC();
		quoteSearchPage = new QuoteSearchPage_AP();
		activitiesCreatedPage_BC = new ActivitiesCreatedPage_BC();
		policySearchPage_AP = new PolicySearchPage_AP();
		bop_PCTests = new BOP_PolicyCenterTests();
		property_PCTests = new Property_PolicyCenterTests();
		gl_APTests = new GL_AgencyPortalTests();
	}

	/*
	 * Create New Account Create New Submission Quote the Submission
	 */
//	@Parameters({ "pcUsers", "insuredName", "state", "businessEntity", "organizationCode", "classCodeNumber",
//			"numberOfLocations", "numberOfBuildings", "term", "accountType", "effectiveDate", "formType" })
//	@Test
//	public void verifyNewQuoteIsCreatedViaPolicyCenter(String pcUsers, String insuredName, String state,
//			String businessEntity, String organizationCode, String classCodeNumber, String numberOfLocations,
//			String numberOfBuildings, String term, String accountType, String effectiveDate, String formType)
//			throws IOException {
//		List<String> usersList = getUsersList(pcUsers);
//		ExtentTestManager.getTest().log(Status.INFO,
//				MarkupHelper.createLabel(
//						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
//								+ ", Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
//								+ ", Class Codes: " + classCodeNumber + ", Number of Locations: " + numberOfLocations
//								+ ", Number of Buildings: " + numberOfBuildings + ", Term: " + term + ", Account Type: "
//								+ accountType + ", Effective Date: " + effectiveDate + ", FormType: " + formType,
//						ExtentColor.PURPLE));
//		bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, businessEntity, organizationCode,
//				classCodeNumber, accountType);
//		String quoteNumber = bop_PCTests.newCustomTermQuote(term, effectiveDate, formType, classCodeNumber, state,
//				numberOfLocations, numberOfBuildings);
//		quotePage_PC.goToPolicyInfoPage();
//		quotePage_PC.goToRiskAnalysisPage();
//		try {
//			riskAnalysispage_PC.approveIssues();
//			riskAnalysispage_PC.goToPolicyReviewPage();
//		} catch (Exception e) {
//			riskAnalysispage_PC.goToPolicyReviewPage();
//		}
//		policyReviewPage_PC.goToQuotePage();
//		quotePage_PC.releaseLockWithoutActivity();
//		quotePage_PC.goToFormsPage();
//		formsPage_PC.goToPaymentPage();
//		paymentPage.IssuePolicy();
//		paymentPage.getPolicyNumber();
//		String accountNumber = summaryPage_PC.getAccountNumber();
//		summaryPage_PC.clickAccountNumber();
//		accountSummaryPage_PC.clickEditAccountButton();
//		editAccountPage_PC.addClassCode("09011");
//		sleep(2000);
//		bop_PCTests.newCustomTermQuote("30", "04/01/2021", formType, classCodeNumber, state, numberOfLocations,
//				numberOfBuildings);
//		quotePage_PC.goToRiskAnalysisPage();
//		riskAnalysispage_PC.approveIssues();
//		riskAnalysispage_PC.goToPolicyReviewPage();
//		policyReviewPage_PC.goToQuotePage();
//		quotePage_PC.goToFormsPage();
//		formsPage_PC.goToPaymentPage();
//		paymentPage.IssuePolicy();
//		String policyNumber2 = paymentPage.getPolicyNumber();
//		summaryPage_PC.clickAccountNumber();
//		summaryPage_PC.getPolicyNumbers();
//		billingCenterLogin("QA1_BGA");
//		activitiesCreatedPage_BC.searchAccount(accountNumber);
//		summaryPage_BC.goToInvoiceStreamsPage();
//		invoiceStreamsPag_BC.getInvoiceStreams();
//	}

	@Parameters({ "pcUsers", "testCaseID", "insuredName", "state", "numberOfLocations", "numberOfBuildings",
			"organizationCode", "password", "insuranceType", "businessEntity", "classCodeNumber", "classCodeNumber2",
			"formType", "percentageOwnerOccupiedValue", "effectiveDate1", "term1", "effectiveDate2", "term2",
			"paymentMethod", "downPaymentMethod", "singleInvoiceStream" })
	@Test
	public void create2PolicesLinkedToSingleAccount(String pcUsers, String testCaseID, String insuredName, String state,
			String numberOfLocations, String numberOfBuildings, String organizationCode, String password,
			String insuranceType, String businessEntity, String classCodeNumber, String classCodeNumber2,
			String formType, String percentageOwnerOccupiedValue, String effectiveDate1, String term1,
			String effectiveDate2, String term2, String paymentMethod, String downPaymentMethod,
			boolean singleInvoiceStream) throws IOException, InterruptedException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Parameters are-> PC Users: " + pcUsers + ", TestCase ID: " + testCaseID
						+ "Insured Name: " + insuredName + ", State: " + state + ", Number of Locations: "
						+ numberOfLocations + ", Number of Buildings: " + numberOfBuildings + ", Organization Code: "
						+ organizationCode + ", Password: " + password + ", Insurance Type: " + insuranceType
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
		sleep(3000);
		quotePage_PC.goToPolicyInfoPage();
		policyInfoPage_PC.editPolicyTransaction();
		policyInfoPage_PC.setCustomTerm(term1, effectiveDate1);
		if (insuranceType.equalsIgnoreCase("businessowners")) {
			policyInfoPage_PC.goToRiskAnalysisPage();
			riskAnalysispage_PC.approveIssues();
			riskAnalysispage_PC.goToPolicyReviewPage();
			policyReviewPage_PC.quotePolicy();
			quotePage_PC.releaseLockWithoutActivity();
		} else {
			policyInfoPage_PC.goToNextPage();
			sleep(3000);
			coveragePartsSelectionPage_PC.goToNextPage();
			sleep(2000);
			locationsPage_PC.goToNextPage();
			sleep(2000);
			coveragesPage_PC.goToNextPage();
			sleep(2000);
			cglExposuresPage_PC.goToNextPage();
			sleep(2000);
			// rateFactorsPage_PC.updateEstimatedOverridePotential();
			// rateFactorsPage_PC.goToNextPage();
			supplementalPage.goToNextPage();
			riskAnalysispage_PC.approveIssues();
			riskAnalysispage_PC.goToPolicyReviewPage();
			policyReviewPage_PC.quotePolicy();
			quotePage_PC.releaseLockWithoutActivity();
		}
		quotePage_PC.goToFormsPage();
		formsPage_PC.goToPaymentPage();
		paymentPage.IssuePolicy(paymentMethod, downPaymentMethod, "NA");
		String policyNumber1 = paymentPage.getPolicyNumber();
		String accountNumber = summaryPage_PC.getAccountNumber();
		summaryPage_PC.clickAccountNumber();
		accountSummaryPage_PC.clickEditAccountButton();
		editAccountPage_PC.addClassCode("09011");
		sleep(2000);
		bop_PCTests.newCustomTermQuote(term2, effectiveDate2, formType, classCodeNumber2, state, numberOfLocations,
				numberOfBuildings, insuredName);
		quotePage_PC.goToRiskAnalysisPage();
		riskAnalysispage_PC.approveIssues();
		riskAnalysispage_PC.goToPolicyReviewPage();
		policyReviewPage_PC.goToQuotePage();

		quotePage_PC.goToFormsPage();
		formsPage_PC.goToPaymentPage();
		if (!insuranceType.equalsIgnoreCase("businessowners") || singleInvoiceStream==true) {
			paymentPage.billToSameAccount();
		}
		paymentPage.IssuePolicy(paymentMethod, downPaymentMethod, "0");
		String policyNumber2 = paymentPage.getPolicyNumber();
		summaryPage_PC.clickAccountNumber();
		summaryPage_PC.getPolicyNumbers();
		billingCenterLogin("QA1_BGA");
		activitiesCreatedPage_BC.searchAccount(accountNumber);
		summaryPage_BC.goToInvoiceStreamsPage();
		invoiceStreamsPag_BC.getInvoiceStreams();
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
