package com.wrg.PC.Tests;

import java.awt.SplashScreen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.AP.Tests.BOP_AgencyPortalTests;
import com.wrg.AP.pages.PaymentDetailsPage_AP;
import com.wrg.AP.pages.PolicySearchPage_AP;
import com.wrg.AP.pages.QuoteSearchPage_AP;
import com.wrg.AP.pages.UnderwritingInfoAndApplicationPage_AP;
import com.wrg.BC.pages.ActivitiesCreatedPage_BC;
import com.wrg.BC.pages.InvoicesPage_BC;
import com.wrg.BC.pages.SummaryPage_BC;
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
import com.wrg.PC.pages.SupplementalPage_PC;
import com.wrg.PC.pages.WrgHomePage_PC;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class BOP_PolicyCenterTests extends AbstractTest {
	EnterAccountInformationPage_PC enterAccountInfoPage = null;
	WrgHomePage_PC homepage = null;
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
	PolicySearchPage_AP policySearchPage_AP = null;
	UnderwritingInfoAndApplicationPage_AP underwritingQuestionsPage_AP = null;
	PaymentDetailsPage_AP paymentDetailsPage_AP = null;
	BOP_AgencyPortalTests apTests = null;
	QuoteSearchPage_AP quoteSearchPage = null;

	String policyNumber = null;
	String quoteNumber = null;

	@BeforeMethod
	public void beforeMethod() {
		apTests = new BOP_AgencyPortalTests();
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
		homepage = new WrgHomePage_PC();
		enterAccountInfoPage = new EnterAccountInformationPage_PC();
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		quoteSearchPage = new QuoteSearchPage_AP();
		policySearchPage_AP = new PolicySearchPage_AP();
	}

	/*
	 * Create New Account Create New Submission Quote the Submission
	 */
	@Parameters({ "pcUsers", "insuredName", "state","insuranceType", "businessEntity", "organizationCode", "classCodeNumber",
			"numberOfLocations", "numberOfBuildings", "term", "accountType", "effectiveDate", "formType" })
	@Test
	public void verifyNewQuoteIsCreatedViaPolicyCenter(String pcUsers, String insuredName, String state,
			String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String numberOfLocations,
			String numberOfBuildings, String term, String accountType, String effectiveDate, String formType)
			throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ ", Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Number Of Locations: " + numberOfLocations
								+ ", NUmber of Buildings: " + numberOfBuildings + ", Term: " + term + ", Account Type: "
								+ accountType + ", Effective Date: " + effectiveDate + ", FormType: " + formType,
						ExtentColor.PURPLE));
		createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity, organizationCode,
				classCodeNumber, accountType);
		quoteNumber = newCustomTermQuote(term, effectiveDate, formType, classCodeNumber, state, numberOfLocations,
				numberOfBuildings,insuredName);
	}

	/* Create New Account */
	@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber",
			"accountType" })
	@Test
	public void verifyNewAccountIsCreatedViaPolicyCenter(String pcUsers, String insuredName, String state,
			String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String accountType)
			throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		createAccount(usersList.get(0).toString(), insuredName, state,insuranceType, businessEntity, organizationCode,
				classCodeNumber, accountType);
	}

	public String createAccount(String pcUser, String insuredName, String state, String insuranceType, String businessEntity,
			String organizationCode, String classCodeNumber, String accountType) throws IOException {
		String accountNumber = null;
		homepage = new WrgHomePage_PC();
		enterAccountInfoPage = new EnterAccountInformationPage_PC();
		organizationsPage_PC = new OrganizationsPage_PC();
		classCodeSearchPage_PC = new ClassCodeSearchPage_PC();
		hazardGradeSelectionPage_PC = new HazardGradeSelectionPage_PC();
		addressVerificationPage_PC = new AddressVerificationPage_PC();
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		login(pcUser);
		homepage.createNewAccount();
		enterAccountInfoPage.searchForAccount();
		enterAccountInfoPage.selectAccountType(accountType);
		enterAccountInfoPage.enterInsuredName(insuredName);
		enterAccountInfoPage.enterAddress(state, businessEntity);
		organizationsPage_PC.selectOrganization(organizationCode);
		if (classCodeNumber.contains(",")) {
			String spiltClassCodes[] = classCodeNumber.split(",");
			List<String> classCodes = new ArrayList<String>();
			for (String classCode : spiltClassCodes) {
				classCodes.add(classCode);
				if (insuranceType.contains("property")) {
					classCodeSearchPage_PC.selectClassCode(classCode, "GL");
				}else {
					classCodeSearchPage_PC.selectClassCode(classCode);
				}
				
				hazardGradeSelectionPage_PC.selecthazardGrade();
			}
		} else {
			if (insuranceType.contains("property")) {
				classCodeSearchPage_PC.selectClassCode(classCodeNumber, "GL");
			}else {
				classCodeSearchPage_PC.selectClassCode(classCodeNumber);
			}
			
			hazardGradeSelectionPage_PC.selecthazardGrade();
		}
		enterAccountInfoPage.updateAccount();
		addressVerificationPage_PC.addressUpdate();
		accountNumber = accountSummaryPage_PC.getAccountNumber();
		accountSummaryPage_PC.isAccountCreationSuccessful();
		return accountNumber;
	}

	public void searchAccount(String accountNumber) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homepage.searchAccount(accountNumber);
		accountSummaryPage_PC.createNewSubmission();
	}

	public void searchPolicyInPC(String policyNumber) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homepage.searchPolicy(policyNumber);
	}

	public void searchPolicyInBC() {
		activitiesPage.searchPolicy(policyNumber);
	}

