package com.wrg.abstestbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.apache.maven.surefire.util.internal.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.GetRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.GetResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import com.rallydev.rest.util.Ref;
import com.wrg.constants.ApiConstants;
import com.wrg.constants.UiConstants;
import com.wrg.constants.UtilConstants;
import com.wrg.utils.ExtentTestManager;
import com.wrg.utils.PostgresqlUtil;
import com.wrg.utils.PropertyFileUtils;
import com.wrg.utils.TestDataGenerator;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest extends AbstractTestBase {

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
	public static EventFiringWebDriver e_driver;
	public static EventListener eventListener;
	public static String testResultsFolder = null;
	public static String env = null;
	public static String automationTester = null;
	public static String buildNumber = null;
	public static String updateTestResults = null;
	private String buildNumberAP = null;
	private String buildNumberPC = null;
	private String buildNumberBC = null;
	protected String[] elpClassCodeArray = { "40005", "40006" };

	@BeforeSuite(alwaysRun = true)
	public void beforesuite() throws IOException {
		HSSFSheet baseSheet;
		printBanner("TEST SUITE STARTED");
		File file = new File("./recordings/");
		if (file.exists()) {
			FileUtils.cleanDirectory(file);
		}
		try {
			File myfile = new File(System.getProperty("user.dir") + "\\" + "TestPlan.xls");
			FileInputStream file2 = new FileInputStream(myfile);
			HSSFWorkbook wb = new HSSFWorkbook(file2);
			baseSheet = wb.getSheetAt(0);
			HSSFCell envcell = baseSheet.getRow(1).getCell(1);
			HSSFCell browsercell = baseSheet.getRow(2).getCell(1);
			HSSFCell buildNumberCell = baseSheet.getRow(3).getCell(1);
			HSSFCell automationTesteCell = baseSheet.getRow(4).getCell(1);
			HSSFCell updateTestResultsCell = baseSheet.getRow(5).getCell(1);
			env = envcell.getStringCellValue();
			browser = browsercell.getStringCellValue();
			automationTester = automationTesteCell.getStringCellValue();
			buildNumber = String.valueOf(buildNumberCell.getStringCellValue());
			updateTestResults = updateTestResultsCell.getStringCellValue();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.info("before env->" + env);
		log.info("before browser->" + browser);
		if (env.equals("") || env.equals(null)) {
			env = System.getProperty("env");
			log.info("before env inside" + env);
		}
		loadEnv(env);

//		pgutil = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"), environment.get("dbpwd"));
//		if (pgutil == null) {
//			log.info("DB Connection failed first attempt");
//			pgutil = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"), environment.get("dbpwd"));
//			if (pgutil == null) {
//				log.info("DB Connection failed second attempt");
//				pgutil = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"),
//						environment.get("dbpwd"));
//				if (pgutil == null) {
//					log.info("DB Connection failed Third attempt Aborting test....");
//					Assert.fail("Aborting test due to db connection failed ...");
//				}
//			}
//
//		}
		AbstractTest.browser = browser;
	}

	public static WebDriver getDriver() {
		String remoteHub = null;
		String proxy = null;
		URL remoteHubUrl = null;
		String targetOS = null;
		DesiredCapabilities cap = null;
		isrunOnRemote = Boolean.valueOf(PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "runOnRemoteMachine"));
		log.info(isrunOnRemote);
		if (isrunOnRemote == true) {
			remoteHub = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "remoteHubIP");
			targetOS = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "targetOS");
			log.info(remoteHub);
			proxy = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "proxy");
			try {
				remoteHubUrl = new URL(remoteHub);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			WebDriverCapabilities webcap = new WebDriverCapabilities(browser, proxy);
			cap = webcap.getDesiredCapabilities(targetOS);
		}
		if (browser == null) {
			browser = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "defaultBrowser");
		}
		log.info("running on browser " + browser);

		log.info(browser);
		targetOS = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "targetOS");
		WebDriverCapabilities webcap = new WebDriverCapabilities(browser, proxy);
		cap = webcap.getDesiredCapabilities(targetOS);
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (isrunOnRemote == true) {
				log.info("starting gecko driver on server");
				driver = new RemoteWebDriver(remoteHubUrl, cap);
			} else {
				log.info("starting gecko driver on local");
				driver = new FirefoxDriver();
			}

		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (isrunOnRemote == true) {
				log.info("starting chrome driver on server");
				log.info(remoteHubUrl.toString());
				log.info("cap " + cap.getBrowserName());
				driver = new RemoteWebDriver(remoteHubUrl, cap);
			} else {
				log.info("starting chrome driver on local");
				driver = new ChromeDriver();
			}

		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "./src/test/resources/drivers/IEDriverServer32.exe");
			if (isrunOnRemote == true) {
				log.info("starting ie driver on server");
				driver = new RemoteWebDriver(remoteHubUrl, cap);
			} else {
				log.info("starting ie driver on local");
				driver = new InternetExplorerDriver();
			}
		} else if (browser.equalsIgnoreCase("safari")) {
			log.info("starting safari driver on local");
			driver = new SafariDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "./src/test/resources/drivers/msedgedriver64.exe");
			if (isrunOnRemote == true) {
				log.info("starting chrome driver on server");
				log.info(remoteHubUrl.toString());
				log.info("cap " + cap.getBrowserName());
				driver = new RemoteWebDriver(remoteHubUrl, cap);
			} else {
				log.info("starting edge driver on local");
				driver = new EdgeDriver();
			}

		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new EventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().timeouts().pageLoadTimeout(UiConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(UiConstants.SCRIPT_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(UiConstants.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}

	public static String randomAlphaNumeric(int count) {
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public List<String> getUsersList(String users) {
		List<String> usersList = new ArrayList<String>();
		if (users.contains(",")) {
			String userArray[] = users.split(",");
			for (int i = 0; i < userArray.length; i++) {
				usersList.add(userArray[i]);
			}
		} else {
			usersList.add(users);
		}
		return usersList;
	}

	public WebDriver login(String pcUser) {
		String uri = environment.get("PCbaseurl");
		reportLog("URI", uri);
		driver = getDriver();
		driver.get(uri);
		try {
			if (browser.equalsIgnoreCase("firefox")) {
				String command = System.getProperty("user.dir") + "\\"
						+ "src\\test\\resources\\AutoIT_Scripts\\firefox\\" + "FirefoxAuthentication.exe" + " " + pcUser
						+ " " + "Guidewire2014";
				Runtime.getRuntime().exec(command);
			} else if (browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("edge")) {
				String command = System.getProperty("user.dir") + "\\" + "src\\test\\resources\\AutoIT_Scripts\\ie\\"
						+ "IEAuthentication.exe" + " " + pcUser + " " + "Guidewire2014";
				Runtime.getRuntime().exec(command);
			} else if (browser.equalsIgnoreCase("chrome")) {
				String command = System.getProperty("user.dir") + "\\"
						+ "src\\test\\resources\\AutoIT_Scripts\\chrome\\" + "ChromeAuthentication.exe" + " " + pcUser
						+ " " + "Guidewire2014";
				Runtime.getRuntime().exec(command);
			}
			ExtentTestManager.getTest().log(Status.INFO,
					MarkupHelper.createLabel("Logged In as " + pcUser + " in PC", ExtentColor.BLUE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			buildNumberPC = driver.findElement(By.xpath("//span[@id='TabBar:svnReleaseTab-btnInnerEl']")).getText()
					.substring(15, 24);
		} catch (Exception e) {
			driver.findElement(By.id(":tabs-menu-trigger-btnIconEl")).click();
			String buildNumberInDropdown = driver.findElement(By.xpath("(//span[contains(text(),'Build Version')])[2]"))
					.getText();
			buildNumberPC = buildNumberInDropdown.substring(15, 24);
		}
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Build Number for Policy Center is " + buildNumberPC, ExtentColor.BLUE));
		return driver;
	}

	public WebDriver agencyPortalLogin(String organizationCode, String password) {
		String uri = environment.get("baseurl");
		reportLog("URI", uri);
		driver = getDriver();
		driver.get(uri);
		try {
			if (browser.equalsIgnoreCase("firefox")) {
				String command = System.getProperty("user.dir") + "\\"
						+ "src\\test\\resources\\AutoIT_Scripts\\firefox\\" + "FirefoxAuthentication.exe"+" "
						+ organizationCode + " " + password + "";
				Runtime.getRuntime().exec(command);
			} else if (browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("edge")) {
				String command = System.getProperty("user.dir") + "\\" + "src\\test\\resources\\AutoIT_Scripts\\ie\\"
						+ "IEAuthentication.exe" +" "+ organizationCode + " " + password + "";
				Runtime.getRuntime().exec(command);
			} else if (browser.equalsIgnoreCase("chrome")) {
				String command = System.getProperty("user.dir") + "\\"
						+ "src\\test\\resources\\AutoIT_Scripts\\chrome\\" + "india.exe" + " "+organizationCode + " "
						+ password + "";
				Runtime.getRuntime().exec(command);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
	
	public String getAgentPortalBuild() {
		buildNumberAP = driver.findElement(By.xpath("//p[contains(@class,'wrg-build-number')]")).getText().substring(7);
		ExtentTestManager.getTest().log(Status.INFO,
				MarkupHelper.createLabel("Build Number for Agency Portal is " + buildNumberAP, ExtentColor.BLUE));
		return buildNumberAP;
	}

	public WebDriver billingCenterLogin(String bcUser) {
		String uri = environment.get("BCbaseurl");
		reportLog("URI", uri);
		driver = getDriver();
		driver.get(uri);
		try {
			if (browser.equalsIgnoreCase("firefox")) {
				String command = System.getProperty("user.dir") + "\\"
						+ "src\\test\\resources\\AutoIT_Scripts\\firefox\\" + "FirefoxAuthentication.exe" + " " + bcUser
						+ " " + "Guidewire2014";
				Runtime.getRuntime().exec(command);
			} else if (browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("edge")) {
				String command = System.getProperty("user.dir") + "\\" + "src\\test\\resources\\AutoIT_Scripts\\ie\\"
						+ "IEAuthentication.exe" + " " + bcUser + " " + "Guidewire2014";
				Runtime.getRuntime().exec(command);
			} else if (browser.equalsIgnoreCase("chrome")) {
				String command = System.getProperty("user.dir") + "\\"
						+ "src\\test\\resources\\AutoIT_Scripts\\chrome\\" + "ChromeAuthentication.exe" + " " + bcUser
						+ " " + "Guidewire2014";
				Runtime.getRuntime().exec(command);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	public Map<String, String> getAuthMap() {
		Map<String, String> authMap = new HashMap<String, String>();
		authMap.put(ApiConstants.AUTH_KEY, cookie);
		return authMap;
	}

	public static void loadEnv(String env) {
		getenv("environment.xml", "environment", "id", env);
	}

	protected void reportLogScreenshot(File file, String priority) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String absolute = file.getName();
		String filePath = System.getProperty("user.dir") + "/src/test/resources/snapshots/"
				+ AbstractTestBase.snapshotfolder + "/" + absolute;

		Reporter.log("<tr><td></td><td></td><td><font size=\"1\" face=\"Comic sans MS\" color=\"green\"><a href=\""
				+ filePath + "\"><p align=\"left\">" + priority + "           " + new Date() + "</p>");
		Reporter.log("<p><img width=\"16\" src=\"" + filePath + "\" alt=\"executed on" + new Date()
				+ "\"/></p></a></font></td>");
	}

	public static void getenv(String fileName, String tagName, String attribuateName, String attribuateValue) {
		NodeList nList = null;
		Node nNode = null;

		File inputFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputFile);
			XPath xPath = XPathFactory.newInstance().newXPath();

			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName(tagName);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int temp = 0; temp < nList.getLength(); temp++) {
			nNode = nList.item(temp);
			String key = nNode.getAttributes().getNamedItem("id").getNodeValue();
			if (key.equals(attribuateValue)) {
				NodeList childnodes = nNode.getChildNodes();
				for (int j = 0; j < childnodes.getLength(); j++) {
					Node n = childnodes.item(j);
					environment.put(n.getNodeName().trim(), n.getTextContent().trim());
				}
			}
		}
	}

	private void printBanner(String s) {
		log.info("========================================= " + s + " ================================");
	}

	public void clickIfElementIsStale(WebElement we) {
		int count = 0;
		while (count < 100) {
			try {
				click(we);
				break;
			} catch (StaleElementReferenceException e) {
				e.getMessage();
			}
			count++;
		}
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
	
	public void waitForAjaxControls() throws InterruptedException {
		while(true) {
			boolean ajaxIsComplete = (boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active==0");
			if(ajaxIsComplete) {
				break;
			}
			Thread.sleep(100);
		}
	}

	public void setPageLoadTimeout(int timeout) {
		try {
			this.driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void setObjectWaitTimeout(int timeoutSeconds) {
		elementWaitTime = timeoutSeconds;
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static By buildLocator(String how, String what) throws Exception {

		switch (how.toLowerCase()) {
		case "id":
			return By.id(what);
		case "name":
			return By.name(what);
		case "class":
		case "classname":
			return By.xpath("//*[@class='" + what + "']");
		// locator = By.className(what);
		case "css":
		case "csslocator":
		case "cssselector":
			return By.cssSelector(what);
		case "linktext":
		case "link":
			return By.linkText(what);
		case "partiallinktext":
		case "partiallink":
			return By.partialLinkText(what);
		case "tagname":
		case "tag":
		case "html tag":
		case "htmltag":
			return By.tagName(what);
		case "xpath":
			return By.xpath(what);
		default:
			throw new Exception("Unknown locator type :" + how);
		}
	}

	public static WebElement findElement(WebDriver driver, By by) {
		if (driver != null) {
			return driver.findElement(by);
		} else
			return null;
	}

	public static By findBy(String propertyFileName, String propertyName) throws Exception {

		String how = getHow(propertyFileName, propertyName);
		String what = getWhat(propertyFileName, propertyName);

		switch (how.toLowerCase()) {
		case "id":
			return By.id(what);
		case "name":
			return By.name(what);
		case "class":
		case "classname":
			return By.xpath("//*[@class='" + what + "']");
		// locator = By.className(what);
		case "css":
		case "csslocator":
		case "cssselector":
			return By.cssSelector(what);
		case "linktext":
		case "link":
			return By.linkText(what);
		case "partiallinktext":
		case "partiallink":
			return By.partialLinkText(what);
		case "tagname":
		case "tag":
		case "html tag":
		case "htmltag":
			return By.tagName(what);
		case "xpath":
			return By.xpath(what);
		default:
			reportFail("Unknown locator type :" + how);
			throw new Exception("Unknown locator type :" + how);
		}

	}

	public static void reportFail(String str) {
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"red\"><b>"
				+ str + "</b></font></td>");
		log.info(str);
	}

	public String getProperty(String propertyName) {
		String locatorFile = getLocatorPath();
		return PropertyFileUtils.getProperty(locatorFile, propertyName);

	}

	public static String getHow(String propertyFileName, String propertyName) throws InvalidLocator {
		String propVal = PropertyFileUtils.getProperty(propertyFileName, propertyName);
		try {

			if (propVal.split("->").length > 1) {
				return propVal.split("->")[0];
			} else
				return "xpath";
		} catch (NullPointerException e) {
			reportFail("Locator type: " + propVal + " provided is either blank or null " + propertyName
					+ "\n trace the property file" + propertyFileName);
			throw new InvalidLocator(propertyName, "Locator type: " + propVal + " provided is either blank or null "
					+ propertyName + "\n trace the property file" + propertyFileName);
		}
	}

	public static String getWhat(String propertyFileName, String propertyName) throws InvalidLocator {
		String propVal = PropertyFileUtils.getProperty(propertyFileName, propertyName);
		try {

			// log.info(propVal);
			if (propVal.split("->").length > 1) {
				return propVal.split("->")[1];
			} else
				return propVal.split("->")[0];
		} catch (NullPointerException e) {
			reportFail("Locator" + propVal + "provided is either blank or null " + propertyName
					+ "\n trace the property file" + propertyFileName);
			throw new InvalidLocator(propertyName, "Locator" + propVal + "provided is either blank or null "
					+ propertyName + "\n trace the property file" + propertyFileName);
		}

	}

	public static String getProperty(String propertyFileName, String propertyName) {
		String propVal = PropertyFileUtils.getProperty(propertyFileName, propertyName);
		return propVal;

	}

	public void highlightElement(WebElement element) {
		if (highLightPropertyName.trim().isEmpty() || highlightColor.trim().isEmpty())
			return;

		try {
			JavascriptExecutor jsDriver = ((JavascriptExecutor) driver);

			// Retrieve current background color
			// String propertyName = "border"; //"outline";
			// propertyName = "outline";
			String originalColor = "none";
			// String highlightColor = "#00ff00 solid 3px";

			try {
				// This works with internet explorer
				originalColor = jsDriver
						.executeScript("return arguments[0].currentStyle." + highLightPropertyName, element).toString();
			} catch (Exception e) {

				// This works with firefox, chrome and possibly others
				originalColor = jsDriver.executeScript("return arguments[0].style." + highLightPropertyName, element)
						.toString();
			}

			try {
				jsDriver.executeScript("arguments[0].style." + highLightPropertyName + " = '" + highlightColor + "'",
						element);
				Thread.sleep(50);

				jsDriver.executeScript("arguments[0].style." + highLightPropertyName + " = '" + originalColor + "'",
						element);
				Thread.sleep(50);

				jsDriver.executeScript("arguments[0].style." + highLightPropertyName + " = '" + highlightColor + "'",
						element);
				Thread.sleep(50);

				jsDriver.executeScript("arguments[0].style." + highLightPropertyName + " = '" + originalColor + "'",
						element);
			} catch (Exception e2) {
				reportFail("error in highlight function " + e2.getMessage());
			}

		} catch (Exception e) {
			reportFail("error in highlight function " + e.getMessage());
		}
	}

	public void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			return;
		}
		for (int i = 0; i < 55; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public WebElement getWebElement(String locator, boolean... optional) {
		try {
			String locatorFile = getLocatorPath();
			we = driver.findElement(findBy(locatorFile, locator));
			// highlightElement(we);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional != null && optional[0] != true) {
				log.info("Locator not found " + locator);
			} else {
				throwElementNotPresentException(e, locator, optional[0]);
			}
		}

		return we;
	}

	public String getData(String data) {
		try {
			String locatorFile = getLocatorPath();
			data = getWhat(locatorFile, data);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return data;
	}

	public void throwElementNotPresentException(Exception e, String locator, boolean optional) {
		if (optional == true) {
			log.info("Locator " + locator + "was not found however this step was optional");
			reportFail("Locator " + locator + "was not found however this step was optional");
		}
		reportFail("Locator " + locator + "was not found ");
		throw new NoSuchElementException("Webelement not found with locator " + locator + e.getMessage());

	}

	public String getWebElementText(String locator) {
		return getWebElement(locator).getText();
	}

	public String getLocatorPath() {
		return UiConstants.LOCATORS_PATH + "/" + this.getClass().getSimpleName() + ".properties";
	}

	public WebElement getWebElement(String locatorFilePath, String locator, boolean... optional) {
		try {
			we = driver.findElement(findBy(locatorFilePath, locator));
			// highlightElement(we);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional[0] != true) {
				throwElementNotPresentException(e, locator, optional[0]);
			}
		}

		return we;
	}

	public List<WebElement> getWebElements(String locator, boolean... optional) {
		List<WebElement> we = null;
		try {
			String locatorFile = getLocatorPath();
			we = driver.findElements(findBy(locatorFile, locator));
		} catch (Exception e) {
			e.printStackTrace();
			if (optional[0] != true) {
				throwElementNotPresentException(e, locator, optional[0]);
			}
		}

		return we;
	}

	public void clear(WebElement element) {

		try {
			// highlightElement(element);
			element.clear();
			// takeSnapShot();
		} catch (Exception e) {
			takeSnapShotonError();
			e.printStackTrace();
			reportFail("error while clearing the web field " + e.getMessage());
		}
	}

	public void clear(String locator) {

		try {
			getWebElement(locator).clear();
		} catch (Exception e) {
			takeSnapShotonError();
			e.printStackTrace();
			reportFail("error while clearing the web field " + e.getMessage());
		}
	}

	public void click(String locator) {

		try {
			getWebElement(locator).click();
			ExtentTestManager.getTest().log(Status.INFO, "clicked on " + locator);
			log("clicked on webelement");
		} catch (NoSuchElementException e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to locate element " + e.getStackTrace());
			log.info(("Unable to locate element " + e.getStackTrace()));
		} catch (Exception e) {
			e.printStackTrace();
			reportFail("error while clicking " + e.getMessage());
		}

	}

	public void click(WebElement element) {

		try {
			element.click();
			log("clicked on webelement");
		} catch (NoSuchElementException e) {
			ExtentTestManager.getTest().log(Status.INFO, "Unable to locate element " + e.getStackTrace());
			log.info(("Unable to locate element " + e.getStackTrace()));
		} catch (Exception e) {
			e.printStackTrace();
			reportFail("error while clicking " + e.getMessage());
		}

	}
	
	public void actionClick(String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(locator)).click().build().perform();
	}

	public void clickUsingJS(String locator) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(locator));
			ExtentTestManager.getTest().log(Status.INFO, "clicked on " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.INFO, "Unable to locate element " + e.getStackTrace());
			reportFail("error while clicking " + e.getMessage());
		}
	}

	public void clickUsingJS(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.INFO, "Unable to locate element " + e.getStackTrace());
		}
	}

	public void scrollToElement(String locator) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(locator));
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.INFO, "Unable to scroll to element " + e.getStackTrace());
		}
	}

	public String getTitle() {

		return driver.getTitle();
	}

	public void maximiseWindow() {

		driver.manage().window().maximize();
	}

	// pop up handling

	public void acceptAlert() {

		driver.switchTo().alert().accept();
	}

	public void dismissAlert() {

		driver.switchTo().alert().dismiss();
	}

	public String getAlertText() {

		return driver.switchTo().alert().getText();
	}

	// handlinf frames

	public void switchToFrame(int frame) {
		try {
			driver.switchTo().frame(frame);
			log.info(("frame  navigation through id " + frame));
		} catch (NoSuchFrameException e) {
			takeSnapShotonError();
			log.info(("Unable to locate frame with id " + frame + e.getStackTrace()));
		} catch (Exception e) {
			takeSnapShotonError();
			log.info(("Unable to navigate to frame with id " + frame + e.getStackTrace()));
		}
	}

	public void switchToFrame(String frame) {
		try {
			driver.switchTo().frame(frame);
			log.info(("frame  navigation through id " + frame));
		} catch (NoSuchFrameException e) {
			takeSnapShotonError();
			log.info(("Unable to locate frame with id " + frame + e.getStackTrace()));
		} catch (Exception e) {
			takeSnapShotonError();
			log.info(("Unable to navigate to frame with id " + frame + e.getStackTrace()));
		}
	}

	public void switchToFrame(String ParentFrame, String ChildFrame) {
		try {
			driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);
			log.info(("Navigated to innerframe with id " + ChildFrame + "which is present on frame with id"
					+ ParentFrame));
		} catch (NoSuchFrameException e) {
			takeSnapShotonError();
			log.info(("Unable to locate frame with id " + ParentFrame + " or " + ChildFrame + e.getStackTrace()));
			reportFail("Unable to locate frame with id " + ParentFrame + " or " + ChildFrame + e.getStackTrace());
		} catch (Exception e) {
			takeSnapShotonError();
			log.info(("Unable to navigate to innerframe with id " + ChildFrame + "which is present on frame with id"
					+ ParentFrame + e.getStackTrace()));
			reportFail("Unable to navigate to innerframe with id " + ChildFrame + "which is present on frame with id"
					+ ParentFrame + e.getStackTrace());
		}
	}

	public void switchtoDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			log.info(("Navigated back to webpage from frame"));
		} catch (Exception e) {
			log.info(("unable to navigate back to main webpage from frame" + e.getStackTrace()));
			Reporter.log("unable to navigate back to main webpage from frame" + e.getStackTrace());
		}
	}

	// quit driver

	public void quitDriver(WebDriver driver) {

		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeDriver(WebDriver driver) {

		try {
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkCheckBox(WebElement we) {
		if (!we.isSelected()) {
			we.click();
		}
	}

	public void uncheckCheckBox(WebElement we) {
		if (we.isSelected()) {
			we.click();
		}
	}

	public void takeSnapShotonError() {
		String fileName = TestDataGenerator.createText() + ".png";
		String fileWithPath = UiConstants.SNAPSHOT_PATH + AbstractTestBase.snapshotfolder + "/" + fileName;
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);

		try {
			FileUtils.copyFile(srcFile, destFile);
			reportLogScreenshot(destFile, "Error occurred");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void performType(WebElement element, String data) {
		if (StringUtils.isBlank(data)) {
			log.info(("Data is not present to perform type action"));
			return;
		}
		highlightElement(element);
		clear(element);
		type(element, data);
		// takeSnapShot();
	}

	public boolean isWebElementPresent(String element) {
		if (getWebElements(element).size() > 0) {
			ExtentTestManager.getTest().log(Status.INFO, element + " is Present");
			return true;
		} else {
			ExtentTestManager.getTest().log(Status.INFO, element + " not present on page");
			return false;
		}
	}

	public boolean isWebElementPresentAfterWait(String element) {
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		if (getWebElements(element).size() > 0) {
			return true;
		} else {
			ExtentTestManager.getTest().log(Status.INFO, element + " not present on page");
			return false;
		}

	}

	public boolean isWebElementDisplayed(String element) {
		return getWebElement(element).isDisplayed();

	}

	public boolean isWebElementEnabled(String element) {
		return getWebElement(element).isEnabled();

	}

	public boolean isTextPresentInPage(String text2Search) {
		return driver.getPageSource().contains(text2Search);

	}

	public void selectByOption(WebElement element, String data) {

		try {
			Select sel = new Select(element);
			highlightElement(element);
			sel.selectByVisibleText(data);
			// takeSnapShot();
			log("select (by option)" + data + " from the dropdown");
		} catch (NoSuchElementException e) {
			takeSnapShotonError();
			e.printStackTrace();
			reportFail("Webelement selection " + e.getMessage());
		} catch (Exception e) {
			takeSnapShotonError();
			e.printStackTrace();
			reportFail("Webelement selection " + e.getMessage());
		}

	}

	public void selectByIndex(WebElement element, int index) {

		try {
			Select sel = new Select(element);
			highlightElement(element);
			sel.selectByIndex(index);
			// takeSnapShot();
			log("select (by index)" + index + " from the dropdown");
		} catch (Exception e) {
			takeSnapShotonError();
			log.info("Exception occured while select by Index " + e.getMessage());
			reportFail("Exception occured while select by Index " + e.getMessage());
		}
	}

	public void selectByValue(WebElement element, String value) {

		try {
			Select sel = new Select(element);
			highlightElement(element);
			sel.selectByValue(value);
			// takeSnapShot();
			log("select (by value)" + value + " from the dropdown");
		} catch (Exception e) {
			takeSnapShotonError();
			log.info("Exception occured while select by Value " + e.getMessage());
			reportFail("Exception occured while select by Value " + e.getMessage());
		}
	}

	public void type(WebElement element, String data) {

		try {
			// highlightElement(element);
			element.sendKeys(data);
//			element.sendKeys(Keys.TAB);
//			if(driver.findElements(By.className("message")).size()>0) {
//				ExtentTestManager.getTest().log(Status.INFO, driver.findElement(By.className("message")).getText());
//			}
			// takeSnapShot();
			log("type " + data + " in input field.");
		} catch (Exception e) {
			e.printStackTrace();
			reportFail("error while type in text field " + e.getMessage());
			ExtentTestManager.getTest().log(Status.FAIL, "Error while Typing value " + data);

		}
	}

	public void type(String locator, String data) {

		try {
			// highlightElement(element);
			getWebElement(locator).sendKeys(data);
			ExtentTestManager.getTest().log(Status.INFO, "Typed value " + data + " in " + locator);
			log("type " + data + " in input field.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Error while Typing value " + data + " in " + locator);
			reportFail("error while type in text field " + e.getMessage());
		}
	}
	
	public void typeUsingJS(String locator, String data) {

		try {
			// highlightElement(element);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].value="+data+";",getWebElement(locator));
			ExtentTestManager.getTest().log(Status.INFO, "Typed value " + data + " in " + locator);
			log("type " + data + " in input field.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Error while Typing value " + data + " in " + locator);
			reportFail("error while type in text field " + e.getMessage());
		}
	}
	
	public void typeUsingJS(String locator, int data) {

		try {
			// highlightElement(element);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].value="+data+";",getWebElement(locator));
			ExtentTestManager.getTest().log(Status.INFO, "Typed value " + data + " in " + locator);
			log("type " + data + " in input field.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Error while Typing value " + data + " in " + locator);
			reportFail("error while type in text field " + e.getMessage());
		}
	}

	public void log(String str) {
		Reporter.log("<td><font size=\"2\" face=\"Comic sans MS\" color=\"green\"><b>" + str + "</b></font></td></tr>");
	}

	public void createTestCaseResults(String verdict, String testCaseID, String fullImageFile)
			throws IOException, URISyntaxException {
		// Create and configure a new instance of RallyRestApi
		String workspaceRef = "/workspace/13295507779";

		RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"), "agam_abrol@wrg-ins.com",
				"Tester@1");

		restApi.setWsapiVersion("v2.x");

		restApi.setApplicationName("Add Test Case Result");
		String testCaseResultRef = "";

		// Query User

		QueryRequest userRequest = new QueryRequest("User");

		userRequest.setFetch(new Fetch("UserName", "Subscription", "DisplayName"));

		userRequest.setQueryFilter(new QueryFilter("DisplayName", "=", automationTester));

		QueryResponse userQueryResponse = restApi.query(userRequest);

		JsonArray userQueryResults = userQueryResponse.getResults();

		JsonElement userQueryElement = userQueryResults.get(0);

		JsonObject userQueryObject = userQueryElement.getAsJsonObject();

		String userRef = userQueryObject.get("_ref").getAsString();

		// Query for Test Case to which we want to add results

		QueryRequest testCaseRequest = new QueryRequest("TestCase");

		testCaseRequest.setFetch(new Fetch("FormattedID", "Name", "Workspace", "Test Folder"));

		testCaseRequest.setWorkspace(workspaceRef);

		// testCaseRequest.setQueryFilter(new QueryFilter("TestFolder.Name", "=",
		// "TF3180"));

		testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", testCaseID));

		QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);

		JsonObject testCaseJsonObject = testCaseQueryResponse.getResults().get(0).getAsJsonObject();

		String testCaseRef = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();

		try {
			// Add a Test Case Result
			System.out.println("Creating Test Case Result...");
			JsonObject newTestCaseResult = new JsonObject();
			newTestCaseResult.addProperty("Verdict", verdict);
			java.util.Date date = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			String timestamp = sdf.format(date);

			newTestCaseResult.addProperty("Date", timestamp);
			newTestCaseResult.addProperty("Notes", "Automated Selenium Test Runs");
			newTestCaseResult.addProperty("Build", buildNumber);
			newTestCaseResult.addProperty("Tester", userRef);
			newTestCaseResult.addProperty("TestCase", testCaseRef);
			newTestCaseResult.addProperty("Workspace",
					testCaseJsonObject.get("Workspace").getAsJsonObject().get("_ref").getAsString());

			CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
			CreateResponse createResponse = restApi.create(createRequest);

			if (createResponse.wasSuccessful()) {
				System.out.println(String.format("Created %s", createResponse.getObject().get("_ref").getAsString()));

				// Read Test Case

				testCaseResultRef = Ref.getRelativeRef(createResponse.getObject().get("_ref").getAsString());

				System.out.println(String.format("\nReading Test Case Result %s...", testCaseResultRef));

				GetRequest getRequest = new GetRequest(testCaseResultRef);

				getRequest.setFetch(new Fetch("Date", "Verdict"));

				GetResponse getResponse = restApi.get(getRequest);

				JsonObject obj = getResponse.getObject();

				System.out.println(String.format("Read Test Case Result. Date = %s, Verdict = %s",
						obj.get("Date").getAsString(), obj.get("Verdict").getAsString()));

				addAttachment(restApi, testCaseResultRef, userRef, workspaceRef, fullImageFile);

			} else {
				String[] createErrors;
				createErrors = createResponse.getErrors();
				System.out.println("Error occurred creating Test Case: ");
				for (int i = 0; i < createErrors.length; i++) {
					System.out.println(createErrors[i]);
				}
			}

		} finally {
			restApi.close();
		}
	}

	private static void addAttachment(RallyRestApi restApi, String testCaseResultRef, String userRef,
			String workspaceRef, String fullImageFile) throws URISyntaxException, IOException {

		System.out.println("Created " + testCaseResultRef);

		String imageBase64String;

		long attachmentSize;

		// Open file

		RandomAccessFile myImageFileHandle = new RandomAccessFile(fullImageFile, "r");

		try {

			long longLength = myImageFileHandle.length();

			long maxLength = 5000000;

			if (longLength >= maxLength)
				throw new IOException("File size >= 5 MB Upper limit for Rally.");

			int fileLength = (int) longLength;

			// Read file and return data

			byte[] fileBytes = new byte[fileLength];

			myImageFileHandle.readFully(fileBytes);

			imageBase64String = Base64.encodeBase64String(fileBytes);

			attachmentSize = fileLength;

			// First create AttachmentContent from image string

			JsonObject myAttachmentContent = new JsonObject();

			myAttachmentContent.addProperty("Content", imageBase64String);

			CreateRequest attachmentContentCreateRequest = new CreateRequest("AttachmentContent", myAttachmentContent);

			// java.lang.NullPointerException if workspace parameter is not set as below

			attachmentContentCreateRequest.addParam("workspace", workspaceRef);

			CreateResponse attachmentContentResponse = restApi.create(attachmentContentCreateRequest);

			String myAttachmentContentRef = attachmentContentResponse.getObject().get("_ref").getAsString();

			System.out.println("Attachment Content created: " + myAttachmentContentRef);

			// Now create the Attachment itself

			JsonObject myAttachment = new JsonObject();

			myAttachment.addProperty("TestCaseResult", testCaseResultRef);

			myAttachment.addProperty("Content", myAttachmentContentRef);

			myAttachment.addProperty("Name", "AttachmentFromAUTOMATION.png");

			myAttachment.addProperty("Description", "Attachment From AUTOMATION");

			myAttachment.addProperty("ContentType", "image/png");

			myAttachment.addProperty("Size", attachmentSize);

			myAttachment.addProperty("User", userRef);

			CreateRequest attachmentCreateRequest = new CreateRequest("Attachment", myAttachment);

			// java.lang.NullPointerException if workspace parameter is not set as below

			attachmentCreateRequest.addParam("workspace", workspaceRef);

			CreateResponse attachmentResponse = restApi.create(attachmentCreateRequest);

			String myAttachmentRef = attachmentResponse.getObject().get("_ref").getAsString();

			System.out.println("Attachment  created: " + myAttachmentRef);

			if (attachmentResponse.wasSuccessful()) {

				System.out.println("Successfully created Attachment");

			} else {

				String[] attachmentContentErrors;

				attachmentContentErrors = attachmentResponse.getErrors();

				System.out.println("Error occurred creating Attachment: ");

				for (int j = 0; j < attachmentContentErrors.length; j++) {

					System.out.println(attachmentContentErrors[j]);

				}

			}

		} catch (Exception e) {

			System.out.println("Exception occurred while attempting to create Content and/or Attachment: ");

			e.printStackTrace();

		}

	}

}
