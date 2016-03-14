package com.aandd.simbaproject.utility;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Date {

	private static String Date;
	
	public static void setDate() {	
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date = sdf.format(gc.getTime());
	}

	public static String getDate() {
		return Date;
	}
	
}
