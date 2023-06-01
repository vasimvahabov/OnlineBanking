package com.onlinebanking.controllers;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView; 
import com.onlinebanking.models.Account;
import com.onlinebanking.models.PaymentHistory;
import com.onlinebanking.models.TransactionHistory;
import com.onlinebanking.models.User;
import com.onlinebanking.repositories.AccountRepository;
import com.onlinebanking.repositories.PaymentHistoryRepository;
import com.onlinebanking.repositories.TransactionHistoryRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AppController {
	
  @Autowired
  AccountRepository accountRepository;
	
  @Autowired
  PaymentHistoryRepository paymentHistoryRepository;

  @Autowired
  TransactionHistoryRepository transactionHistoryRepository;
  
  @GetMapping("/dashboard")
  public ModelAndView getDashboard(HttpSession session){
    System.out.println("In Dashboard Page/Controller");
    ModelAndView dashboardView=new ModelAndView("dashboard");
    
    User user=(User)session.getAttribute("user");
    List<Account> accounts=accountRepository.getAccountsByUserId(user.getUserId());
//    for(int i=0; i<accounts.size(); i++)
//       System.out.println(accounts.get(i).getAccountName());
  	
    BigDecimal totalBalance=accountRepository.getTotalBalance(user.getUserId());
//    System.out.println(totalBalance);
  	
    dashboardView.addObject("accounts",accounts);
    dashboardView.addObject("totalBalance",totalBalance);
  	
    return dashboardView;
  }
  
  @GetMapping("/payment-history")
  public ModelAndView getPaymentHistory(HttpSession session){
    ModelAndView paymentHistoryView=new ModelAndView("paymentHistory");
    
    User user=(User)session.getAttribute("user");
    List<PaymentHistory> paymentHistory=
                           paymentHistoryRepository.getPaymentHistoriesByUserId(user.getUserId());
    
    paymentHistoryView.addObject("payment_history",paymentHistory);
    return paymentHistoryView;
  }
  
  @GetMapping("/transaction-history")
  public ModelAndView getTransactionHistory(HttpSession session){
   ModelAndView transactionHistoryView=new ModelAndView("transactionHistory");
   
   User user=(User)session.getAttribute("user");
   List<TransactionHistory> transactionHistory=
                             transactionHistoryRepository.getTransactionsByUserId(user.getUserId());
   
   transactionHistoryView.addObject("transaction_history",transactionHistory);
   return transactionHistoryView;
  }
  
}
