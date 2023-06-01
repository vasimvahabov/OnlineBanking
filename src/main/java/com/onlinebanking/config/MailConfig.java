package com.onlinebanking.config;

import java.util.Properties; 
//import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {
  
  @Bean
  public static JavaMailSenderImpl getMailConfig() {
    JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
    Properties properties=javaMailSender.getJavaMailProperties();
    
    properties.put("mail.transport.protocol","smtp");
    properties.put("mail.smtp.auth","true");
    properties.put("mail.smtp.starttls.enable","true");
    properties.put("mail.smtp.ssl.enable","true");
    
    javaMailSender.setHost("smtp.gmail.com");	  
    javaMailSender.setPort(465);
    javaMailSender.setUsername(");
    javaMailSender.setPassword("");

    return javaMailSender;
  }	
}
