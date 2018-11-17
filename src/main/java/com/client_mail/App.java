package com.client_mail;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.DaoClasses.Custom_Imp;
import com.DaoClasses.QR_Image_Gemerator;
import com.EntityClasses.Booking_Master;
import com.EntityClasses.Location_Master;
import com.EntityClasses.Pickup_Location_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Mail;
import com.PaymentGateway.EmbeddedImageEmailUtil;
 

 
public class App {
 
    public static void main(String args[]) {
   	
     	Custom_Imp ci=new Custom_Imp();
        List<User_Info> user = new ArrayList<User_Info>();
        List<Pickup_Location_Master> pickUp = new ArrayList<Pickup_Location_Master>();
        List<Location_Master> source = new ArrayList<Location_Master>();
        List<Pickup_Location_Master> drop_off = new ArrayList<Pickup_Location_Master>();
        List<Location_Master> destination = new ArrayList<Location_Master>();
        Map<String, Object> map=new HashMap<String, Object>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        trns = session.beginTransaction();
		try {
			Booking_Master bm =(Booking_Master) session.load(Booking_Master.class, 43);
            user = session.createQuery("from User_Info where id=:id").setParameter("id", bm.getUser_id()).list();
        	pickUp = session.createQuery("from Pickup_Location_Master where id=:id").setParameter("id", bm.getSource_id()).list();
        	source = session.createQuery("from Location_Master where id=:id").setParameter("id", bm.getFrom_id()).list();
        	drop_off = session.createQuery("from Pickup_Location_Master where id=:id").setParameter("id", bm.getDestination_id()).list();
        	destination = session.createQuery("from Location_Master where id=:id").setParameter("id", bm.getTo_id()).list();
        	if(user.size()>0&&pickUp.size()>0&&source.size()>0
        			&&drop_off.size()>0&&destination.size()>0){
        		
        		QR_Image_Gemerator qr =new QR_Image_Gemerator();
        		qr.qr_generator(bm);
        		// SMTP info
                String host = "smtp.gmail.com";
                String port = "587";
                String mailFrom = "nanaresearch9@gmail.com";
                String password = "hanako12624120";
         
                // message info
                String mailTo = "sopheakdy23@gmail.com";
                String subject = "vKirirom Shuttle Bus Booking Confirmation";
                StringBuffer body
                    = new StringBuffer("<html>"
                    		+ "<div style=\"padding:3%\">"
                    		+ "<img style=\"width:120px;height:100px\" "
                    		+ "src=\"http://vkirirom.com/images/About_Company/vKirirom.png\">");
                //Open <td>
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">Dear, <b>"+user.get(0).getUsername()+"</b></p>");
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">Thank you for using our vKirirom Shuttle Bus. Please enjoy your trip"
                		+ "with the beautiful view of Kirirom moutain.</p>");
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">Here is your trip detail: </p>");
                //Table
                body.append("<table style=\"font-family: arial, sans-serif; border-collapse: collapse;width: 100%; color:black\">");
                body.append("<tr>"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Booking Code</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+bm.getCode()+"</td>"
                		+"</tr>");
                body.append("<tr style=\"background-color: #dddddd;\">"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">From</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+source.get(0).getName()+"</td>"
                		+"</tr>");
                body.append("<tr>"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Pick-up Location</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+pickUp.get(0).getName()+"</td>"
                		+"</tr>");
                body.append("<tr style=\"background-color: #dddddd;\">"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">To</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+destination.get(0).getName()+"</td>"
                		+"</tr>");
                body.append("<tr>"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Drop-off Location</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+drop_off.get(0).getName()+"</td>"
                		+"</tr>");
                body.append("<tr style=\"background-color: #dddddd;\">"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Departure Date</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+ci.convertDateTimetoDate(bm.getDept_date())+"</td>"
                		+"</tr>");
                body.append("<tr>"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Departure Time</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+bm.getDept_time()+"</td>"
                		+"</tr>");
                body.append("<tr style=\"background-color: #dddddd;\">"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+(bm.getChild()<=1?"Child":"Children")+"</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+bm.getChild()+"</td>"
                		+"</tr>");
                body.append("<tr>"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+(bm.getAdult()<=1?"Adult":"Adults")+"</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+bm.getAdult()+"</td>"
                		+"</tr>");
                body.append("<tr style=\"background-color: #dddddd;\">"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Total Amount</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\"> "+bm.getNumber_booking()+" "
                			+(bm.getNumber_booking()>1?"people":"person")+"</td>"
                		+"</tr>");
                body.append("<tr>"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Total Cost</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\"> "+bm.getTotal_cost()+" $</td>"
                		+"</tr>");
                body.append("</table>");
                body.append("<br>");
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">Below is your QR-code for this trip:</p>");
                //End Table
                body.append("<img src=\"cid:image1\" width=\"50%\" height=\"40%\" /><br>");
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">Thank you</p>"
                		+ "<p style=\"font-family: arial, sans-serif; color:black\">KIT Technical Support</p>");
                
                //close </td>, </tr>, and <table>
                body.append("</div>");
                body.append("</html>");

                // inline images
                Map<String, String> inlineImages = new HashMap<String, String>();
                inlineImages.put("image1", "D:/Directory_Test/"+bm.getQr_name()+".png");
                //inlineImages.put("image2", "C:/Users/Hanako/Pictures/Screenshots/a.png");
         
                try {
                    EmbeddedImageEmailUtil.send(host, port, mailFrom, password, mailTo,
                        subject, body.toString(), inlineImages);
                    System.out.println("Email sent.");
                } catch (Exception ex) {
                    System.out.println("Could not send email.");
                    ex.printStackTrace();
                }
        		
        		
