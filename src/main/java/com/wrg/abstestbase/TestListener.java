package com.wrg.abstestbase;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.wrg.utils.ExtentManager;
import com.wrg.utils.ExtentTestManager;
import com.wrg.utils.MyScreenRecorder;

/**
 
 *
 */
public class TestListener extends AbstractTest implements ITestListener, ITest {

	String snapshotsFolder = null;
	String fullImageFile=null;
	File destFile = null;

	@Override
	public void onFinish(ITestContext r) {
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onStart(ITestContext r) {

//		destFile = new File(folderPath+r.getSuite().getName()+System.currentTimeMillis());
//		if(!destFile.exists()) {
//			destFile.mkdir();
//			snapshotsFolder=destFile.toString();
//		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// takeSnapShotonError(r.getName(),destFile.toString());
		attributes.getAttribute("URI");
		//// reportLog("Test " + result.getName() + " Failed");
		// reportFailure("Test case failed:- " + result.getName());
		// reportLog("Error stacktrace:- " + result.getThrowable().getMessage());
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Case Failed is " + result.getName());
			ExtentTestManager.getTest().log(Status.DEBUG, "Error is " + result.getThrowable().toString());
			// To capture screenshot path and store the path of the screenshot in the string
			// "screenshotPath"
			// We do pass the path captured by this mehtod in to the extent reports using
			// "logger.addScreenCapture" method.
			String screenshotPath = null;
			String testCaseID = result.getTestContext().getCurrentXmlTest().getParameter("testCaseID");
			try {
				if(testCaseID!=null) {
					screenshotPath = getScreenshot(testCaseID);
				}else if(testCaseID==null){
					screenshotPath = getScreenshot(result.getName());
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// To add it in the extent report
			try {
				ExtentTestManager.getTest().log(Status.INFO,
						String.valueOf(ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(updateTestResults.equalsIgnoreCase("YES")) {
			try {
				if(testCaseID!=null) {
				createTestCaseResults("Fail", testCaseID, screenshotPath);
				}
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			reportLog("Test " + result.getName() + result.getStatus());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(Status.SKIP, "Test Case Skipped is " + result.getName());
		}
		try {
			MyScreenRecorder.stopRecording();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTestManager.endTest();
//		
	}

	@Override
	public void onTestSkipped(ITestResult r) {
		reportSuccess("Test " + r.getName() + " Skipped");
		ExtentTestManager.getTest().log(Status.SKIP, r.getName() + " Test Skipped");
//		if(r.getStatus()==ITestResult.FAILURE)
//			ExtentTestManager.getTest().log(Status.FAIL, r.getThrowable());
//		else if(r.getStatus()==ITestResult.SKIP)
//			ExtentTestManager.getTest().log(Status.SKIP, r.getThrowable());
//		else if(r.getStatus()==ITestResult.SUCCESS)
//			ExtentTestManager.getTest().log(Status.PASS, r.getTestName());
//		String str = r.getTestClass().getName() + "," + Arrays.toString(r.getMethod().getGroups()).replaceAll(",", ";")
//				+ "," + r.getName() + "," + r.getMethod().getDescription() + ","
//				+ Arrays.toString(r.getParameters()).replaceAll(",", ";") + "," + attributes.getAttribute("URI") + ","
//				+ attributes.getAttribute("INPUT") + "," + attributes.getAttribute("OUTPUT") + ","
//				+ getTestStatus(r.getStatus()) + ",,,";
		// csv.append2CSV(str.replaceAll("\"", ""));
	}

	@Override
	public void onTestStart(ITestResult r) {
		reportLog("Test Case " + r.getName() + " started on " + System.currentTimeMillis());
		ExtentTestManager.startTest(r.getMethod().getMethodName());
		try {
			MyScreenRecorder.startRecording(r.getMethod().getMethodName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		if(r.getStatus()==ITestResult.FAILURE)
//			ExtentTestManager.getTest().log(Status.FAIL, r.getThrowable());
//		else if(r.getStatus()==ITestResult.SKIP)
//			ExtentTestManager.getTest().log(Status.SKIP, r.getThrowable());
//		else if(r.getStatus()==ITestResult.SUCCESS)
//			ExtentTestManager.getTest().log(Status.PASS, r.getTestName());
	}

	@Override
	public void onTestSuccess(ITestResult r) {
		ExtentTestManager.getTest().log(Status.PASS, r.getName() + " Test passed");
		try {
			MyScreenRecorder.stopRecording();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(updateTestResults.equalsIgnoreCase("YES")) {
		String testCaseID = r.getTestContext().getCurrentXmlTest().getParameter("testCaseID");
		System.out.println(testCaseID);
		try {
			if(testCaseID!=null) {
				fullImageFile = getScreenshotForSuccessCases(testCaseID);
			}else {
				fullImageFile = getScreenshotForSuccessCases(r.getName());
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			createTestCaseResults("Pass", testCaseID, fullImageFile);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		reportLog("Test " + r.getName() + r.getStatus());
//		if(r.getStatus()==ITestResult.FAILURE)
//			ExtentTestManager.getTest().log(Status.FAIL, r.getThrowable());
//		else if(r.getStatus()==ITestResult.SKIP)
//			ExtentTestManager.getTest().log(Status.SKIP, r.getThrowable());
//		else if(r.getStatus()==ITestResult.SUCCESS)
//			ExtentTestManager.getTest().log(Status.PASS, r.getTestName());
//		String str = r.getTestClass().getName() + "," + Arrays.toString(r.getMethod().getGroups()).replaceAll(",", ";")
//				+ "," + r.getName() + "," + r.getMethod().getDescription() + ","
//				+ Arrays.toString(r.getParameters()).replaceAll(",", ";") + "," + attributes.getAttribute("URI") + ","
//				+ attributes.getAttribute("INPUT") + "," + attributes.getAttribute("OUTPUT") + ","
//				+ getTestStatus(r.getStatus()) + ",,,";
		// csv.append2CSV(str.replaceAll("\"", ""));
		// String stringTracerArray =
		// getStringFromArrayList(attributes.getTracerIdList());

		// String tracerdetails = r.getTestClass().getName() + "," + r.getName() + "," +
		// stringTracerArray;
		// csvtracers.append2CSV(tracerdetails);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	private String getStringFromArrayList(ArrayList<String> l) {
		String tmp = "";
		for (int i = 0; i < l.size(); i++) {
			tmp = tmp + "," + l.get(i);
		}
		return tmp;
	}

	private String getTestStatus(int status) {
		if (status == 1) {
			return "PASSED";
		} else if (status == 2) {
			return "FAILED";
		} else if (status == 3) {
			return "SKIPPED";
		} else
			return "STARTED";
	}

	@Override
	public String getTestName() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getScreenshot(String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
		String fileName=screenshotName + dateName+ ".png";
		String fileNameWithPath = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+fileName;
		TakesScreenshot ts = ((TakesScreenshot) driver);
		File source = ts.getScreenshotAs(OutputType.FILE);
		File finalDestination = new File(fileNameWithPath);
		FileUtils.copyFile(source, finalDestination);
		return fileNameWithPath;
	}

	public static String getScreenshotForSuccessCases(String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
		String fileName=screenshotName + dateName+ ".png";
		String fileNameWithPath = System.getProperty("user.dir") + "/src/test/resources/snapshots/"+fileName;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File finalDestination = new File(fileNameWithPath);
		FileUtils.copyFile(source, finalDestination);
		return fileNameWithPath;
	}

}
