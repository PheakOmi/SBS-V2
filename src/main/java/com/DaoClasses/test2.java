package com.DaoClasses;

import com.EntityClasses.Booking_Master;
import com.EntityClasses.Schedule_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Mail;
import com.ModelClasses.Schedule_Model;
import com.client_mail.ApplicationConfig;
import com.client_mail.MailService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class test2 {

	public static void main(String[] args) throws ParseException {

		String[] arr = new String[2];
		arr[0] = "sopheakdy23@gmail.com";
		arr[1] = "dysopheak15@kit.edu.kh";

		Mail mail = new Mail();
		mail.setMailFrom("shuttlebus@kit.edu.kh");
		mail.setMailTo(arr);
		mail.setMailSubject("Updated Schedule");

		Map<String, Object> model = new HashMap<String, Object>();
		mail.setModel(model);
		mail.setFile_name("blank.txt");


		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		MailService mailService = (MailService) context.getBean("mailService");
		mailService.sendEmail(mail);

	}




}