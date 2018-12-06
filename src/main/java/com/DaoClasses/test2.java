package com.DaoClasses;

import com.EntityClasses.Booking_Master;
import com.EntityClasses.Schedule_Master;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class test2 {

	public static void main(String[] args) throws ParseException {
		String bdate = "13:00:00";
		System.out.println(new userDaoImpl().getScheduleById(29).getDept_time());
		boolean check = bdate.equals(new userDaoImpl().getScheduleById(29).getDept_time().toString());
		System.out.println(check);


	}


}