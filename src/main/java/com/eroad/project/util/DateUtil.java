package com.eroad.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formatDate(Date date) {
		return dateSdf.format(date);
	}
	
	public static String formatTime(Date date) {
		return timeSdf.format(date);
	}
	
	public static Date parseDate(String dateStr) {
		try {
			return dateSdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Date parseTime(String dateStr) {
		try {
			return timeSdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String dateAdd(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - days);
		
		return formatDate(cal.getTime());
	}
	
	public static void main(String[] args) {
		
		Date now = new Date();
		for (int i = 9; i >= 0; i-- ){
			System.out.println(dateAdd(now, i));
		}
	}
	
}
