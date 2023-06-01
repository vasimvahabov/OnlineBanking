package com.onlinebanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.onlinebanking.helpers.Token;
import com.onlinebanking.models.User;
import com.onlinebanking.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController{
	
  @Autowired
  private UserRepository userRepository;
  
  @GetMapping("/login")
  public ModelAndView getLogin(){
    System.out.println("In Login Page/Controller");
    String token=Token.generateToken();
    ModelAndView loginView=new ModelAndView("login");
    loginView.addObject("PageTitle","Login");
    loginView.addObject("token",token);
    return loginView;
  }
	
  @PostMapping("/login")
  public String login(@RequestParam("email") String email,@RequestParam("password") String password,
                              @RequestParam("token") String token,Model model,HttpSession session){
    if(email.isEmpty() || email==null || password.isEmpty() || password==null){
      model.addAttribute("error","Username or Password cannot be empty!!!");
      return "login";
    }
	
    String dbEmail=userRepository.getUserEmail(email);
    if(dbEmail!=null){
      String dbPassword=userRepository.getUserPassword(email);
      if(!BCrypt.checkpw(password,dbPassword)){
        model.addAttribute("error","Incorret Username or Password!!!");
        return "login";
      }
      else
        System.out.println("Logged In Succesfully!!!");
    }else{
      model.addAttribute("error","Incorret UserName or Password!!!");
      return "login";
    }
    
    int verified=userRepository.isVerified(email);
    if(verified!=1){
      String msg="Account is not yet verified.Please check email and verfy account!!!";
      model.addAttribute("error",msg);
      return "login"; 
    }
		
    User user=userRepository.getUserDetails(email);
    session.setAttribute("user",user);
    session.setAttribute("token",token);
    session.setAttribute("authenticated",true);

    return "redirect:/app/dashboard";
  }
	
  @GetMapping("/logout")
  public String logout(HttpSession session,RedirectAttributes redirectAttributes){
    session.invalidate();
    redirectAttributes.addFlashAttribute("logged_out",
                                                     "Logged Out Successfully!!!");
    return "redirect:/login";
  }	
}
