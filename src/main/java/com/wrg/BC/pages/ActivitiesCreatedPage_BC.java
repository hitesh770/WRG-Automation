package com.wrg.BC.pages;

import com.wrg.abstestbase.AbstractTest;

public class ActivitiesCreatedPage_BC extends AbstractTest{

	
	
	
	public void searchPolicy(String policyNumber) {
		click(getWebElement("searchButton"));
		sleep(2000);
		type(("policyNumberSearchBox"),policyNumber);
		click(("searchAccountsButton"));
		click(("accountSearchedLink"));
	}
	
	public void searchAccount(String accountNumber) {
		click("searchButton");
		sleep(2000);
		type(("acountNumberSearchBox"),accountNumber);
		click(("searchAccountsButton"));
		click(("accountSearchedLink"));
	}

	
	
}
