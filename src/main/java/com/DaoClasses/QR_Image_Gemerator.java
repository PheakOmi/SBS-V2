package com.DaoClasses;

import java.awt.image.BufferedImage;  
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;
import java.io.File;  
import java.io.IOException;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;  

import javax.imageio.ImageIO;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.apache.commons.codec.binary.Base64;  
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.EntityClasses.Booking_Master;
import com.EntityClasses.Cost;
import com.EntityClasses.Location_Master;
import com.EntityClasses.Pickup_Location_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.client_mail.EmbeddedImageEmailUtil;
public class QR_Image_Gemerator {  
     /**  
      * @param args  
      */  
	
	 public static String QR_Dir="./QRCode/";
     public static void main(String[] args) throws IOException {  
          // TODO Auto-generated method stub  
          Scanner s=new Scanner(System.in);  
          System.out.println("Enter base64 string to be converted to image"); 
          String base64="iVBORw0KGgoAAAANSUhEUgAAAH0AAAB9AQAAAACn+1GIAAAApElEQVR42u3VMQ7DMAwDQP6A//+lxm4qxbZoPZpOtwQGnNxg2JSMoNfngRsugALQgKcYPN5TCoVqlqcTqOIFUGcwQ9/raTdB6XrN39Q3wc9saqn+HhQbZM2cg96ISYk5tMNRqRCDDkZH9Ak5AfeKztfIYTp/9oaOwcXWkqwDeOWszTGH6Xr4CpzB2jAZqNDf6gdgU/P+XuRdmJCbBDuG+x/1b3gCOSaFcLrWXbIAAAAASUVORK5CYII";
          //String base64=s.nextLine();  
          byte[] base64Val=convertToImg(base64);  
          System.out.println(base64Val);
          writeByteToImageFile(base64Val, "D:/Directory1/6.png");  
          System.out.println("Saved the base64 as image in current directory with name image.png");  
     }  
     public static void sendEmailQRCode(Session session, Booking_Master bm){
 		Custom_Imp ci=new Custom_Imp();
         List<User_Info> user = new ArrayList<User_Info>();
         List<Location_Master> source = new ArrayList<Location_Master>();
         List<Location_Master> destination = new ArrayList<Location_Master>();
         Map<String, Object> map=new HashMap<String, Object>();
 		try {
             user = session.createQuery("from User_Info where id=:id")
             		.setParameter("id", bm.getUser_id()).list();
         	source = session.createQuery("from Location_Master where id=:id")
         			.setParameter("id", bm.getSource_id()).list();
         	destination = session.createQuery("from Location_Master where id=:id")
         			.setParameter("id", bm.getDestination_id()).list();
         	if(user.size()>0&&source.size()>0&&destination.size()>0){
        		
        		QR_Image_Gemerator qr =new QR_Image_Gemerator();
        		qr.qr_generator(bm);
        		// SMTP info
                String host = "smtp.gmail.com";
                String port = "587";
                String mailFrom = "shuttlebus@kit.edu.kh";
                String password = "shuttlebus";
         
                // message info
                String mailTo = user.get(0).getEmail();
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+mailTo);
                String subject = "vKirirom Shuttle Bus Booking Confirmation";
                StringBuffer body
                    = new StringBuffer("<html>"
                    		+ "<div style=\"padding:3%\">"
                    		+ "<img style=\"width:120px;height:100px\" "
                    		+ "src=\"http://vkirirom.com/images/About_Company/vKirirom.png\">");
                //Open <td>
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">Dear, <b>"
                			+user.get(0).getUsername()+"</b></p>");
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">"
                		+ "Thank you for using our vKirirom Shuttle Bus. Please enjoy your trip.</p>");
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
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">To</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+destination.get(0).getName()+"</td>"
                		+"</tr>");
                body.append("<tr style=\"background-color: #dddddd;\">"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Departure Date</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+ci.convertDateTimetoDate(bm.getDept_date())+"</td>"
                		+"</tr>");
                body.append("<tr>"
                		+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Departure Time</td>"
					    + "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+bm.getDept_time()+"</td>"
                		+"</tr>");
                body.append("</table>");
                body.append("<br>");
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">Below is your QR-code for this trip:</p>");
                //End Table
                body.append("<img src=\"cid:image1\" width=\"300px\" height=\"300px\" /><br>");
                body.append("<p style=\"font-family: arial, sans-serif; color:black\">Thank you</p>"
                		+ "<p style=\"font-family: arial, sans-serif; color:black\">A2A Shuttle Bus Team</p>");
                
                body.append("</div>");
                body.append("</html>");

                // in-line images
                Map<String, String> inlineImages = new HashMap<String, String>();
                inlineImages.put("image1", QR_Dir+bm.getQr_name()+".png");
         
                try {
                    EmbeddedImageEmailUtil.send(host, port, mailFrom, password, mailTo,
                        subject, body.toString(), inlineImages);
                    System.out.println("Email sent.");
                } catch (Exception ex) {
                    System.out.println("Could not send email.");
                    ex.printStackTrace();
                }
 		        
 		        String hql ="Update Booking_Master set email_confirm='true' where id=:id";
 		        Query query =  session.createQuery(hql);
 		        query.setParameter("id", bm.getId());
 		        int ret=query.executeUpdate();
         	}
         } catch (RuntimeException e) {
         	e.printStackTrace();
         }  
 	}


	public static void sendEmailQRCode2(Session session, Booking_Master bm, Booking_Master rbooking){
		Custom_Imp ci=new Custom_Imp();
		List<User_Info> user = new ArrayList<User_Info>();
		List<Location_Master> source = new ArrayList<Location_Master>();
		List<Location_Master> destination = new ArrayList<Location_Master>();
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			user = session.createQuery("from User_Info where id=:id")
					.setParameter("id", bm.getUser_id()).list();
			source = session.createQuery("from Location_Master where id=:id")
					.setParameter("id", bm.getSource_id()).list();
			destination = session.createQuery("from Location_Master where id=:id")
					.setParameter("id", bm.getDestination_id()).list();
			if(user.size()>0&&source.size()>0&&destination.size()>0){

				QR_Image_Gemerator qr =new QR_Image_Gemerator();
				qr.qr_generator(bm);
				qr.qr_generator(rbooking);
				// SMTP info
				String host = "smtp.gmail.com";
				String port = "587";
				String mailFrom = "shuttlebus@kit.edu.kh";
				String password = "shuttlebus";

				// message info
				String mailTo = user.get(0).getEmail();
				String subject = "vKirirom Shuttle Bus Booking Confirmation";
				StringBuffer body
						= new StringBuffer("<html>"
						+ "<div style=\"padding:3%\">"
						+ "<img style=\"width:120px;height:100px\" "
						+ "src=\"http://vkirirom.com/images/About_Company/vKirirom.png\">");
				//Open <td>
				body.append("<p style=\"font-family: arial, sans-serif; color:black\">Dear, <b>"
						+user.get(0).getUsername()+"</b></p>");
				body.append("<p style=\"font-family: arial, sans-serif; color:black\">"
						+ "Thank you for using our vKirirom Shuttle Bus. Please enjoy your trip.</p>");
				body.append("<p style=\"font-family: arial, sans-serif; color:black\">Here is your trip detail: </p>");
				//Table
				body.append("<table style=\"font-family: arial, sans-serif; border-collapse: collapse;width: 100%; color:black\">");
				body.append("<tr>"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Booking Code</td>"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+bm.getCode()+", "+rbooking.getCode()+"</td>"
						+"</tr>");
				body.append("<tr style=\"background-color: #dddddd;\">"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">From</td>"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+source.get(0).getName()+"</td>"
						+"</tr>");
				body.append("<tr>"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">To</td>"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+destination.get(0).getName()+"</td>"
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
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Return Date</td>"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+ci.convertDateTimetoDate(rbooking.getDept_date())+"</td>"
						+"</tr>");
				body.append("<tr>"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Return Time</td>"
						+ "<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">"+rbooking.getDept_time()+"</td>"
						+"</tr>");
				body.append("</table>");
				body.append("<br>");
				body.append("<p style=\"font-family: arial, sans-serif; color:black\">Below is your QR-code for your departure:</p>");
				//End Table
				body.append("<img src=\"cid:image1\" width=\"300px\" height=\"300px\" /><br>");

				body.append("<br>");
				body.append("<p style=\"font-family: arial, sans-serif; color:black\">Below is your QR-code for return trip:</p>");
				//End Table
				body.append("<img src=\"cid:image2\" width=\"300px\" height=\"300px\" /><br>");


				body.append("<p style=\"font-family: arial, sans-serif; color:black\">Thank you</p>"
						+ "<p style=\"font-family: arial, sans-serif; color:black\">A2A Shuttle Bus Team</p>");

				body.append("</div>");
				body.append("</html>");

				// in-line images
				Map<String, String> inlineImages = new HashMap<String, String>();
				inlineImages.put("image1", QR_Dir+bm.getQr_name()+".png");
				inlineImages.put("image2", QR_Dir+rbooking.getQr_name()+".png");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^ "+bm.getQr_name());
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^ "+bm.getQr_name());

				try {
					EmbeddedImageEmailUtil.send(host, port, mailFrom, password, mailTo,
							subject, body.toString(), inlineImages);
					System.out.println("Email sent.");
				} catch (Exception ex) {
					System.out.println("Could not send email.");
					ex.printStackTrace();
				}

				String hql ="Update Booking_Master set email_confirm='true' where id=:id";
				Query query =  session.createQuery(hql);
				query.setParameter("id", bm.getId());
				int ret=query.executeUpdate();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}


 	
     public Cost Cost_Master(){
 	    Map<String,Object> status = new HashMap<String, Object>();
 	    Transaction trns1 = null;
 	    Session session = HibernateUtil.getSessionFactory().openSession();
 	    Cost cost = new Cost();
 	    try {
 	
 	        Query query = session.createQuery("From Cost");
 	
 	        cost = (Cost) query.uniqueResult();
 	
 	        return cost;
 	    } catch (RuntimeException e) {
 	        e.printStackTrace();
 	        return cost;
 	
 	    }finally {
 	        session.flush();
 	        session.close();
 	    }
 	}
     
     
     public Boolean qr_generator(Booking_Master bm) {
    	System.out.println(bm.getQr());
    	//QR---> source_id+destin_id+dept_date+dept_time+booing_master_id
    	String qr_code=	bm.getSource_id()
    				+bm.getDestination_id()
    				+dateToString(bm.getDept_date())
    				+timeToString(bm.getDept_time())
    				+bm.getUser_id();
        ByteArrayOutputStream out = QRCode.from(qr_code).to(ImageType.PNG).stream();  
  		byte[] test = out.toByteArray();
  		String encodedImage = java.util.Base64.getEncoder().encodeToString(test);
         
        byte[] base64Val;
		try {
			//Create New Folder
			new File(QR_Dir).mkdir();
			
			base64Val = convertToImg(encodedImage);
			writeByteToImageFile(base64Val, QR_Dir+bm.getQr_name()+".png");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		return true;  
     } 
     public static byte[] convertToImg(String base64) throws IOException  
     {  
          return Base64.decodeBase64(base64);  
     }  
     public static void writeByteToImageFile(byte[] imgBytes, String imgFileName) throws IOException  
     {  
    	 System.out.println(imgBytes);
          File imgFile = new File(imgFileName);  
          BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBytes));  
          ImageIO.write(img, "png", imgFile);  
     }  
     
     
     public static String dateToString(Date date){
 		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
 		return f.format(date);
 	}

 	public static String timeToString(Date date){
 		SimpleDateFormat f=new SimpleDateFormat("HH:mm:ss");
 		return f.format(date);
 	}
}
