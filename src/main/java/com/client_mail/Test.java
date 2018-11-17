package com.client_mail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Booking_Master;
import com.HibernateUtil.HibernateUtil;



public class Test {
	
	public String DateNow(){
		Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTimeString = sdf.format(d);
        System.out.println(currentDateTimeString);
        return currentDateTimeString;
	}
    public static void main(String args[]){
    	Test test=new Test();
    	Transaction trns1 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();   
        List<Booking_Master> cr = new ArrayList<Booking_Master>();
		try {
            trns1 = session.beginTransaction();
            cr = session.createQuery("from Booking_Master where dept_date>=? and email_confirm='false' and description='customer'").setDate(0, java.sql.Date.valueOf(test.DateNow())).list();
            System.out.println(cr.size());
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }    
		finally {
            session.flush();
            session.close();
        }
    }
}
