package com.client_mail;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.EntityClasses.Booking_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Mail;

public class Test_Forget_Password {
	public static void main(String args[]) {
		int id=17;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
     	try {
     		trns = session.beginTransaction();
	        String hql1 ="from User_Info where id='"+id+"'";
	        Query query1 =  session.createQuery(hql1);
	        if(query1.list().size()>0){
	        	User_Info user = (User_Info) query1.list().get(0);
	        	Mail mail = new Mail();
		        mail.setMailFrom("maimom2222@gmail.com");
		        mail.setMailTo("maimom61@gmail.com");
		        mail.setMailSubject("vKirirom Shuttle Bus Password Reset");
		        mail.setFile_name("forget_password_template.txt");
		 
		        Map < String, Object > model = new HashMap < String, Object > ();
		        model.put("email", user.getEmail());
		        model.put("key", user.getReset_token());
		        mail.setModel(model);
		 
		        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		        MailService mailService = (MailService) context.getBean("mailService");
		        mailService.sendEmail(mail);
		        context.close();
	        } 	 
	        trns.commit();
	
     	}catch (RuntimeException e){
	       System.out.println(e);
	     }finally {
	            session.flush();
	            session.close();
	     }
    }
}
