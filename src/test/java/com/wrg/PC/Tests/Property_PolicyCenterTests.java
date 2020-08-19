package com.wrg.PC.Tests;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.PC.pages.AccountSummaryPage_PC;
import com.wrg.PC.pages.BlanketsPage_PC;
import com.wrg.PC.pages.BuildingsAndClassificationsPage_PC;
import com.wrg.PC.pages.BusinessOwnersLinePage_PC;
import com.wrg.PC.pages.CommercialPropertyLinePage_PC;
import com.wrg.PC.pages.LocationsPage_PC;
import com.wrg.PC.pages.NewSubmissionPage_PC;
import com.wrg.PC.pages.OfferingsPage_PC;
import com.wrg.PC.pages.PolicyInfoPage_PC;
import com.wrg.PC.pages.PolicyReviewPage_PC;
import com.wrg.PC.pages.QuotePage_PC;
import com.wrg.PC.pages.RiskAnalysisPage_PC;
import com.wrg.PC.pages.SupplementalPage_PC;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class Property_PolicyCenterTests extends AbstractTest {
	BOP_PolicyCenterTests bop_PCTests = null;
	AccountSummaryPage_PC accountSummaryPage_PC = null;
	NewSubmissionPage_PC newSubmissionPage_PC = null;
	OfferingsPage_PC offeringsPage_PC = null;
	PolicyInfoPage_PC policyInfoPage_PC = null;
	CommercialPropertyLinePage_PC commercialPropertyLinePage_PC = null;
	String policyNumber = null;
	String quoteNumber = null;
	String accountNumber = null;

	@BeforeMethod
	public void beforeMethod() {
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		newSubmissionPage_PC = new NewSubmissionPage_PC();
		offeringsPage_PC = new OfferingsPage_PC();
		policyInfoPage_PC = new PolicyInfoPage_PC();
		commercialPropertyLinePage_PC = new CommercialPropertyLinePage_PC();
		bop_PCTests = new BOP_PolicyCenterTests();

	}

	/* Create New Account */
	@Parameters({ "pcUsers", "insuredName", "state", "businessEntity", "organizationCode", "classCodeNumber", "term",
			"accountType", "effectiveDate", "formType" })
	@Test
	public void checkAllValidationsOnPolicyInfoPage(String pcUsers, String insuredName, String state,
			String businessEntity, String organizationCode, String classCodeNumber, String term, String accountType,
			String effectiveDate, String formType) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, businessEntity,
				organizationCode, classCodeNumber, accountType);
		// newCustomTermQuote(term, effectiveDate, formType, classCodeNumber);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accountSummaryPage_PC.createNewSubmission();
		newSubmissionPage_PC.selectCommercialPropertyClassCode();
		policyInfoPage_PC.checkAllElementOnPolicyInfoPage();
		policyInfoPage_PC.validateAddressScreenAppearsOnPolicyAdressChangeToDropdownclick();
		policyInfoPage_PC.checkValidationErrors();
	}

