package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class StartQuotePage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void addClassification(String classCodeNumber) {
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (classCodeNumber.contains(",")) {
			String spiltClassCodes[] = classCodeNumber.split(",");
			List<String> classCodes = new ArrayList<String>();
			for (String classCode : spiltClassCodes) {
				classCodes.add(classCode);
				Actions action = new Actions(driver);
				action.moveToElement(getWebElement("addClassificationButton")).build().perform();;
				click("addClassificationButton");

				String mainwindow = driver.getWindowHandle(); // get parent(current) window name
				for (String popup : driver.getWindowHandles()) // iterating on child windows
				{
					driver.switchTo().window(popup);
					type(("classCodeNumber"), classCode);
					clickUsingJS("classCode");

					clickUsingJS("okButton");
					if (isWebElementPresent("hazardCode") == true) {
						clickUsingJS("hazardCode");
						clickUsingJS("okButtonForHazardCode");
					}
				}
				driver.switchTo().window(mainwindow);

			}
		} else {
			clickUsingJS("addClassificationButton");

			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(("classCodeNumber"), classCodeNumber);
				clickUsingJS("classCode");

				clickUsingJS("okButton");
				if (isWebElementPresent("hazardCode") == true) {
					clickUsingJS("hazardCode");
					clickUsingJS("okButtonForHazardCode");
				}
			}

			driver.switchTo().window(mainwindow);
		}
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		clickUsingJS("nextButton");
		if(isWebElementPresent("okButtonForUnderwritingReferralRequired")==true) {
			clickUsingJS("okButtonForUnderwritingReferralRequired");
		}
	}

}
