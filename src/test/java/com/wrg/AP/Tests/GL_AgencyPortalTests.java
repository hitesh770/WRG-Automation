package com.wrg.AP.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.AP.pages.ApplicantInformationPage_AP;
import com.wrg.AP.pages.ClassificationsPage_AP;
import com.wrg.AP.pages.LocationsAndBuildingsPage_AP;
import com.wrg.AP.pages.LocationsPage_AP;
import com.wrg.AP.pages.OptionalCoveragesPage_AP;
import com.wrg.AP.pages.PaymentDetailsPage_AP;
import com.wrg.AP.pages.PolicyFormSelectionPage_AP;
import com.wrg.AP.pages.PolicySearchPage_AP;
import com.wrg.AP.pages.PolicywideCoveragesPage_AP;
import com.wrg.AP.pages.QuotePage_AP;
import com.wrg.AP.pages.QuoteSearchPage_AP;
import com.wrg.AP.pages.StartQuotePage_AP;
import com.wrg.AP.pages.UnderwritingGuidelinesPage_AP;
import com.wrg.AP.pages.UnderwritingInfoAndApplicationPage_AP;
import com.wrg.AP.pages.WrgHomePage_AP;
import com.wrg.PC.Tests.BOP_PolicyCenterTests;
import com.wrg.PC.pages.LocationsPage_PC;
import com.wrg.PC.pages.SummaryPage_PC;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class GL_AgencyPortalTests extends AbstractTest {

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
	StartQuotePage_AP startQuotePage_AP = null;
	LocationsPage_AP locationsPage_AP = null;
	ClassificationsPage_AP classificationPage_AP = null;
	BOP_AgencyPortalTests apTests = null;
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
		startQuotePage_AP = new StartQuotePage_AP();
		locationsPage_AP = new LocationsPage_AP();
		apTests = new BOP_AgencyPortalTests();
	}

	@Parameters({ "insuredName", "state", "numberOfLocations", "organizationCode", "password", "insuranceType",
			"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void createNewQuoteViaAgentPortalForGL(String insuredName, String state, String numberOfLocations,
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
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state + ", Organization Code: "
								+ organizationCode + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String quoteNumber = null;
		String applicantName = null;
		agentPortalLogin(organizationCode, password);
		buildNumber_AP = getAgentPortalBuild();
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			quoteNumber = newQuote(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);
		}

//		String applicantName = searchQuote(state);
//		String quoteNumber = newQuote(state, businessEntity, classCodeNumber, formType, percentageOwnerOccupiedValue);
//		underwritingQuestionsPage_AP.clickBeginSubmissionBtn();
//		underwritingQuestionsPage_AP.beginSubmission();
//		policySearchPage_AP.searchViaQuoteNumberUnderViewEditQuote(quoteNumber, applicantName, "Under UW Review");
	}

	public String newQuote(String state, String numberOfLocations, String insuranceType, String businessEntity,
			String classCodeNumber, String formType, String percentageOwnerOccupiedValue) {
		applicantInfoPage_AP = new ApplicantInformationPage_AP();
		underwritingGuidelinesPage = new UnderwritingGuidelinesPage_AP();
		policyFormSelectionPage_AP = new PolicyFormSelectionPage_AP();
		policywideCoveragesPage_AP = new PolicywideCoveragesPage_AP();
		optionalCoveragesPage_AP = new OptionalCoveragesPage_AP();
		locationsAndBuildingsPage_AP = new LocationsAndBuildingsPage_AP();
		quotePage_AP = new QuotePage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		startQuotePage_AP = new StartQuotePage_AP();
		locationsPage_AP = new LocationsPage_AP();
		classificationPage_AP = new ClassificationsPage_AP();
		applicantInfoPage_AP.enterAddress(state, businessEntity);
		applicantInfoPage_AP.selectInsuranceType(insuranceType);
		applicantInfoPage_AP.clickNextButton();
		sleep(2000);
		startQuotePage_AP.addClassification(classCodeNumber);
		String quoteNumber = null;
		if (insuranceType.equalsIgnoreCase("Businessowners")) {
			quoteNumber = underwritingGuidelinesPage.goToPolicyFormSelectionPage();
			policyFormSelectionPage_AP.policyForm(formType);
			policywideCoveragesPage_AP.coverages();
			optionalCoveragesPage_AP.optionalCoverages();
			locationsAndBuildingsPage_AP.addMultipleLocations(state, percentageOwnerOccupiedValue, numberOfLocations,
					classCodeNumber);
			quotePage_AP.quote();
			underwritingQuestionsPage_AP.answerQuestions();
		} else if (insuranceType.equalsIgnoreCase("General Liability")) {
			quoteNumber = underwritingGuidelinesPage.goToPolicyWideCoveragesPage(classCodeNumber);
			policywideCoveragesPage_AP.coverages();
			locationsPage_AP.goToClassificationsPage(state, numberOfLocations);
			classificationPage_AP.addClassifications(classCodeNumber, "10000", numberOfLocations);
			optionalCoveragesPage_AP.quote();

			List<String> codes = new ArrayList<String>();
			if (classCodeNumber.contains(",")) {
				String[] codesArray = classCodeNumber.split(",");
				for (String code : codesArray) {
					codes.add(code);
				}
				int arraySize = elpClassCodeArray.length;
				for (int i = 0; i < arraySize; i++) {
					for (String classCode : codes) {
						if (elpClassCodeArray[i].equalsIgnoreCase(classCode)) {
							break;
						} else if (!elpClassCodeArray[i].equalsIgnoreCase(classCode)) {
							try {
								quotePage_AP.quote();
								underwritingQuestionsPage_AP.glNonEplQuestions();
								break;
							} catch (Exception e) {
								break;
							}
						}
					}
					break;

				}
			} else {
				for (int i = 0; i < elpClassCodeArray.length; i++) {
					if (elpClassCodeArray[i].equalsIgnoreCase(classCodeNumber)) {
						break;
					} else {
						quotePage_AP.quote();
						underwritingQuestionsPage_AP.glNonEplQuestions();
					}
				}
			}
		}
		return quoteNumber;
	}

	public void agentPortalLogin(String organizationCode, String password) {
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
		waitForPageLoaded();
	}

	public String searchQuote(String applicantName) {
		homepage = new WrgHomePage_AP();
		quoteSearchPage = new QuoteSearchPage_AP();
		homepage.createNewQuote("Commercial Lines");
		applicantName = quoteSearchPage.searchQuote(applicantName);
		return applicantName;
	}
	
	@Parameters({ "insuredName", "state", "numberOfLocations", "organizationCode", "password", "insuranceType",
		"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void US20519ToolTipValidationTC41179(String insuredName, String state, String numberOfLocations, String organizationCode, String password, String insuranceType, 
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue) throws IOException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state + ", Organization Code: "
								+ organizationCode + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String quoteNumber = null;
		String applicantName = null;
		agencyPortalLogin(organizationCode, password);
		sleep(8000);
		buildNumber_AP = getAgentPortalBuild();
		String tooltiptext = "Lawn Care Services is available for risks without a pesticide license and includes coverage for incidental application of \"over the counter\" herbicides and pesticides on lawns under the insured's regular care. This form cannot be used with WCG 26 87.";
		String coverageNumber= "CG2293";
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					coverageNumber,tooltiptext);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			/*quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);*/
			//ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,

		}
	}
	
	@Parameters({ "insuredName", "state", "numberOfLocations", "organizationCode", "password", "insuranceType",
		"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void US20519ToolTipValidationTC41180(String insuredName, String state, String numberOfLocations, String organizationCode, String password, String insuranceType, 
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue) throws IOException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state + ", Organization Code: "
								+ organizationCode + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String quoteNumber = null;
		String applicantName = null;
		agencyPortalLogin(organizationCode, password);
		sleep(8000);
		buildNumber_AP = getAgentPortalBuild();
		String tooltiptext = "Pesticide applicator coverage is available for risks that have an active pesticide license in the state of Ohio. This form meets the requirements for financial responsibility as set forth by the Ohio Department of Agriculture. This form cannot be used with CG 22 93.";
		String coverageNumber= "WCG2687";
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					coverageNumber,tooltiptext);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			/*quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);*/
			//ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,

		}
	}
	
	@Parameters({ "insuredName", "state","state1", "numberOfLocations", "organizationCode","organizationCode1", "password", "insuranceType",
		"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void US20519ToolTipValidationTC41181(String insuredName, String state, String state1, String numberOfLocations, String organizationCode, String organizationCode1, String password, String insuranceType, 
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue) throws IOException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state + ", Organization Code: "
								+ organizationCode + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String quoteNumber = null;
		String applicantName = null;
		agencyPortalLogin(organizationCode, password);
		sleep(8000);
		buildNumber_AP = getAgentPortalBuild();
		String tooltiptext = "Provides insured status to \"any person or organization\" when the named insured has agreed in a contract or agreement to name that person or organization as an insured on the policy. No completed operations coverage exists under the endorsement.";
		String coverageNumber= "CG2033";
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			OptionalCoveragesTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					coverageNumber,tooltiptext);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			/*quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);*/
			//ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,

		}
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state1 + ", Organization Code: "
								+ organizationCode1 + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		agencyPortalLogin(organizationCode1, password);
		sleep(8000);
		buildNumber_AP = getAgentPortalBuild();
		//String tooltiptext = "Provides insured status to \"any person or organization\" when the named insured has agreed in a contract or agreement to name that person or organization as an insured on the policy. No completed operations coverage exists under the endorsement.";
		//String coverageNumber= "CG2033";
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			OptionalCoveragesTooltipValidation(state1, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					coverageNumber,tooltiptext);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode1, password);
			/*quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);*/
			//ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,

		}
	}
	@Parameters({ "insuredName", "state","state1", "numberOfLocations", "organizationCode","organizationCode1", "password", "insuranceType",
		"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void US20519ToolTipValidationTC41182(String insuredName, String state, String state1, String numberOfLocations, String organizationCode, String organizationCode1, String password, String insuranceType, 
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue) throws IOException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state + ", Organization Code: "
								+ organizationCode + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String quoteNumber = null;
		String applicantName = null;
		agencyPortalLogin(organizationCode, password);
		sleep(8000);
		buildNumber_AP = getAgentPortalBuild();
		String tooltiptext = "Grants automatic additional insured status to a person or organization from whom the named insured leases equipment when the named insured has agreed by contract to provide such additional insured coverage.";
		String coverageNumber= "CG2034";
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			OptionalCoveragesTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					coverageNumber,tooltiptext);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			/*quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);*/
			//ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,

		}
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state1 + ", Organization Code: "
								+ organizationCode1 + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		agencyPortalLogin(organizationCode1, password);
		sleep(8000);
		buildNumber_AP = getAgentPortalBuild();
		//String tooltiptext = "Provides insured status to \"any person or organization\" when the named insured has agreed in a contract or agreement to name that person or organization as an insured on the policy. No completed operations coverage exists under the endorsement.";
		//String coverageNumber= "CG2033";
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			OptionalCoveragesTooltipValidation(state1, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					coverageNumber,tooltiptext);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode1, password);
			/*quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);*/
			//ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,

		}
	}
	@Parameters({ "insuredName", "state","state1", "numberOfLocations", "organizationCode","organizationCode1", "password", "insuranceType",
		"businessEntity", "classCodeNumber", "formType", "percentageOwnerOccupiedValue" })
	@Test
	public void US20519ToolTipValidationTC41183(String insuredName, String state, String state1, String numberOfLocations, String organizationCode, String organizationCode1, String password, String insuranceType, 
			String businessEntity, String classCodeNumber, String formType, String percentageOwnerOccupiedValue) throws IOException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state + ", Organization Code: "
								+ organizationCode + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		String quoteNumber = null;
		String applicantName = null;
		agencyPortalLogin(organizationCode, password);
		sleep(8000);
		buildNumber_AP = getAgentPortalBuild();
		String tooltiptext = "Provides many enhancements to the Commercial General Liability Coverage form. View General Liability Enhancement Endorsement for details of these enhancements.";
		String coverageNumber= "WGL12";
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			AdditionalCoveragesTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					coverageNumber,tooltiptext);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode, password);
			/*quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);*/
			//ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,

		}
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel(
						"Parameters are-> Insured Name: " + insuredName + ", State: " + state1 + ", Organization Code: "
								+ organizationCode1 + ", Password: " + password + ", Insurance type: " + insuranceType
								+ ", Business Entity: " + businessEntity + ", Class Codes: " + classCodeNumber
								+ ", FormType: " + formType + ", Percentage Owner: " + percentageOwnerOccupiedValue,
						ExtentColor.PURPLE));
		agencyPortalLogin(organizationCode1, password);
		sleep(8000);
		buildNumber_AP = getAgentPortalBuild();
		//String tooltiptext = "Provides insured status to \"any person or organization\" when the named insured has agreed in a contract or agreement to name that person or organization as an insured on the policy. No completed operations coverage exists under the endorsement.";
		//String coverageNumber= "CG2033";
		if (buildNumber_AP.contains("R3")) {
			applicantName = searchQuote(insuredName);
			AdditionalCoveragesTooltipValidation(state1, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,
					coverageNumber,tooltiptext);
		} else if (buildNumber_AP.contains("R2")) {
			applicantName = apTests.searchQuote(insuredName, organizationCode1, password);
			/*quoteNumber = apTests.newQuote(state, businessEntity, classCodeNumber, formType,
					percentageOwnerOccupiedValue, numberOfLocations);*/
			//ClassificationsTooltipValidation(state, numberOfLocations, insuranceType, businessEntity, classCodeNumber, formType,

		}
	}
	public void ClassificationsTooltipValidation(String state, String numberOfLocations, String insuranceType, String businessEntity, String classCodeNumber, String formType,
			String coverageNumber, String tooltipText) {
		applicantInfoPage_AP = new ApplicantInformationPage_AP();
		underwritingGuidelinesPage = new UnderwritingGuidelinesPage_AP();
		policyFormSelectionPage_AP = new PolicyFormSelectionPage_AP();
		policywideCoveragesPage_AP = new PolicywideCoveragesPage_AP();
		optionalCoveragesPage_AP = new OptionalCoveragesPage_AP();
		locationsAndBuildingsPage_AP = new LocationsAndBuildingsPage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		startQuotePage_AP = new StartQuotePage_AP();
		locationsPage_AP = new LocationsPage_AP();
		classificationPage_AP = new ClassificationsPage_AP();
		applicantInfoPage_AP.enterAddress(state, businessEntity);
		applicantInfoPage_AP.selectInsuranceType(insuranceType);
		applicantInfoPage_AP.clickNextButton();
		sleep(2000);
		startQuotePage_AP.addClassification(classCodeNumber);
		String quoteNumber = null;
		if (insuranceType.equalsIgnoreCase("General Liability")) {
			quoteNumber = underwritingGuidelinesPage.goToPolicyWideCoveragesPage(classCodeNumber);
			policywideCoveragesPage_AP.coverages();
			locationsPage_AP.goToClassificationsPage(state, numberOfLocations);
			//classificationPage_AP.addClassifications(classCodeNumber, "10000", numberOfLocations);
			classificationPage_AP.addClassificationsToolTipValidation(classCodeNumber,coverageNumber, tooltipText);
	}
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
	public void OptionalCoveragesTooltipValidation(String state, String numberOfLocations, String insuranceType, String businessEntity, String classCodeNumber, String formType,
			String coverageNumber, String tooltipText) {
		applicantInfoPage_AP = new ApplicantInformationPage_AP();
		underwritingGuidelinesPage = new UnderwritingGuidelinesPage_AP();
		policyFormSelectionPage_AP = new PolicyFormSelectionPage_AP();
		policywideCoveragesPage_AP = new PolicywideCoveragesPage_AP();
		optionalCoveragesPage_AP = new OptionalCoveragesPage_AP();
		locationsAndBuildingsPage_AP = new LocationsAndBuildingsPage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		startQuotePage_AP = new StartQuotePage_AP();
		locationsPage_AP = new LocationsPage_AP();
		classificationPage_AP = new ClassificationsPage_AP();
		applicantInfoPage_AP.enterAddress(state, businessEntity);
		applicantInfoPage_AP.selectInsuranceType(insuranceType);
		applicantInfoPage_AP.clickNextButton();
		sleep(2000);
		startQuotePage_AP.addClassification(classCodeNumber);
		String quoteNumber = null;
		if (insuranceType.equalsIgnoreCase("General Liability")) {
			quoteNumber = underwritingGuidelinesPage.goToPolicyWideCoveragesPage(classCodeNumber);
			policywideCoveragesPage_AP.coverages();
			locationsPage_AP.goToClassificationsPage(state, numberOfLocations);
			classificationPage_AP.addClassifications(classCodeNumber, "10000", numberOfLocations);
			optionalCoveragesPage_AP.optionalCoveragesToolTipValidation(classCodeNumber,coverageNumber, tooltipText);
		}
	}
	public void AdditionalCoveragesTooltipValidation(String state, String numberOfLocations, String insuranceType, String businessEntity, String classCodeNumber, String formType,
			String coverageNumber, String tooltipText) {
		applicantInfoPage_AP = new ApplicantInformationPage_AP();
		underwritingGuidelinesPage = new UnderwritingGuidelinesPage_AP();
		policyFormSelectionPage_AP = new PolicyFormSelectionPage_AP();
		policywideCoveragesPage_AP = new PolicywideCoveragesPage_AP();
		optionalCoveragesPage_AP = new OptionalCoveragesPage_AP();
		locationsAndBuildingsPage_AP = new LocationsAndBuildingsPage_AP();
		underwritingQuestionsPage_AP = new UnderwritingInfoAndApplicationPage_AP();
		startQuotePage_AP = new StartQuotePage_AP();
		locationsPage_AP = new LocationsPage_AP();
		classificationPage_AP = new ClassificationsPage_AP();
		applicantInfoPage_AP.enterAddress(state, businessEntity);
		applicantInfoPage_AP.selectInsuranceType(insuranceType);
		applicantInfoPage_AP.clickNextButton();
		sleep(2000);
		startQuotePage_AP.addClassification(classCodeNumber);
		String quoteNumber = null;
		if (insuranceType.equalsIgnoreCase("General Liability")) {
			quoteNumber = underwritingGuidelinesPage.goToPolicyWideCoveragesPage(classCodeNumber);
			policywideCoveragesPage_AP.additionalCoveragesToolTipValidation(classCodeNumber, coverageNumber, tooltipText);
			//locationsPage_AP.goToClassificationsPage(state, numberOfLocations);
			//classificationPage_AP.addClassifications(classCodeNumber, "10000", numberOfLocations);
			//optionalCoveragesPage_AP.optionalCoveragesToolTipValidation(classCodeNumber,coverageNumber, tooltipText);
	}
}
}
