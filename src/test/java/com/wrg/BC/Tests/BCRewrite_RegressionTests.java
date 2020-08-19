package com.wrg.BC.Tests;

import org.testng.annotations.BeforeMethod;

import com.wrg.BC.pages.ActivitiesCreatedPage_BC;
import com.wrg.abstestbase.AbstractTest;

public class BCRewrite_RegressionTests extends AbstractTest{
	
	ActivitiesCreatedPage_BC activitiesCreatedPage_BC = null;
	
	@BeforeMethod
	public void befroeMethod() {
		activitiesCreatedPage_BC = new ActivitiesCreatedPage_BC();
	}
	

}
