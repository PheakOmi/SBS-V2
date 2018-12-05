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
		Schedule_Master schedule = new userDaoImpl().getScheduleById(29);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today=sdf.format(cal.getTime());
		Date d1 = sdf.parse(today);
		Date d2 = null;

		d2 = schedule.getDept_date();
		d2.setHours(schedule.getDept_time().getHours());
		d2.setMinutes(schedule.getDept_time().getMinutes());

		System.out.println(d1);
		System.out.println(d2);

		System.out.println(d1.after(d2));

//		long diff = d2.getTime() - d1.getTime();
//		long diffDays = diff / (24 * 60 * 60 * 1000);

//
//
//
//		System.out.println(d1);
//		System.out.println(d2);
//
//		System.out.println(d1.getTime());
//		System.out.println(d2.getTime());
//
//		System.out.println(diffDays);
//
//		if (diffDays<0)
//			{
//				System.out.println("Past");
//			}
//		else if(diffDays==0)
//			{
//				System.out.println("Same Date");
//				if(d1.getHours()-d2.getHours()>0)
//					{
//						System.out.println("Past");
//					}
//				else if(d1.getHours()-d2.getHours()==0)
//					{
//						System.out.println("Same Hour");
//						if(d1.getMinutes()-d2.getMinutes()>0)
//							{
//								System.out.println("Past");
//							}
//						else
//							System.out.println("Present");
//					}
//				else
//					System.out.println("Current");
//			}
//
//		else
//			System.out.println("Current");
	}
	public boolean checkDateTime(Booking_Master booking) throws ParseException{

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today=sdf.format(cal.getTime());
		Date d1 = sdf.parse(today);
		Date d2 = null;

		d2 = booking.getDept_date();
		d2.setHours(booking.getDept_time().getHours());
		d2.setMinutes(booking.getDept_time().getMinutes());

		return d1.after(d2);
	}


}