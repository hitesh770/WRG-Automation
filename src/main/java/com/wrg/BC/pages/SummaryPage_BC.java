package com.wrg.BC.pages;

import com.wrg.abstestbase.AbstractTest;

public class SummaryPage_BC extends AbstractTest{

	
	
	

	public void goToInvoicesPage() {
		click(getWebElement("invoicesTabLink"));
	}
	
	public void goToInvoiceStreamsPage(){
		clickUsingJS("invoiceStreamsLink");
	}
	
}