//	/* Verify All Fields on Policy Info Page */
//	@Parameters({ "pcUsers", "insuredName", "state", "businessEntity", "organizationCode", "classCodeNumber", "term",
//			"accountType", "effectiveDate", "formType" })
//	@Test
//	public void verifyFieldsOnPolicyInfoPage(String pcUsers, String insuredName, String state, String businessEntity,
//			String organizationCode, String classCodeNumber, String term, String accountType, String effectiveDate,
//			String formType) throws IOException {
//		List<String> usersList = getUsersList(pcUsers);
//		ExtentTestManager.getTest().log(Status.INFO,
//				MarkupHelper.createLabel(
//						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
//								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
//								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
//						ExtentColor.PURPLE));
//		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, businessEntity,
//				organizationCode, classCodeNumber, accountType);
//		accountSummaryPage_PC = new AccountSummaryPage_PC();
//		newSubmissionPage_PC = new NewSubmissionPage_PC();
//		// offeringsPage_PC = new OfferingsPage_PC();
//		policyInfoPage_PC = new PolicyInfoPage_PC();
//
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		accountSummaryPage_PC.createNewSubmission();
//		newSubmissionPage_PC.selectCommercialPropertyClassCode();
//		policyInfoPage_PC.checkAllElementOnPolicyInfoPage();
//		policyInfoPage_PC.validateAddressScreenAppearsOnPolicyAdressChangeToDropdownclick();
//		policyInfoPage_PC.checkValidationErrors();
//	}

	/*
	 * Verify Address Details Screen Appears On Policy Address-> Change To Dropdown
	 * click
	 * 
	 * @Parameters({ "pcUsers", "insuredName", "state", "businessEntity",
	 * "organizationCode", "classCodeNumber", "term", "accountType",
	 * "effectiveDate", "formType" })
	 * 
	 * @Test public void validateAddressDetailScreen(String pcUsers, String
	 * insuredName, String state, String businessEntity, String organizationCode,
	 * String classCodeNumber, String term, String accountType, String
	 * effectiveDate, String formType) throws IOException { List<String> usersList =
	 * getUsersList(pcUsers); ExtentTestManager.getTest().log(Status.INFO,
	 * MarkupHelper.createLabel( "Parameters are-> PC USers: " + pcUsers +
	 * ", Insured Name: " + insuredName + ", State: " + state +
	 * " , Business Entity: " + businessEntity + ", Organization Code: " +
	 * organizationCode + ", Class Codes: " + classCodeNumber + ", Account Type: " +
	 * accountType, ExtentColor.PURPLE)); accountNumber =
	 * bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state,
	 * businessEntity, organizationCode, classCodeNumber, accountType);
	 * accountSummaryPage_PC = new AccountSummaryPage_PC(); newSubmissionPage_PC =
	 * new NewSubmissionPage_PC(); // offeringsPage_PC = new OfferingsPage_PC();
	 * policyInfoPage_PC = new PolicyInfoPage_PC();
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * accountSummaryPage_PC.createNewSubmission();
	 * newSubmissionPage_PC.selectCommercialPropertyClassCode(); policyInfoPage_PC.
	 * validateAddressScreenAppearsOnPolicyAdressChangeToDropdownclick(); }
	 * 
	 * Verify Validation Errors
	 * 
	 * @Parameters({ "pcUsers", "insuredName", "state", "businessEntity",
	 * "organizationCode", "classCodeNumber", "term", "accountType",
	 * "effectiveDate", "formType"
	 * 
	 * })
	 * 
	 * @Test public void verifyValidationErrors(String pcUsers, String insuredName,
	 * String state, String businessEntity, String organizationCode, String
	 * classCodeNumber, String term, String accountType, String effectiveDate,
	 * String formType) throws IOException { List<String> usersList =
	 * getUsersList(pcUsers); ExtentTestManager.getTest().log(Status.INFO,
	 * MarkupHelper.createLabel( "Parameters are-> PC USers: " + pcUsers +
	 * ", Insured Name:" + insuredName + ", State: " + state +
	 * " , Business Entity: " + businessEntity + ", Organization Code: " +
	 * organizationCode + ", Class Codes: " + classCodeNumber + ", Account Type: " +
	 * accountType, ExtentColor.PURPLE)); accountNumber =
	 * bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state,
	 * businessEntity, organizationCode, classCodeNumber, accountType);
	 * accountSummaryPage_PC = new AccountSummaryPage_PC(); newSubmissionPage_PC =
	 * new NewSubmissionPage_PC(); // offeringsPage_PC = new OfferingsPage_PC();
	 * policyInfoPage_PC = new PolicyInfoPage_PC();
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * accountSummaryPage_PC.createNewSubmission();
	 * newSubmissionPage_PC.selectCommercialPropertyClassCode();
	 * policyInfoPage_PC.checkValidationErrors(); }
	 */

	/* Leap Year Test */
	@Parameters({ "pcUsers", "insuredName", "state", "businessEntity", "organizationCode", "classCodeNumber", "term",
			"accountType", "effectiveDate", "formType" })
	@Test
	public void validateLeapYearScenario(String pcUsers, String insuredName, String state, String businessEntity,
			String organizationCode, String classCodeNumber, String term, String accountType, String effectiveDate,
			String formType) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, businessEntity,
				organizationCode, classCodeNumber, accountType);
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		newSubmissionPage_PC = new NewSubmissionPage_PC();
		// offeringsPage_PC = new OfferingsPage_PC();
		policyInfoPage_PC = new PolicyInfoPage_PC();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accountSummaryPage_PC.createNewSubmission();
		newSubmissionPage_PC.selectCommercialPropertyClassCode();
		policyInfoPage_PC.checkLeapYearValidation(term, effectiveDate);
	}

	// Commercial Property Line Tests
	@Parameters({ "pcUsers", "insuredName", "state", "businessEntity", "organizationCode", "classCodeNumber", "term",
			"accountType", "effectiveDate", "formType" })
	@Test
	public void validatePolicyLevelAndAdditionalCoveragesCoveragesOnCommercialPropertyLinePage(String pcUsers, String insuredName,
			String state, String businessEntity, String organizationCode, String classCodeNumber, String term,
			String accountType, String effectiveDate, String formType) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, businessEntity,
				organizationCode, classCodeNumber, accountType);
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		newSubmissionPage_PC = new NewSubmissionPage_PC();
		policyInfoPage_PC = new PolicyInfoPage_PC();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accountSummaryPage_PC.createNewSubmission();
		newSubmissionPage_PC.selectCommercialPropertyClassCode();
		policyInfoPage_PC.setCustomTerm(term, effectiveDate);
		policyInfoPage_PC.goToNextPage();
		commercialPropertyLinePage_PC.validatePolicyLevelCoveragesPresence();
		commercialPropertyLinePage_PC.validateWIL60CoverageLabelsAndFields();
		commercialPropertyLinePage_PC.goToAdditionalCoveragesTab();
		commercialPropertyLinePage_PC.verifyAdditionalCoverages();
	}

