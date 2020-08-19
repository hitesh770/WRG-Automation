package com.wrg.AP.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.wrg.abstestbase.AbstractTest;

public class QuotePage_AP extends AbstractTest {
	WebDriverWait wait = null;

	public void quote() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (isWebElementPresentAfterWait("continueButton") == true && isWebElementDisplayed("continueButton") == true) {
			clickUsingJS("continueButton");
		}
		if (isWebElementPresentAfterWait("startApplicationButton") == true) {
			clickUsingJS("startApplicationButton");
		}
	}

}
