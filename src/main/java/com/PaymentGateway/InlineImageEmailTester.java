package com.PaymentGateway;

import java.util.HashMap;
import java.util.Map;

public class InlineImageEmailTester {
	/**
     * main entry of the program
     */
    public static void main(String[] args) {
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "nanaresearch9@gmail.com";
        String password = "hanako12624120";
 
        // message info
        String mailTo = "maimom61@gmail.com";
        String subject = "Test e-mail with inline images";
        StringBuffer body
            = new StringBuffer("<html>This message contains two inline images.<br>");
        body.append("The first image is a chart:<br>");
        body.append("<img src=\"cid:image1\" width=\"100%\" height=\"30%\" /><br>");
        body.append("The second one is a cube:<br>");
        body.append("<img src=\"cid:image2\" width=\"15%\" height=\"15%\" /><br>");
        body.append("End of message.");
        body.append("</html>");
 
        // inline images
        Map<String, String> inlineImages = new HashMap<String, String>();
        inlineImages.put("image1", "D:/Directory1/6.png");
        inlineImages.put("image2", "D:/Directory1/6.png");
 
        try {
            EmbeddedImageEmailUtil.send(host, port, mailFrom, password, mailTo,
                subject, body.toString(), inlineImages);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
}
