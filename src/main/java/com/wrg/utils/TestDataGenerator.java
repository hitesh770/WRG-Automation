package com.wrg.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.wrg.abstestbase.MasterLogger;

public class TestDataGenerator {
	public static Logger log = MasterLogger.getInstance();


	public static String createText() {
		String uuid = UUID.randomUUID().toString();
		return uuid.toString();
	}


	public static String getformatedDateinTimeZone(String pattern, String zone) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		String currentTimeStamp=sdf.format(new Date());
		return currentTimeStamp;
	}
}
