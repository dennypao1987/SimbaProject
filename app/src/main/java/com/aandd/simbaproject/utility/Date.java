package com.aandd.simbaproject.utility;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Date {

	private static String date;
	
	public static void setDate() {	
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		date = sdf.format(gc.getTime());
	}

	public static String getDate() {
		return date;
	}
	
}
