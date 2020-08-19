package com.wrg.BC.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import com.wrg.abstestbase.AbstractTest;

public class InvoiceStreamsPage_BC extends AbstractTest{
	
	public List getInvoiceStreams() {
		List<String> invoiceStreams = new ArrayList<String>();
		for(int i=1;i<=getWebElements("invoiceStreams").size();i++) {
			invoiceStreams.add(driver.findElement(By.xpath("//div[@id='AccountDetailInvoiceStreams:AccountDetailInvoiceStreamsScreen:InvoiceStreamsDetailPanel:InvoiceStreamsLV-body']//table//tr["+i+"]/td/div")).getText());
		}
		
		for(String s : invoiceStreams) {
			System.out.println(s);
		}
		return invoiceStreams;
	}

}
