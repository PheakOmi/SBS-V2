package com.client_mail;


import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
 

 

import com.ModelClasses.Mail;

import freemarker.template.Configuration;
 
@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
 
    @Autowired
    Configuration fmConfiguration;
 
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            System.out.println("sendEmail");
            System.out.println("****************** "+mail.getMailSubject()+" "+mail.getMailFrom()+" "+mail.getMailTo());
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mail.setMailContent(geContentFromTemplate(mail.getFile_name(),mail.getModel()));
            mimeMessageHelper.setText(mail.getMailContent(), true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
 
    public String geContentFromTemplate(String file_name,Map < String, Object > model) {
        StringBuffer content = new StringBuffer();
        System.out.println("geContentFromTemplate");
        try {
            content.append(FreeMarkerTemplateUtils
                .processTemplateIntoString(fmConfiguration.getTemplate(file_name), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
 
}