//        		Mail mail = new Mail();
//		        mail.setMailFrom("nanaresearch9@gmail.com");
//		        mail.setMailTo(user.get(0).getEmail());
//		        mail.setMailSubject("vKirirom Shuttle Bus Booked Confirmation");
//		        mail.setFile_name("qr_code_template.txt");
		 
		        
		        //Take current IP
//		        InetAddress ip = null;
//				try {
//					ip = InetAddress.getLocalHost();
//				} catch (UnknownHostException e) {
//					e.printStackTrace();
//				}
		        
				
//		        Map < String, Object > model = new HashMap < String, Object > ();
//		        model.put("name", user.get(0).getUsername());
//		        model.put("booking_code", bm.getCode());
//		        model.put("email", user.get(0).getEmail());
//		        model.put("source", source.get(0).getName());
//		        model.put("pick_up", pickUp.get(0).getName());
//		        model.put("destination", destination.get(0).getName());
//		        model.put("drop_off", drop_off.get(0).getName());
//		       // model.put("ip_address", ip.getHostAddress());
//		        model.put("dept_date", ci.convertDateTimetoDate(bm.getDept_date()));
//		        model.put("dept_time", bm.getDept_time());
//		        model.put("amount", bm.getNumber_booking());
//		        model.put("total_cost", bm.getTotal_cost());
//		        model.put("child", bm.getChild());
//		        model.put("adult", bm.getAdult());
//		        model.put("qr_name", bm.getQr_name());
//		        mail.setModel(model);
//		 
//		        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//		        MailService mailService = (MailService) context.getBean("mailService");
//		        mailService.sendEmail(mail);
//		        context.close();
		        
		        String hql ="Update Booking_Master set email_confirm='true' where id=:id";
		        Query query =  session.createQuery(hql);
		        query.setParameter("id", bm.getId());
		        int ret=query.executeUpdate();
        	}
        	trns.commit();
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }  finally {
            session.flush();
            session.close();
     }
    }
    public String Key(int mount){
   		 SecureRandom random = new SecureRandom();
   		    String key;
   		  
   		    key=  new BigInteger(mount*5, random).toString(32);
   		   
   		return key;
   	}

}
