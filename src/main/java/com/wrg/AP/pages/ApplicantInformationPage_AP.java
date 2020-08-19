package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class ApplicantInformationPage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void fillApplicantDetails(String state, String businessEntity, String classCodeNumber) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		enterAddress(state,businessEntity);
		if(classCodeNumber.contains(",")) {
			String spiltClassCodes[] = classCodeNumber.split(",");
			List<String> classCodes = new ArrayList<String>();
			for(String classCode:spiltClassCodes) {
			classCodes.add(classCode);
			
		click("addClassificationButton");

		String mainwindow = driver.getWindowHandle(); // get parent(current) window name
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			type(("classCodeNumber"), classCode);
			click("classCode");

			clickUsingJS("okButton");
			if (isWebElementPresent("hazardCode") == true) {
				clickUsingJS("hazardCode");
				clickUsingJS("okButtonForHazardCode");
			}
		}
		driver.switchTo().window(mainwindow);
			}
		}else {
			click("addClassificationButton");

			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(("classCodeNumber"), classCodeNumber);
				click("classCode");

				clickUsingJS("okButton");
				if (isWebElementPresent("hazardCode") == true) {
					clickUsingJS("hazardCode");
					clickUsingJS("okButtonForHazardCode");
				}
			}
			driver.switchTo().window(mainwindow);
		}
		clickUsingJS("nextButton");
		sleep(2000);
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			try {
				clickUsingJS("useverifiedButton");
				}catch(Exception e) {
					e.getMessage();
				}
		}

	}
	
	public void enterAddress(String state,String businessEntity) {
		wait = new WebDriverWait(driver, 20);
		waitForPageLoaded();
		wait.until(ExpectedConditions.urlContains("qnb-flow"));
		wait.until(ExpectedConditions.visibilityOf(getWebElement("producerCodeDropdown")));
		if (isWebElementPresent("effectiveDate") == true) {
			String effectveDateValue = getWebElementText("effectiveDate");
			type(("effectiveDate"), effectveDateValue);
		}
		selectByOption(getWebElement("producerCodeDropdown"), "FORWARD AGENCIES INC (000)");
		if (state.equalsIgnoreCase("Indiana")) {
			
			type(("addressLine1"), getData("indianaAddress"));
			type(("city"), getData("indianaCityName"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("state"));
			selectByOption(getWebElement("state"), state);
			type(("zipCode"), getData("indianaPincodeValue"));
		} else if (state.equalsIgnoreCase("Ohio")) {
			type(("addressLine1"), getData("ohioAddress"));
			type(("city"), getData("ohioCityName"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("state"));
			selectByOption(getWebElement("state"), state);
			type(("zipCode"), getData("ohioPincodeValue"));
		}
		selectByOption(getWebElement("businessEntityType"), businessEntity);
		type(("description"), getData("descriptionValue"));

	}
	
	public void selectInsuranceType(String insuranceType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("nextButton"));
		clickUsingJS(driver.findElement(By.xpath("//label[contains(text(),'"+insuranceType+"')]/parent::div[1]/input")));
	}
	
	public void clickNextButton() {
		clickUsingJS("nextButton");
		for (String popup : driver.getWindowHandles()) // iterating on child windows
		{
			driver.switchTo().window(popup);
			try {
			clickUsingJS("useverifiedButton");
			}catch(Exception e) {
				clickUsingJS("useOriginalButton");
			}
		}
	}

}
