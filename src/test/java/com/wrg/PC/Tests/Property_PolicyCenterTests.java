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
import com.wrg.PC.pages.CoveragesPage_PC;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class Property_PolicyCenterTests extends AbstractTest {
	BOP_PolicyCenterTests bop_PCTests = null;
	AccountSummaryPage_PC accountSummaryPage_PC = null;
	NewSubmissionPage_PC newSubmissionPage_PC = null;
	OfferingsPage_PC offeringsPage_PC = null;
	PolicyInfoPage_PC policyInfoPage_PC = null;
	CommercialPropertyLinePage_PC commercialPropertyLinePage_PC = null;
	CoveragesPage_PC coveragesPage_PC=null;
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
		coveragesPage_PC = new CoveragesPage_PC();

	}

	/* Create New Account */
	@Parameters({ "pcUsers", "insuredName", "state", "insuranceType", "businessEntity", "organizationCode", "classCodeNumber", "term",
			"accountType", "effectiveDate", "formType" })
	@Test
	public void checkAllValidationsOnPolicyInfoPage(String pcUsers, String insuredName, String state,
			String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term, String accountType,
			String effectiveDate, String formType) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity,
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
	@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
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
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, "insuranceType",businessEntity,
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
	@Parameters({ "pcUsers", "insuredName", "state","insuranceType", "businessEntity", "organizationCode", "classCodeNumber", "term",
			"accountType", "effectiveDate", "formType" })
	@Test
	public void validatePolicyLevelAndAdditionalCoveragesCoveragesOnCommercialPropertyLinePage(String pcUsers, String insuredName,
			String state, String insuranceType,String businessEntity, String organizationCode, String classCodeNumber, String term,
			String accountType, String effectiveDate, String formType) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType,businessEntity,
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
		commercialPropertyLinePage_PC.validatePolicyDefaults();
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

	@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
			"accountType", "effectiveDate", "formType" })
	@Test
	public void US22280BuildingAdditionalCoverages(String pcUsers, String insuredName,
			String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
			String accountType, String effectiveDate, String formType) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity,
				organizationCode, classCodeNumber, accountType);
		accountSummaryPage_PC = new AccountSummaryPage_PC();
		newSubmissionPage_PC = new NewSubmissionPage_PC();
		policyInfoPage_PC = new PolicyInfoPage_PC();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accountSummaryPage_PC.createNewSubmission();
		newSubmissionPage_PC.selectCommercialPropertyClassCode();
		policyInfoPage_PC.setCustomTerm(term, effectiveDate);
		policyInfoPage_PC.goToNextPage();
		commercialPropertyLinePage_PC.goToNextPage();
		coveragesPage_PC.addBuilding();
		coveragesPage_PC.addBuildingDetails("Builders Risk","Broad","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
		coveragesPage_PC.goToAdditionalCoveragesTab();
		coveragesPage_PC.addAdditionalCoverages("CP 14 15");
		Assert.assertTrue(coveragesPage_PC.validateCP1415Coverages(), "Labels are missing");
		coveragesPage_PC.CP1415Coverages("test");
		coveragesPage_PC.removeItems("CP 14 15");
		coveragesPage_PC.validateCoveragesErrorMessage("You must enter at least 1 row of Additional Building Property");
		coveragesPage_PC.validateCoveragesErrorMessage("Please fill in all required fields.");
	}
