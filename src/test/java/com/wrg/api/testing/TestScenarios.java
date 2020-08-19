package com.wrg.api.testing;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.abstestbase.UITestBase;

public class TestScenarios extends AbstractTest{
	
	public ClientConfig config = new DefaultClientConfig();
	public Client client = Client.create(config);
	UITestBase uiTestbase = null;
	String buyerEmail;
	
	@BeforeClass(enabled = true, description = "login API to get api key", groups = { "SANITY_GROUP" })
	public void beforeclass() {
		uiTestbase = new UITestBase();
	}

	@Test
	public void test() {
		//TO DO
	}
	
	
	
}
