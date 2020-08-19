package com.wrg.abstestbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wrg.constants.ApiConstants;
import com.wrg.constants.UiConstants;
import com.wrg.constants.UtilConstants;
import com.wrg.interfaces.ExpectedDBConditions;
import com.wrg.utils.CSVUtils;
import com.wrg.utils.PostgresqlUtil;
import com.wrg.utils.PropertyFileUtils;
import com.wrg.waits.WaitUntilCondition;

public abstract class AbstractTestBase {
	public static WebDriver driver;
	public static HashMap<String, String> environment = new HashMap<String, String>();
	public static Logger log = MasterLogger.getInstance();
	public static MyAttributes attributes = new MyAttributes();
	protected static String os = System.getProperty("os.name");
	public static String snapshotfolder = "";
	public static PostgresqlUtil pgutilg = null;
	public static CSVUtils csv = null;
	public static CSVUtils csvtracers = null;
	public static CSVUtils csvknownIssues = null;
	public static HashSet<String> knownIssues =new HashSet<String>();
	
	public static PostgresqlUtil pgutil = null;
	public static String highLightPropertyName = "outline";
	int elementWaitTime = 10;
	public static String highlightColor = "#00ff00 solid 3px";
	WebElement we = null;
	public static String browser = null;
	public static String envoir = null;
	public static String cookie = null;
	public static String token = null;
	public static boolean isrunOnRemote = false;
	public static String testResultsFolder = null;
	public static String env=null;

	

	
	public void reportLog(String label, String inputValue) {
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"green\"><b>"
				+ label + ": " + inputValue + "</b></font></td>");
		if (label.equalsIgnoreCase("URI")) {
			attributes.setAttribute("URI", inputValue);
		}
		log.info(label + ": " + inputValue);
	}

	public void reportFailure(String inputValue) {
		log.info("test failed");
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"red\"><b>"
				+ inputValue + "</b></font></td>");
	}

	public void reportLog(String str) {
		log.info(str);
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"green\"><b>"
				+ str + "</b></font></td>");
	}

	public void imbedInputFileinReport(File file) {
		String filename = file.getAbsoluteFile() + "";
		log.info("input file : " + filename);
		Reporter.log("<tr><td></td><td></td><td><font size=\"1\" face=\"Comic sans MS\" color=\"blue\"><a href="
				+ filename + "><p style='color:blue;'><strong>Input file/json (size: " + file.length()
				+ " bytes)</strong></p> </a></font></td>");
	}
	


	public void imbedUrl(String legend, String url) {
		Reporter.log("<tr><td></td><td></td><td><font size=\"1\" face=\"Comic sans MS\" color=\"blue\"><a href="
				+ legend + "><p style='color:blue;'><strong> " + url + " </strong></p> </a></font></td>");
	}

	public static void reportSuccess(String str) {
		log.info("test case passed");
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"green\"><b>"
				+ str + "</b></font></td>");
	}

	public static void reportFail(String str) {
		log.info("test case failed");
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"red\"><b>"
				+ str + "</b></font></td>");
	}

	public static void reporError(String str) {
		log.info("error reported " + str);
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"red\"><b>"
				+ str + "</b></font></td>");
	}
	
	
	public void waitUntilDBCondition(int start, int maxTime, int polling, ExpectedDBConditions condition,
			String query) {
		/*
		 * for (int i = start; (i < maxTime &&
		 * condition.getDBConditions(query)); i = i + polling) { try {
		 * Thread.sleep(polling); log.info("polling done to get db conditions "
		 * + i); } catch (InterruptedException e) {
		 * log.info("Thread.sleep interupted"); } }
		 */

		WaitUntilCondition wait = new WaitUntilCondition();
		wait.setMaxTime(maxTime);
		wait.setPolling(polling);
		wait.setStart(start);
		wait.waitUntilCondition(condition, query);
	}
}
