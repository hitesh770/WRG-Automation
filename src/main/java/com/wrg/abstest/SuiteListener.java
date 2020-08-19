package com.wrg.abstest;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;

import com.wrg.abstestbase.AbstractTest;
import com.wrg.abstestbase.MasterLogger;
import com.wrg.utils.EmailUtility;
import com.wrg.utils.TestDataGenerator;

public class SuiteListener extends AbstractTest implements IExecutionListener {
	Logger log = MasterLogger.getInstance();
	static boolean firsttimeLog = false;
	public String saveServerLogs = "false";
	public String processLogs = "false";
	//static ServerLogsProcessor logProcessor = new ServerLogsProcessor();
	String startTime = null;
	String endTime = null;

	private void printBanner(String s) {
		log.info("========================================= " + s + " ================================");

	}

	/*@Override
	public void onExecutionStart() {
		Date date = new Date();
		startTime = TestDataGenerator.getformatedDateinTimeZone(date, "yyyy-MM-dd HH:mm", "GMT");
		log.info("start time for logs ->" + startTime);
		String env = System.getProperty("env");
		envoir = env;
		log.info("before env inside " + env);
		if (saveServerLogs.equalsIgnoreCase("true")) {
			verifyMachinesAreUp();
			log.info("all machines are up for testing");
		}

		if (firsttimeLog == false) {
			String cmd = "sudo rm -rf " + System.getProperty("user.dir") + "/src/test/resources/snapshots/*";
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() == 0) {
				log.info("snapshot contents deleted");
			} else {
				log.info("snapshot folder not deleted");
			}
			log.info("setting up for first time ");
			FileIOUtility.deleteFileIfExists("extentReport/ExtentReportTestNG.html");
			setExtentReport();
			log.info("extent report set");
			printBanner("NETRADYNE AUTOMATION FRAMEWORK LOG STARTS BELOW");

			csv = new CSVUtils("./testcaseList.csv");
			csvtracers = new CSVUtils("./extentReport/tracerIdsList.csv");
			csvknownIssues = new CSVUtils("./extentReport/knownIssues.csv");
			csv.createNewCSV("./testcaseList.csv");
			csvtracers.createNewCSV("./extentReport/tracerIdsList.csv");
			csvtracers.append2CSV("testClass,testCase,tracerIdsList,,,");
			csvknownIssues.append2CSV("ids,url,priority,,");
			csv.append2CSV(
					"TestName,TestGroups,TestCaseName,Description,Parameters,URI,INPUT,OUTPUT,Status,DefectId, DefectUrl,DefectPrty");
			firsttimeLog = true;
			log.info("set first time logging true");
			FileIOUtility.deleteAllFilesNSubfolders("./target/test-classes/api/");
		}
		saveServerLogs = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "saveserverlogs");
		processLogs = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "processServerLogsAfterRun");
		FileIOUtility.deleteAllFilesNSubfolders("./target/surefire-reports/html");
		FileIOUtility.deleteAllFilesNSubfolders("./target/surefire-reports/xml");
		FileIOUtility.createIfFolderNotExists("./target/surefire-reports/html");
		FileIOUtility.createIfFolderNotExists("./target/surefire-reports/xml");
	}
*/
	@Override
	public void onExecutionFinish() {
		// TODO Auto-generated method stub
		Date date = new Date();
		endTime = TestDataGenerator.getformatedDateinTimeZone("yyyy-MM-dd HH:mm", "IST");
		log.info("end time for logs ->" + endTime);
	}

	@Override
	public void onExecutionStart() {
		Date date = new Date();
		startTime = TestDataGenerator.getformatedDateinTimeZone("yyyy-MM-dd HH:mm", "IST");
		log.info("start time for logs ->" + startTime);
		
		// TODO Auto-generated method stub
		
	}

	

}