//	// Create New submission on Policy Center for a Custom Term
//	public String newCustomTermSubmission(String days, String effectiveDate, String formType, String classCodeNumber, String state, String numberOfLocations, String numberOfBuildings)
//			throws IOException {
//		String policyNumber = null;
//		newCustomTermQuote(days, effectiveDate, formType, classCodeNumber,state, numberOfLocations, numberOfBuildings);
//		quotePage.goToFormsPage();
//		formsPage_PC.goToPaymentPage();
//		paymentPage.IssuePolicy();
//		policyNumber = paymentPage.getPolicyNumber();
//		return policyNumber;
//	}

	public String newCustomTermQuote(String days, String effectiveDate, String formType, String classCodeNumber,
			String state, String numberOfLocations, String numberOfBuildings,String insuredName) throws IOException {
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		newSubmissionPage_PC = new NewSubmissionPage_PC();
		offeringsPage_PC = new OfferingsPage_PC();
		policyInfoPage = new PolicyInfoPage_PC();
		businessOwnersLinePage_PC = new BusinessOwnersLinePage_PC();
		locationsPage_PC = new LocationsPage_PC();
		buildingsAndClassificationspage = new BuildingsAndClassificationsPage_PC();
		supplementalPage = new SupplementalPage_PC();
		blanketsPage_PC = new BlanketsPage_PC();
		riskAnalysispage = new RiskAnalysisPage_PC();
		policyReviewPage = new PolicyReviewPage_PC();
		quotePage = new QuotePage_PC();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accountSummaryPage_PC.createNewSubmission();
		newSubmissionPage_PC.selectBOPClassCode();
		offeringsPage_PC.selectOffering(formType);
		policyInfoPage.setCustomTerm(days, effectiveDate);
		policyInfoPage.goToNextPage();
		businessOwnersLinePage_PC.goToLocationsPage();
		if(Integer.parseInt(numberOfLocations)>1) {
		locationsPage_PC.addNewLocation(state, numberOfLocations);
		}
		sleep(2000);
		locationsPage_PC.goToNextPage();
		buildingsAndClassificationspage.addBuilding(classCodeNumber, state, numberOfBuildings,insuredName);
		buildingsAndClassificationspage.goToBlanketsPage();
		blanketsPage_PC.goToSupplementalPage();
		supplementalPage.generalQuestions();
		supplementalPage.goToRiskAnalysisPage();
		riskAnalysispage.goToPolicyReviewPage();
		policyReviewPage.quotePolicy();
		quotePage.releaseLockWithoutActivity();
		String quoteNumber = quotePage.getQuoteNumber();
		return quoteNumber;
	}

	public void approveUnderwriterIssues(String quoteNumber, String underwritingCompany, String pcUser) {
		login(pcUser);
		homepage.searchSubmission(quoteNumber);
		if (underwritingCompany != null) {
			quotePage.goToPolicyInfoPage();
			policyInfoPage.editPolicyTransaction();
			policyInfoPage.changeUnderwritingCompany(underwritingCompany);
		}
		quotePage.goToRiskAnalysisPage();
		riskAnalysispage.approveIssues();
		riskAnalysispage.goToPolicyReviewPage();
		policyReviewPage.quotePolicy();
		// policyReviewPage.goToQuotePage();
		quotePage.releaseLockWithoutActivity();
	}

	public String issuePolicyFromPCWithoutPayment(String quoteNumber, String pcUser) {
		login(pcUser);
		homepage = new WrgHomePage_PC();
		quotePage = new QuotePage_PC();
		homepage.searchSubmission(quoteNumber);
		quotePage.issuePolicyWithoutMakingPayment();
		String policyNumber = quotePage.getPolicyNumber();
		return policyNumber;
	}

	@AfterTest
	public void afterTest() throws IOException {
		if (browser.equalsIgnoreCase("firefox")) {
			Runtime.getRuntime().exec("taskkill /IM geckodriver.exe /T");
		} else if (browser.equalsIgnoreCase("ie")) {
			Runtime.getRuntime().exec("taskkill /IM IEDriverServer32.exe /T");
		} else if (browser.equalsIgnoreCase("chrome")) {
			Runtime.getRuntime().exec("taskkill /IM  chromedriver.exe /T");
		}
	}

}
