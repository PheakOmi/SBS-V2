package com.DaoClasses;

import java.text.SimpleDateFormat;

public class test2 {

	public static void main(String[] args) {
		try {
			String now = new String("1:00:00 PM");
			SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm:ss aa");
			SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm:ss");
			String time24 = outFormat.format(inFormat.parse(now));
			System.out.println("time in 24 hour format : " + time24);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
	}
}