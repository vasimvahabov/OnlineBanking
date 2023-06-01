package com.onlinebanking.advisorcontoller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.onlinebanking.models.User;

@ControllerAdvice
public class AdvisorController {
	
  @ModelAttribute("registerUser")
  public User getUserDefaults(){
  	return new User();
  }

}
