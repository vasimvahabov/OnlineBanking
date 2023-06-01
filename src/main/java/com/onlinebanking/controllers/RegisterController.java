package com.onlinebanking.controllers;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.onlinebanking.helpers.HTML;
import com.onlinebanking.helpers.Token;
import com.onlinebanking.messenger.MailMessenger;
import com.onlinebanking.models.User;
import com.onlinebanking.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Controller
public class RegisterController {
	
  @Autowired
  UserRepository userRepository;
   
  @GetMapping("/register")
  public ModelAndView getRegister(){
    System.out.println("In Register Page Controller");
    ModelAndView registerView=new ModelAndView("register");
    registerView.addObject("PageTitle","Register");
    return registerView;
  }
	
  @PostMapping("/register")
  public ModelAndView register(@Valid @ModelAttribute("registerUser") User user,
              BindingResult result,@RequestParam("firstName") String firstName,
              @RequestParam("lastName") String lastName,
              @RequestParam("email") String email,
              @RequestParam("password") String password,
              @RequestParam("confirmPassword") String confirmPassword) throws MessagingException{
    ModelAndView registrationView=new ModelAndView("register");
		
    if(result.hasErrors() || confirmPassword.isEmpty()){
      if(confirmPassword.isEmpty()){
        registrationView.addObject("confirm_password",
                                           "The Confirm Password required!!!"); 
      }
      return registrationView;
    }
	  
    if(!password.equals(confirmPassword)){
      registrationView.addObject("passwordsMisMatch",
                                                    "Passwords don't match!!!");
      return registrationView;
    } 
	  
    Random rand=new Random();
    int bound=123;
    int code=bound*rand.nextInt(bound);
	  
    String token=Token.generateToken();
    String hashedPassword=BCrypt.hashpw(password,BCrypt.gensalt());
  	  
    userRepository.registerUser(firstName,lastName,email,hashedPassword,token,code);
	  
    String emailBody=HTML.htmlEmailTemplate(token,code);
    MailMessenger.htmlEmailMessenger("","","Verify Account",emailBody);
	  
    String successMessage="Account registered successfully. "
                                        + "Please check your email and verify account!";
    registrationView.addObject("success",successMessage);
	  
    return registrationView;
  }	
}
