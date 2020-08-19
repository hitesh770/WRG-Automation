package com.wrg.BC.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;

import com.wrg.abstestbase.AbstractTest;

public class InvoicesPage_BC extends AbstractTest{

	
	
	public ArrayList<String> getInvoiceDatesList(){
		ArrayList<String> invoiceDatesList = new ArrayList<String>();
		for(int i=1;i<getInvoicesCount();i++) {
			String invoiceDate=driver.findElement(By.xpath("//div[@id=\"AccountDetailInvoices:AccountDetailInvoicesScreen:DetailPanel:AccountInvoicesLV-body\"]//table/tbody/tr["+i+"]/td[2]")).getText();
			invoiceDatesList.add(invoiceDate);
		}
		System.out.println(invoiceDatesList);
		return invoiceDatesList;
	}
	
	public int getInvoicesCount() {
		int invoicesCount=driver.findElements(By.xpath("//div[@id=\"AccountDetailInvoices:AccountDetailInvoicesScreen:DetailPanel:AccountInvoicesLV-body\"]//table/tbody/tr")).size();
		return invoicesCount;
	}

}
