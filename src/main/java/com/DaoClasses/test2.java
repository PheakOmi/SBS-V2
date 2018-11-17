package com.DaoClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.client_mail.ApplicationConfig;
import com.client_mail.MailService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.*;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class test2 {
	
	
	public static void main(String args[]) throws ParseException
	{

			sendE("ryota.tateishi@kit.edu.kh");


	}
	
	
	

	

	
	public static void sendE(String email) throws ParseException {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			trns  = session.beginTransaction();
				Mail mail = new Mail();
				mail.setMailFrom("vkirirom_shuttlebus@gmail.com");
				mail.setMailTo(email);
				mail.setMailSubject("Monthly Schedules");

				Map<String, Object> model = new HashMap<String, Object>();
				mail.setModel(model);
				mail.setFile_name("email_monthly_schedule5.txt");

				AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
				MailService mailService = (MailService) context.getBean("mailService");
				mailService.sendEmail(mail);
				context.close();
		}
		catch (RuntimeException e){
			if (trns != null) {
				trns.rollback();
			}
		}finally {
			session.flush();
			session.close();
		}
	}
}


