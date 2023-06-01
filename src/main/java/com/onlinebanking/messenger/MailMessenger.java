package com.onlinebanking.messenger;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.onlinebanking.config.MailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class MailMessenger {
  public static void htmlEmailMessenger(String from,String to,
                            String subject,String body) throws MessagingException{
    JavaMailSender javaMailSender=MailConfig.getMailConfig();
    MimeMessage mimeMessage=javaMailSender.createMimeMessage();
    MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true);
    
    messageHelper.setFrom(from);
    messageHelper.setTo("");
    messageHelper.setSubject(subject);
    messageHelper.setText(body,true);
  	
    javaMailSender.send(mimeMessage);
  }
}
