package com.onlinebanking.controllers;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.onlinebanking.repositories.UserRepository;

@Controller
public class IndexController {
	
  @Autowired
  UserRepository userRepository;
  
  @GetMapping("/")
  public ModelAndView getIndex(){
    System.out.println("In Index Page Controller");
    ModelAndView indexView=new ModelAndView("index");
    indexView.addObject("PageTitle","Home");
    return indexView;
  }
	
  @GetMapping("/error")
  public ModelAndView getError() {
    System.out.println("In Error Page/Controller");
    ModelAndView errorView=new ModelAndView("error");
    errorView.addObject("PageTitle","Error");
    return errorView;
  }
	
  @GetMapping("/verify")
  public ModelAndView getVerify(@RequestParam("token") String token,
                                   @RequestParam("code") String code){
    System.out.println("In Verify Account Page/Controller");
    if(userRepository.checkToken(token)==null){
      ModelAndView errorView=new ModelAndView("error");
      errorView.addObject("PageTitle","Error");
      errorView.addObject("error","This Session Has Expired!!!");
      return errorView;
    }
    userRepository.verifyAccount(token,code);
    ModelAndView loginView=new ModelAndView("login");
    loginView.addObject("PageTitle","Verify Account");
    loginView.addObject("success",
                            "Account Verified Successfully, Please proceed to Log In!!!");
    return loginView;	   
  }	
}
