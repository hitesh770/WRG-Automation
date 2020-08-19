package com.wrg.PC.pages;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;

public class PolicyInfoPage_PC extends AbstractTest {
	WebDriverWait wait = null;

	public void goToNextPage() {
		wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
			click("nextButton");
		} catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert data: " + alertText);
				alert.dismiss();
			} catch (NoAlertPresentException f) {
				f.printStackTrace();
			}
		}
	}

	public void changeUnderwritingCompany(String underwritingCompany) {
		clickUsingJS("underWritingCompanyDropDown");
		sleep(2000);
		clickUsingJS(driver.findElement(
				By.xpath("//div[starts-with(@id,'boundlist')]/ul/li[contains(text(),'" + underwritingCompany + "')]")));
		clickUsingJS("saveButton");
	}

	public void editPolicyTransaction() {
		wait = new WebDriverWait(driver, 30);
		if (isWebElementPresent("editPolicyTransactionButton")) {
			clickUsingJS("editPolicyTransactionButton");
		}
		sleep(2000);
		if (isWebElementPresent("okButton")) {
			clickUsingJS("okButton");
		}
		sleep(1000);
	}

	public void setCustomTerm(String days, String effectiveDate) throws IOException {
		// driver.navigate().refresh();
		wait = new WebDriverWait(driver, 20);
		String newDate = null;
		// sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policyInfoScreenValidation")));
		String currentEffectiveDate = getWebElementText("effectiveDate");
		sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("termTypeDropdown")));
		click("termTypeDropdown");
		clickUsingJS(driver.findElement(By.xpath("//li[contains(text(),'Other')]")));
		wait.until((ExpectedConditions.textToBePresentInElement(getWebElement("expirationDate"), "")));
		// sleep(5000);
		if (!effectiveDate.equals("NA")) {
			newDate = addDaysToDate(effectiveDate, days);
			WebElement effDate = getWebElement("effDateAP");
			clear(effDate);
			type(effDate, effectiveDate);
			getWebElement("effDateAP").sendKeys(Keys.TAB);
			sleep(5000);
			getWebElement("expirationDate").sendKeys(newDate);
		} else {
			newDate = addDaysToDate(currentEffectiveDate, days);
			clear("expirationDate");
			getWebElement("expirationDate").sendKeys(newDate);
		}

	}

	public void checkLeapYearValidation(String days, String effectiveDate) {
		wait = new WebDriverWait(driver, 10);
		String newDate = null;
		// sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("policyInfoScreenValidation")));
		String currentEffectiveDate = getWebElementText("effectiveDate");
		sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement("termTypeDropdown")));
		// click("termTypeDropdown");
		// clickUsingJS(driver.findElement(By.xpath("//li[contains(text(),'Other')]")));
		WebElement expirationDate = getWebElement("expirationDateAnnualTerm");
		if (!effectiveDate.equals("NA")) {
			WebElement effDate = getWebElement("effDate");
			clear(effDate);
			type(effDate, effectiveDate);
			getWebElement("effDate").sendKeys(Keys.TAB);

			newDate = addDaysToDate(effectiveDate, days);
		} else {
			newDate = addDaysToDate(currentEffectiveDate, days);
		}
		clear(expirationDate);
		type(expirationDate, newDate);
		clickUsingJS("nextButton");
		sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, getWebElementText("errorMessage"));

	}

	public String addDaysToDate(String oldDate, String days) {
		// Specifying date format that matches the given date
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			// Setting the date to the given date
			c.setTime(sdf.parse(oldDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, Integer.valueOf(days));
		// Date after adding the days to the given date
		String newDate = sdf.format(c.getTime());
		// Displaying the new Date after addition of Days
		return newDate;
	}

	public void goToRiskAnalysisPage() {
		clickUsingJS("riskAnalysisButton");
	}

	public boolean isWebElementPresentOnPolicyInfoPage(String element) {
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		if (getWebElements(element).size() > 0) {
			return true;
		} else {
			ExtentTestManager.getTest().log(Status.INFO, element + " not present on page");
			return false;
		}

	}

	public void checkAllElementOnPolicyInfoPage() {
		waitForPageLoaded();
		isWebElementPresentOnPolicyInfoPage("quoteButton");
		isWebElementPresentOnPolicyInfoPage("closeOptionsDropdown");
		isWebElementPresentOnPolicyInfoPage("saveButton");
		isWebElementPresentOnPolicyInfoPage("nextButton");
		isWebElementPresentOnPolicyInfoPage("dateQuoteNeededTetBox");
		isWebElementPresentOnPolicyInfoPage("ssnTextBox");
		isWebElementPresentOnPolicyInfoPage("feinTextBox");
		isWebElementPresentOnPolicyInfoPage("termTypeDropdown");
		isWebElementPresentOnPolicyInfoPage("expirationDateAnnualTerm");
		isWebElementPresentOnPolicyInfoPage("effDate");
		isWebElementPresentOnPolicyInfoPage("underWritingCompanyDropDown");
		isWebElementPresentOnPolicyInfoPage("producerCodeDropdown");
		isWebElementPresentOnPolicyInfoPage("organizationTextBox");
		isWebElementPresentOnPolicyInfoPage("businessEntityTypeLabel");
		isWebElementPresentOnPolicyInfoPage("businessEntityTypeValue");
		isWebElementPresentOnPolicyInfoPage("descriptionLabel");
		isWebElementPresentOnPolicyInfoPage("descriptionValue");
		isWebElementPresentOnPolicyInfoPage("originalEffectiveDateValue");
		isWebElementPresentOnPolicyInfoPage("originalEffectiveDateValue");
		isWebElementPresentOnPolicyInfoPage("rateAsOfDateLabel");
		isWebElementPresentOnPolicyInfoPage("organizationLabel");
		isWebElementPresentOnPolicyInfoPage("organizationTextBox");
		isWebElementPresentOnPolicyInfoPage("organizationTypeLabel");
		isWebElementPresentOnPolicyInfoPage("organizationTypeValue");
		isWebElementPresentOnPolicyInfoPage("phoneNumberLabel");
		isWebElementPresentOnPolicyInfoPage("phoneNumberValue");
		isWebElementPresentOnPolicyInfoPage("producerCodeLabel");
		isWebElementPresentOnPolicyInfoPage("producerCodeDropdown");
		isWebElementPresentOnPolicyInfoPage("marketingTerritoryLabel");
		isWebElementPresentOnPolicyInfoPage("marketingTerritoryValue");
		isWebElementPresentOnPolicyInfoPage("underwritingTerritoryLabel");
		isWebElementPresentOnPolicyInfoPage("underwritingTerritoryValue");
	}

	public void validateAddressScreenAppearsOnPolicyAdressChangeToDropdownclick() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		click("policyAddressDropdown");
		click("editCurrentAddressLink");
		isWebElementPresentOnPolicyInfoPage("addressDetailHeading");
		click("returnToPolicyInfoPage");
		click("okButton");
		isWebElementPresentOnPolicyInfoPage("policyInfoScreenValidation");
	}

	public void checkValidationErrors() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		type("ssnTextBox", "asfabfkd");
		getWebElement("ssnTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		ExtentTestManager.getTest().log(Status.INFO, getWebElementText("errorMessage"));
		type("feinTextBox", "asfabfkd");
		getWebElement("feinTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		ExtentTestManager.getTest().log(Status.INFO, getWebElementText("errorMessage1"));
		try {
			setCustomTerm("-10", "NA");
			getWebElement("expirationDate").sendKeys(Keys.TAB);
			ExtentTestManager.getTest().log(Status.INFO, getWebElementText("errorMessage2"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sleep(1000);
		WebElement effDate = getWebElement("effDateAP");
		clear(effDate);
		type(effDate, "afafa");
		getWebElement("effDateAP").sendKeys(Keys.TAB);
		sleep(1000);
		ExtentTestManager.getTest().log(Status.INFO, getWebElementText("errorMessage2"));
		clear("organizationTextBox");
		type("organizationTextBox", "asfabfkd");
		getWebElement("organizationTextBox").sendKeys(Keys.TAB);
		sleep(1000);
		ExtentTestManager.getTest().log(Status.INFO, getWebElementText("errorMessage3"));
	}
}
