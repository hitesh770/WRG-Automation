package com.wrg.interfaces;

import com.wrg.interfaces.ExpectedConditions;

public interface ExpectedDBConditions extends ExpectedConditions {
	boolean getDBConditions(String query);
}
