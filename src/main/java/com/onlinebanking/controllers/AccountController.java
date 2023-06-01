package com.onlinebanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.onlinebanking.helpers.GenerateAccountNumber;
import com.onlinebanking.models.User;
import com.onlinebanking.repositories.AccountRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {
	
  @Autowired
  AccountRepository accountRepository;
	
  @PostMapping("/create_account")
  public String createAccount(@RequestParam("accountName") String accountName,
                              @RequestParam("accountType") String accountType,
                              RedirectAttributes redirectAttributes, HttpSession session){
    if(accountName.isEmpty() || accountType.isEmpty()){
      redirectAttributes.addFlashAttribute("error",
                       "Account Name and Type Cannot be Empty!!!");
      return "redirect:/app/dashboard";
    }
    
    User user=(User)session.getAttribute("user");
    
    int accountNo=GenerateAccountNumber.generateAccountumber();
    String accountNumber=Integer.toString(accountNo);
    
    accountRepository.createAccount(user.getUserId(),accountNumber,accountName,accountType);
    
    redirectAttributes.addFlashAttribute("success",
                                              "Account Created Successfully!!!");
    return "redirect:/app/dashboard";
  }
}
