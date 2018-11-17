package com.DaoClasses;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.ModelClasses.Mail;

public class Mailer {
 private MailSender mailSender;

 public void setMailSender(MailSender mailSender) {
  this.mailSender = mailSender;
 }
 
 public void sendMail(Mail mail) {
  SimpleMailMessage message = new SimpleMailMessage();
  message.setFrom(mail.getMailFrom());
  message.setTo(mail.getMailTo());
  message.setSubject(mail.getMailSubject());
  message.setText(mail.getMailContent());

  mailSender.send(message);
 }

}