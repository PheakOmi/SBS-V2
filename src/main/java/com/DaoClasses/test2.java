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

		List<User_Info> users = new userDaoImpl().getAllUsersOfAllTypesButDriver();

		List<String> user_list= new ArrayList<String>();
		for (User_Info user:users)
			user_list.add(user.getEmail());
		String[] arr = new String[user_list.size()];
		arr = user_list.toArray(arr);
		for(String a:arr)
			System.out.println(a);


	}




}