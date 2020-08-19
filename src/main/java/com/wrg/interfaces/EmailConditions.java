package com.wrg.interfaces;

import com.wrg.interfaces.ExpectedConditions;
import com.wrg.utils.EmailUtility;

public interface EmailConditions extends ExpectedConditions {
	boolean getEmailConditions(EmailUtility e);
}