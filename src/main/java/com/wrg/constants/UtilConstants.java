package com.wrg.constants;

public class UtilConstants {
	public static final String S3_CONFIG = "./.awscred";
	public static final String ENCODING = "UTF-8";
	public static final String CONFIG_FILE = "./config.properties";
	public static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String POSTGRESQL_JDBC_DRIVER = "org.postgresql.Driver";
	public static final int HTTP_CONNECTION_TIMEOUT = 30000;
	public static final String TLS_VERSION = "TLSv1.2";
	public static final String DATA_Framework_CONSTANTS = "./src/main/resources/conf.properties";
	public static final String LOG4J_PROP_LOCATION = "./src/main/resources/log4j.properties";
	public static final String LOGS_PATH = "/src/test/resources/logs";
	public static final String COMMON_LOCATORS = "./src/test/resources/locators/commonLocators.properties";
	public static final String TEST_ENV_FILE_LOCATION = "./environment.xml";
	public static final String TEST_REPORT = "./test-output";
	public static final int MAX_RETRY_COUNT=1;
	public static final String QUOTE_SUBMIT_CONFIRM_MESSAGE="Your quote has been submitted for underwriting review. Your underwriting team will contact you once their review is final.";
	public static final String UNDERWRITING_GUIDELINE_WIZARD_MENU_HEADING = "General Liability Quote";
	public static final String QUOTE_DISCLAIMER_TEXT_STRING="Disclaimer: THIS IS NOT AN OFFER OF INSURANCE AND DOES NOT BIND COVERAGE.\n" + 
			"Premium and coverages shown is an estimate based upon information provided to us and is subject to change. Further details regarding insurance values and exposures may be required or dependent on additional underwriting documentation and approval. A completed Application is necessary to bind and issue a policy.\n" + 
			"Please carefully review the quote proposal for all coverage and exclusion forms included in this quote.";

}

