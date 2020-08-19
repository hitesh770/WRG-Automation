package com.wrg.interfaces;

import com.wrg.interfaces.ExpectedConditions;
import com.wrg.utils.EmailUtility;

public interface EmailFilterConditions extends ExpectedConditions {
	boolean getEmailFilterConditions(EmailUtility e,String emailSubject);
}
