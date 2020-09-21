 package com.wrg.main.Tests;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;

import com.wrg.abstestbase.AbstractTest;

public class MyMainClass extends AbstractTest {

	public static void main(String[] args) throws IOException

	{
		xmlCreation();
		testNGRun();
	}

	public static void testNGRun() {
		List<String> file = new ArrayList<String>();

		file.add(System.getProperty("user.dir") + "/TestScenarios.xml");

		TestNG tng = new TestNG();

		tng.setTestSuites(file);

		tng.run();
	}

	public static void xmlCreation() throws IOException {
		String f1 = System.getProperty("user.dir") + "\\" + "TestPlan.xls";

		FileInputStream fi = new FileInputStream(f1);
		HSSFWorkbook workbook = new HSSFWorkbook(fi);
		HSSFSheet sheetForModule = workbook.getSheet("MasterTestPlan");

		Map<String, String> suiteParams = new HashMap<String, String>();

		HSSFSheet sheetForFunctionality = null;

		HSSFRow rowForModule = null;

		HSSFRow rowForFunctionality = null;

		String className = null;

		XmlSuite suite = new XmlSuite();

		suite.setName("WRG");

		suiteParams.put("testng.metrics.report.name", "RegressionTest.html");

		suiteParams.put("testng.metrics.report.appendTimestamp", "True");

		suiteParams.put("testng.metrics.report.logo",
				System.getProperty("user.dir") + "\\src\\test\\resources\\" + "wrg_logo.jpg");

		suite.setParameters(suiteParams);

		int row_num_module = sheetForModule.getLastRowNum();

		for (int i = 1; i <= row_num_module; i++) {

			rowForModule = sheetForModule.getRow(i);

			if (rowForModule.getCell(3).toString().equalsIgnoreCase("Yes")) {

				className = rowForModule.getCell(1).toString();

				sheetForFunctionality = workbook.getSheet(rowForModule.getCell(1).toString());

				int row_num_functionality = sheetForFunctionality.getLastRowNum();

				for (int ii = 1; ii <= row_num_functionality; ii++) {

					XmlTest test = new XmlTest(suite);

					test.setThreadCount(1);

					ArrayList<XmlClass> classes = new ArrayList<XmlClass>();

					rowForFunctionality = sheetForFunctionality.getRow(ii);

					test.setName(rowForFunctionality.getCell(1).toString() + ii);

					if (rowForFunctionality.getCell(4).toString().equalsIgnoreCase("Yes")) {

						Map<String, String> testngParams = new HashMap<String, String>();

						int parameter_Columns = rowForFunctionality.getLastCellNum();

						for (int j = 1; j <= parameter_Columns - 1; j++) {
							if (rowForFunctionality.getCell(j) != null) {
								testngParams.put(String.valueOf(sheetForFunctionality.getRow(0).getCell(j)),
										String.valueOf(rowForFunctionality.getCell(j)).replace(".0", ""));

								test.setParameters(testngParams);
							}
						}

						XmlClass testClass = new XmlClass();

						ArrayList<XmlInclude> includedMethods = new ArrayList<XmlInclude>();

						includedMethods.add(new XmlInclude(rowForFunctionality.getCell(1).toString()));

						testClass.setIncludedMethods(includedMethods);

						testClass.setName(rowForFunctionality.getCell(3).toString().trim() + "." + className);

						classes.add(testClass);

					}

					test.setXmlClasses(classes);

				}

			}

		}

		suite.addListener("com.wrg.abstestbase.EventListener");

		suite.addListener("com.wrg.abstestbase.TestListener");

		suite.addListener("com.wrg.abstest.SuiteListener");
		
	    suite.addListener("com.wrg.utils.AnnotationTransformer");

		System.out.println(suite.toXml());

		File file = new File(System.getProperty("user.dir") + "/TestScenarios.xml");

		FileWriter writer = new FileWriter(file);

		writer.write(suite.toXml());

		writer.close();

	}

}