@Parameters({ "pcUsers", "insuredName", "state", "insuranceType", "businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22298BuildingAdditionalCoverages2(String pcUsers, String insuredName,
		String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType,businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Leasehold Interest","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Collapse During Construction", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Building","Broad","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Collapse During Construction", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Building","Basic","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Collapse During Construction", 0);
	
}
	
@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22298BuildingAdditionalCoverages3(String pcUsers, String insuredName,
		String state, String insuranceType,String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Condominium Association","Basic","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Collapse During Construction", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Improvements and Betterments","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Collapse During Construction", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Legal Liability","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Collapse During Construction", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Tenant Glass","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Collapse During Construction", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();

}

@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22298BuildingAdditionalCoverages(String pcUsers, String insuredName,
		String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Builders Risk","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.addAdditionalCoverages("Builders Risk - Collapse During Construction");
	coveragesPage_PC.validateCP1120Coverages();
}


@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22304BuildingAdditionalCoverages(String pcUsers, String insuredName,
		String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Tenant Glass","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk Renovations", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Builders Risk","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.addAdditionalCoverages("Builders Risk Renovations");
	coveragesPage_PC.validateCP1113Coverages(state);
	coveragesPage_PC.addNewPerson(state);
	coveragesPage_PC.addNewPerson(state);
	coveragesPage_PC.validateCoveragesErrorMessage("Builders Risk Renovations : Cannot have same Loss Payee more than once");
	coveragesPage_PC.addNewCompany(state);
}

@Parameters({ "pcUsers", "insuredName", "state", "insuranceType", "businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22316BuildingAdditionalCoverages(String pcUsers, String insuredName,
		String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state,insuranceType, businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Tenant Glass","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Separate Or Sub-Contractors Coverage", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Builders Risk","Special","55", "2020", "Frame","0844","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.addAdditionalCoverages("Builders Risk - Separate Or Sub-Contractors Coverage");
	coveragesPage_PC.validateCP1115Coverages();
	coveragesPage_PC.validatesCP1115ErrorMessages("You must enter at least 1 row of Builders Risk - Separate Or Sub-Contractors Coverage");
	coveragesPage_PC.validatesCP1115ErrorMessages("Contractor : Missing required field \"Contractor\"");
	coveragesPage_PC.validatesCP1115ErrorMessages("Contractor : Exceeds the maximum length of 255 (344)");
	coveragesPage_PC.CP1115Coverages("testcontractor", "testinstallation");
}

@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22322BuildingAdditionalCoverages(String pcUsers, String insuredName,
		String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Builders Risk","Special","55", "2020", "Frame","1150","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.addAdditionalCoverages("Builders Risk - Theft Of Building Materials, Fixtures, Machinery, Equipment");
	coveragesPage_PC.validateCP1121Coverages();
	coveragesPage_PC.validatesCP1121CovergaesErrorMessages("Theft Limit : Missing required field \"Theft Limit\"");
	coveragesPage_PC.validatesCP1121CovergaesErrorMessages("Theft Limit : Must not be less than $1.00");
	coveragesPage_PC.validatesCP1121CovergaesErrorMessages("Theft Limit : Must be a numeric value");
	coveragesPage_PC.validatesCP1121CovergaesErrorMessages("Theft Limit : Must be no greater than $999,999,999.00");
}

@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22346BuildingAdditionalCoverages(String pcUsers, String insuredName,
		String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType,businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Building","Special","55", "2020", "Frame","0844","Amusement Centers","Actual Cash Value");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Builders Risk - Collapse During Construction", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Building","Special","55", "2020", "Frame","1150","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.addAdditionalCoverages("Increased Cost Of Loss And Related Expenses For Green Upgrades");
	coveragesPage_PC.validateGreenUpgradesCoverages();
	coveragesPage_PC.validatesGreenCoveragesErrorMessages("Increased Cost Of Loss Percentage : Missing required field \"Increased Cost Of Loss Percentage\"");
	coveragesPage_PC.validatesGreenCoveragesErrorMessages("Green Upgrades Limit : Must not be less than $1.00");
	coveragesPage_PC.validatesGreenCoveragesErrorMessages("Green Upgrades Limit : Must be a numeric value");
	coveragesPage_PC.validatesGreenCoveragesErrorMessages("Green Upgrades Limit : Must be no greater than $999,999,999.00");
	coveragesPage_PC.validatesGreenCoveragesErrorMessages("Description : Exceeds the maximum length of 255 (363)");
	coveragesPage_PC.validatesGreenCoveragesErrorMessages("Related Expenses Limit : Must not be less than $0.00");
	coveragesPage_PC.validatesGreenCoveragesErrorMessages("Related Expenses Limit : Must be no greater than $999,999,999.00");
}


@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22424BuildingAdditionalCoverages(String pcUsers, String insuredName,
		String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType,businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Building","Special","55", "2020", "Frame","0844","Amusement Centers","Actual Cash Value");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.verifyAdditionalCoverages("Pier And Wharf Additional Covered Causes Of Loss", 0);
	coveragesPage_PC.goToDetailsCoveragesTab();
	coveragesPage_PC.addBuildingDetails("Building","Broad","55", "2020", "Frame","1150","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.addAdditionalCoverages("Pier And Wharf Additional Covered Causes Of Loss");
	coveragesPage_PC.validateCP1070Coverages();
	coveragesPage_PC.validatesCP1070CoveragesErrorMessages("Construction : Missing required field \"Construction\"");
	//coveragesPage_PC.validateCoveragesErrorMessage("Construction : Missing required field \"Construction\"");
	coveragesPage_PC.validatesCoverageMenu("All Other Construction Including Pier Deck and Pier Platform, but Without Superstructure", "CP1070ConstructionMenu1");
	coveragesPage_PC.validatesCoverageMenu("All Other Construction - Substructure Including Superstructure, All Sides Enclosed", "CP1070ConstructionMenu2");
	coveragesPage_PC.validatesCoverageMenu("Fire Resistive Including Pier Deck and Pier Platform, but Without Superstructure", "CP1070ConstructionMenu3");
	coveragesPage_PC.validatesCoverageMenu("Fire Resistive - Substructure Including Superstructure, All Sides Enclosed", "CP1070ConstructionMenu4");
	coveragesPage_PC.validatesCoverageMenu("Non-Combustible and Heavy Timber - Substructure Including Superstructure, All Sides Enclosed", "CP1070ConstructionMenu5");
	coveragesPage_PC.validatesCoverageMenu("Substructure with Canopy Superstructure, Sides Not Enclosed", "CP1070ConstructionMenu6");
}


@Parameters({ "pcUsers", "insuredName", "state", "insuranceType","businessEntity", "organizationCode", "classCodeNumber", "term",
		"accountType", "effectiveDate", "formType" })
@Test
public void US22288BuildingAdditionalCoverages(String pcUsers, String insuredName,
		String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
		String accountType, String effectiveDate, String formType) throws IOException {
	List<String> usersList = getUsersList(pcUsers);
	ExtentTestManager.getTest().log(Status.INFO,
			MarkupHelper.createLabel(
					"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
							+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
							+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
					ExtentColor.PURPLE));
	accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity,
			organizationCode, classCodeNumber, accountType);
	accountSummaryPage_PC = new AccountSummaryPage_PC();
	newSubmissionPage_PC = new NewSubmissionPage_PC();
	policyInfoPage_PC = new PolicyInfoPage_PC();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accountSummaryPage_PC.createNewSubmission();
	newSubmissionPage_PC.selectCommercialPropertyClassCode();
	policyInfoPage_PC.setCustomTerm(term, effectiveDate);
	policyInfoPage_PC.goToNextPage();
	commercialPropertyLinePage_PC.goToNextPage();
	coveragesPage_PC.addBuilding();
	coveragesPage_PC.addBuildingDetails("Building","Special","55", "2020", "Frame","1150","Amusement Centers","Replacement Cost");
	coveragesPage_PC.goToAdditionalCoveragesTab();
	coveragesPage_PC.addAdditionalCoverages("Additional Property NOT Covered");
	coveragesPage_PC.additionalPropertyNotCoveredCoverages();
	coveragesPage_PC.validateCoveragesErrorMessage("Performed by : Exceeds the maximum length of 255 (263)");
	coveragesPage_PC.validateCoveragesErrorMessage("Description of Excluded Stock : Exceeds the maximum length of 255 (263)");
	//coveragesPage_PC.validateCoveragesErrorMessage("Green Upgrades Limit : Must be a numeric value");
	//coveragesPage_PC.validateCoveragesErrorMessage("Green Upgrades Limit : Must be no greater than $999,999,999.00");
	
}
	@Parameters({ "pcUsers", "insuredName", "state","insuranceType", "businessEntity", "organizationCode", "classCodeNumber", "term",
			"accountType", "effectiveDate", "formType" })
	@Test
	public void validateLabelsAndErrorMessagesForElectronicCommerceCoverage(String pcUsers, String insuredName,
			String state, String insuranceType, String businessEntity, String organizationCode, String classCodeNumber, String term,
			String accountType, String effectiveDate, String formType) throws IOException {
		List<String> usersList = getUsersList(pcUsers);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> PC USers: " + pcUsers + ", Insured Name: " + insuredName + ", State: " + state
								+ " , Business Entity: " + businessEntity + ", Organization Code: " + organizationCode
								+ ", Class Codes: " + classCodeNumber + ", Account Type: " + accountType,
						ExtentColor.PURPLE));
		accountNumber = bop_PCTests.createAccount(usersList.get(0).toString(), insuredName, state, insuranceType, businessEntity,
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