//	@Parameters({ "pcUsers", "insuredName", "state", "businessEntity", "organizationCode", "classCodeNumber", "term",
//			"accountType", "effectiveDate", "formType" })
//	@Test
//	public void validatePresenceOfAdditionalCoveragesOnCommercialPropertyLinePage(String pcUsers, String insuredName,
//			String state, String businessEntity, String organizationCode, String classCodeNumber, String term,
//			String accountType, String effectiveDate, String formType) throws IOException {
//		List<String> usersList = getUsersList(pcUsers);
//		ExtentTestManager.getTest().log(Status.INFO,
//				MarkupHelper.createLabel(
//						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
//								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
//								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
//						ExtentColor.PURPLE));
//		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, businessEntity,
//				organizationCode, classCodeNumber, accountType);
//		accountSummaryPage_PC = new AccountSummaryPage_PC();
//		newSubmissionPage_PC = new NewSubmissionPage_PC();
//		policyInfoPage_PC = new PolicyInfoPage_PC();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		accountSummaryPage_PC.createNewSubmission();
//		newSubmissionPage_PC.selectCommercialPropertyClassCode();
//		policyInfoPage_PC.setCustomTerm(term, effectiveDate);
//		policyInfoPage_PC.goToNextPage();
//		commercialPropertyLinePage_PC.goToAdditionalCoveragesTab();
//		commercialPropertyLinePage_PC.verifyAdditionalCoverages();
//	}

	@Parameters({ "pcUsers", "insuredName", "state", "businessEntity", "organizationCode", "classCodeNumber", "term",
			"accountType", "effectiveDate", "formType" })
	@Test
	public void validateLabelsAndErrorMessagesForElectronicCommerceCoverage(String pcUsers, String insuredName,
			String state, String businessEntity, String organizationCode, String classCodeNumber, String term,
			String accountType, String effectiveDate, String formType) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, businessEntity,
				organizationCode, classCodeNumber, accountType);
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		newSubmissionPage_PC = new NewSubmissionPage_PC();
		policyInfoPage_PC = new PolicyInfoPage_PC();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accountSummaryPage_PC.createNewSubmission();
		newSubmissionPage_PC.selectCommercialPropertyClassCode();
		policyInfoPage_PC.setCustomTerm(term, effectiveDate);
		policyInfoPage_PC.goToNextPage();
		commercialPropertyLinePage_PC.goToAdditionalCoveragesTab();
		// commercialPropertyLinePage_PC.verifyAdditionalCoverages();
		commercialPropertyLinePage_PC.clickOnAddCoveragesButton();
		commercialPropertyLinePage_PC.addElectronicCommerceCoverage();
		Assert.assertTrue(commercialPropertyLinePage_PC.validateElectronicCommerceLabels(), "Labels are missing");
		commercialPropertyLinePage_PC.validateErrorMessagesForElectronicCommerceCoverage();
	}

	public String newCustomTermQuote(String term, String effectiveDate, String formType, String classCodeNumber)
			throws IOException {
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		newSubmissionPage_PC = new NewSubmissionPage_PC();
		// offeringsPage_PC = new OfferingsPage_PC();
		policyInfoPage_PC = new PolicyInfoPage_PC();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accountSummaryPage_PC.createNewSubmission();
		newSubmissionPage_PC.selectCommercialPropertyClassCode();
		// offeringsPage_PC.selectOffering(formType);
		policyInfoPage_PC.setCustomTerm(term, effectiveDate);
		policyInfoPage_PC.checkAllElementOnPolicyInfoPage();
		// policyInfoPage_PC.goToBusinessOwnerLinePage();

		return quoteNumber;
	}

	@AfterTest
	public void afterTest() throws IOException {
		if (browser.equalsIgnoreCase("firefox")) {
			Runtime.getRuntime().exec("taskkill /IM geckodriver.exe /T");
		} else if (browser.equalsIgnoreCase("ie")) {
			Runtime.getRuntime().exec("taskkill /IM IEDriverServer32.exe /T");
		} else if (browser.equalsIgnoreCase("chrome")) {
			Runtime.getRuntime().exec("taskkill /IM  chromedriver.exe /T /f");
		}
	}

}
