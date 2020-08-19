package com.wrg.abstest;

import org.testng.Assert;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.wrg.abstestbase.TestBase;
import com.wrg.utils.PostgresqlUtil;

public class UserAPITestBase extends TestBase{
	private PostgresqlUtil pgutil1 = null;
	public ClientConfig config = null;
	public Client client = null;

	public UserAPITestBase() {
		super();
		this.pgutil1 = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"),
				environment.get("dbpwd"));
		if (this.pgutil1 == null) {
			log.info("DB Connection failed");
			this.pgutil1 = pgutilg;
		}
		if (this.pgutil1 == null) {
			Assert.fail("database connection not successful");
		}
		config = new DefaultClientConfig();
		client = Client.create(config);
	}

	public UserAPITestBase(PostgresqlUtil pgutil) {
		super(pgutil);
		this.pgutil1 = pgutil;
		config = new DefaultClientConfig();
		client = Client.create(config);
	}
	
	
	
	
}
