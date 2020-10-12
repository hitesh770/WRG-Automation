package com.wrg.AP.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.NeedsLocalLogs;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wrg.abstestbase.AbstractTest;
import com.wrg.utils.ExtentTestManager;


public class StartQuotePage_AP extends AbstractTest {
	WebDriverWait wait = null;

	
	public int verifyStartQuotePageElementsPresence() {
		int totalcount=0;
		 int currentelementcount=0;
			List<String> totalPageElements=getStartQuoteUIElementList();
		      
		     for(int i=0;i<totalPageElements.size();i++) {
		    	  currentelementcount=0;
		    	  String curlocater=totalPageElements.get(i);
		    	 
		    	  List<WebElement> curloclist=getCurrentList(totalPageElements.get(i));
		    	  inner:for(int j=1;j<=curloclist.size();j++) {
		    		  WebElement curElement=null;
		    		  try { 
		    		   curElement=getCurrentElement(curlocater+"["+j+"]"); 
		    	          }catch(Exception e) {
		    	        	  continue inner;
			              }
		    		  if(curElement.isDisplayed()==true) { 
		    			  ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(curElement.getText() + " Page Element is Present",ExtentColor.BLUE));
			    			currentelementcount++;
			    			continue inner;
			    		}
			    		 
			    	 } //inner for loop close here
		    	  totalcount=totalcount+currentelementcount;
		    	  
		      } //outer  for loop close here
		      
			
			return totalcount;
		}
		
		public  List<String> getStartQuoteUIElementList() {
			waitForPageLoaded();
			List<String> totalelements=new ArrayList<String>();
			totalelements.add(getData("startquoteuilabels"));
			totalelements.add(getData("startquoteuiinputfields"));
			totalelements.add(getData("startquoteuibuttons"));
			return totalelements;
		}
		
		
		public int getStartQuoteUIElementTotalCount() {
			return Integer.parseInt(getData("startquoteuielements"));	
		}
		
	
		public List<WebElement> getCurrentList(String data, boolean... optional) {
			List<WebElement> we = null;
			try {
				we = driver.findElements(By.xpath(data));
			} catch (Exception e) {
				e.printStackTrace();
				if (optional[0] != true) {
					throwElementNotPresentException(e, data, optional[0]);
				}
			}

			return we;
		}
		
		
		
		
		public WebElement getCurrentElement(String data) {
			WebElement we = null; 
			try {
				 we = driver.findElement(By.xpath(data));
			} catch (Exception e) {
				e.printStackTrace();
				
			}

			return we;
		}
		
	// implementing condition to check insurance type button selection state
		public void checkInsurancetypeButtonState() {
			if(isWebElementPresent("insurancetypebutton")==true) { 
	          clickUsingJS("insurancetypebutton");   
			}
			
		}
	
	
	public void addClassification(String classCodeNumber) {
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForPageLoaded();
		if (classCodeNumber.contains(",")) {
			String spiltClassCodes[] = classCodeNumber.split(",");
			List<String> classCodes = new ArrayList<String>();
			for (String classCode : spiltClassCodes) {
				classCodes.add(classCode);
				Actions action = new Actions(driver);
				action.moveToElement(getWebElement("addClassificationButton")).build().perform();;
				click("addClassificationButton");

				String mainwindow = driver.getWindowHandle(); // get parent(current) window name
				for (String popup : driver.getWindowHandles()) // iterating on child windows
				{
					driver.switchTo().window(popup);
					type(("classCodeNumber"), classCode);
					clickUsingJS("classCode");

					clickUsingJS("okButton");
					if (isWebElementPresent("hazardCode") == true) {
						clickUsingJS("hazardCode");
						clickUsingJS("okButtonForHazardCode");
					}
				}
				driver.switchTo().window(mainwindow);

			}
		} else {
			clickUsingJS("addClassificationButton");

			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				type(("classCodeNumber"), classCodeNumber);
				clickUsingJS("classCode");

				clickUsingJS("okButton");
				if (isWebElementPresent("hazardCode") == true) {
					clickUsingJS("hazardCode");
					clickUsingJS("okButtonForHazardCode");
				}
			}

			driver.switchTo().window(mainwindow);
		}
		sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getWebElement("nextButton")));
		clickUsingJS("nextButton");
		if(isWebElementPresent("okButtonForUnderwritingReferralRequired")==true) {
			clickUsingJS("okButtonForUnderwritingReferralRequired");
		}
	}
	
	
	// function to verify Gl class code search results
	public boolean verifyClassCodeSearchResults(String classCodeNumber) {
		String message="";
		String regex="[^0-9a-zA-Z]+";
		String regexString="[0-9]+";
		String regexString2="[a-zA-Z]+";
		boolean flag=true;
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			clickUsingJS("addClassificationButton");
			String mainwindow = driver.getWindowHandle(); // get parent(current) window name
			for (String popup : driver.getWindowHandles()) // iterating on child windows
			{
				driver.switchTo().window(popup);
				String okbuttoncolor=getWebElementColor("okButton", "background-color");
				// verifying input field names and column names
				if(getWebElements("classcodesearchinputfields").size()<=2 && getWebElements("classcodesearchresultcolumns").size()<=3) {
					
					for(WebElement ele1:getWebElements("classcodesearchinputfieldsnames")) {
						 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Fields name are " +ele1.getText(),ExtentColor.BLUE));
					}
					
                          for(WebElement ele2:getWebElements("classcodesearchresultcolumns")) {
						if(!ele2.getText().isEmpty()) {
							 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Columns names are " +ele2.getText(),ExtentColor.BLUE));
						}
                        	  
					}
                          String value1=getWebElement("classCodeNumber").getAttribute("value");
          				String value2=getWebElement("classCodeClassification").getAttribute("value");
          				ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Value in class code field is blank " +value1,ExtentColor.BLUE));
          				ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Value in classfication field is blank " +value2,ExtentColor.BLUE));
					flag=true;  
					
				}else {
					 return flag=false;
					
				}
				type(("classCodeNumber"), classCodeNumber);
                if(getWebElement("classCodeNumber").getAttribute("value").matches(regex)) { 
                	type(("classCodeClassification"), getData("classCodeClassificationdata"));
                	
                	// retrieving classification results based on given classification input
                	HashMap<List<WebElement>, List<WebElement>> searchresults=storeGLClassCodeSearchResults(getWebElements("glclasscodesearchresults"), getWebElements("glclasscodedescriptionsearchresults"));
    				Set<Entry<List<WebElement>, List<WebElement>>> s=searchresults.entrySet();
    				for(Map.Entry<List<WebElement>,List<WebElement>> data: s) {
    					List<WebElement> classificationlist=data.getValue();
    					for(WebElement classificationele:classificationlist) { 
    						System.out.println(classificationele.getText());
    						
    						 if(classificationele.getText().contains(getData("classCodeClassificationdata"))) {
    							 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Classfications in search results are " +classificationele.getText(),ExtentColor.BLUE));
    							 flag=true;
    					      }else {
    					    	  ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Classfications in search results are " +classificationele.getText(),ExtentColor.BLUE));
     							 return flag=false; 
    					      }
    					
    					}
    				}
					
                	
                	
                	
                	String tagname=getWebElement("classCodeNumber").getTagName();
                	String tagname2=getWebElement("classCodeClassification").getTagName();
    				if(tagname.equalsIgnoreCase("input")) {
    					 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Fields is textbox",ExtentColor.BLUE));
    					 flag=true;
    				}else {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Field is " +tagname,ExtentColor.BLUE));
    					 return flag=false;
    				}
    				
    				if(tagname2.equalsIgnoreCase("input")) {
   					 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Fields is textbox",ExtentColor.BLUE));
   					 flag=true;
   				}else {
   					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Field is " +tagname2,ExtentColor.BLUE));
   					 return flag=false;
   				}
    				
    				if(okbuttoncolor.equals("#bcbcbc")) {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Ok button color is grey in disabled mode ",ExtentColor.BLUE));
    				   clickUsingJS("classcodesearchcancelbtn");
    				   if(isWebElementDisplayed("startquotepagesubsectionheading")) {
							ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" class code sub section headingon start quote page is " +getWebElementText("startquotepagesubsectionheading"),ExtentColor.BLUE));
						}else {  
							return flag=false;
						}// internal if else for verifying after click  on cancel button
    				   
    				}else {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Ok button color is " +okbuttoncolor,ExtentColor.BLUE));
    					return flag=false;
    				}
    			
    				
                }else if(getWebElement("classCodeNumber").getAttribute("value").matches(regexString) || getWebElement("classCodeClassification").getAttribute("value").matches(regexString2) && isWebElementPresent("classcodesearchresultmessage")==false) {
                	//checking code 
    				HashMap<List<WebElement>, List<WebElement>> searchresults=storeGLClassCodeSearchResults(getWebElements("glclasscodesearchresults"), getWebElements("glclasscodedescriptionsearchresults"));
    				Set<Entry<List<WebElement>, List<WebElement>>> s=searchresults.entrySet();
    				for(Map.Entry<List<WebElement>,List<WebElement>> data: s) {
    					// retrieving class code results
    					List<WebElement> codelist=data.getKey();
    					for(WebElement codeele:codelist) { 
    					      if(codeele.getText().contains(classCodeNumber)) {
    					    	  ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Class code in search results are " +codeele.getText(),ExtentColor.BLUE));
    					    	  flag=true;
    					      }else {
    					    	  ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Class code in search results are " +codeele.getText(),ExtentColor.BLUE));
    					    	  return flag=false;
    					      }
    					
    					} // inner list iterator for loop closes here
    					
    					
    						
    					
    				}// outer for loop closes here
    				
    			
    				
    				
    				// clicking on class code column descending button

    				for(WebElement ele1:getWebElements("classcodesearchresultcolumns")) {
    					if(ele1.getText().equalsIgnoreCase("GL Class Code")) {
    						ele1.click();
    						ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" clicked on Class codes column to sort codes in descending order  ",ExtentColor.BLUE));
    						break;
    					}
    				}
    				
    				// verifying descending sorting of class  codes 
    
    				if(isClassCodeSortedinDescendingOrder(getWebElements("glclasscodesearchresults"))) {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Class codes are sorted in descending order ",ExtentColor.BLUE));
    				}else {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Class codes are not sorted in descending order ",ExtentColor.BLUE));
    					return flag=false;
    				}
    				
    				
  
    				
    				// clicking on class code column ascending button

    				for(WebElement ele1:getWebElements("classcodesearchresultcolumns")) {
    					if(ele1.getText().equalsIgnoreCase("GL Class Code")) {
    						ele1.click();
    						ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" clicked on classification  column to sort codes in ascending order  ",ExtentColor.BLUE));
    						break;
    					}
    				}
    				
    				
    				// verifying ascending sorting of class  codes 
    	    		
    				if(isClassCodeSortedinAscendingOrder(getWebElements("glclasscodesearchresults"))) {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Class codes are sorted in ascending order ",ExtentColor.BLUE));
    				}else {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Class codes are not sorted in ascending order ",ExtentColor.BLUE));
    					return flag=false;
    				}
    				
    				
    				// clicking on classification column descending button

    				for(WebElement ele1:getWebElements("classcodesearchresultcolumns")) {
    					if(ele1.getText().equalsIgnoreCase("Classification")) {
    						ele1.click();
    						ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" clicked on classification  column to sort codes in descending order  ",ExtentColor.BLUE));
    						break;
    					}
    				}
    				
    			
                      // verifying descending sorting of classification 
    			    
    				if(isClassficiationSortedinDescendingOrder(getWebElements("glclasscodedescriptionsearchresults"))) { 
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Classficiations are sorted in descending order ",ExtentColor.BLUE));
    				}else {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Classficiations are not sorted in descending order ",ExtentColor.BLUE));
    					return flag=false;
    				} 
    				
    				
    				// clicking on classification column ascending button

    				for(WebElement ele1:getWebElements("classcodesearchresultcolumns")) {
    					if(ele1.getText().equalsIgnoreCase("Classification")) {
    						ele1.click();
    						ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" clicked on Class codes column to sort codes in descending order  ",ExtentColor.BLUE));
    						break;
    					}
    				}
    				
    				
    				
                    // verifying ascending sorting of classification 
    			    
    				if(isClassficiationSortedinAscendingOrder(getWebElements("glclasscodedescriptionsearchresults"))) { 
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Classficiations are sorted in ascending order ",ExtentColor.BLUE));
    				}else {
    					ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Classficiations are not sorted in ascending order ",ExtentColor.BLUE));
    					return flag=false;
    				}
    				
    				
    				
    				// selecting record and click on ok button
    				clickUsingJS("classCode");
    				String okbuttoncolorafterrecordselect =getWebElementColor("okButton", "background-color");
					 if(okbuttoncolorafterrecordselect.equals("#004ebd")) {
						ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Ok button color is blue in enabled mode after record selection ",ExtentColor.BLUE));
						clickUsingJS("okButton");
						if(isWebElementDisplayed("startquotepagesubsectionheading")) {
							ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" class code sub section heading on start quote page is " +getWebElementText("startquotepagesubsectionheading"),ExtentColor.BLUE));
						}else {  
							return flag=false;
						} // inner if else condition close for verifying heading after click on ok button
					}else {
						return flag=false;
					}
					
                }else if(isWebElementPresent("classcodesearchresultmessage")) {
                 	
                	String value=getWebElementText("classcodesearchresultmessage");
					if(value.equalsIgnoreCase("The search returned zero results")) {
						 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel(" Search Result window message is " +value,ExtentColor.BLUE));
						message=value; 
					}else {
						ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Search Result window message is " +value,ExtentColor.BLUE));
						message=value; 
						 return flag=false;
					}
                	
                	
                }
              
				
				
			
			}  
			driver.switchTo().window(mainwindow);
		return flag;
		
	}
	
		//function to store Gl class code results
		 
		public HashMap<List<WebElement>, List<WebElement>> storeGLClassCodeSearchResults(List<WebElement> classcodelist,List<WebElement> classificationlist) {
			HashMap<List<WebElement>, List<WebElement>> searchresults=new HashMap<List<WebElement>, List<WebElement>>();
			searchresults.put(classcodelist, classificationlist);
			return searchresults;
			
		}
		
		//function to know whether  class codes in descending order are sorted on search pop up
		public boolean isClassCodeSortedinDescendingOrder(List<WebElement> codelist){
			boolean sorted=true;
		for(int i=0;i<codelist.size()-1;i++) {
		
			if(Integer.parseInt(codelist.get(i).getText())>Integer.parseInt(codelist.get(i+1).getText())) {
				ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Class code is " +Integer.parseInt(codelist.get(i).getText()),ExtentColor.BLUE));
				sorted=true;
			}else {
				ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Class code is " +Integer.parseInt(codelist.get(i).getText()),ExtentColor.BLUE));
				ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Class code is " +Integer.parseInt(codelist.get(i+1).getText()),ExtentColor.BLUE));
				return sorted=false;
			}
			
		}
			
			return sorted;
		}
		
		//function to know whether  class codes are sorted in ascending order on search pop up
             public boolean isClassCodeSortedinAscendingOrder(List<WebElement> codelist){
            	 boolean sorted=true;
         		for(int i=0;i<codelist.size()-1;i++) {
         		
         			if(Integer.parseInt(codelist.get(i).getText())<Integer.parseInt(codelist.get(i+1).getText())) {
         				ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Class code is " +Integer.parseInt(codelist.get(i).getText()),ExtentColor.BLUE));
         				sorted=true;
         			}else {
         				ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Class code is " +Integer.parseInt(codelist.get(i).getText()),ExtentColor.BLUE));
         				ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Class code is " +Integer.parseInt(codelist.get(i+1).getText()),ExtentColor.BLUE));
         				return sorted=false;
         			}
         			
         		}
         			
         			return sorted;
		}
	

             
             // function to check whether list is in ascending order
             public static boolean isClassficiationSortedinAscendingOrder(List<WebElement> listOfStrings) {
         	    if (isEmpty(listOfStrings) || listOfStrings.size() == 1) {
         	        return true;
         	    }
         	 
         	    Iterator<WebElement> iter = listOfStrings.iterator();
         	    String current="";
         	    String previous = iter.next().getText();
         	   ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Classification is " +previous,ExtentColor.BLUE));
         	    while (iter.hasNext()) {
         	        current = iter.next().getText();
         	       ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Classification is " +current,ExtentColor.BLUE));
         	        if (previous.compareTo(current) > 0) {
         	            return false;
         	        }
         	        previous = current;
         	    }
         	    return true;
         	}
         	
         	
          // function to check whether list is in descending  order
         	public static boolean isClassficiationSortedinDescendingOrder(List<WebElement> listOfStrings) {
         	    if (isEmpty(listOfStrings) || listOfStrings.size() == 1) {
         	        return true;
         	    }
         	 
         	   Iterator<WebElement> iter = listOfStrings.iterator();
        	    String current="";
        	    String previous = iter.next().getText();
        	    ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Classification is " +previous,ExtentColor.BLUE));
         	    while (iter.hasNext()) {
         	    	 current = iter.next().getText();
         	    	 ExtentTestManager.getTest().log(Status.INFO,  MarkupHelper.createLabel("Classification is " +current,ExtentColor.BLUE));
         	        if (previous.compareTo(current) < 0) {
         	            return false;
         	        }
         	        previous = current;
         	    }
         	    return true;
         	}
             
             
}
