package com.onlinebanking.controllers;

import java.time.LocalDateTime; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 
import com.onlinebanking.models.User;
import com.onlinebanking.repositories.AccountRepository;
import com.onlinebanking.repositories.PaymentRepository;
import com.onlinebanking.repositories.TransactionRepository; 
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/transact")
public class TransactController {
  
  @Autowired
  AccountRepository accountRepository;
  
  @Autowired
  TransactionRepository transactionRepository;
  
  @Autowired
  PaymentRepository paymentRepository;
  
  @PostMapping("/deposit")
  public String deposit(@RequestParam("depositAmount") String depositAmount,
                       @RequestParam("accountId") String accountId,
                       RedirectAttributes redirectAttributes,HttpSession session){
    if(depositAmount.isEmpty() || accountId.isEmpty()){
      redirectAttributes.addFlashAttribute("error",
                        "Deposit Amount or Account Depositing to Cannot Be Empty!!!");
      return "redirect:/app/dashboard";
    }
    
    User user=(User)session.getAttribute("user");
    int accountID=Integer.parseInt(accountId);
    double depositAmountValue=Double.parseDouble(depositAmount);
    
    if(depositAmountValue==0){
      redirectAttributes.addFlashAttribute("error",
                                "Deposit Amount Cannot Be of 0 (Zero) Value!!!");
      return "redirect:/app/dashboard";
    }
    
    double currentBalance=accountRepository.getAccountBalance(accountID,user.getUserId());
    double newBalance=currentBalance+depositAmountValue;
    LocalDateTime currentDateTime=LocalDateTime.now();
    
    accountRepository.updateAccountBalance(newBalance,accountID);
    transactionRepository.logTransaction(accountID,"deposit",depositAmountValue,"online",
                                        "success","Deposit Transaction Successful!!!",currentDateTime);
    
    redirectAttributes.addFlashAttribute("success",
                                                    "Amount Deposited Successfully!!!");
    return "redirect:/app/dashboard";
  }
  
  @PostMapping("/transfer")
  public String transfer(@RequestParam("transferFrom") String transferFrom,
		   @RequestParam("transferTo") String transferTo,
                       @RequestParam("transferAmount") String transferAmount,
                       RedirectAttributes redirectAttributes,HttpSession session){
    if(transferFrom.isEmpty() || transferTo.isEmpty() || transferAmount.isEmpty()){
      String errorMsg="The account transferring from and to"
                                   + " along with the amount cannot be empty!!!";
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }
    
    int transferFromAccount=Integer.parseInt(transferFrom);
    int transferToAccount=Integer.parseInt(transferTo);
    double transferAmountValue=Double.parseDouble(transferAmount);
    
    if(transferFromAccount==transferToAccount){
      String errorMsg="Cannot Transfer Into The same Account, Please select the "
                                                 + "appropriate account to perform transfer!!!";
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }
    
    if(transferAmountValue==0){
      String errorMsg="Cannot Transfer an amount of 0 (Zero) "
                                + "value, please enter a value greater than 0 (Zero)!!!";
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }

    User user=(User)session.getAttribute("user");
    
    double currentBalanceTransferringFromAccount=accountRepository
                                      .getAccountBalance(transferFromAccount,user.getUserId());
    LocalDateTime currentDateTime=LocalDateTime.now();
    
    if(currentBalanceTransferringFromAccount<transferAmountValue){
      String errorMsg="You Have insufficient Funds to perform this Transfer!!!";
      transactionRepository.logTransaction(transferFromAccount,"transfer",transferFromAccount,
                                             "online","failed","Insufficient Funds!!!",currentDateTime);
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }
    
    double currentBalanceTransferringToAccount=accountRepository
                                             .getAccountBalance(transferToAccount,user.getUserId());
    double newBalanceTransferringFromAccount=
                                     currentBalanceTransferringFromAccount-transferAmountValue;
    double newBalanceTransferringToAccount=currentBalanceTransferringToAccount+transferAmountValue;
    
    accountRepository.updateAccountBalance(newBalanceTransferringFromAccount,transferFromAccount);
    accountRepository.updateAccountBalance(newBalanceTransferringToAccount,transferToAccount);
    
    transactionRepository.logTransaction(transferFromAccount,"transfer",transferAmountValue,
      "online","success","Transfer Transaction Successful!!!",currentDateTime);
    
    redirectAttributes.addFlashAttribute("success",
                                  "Amount Transferred Successfully!!!");
    return "redirect:/app/dashboard";    
  }
  
  @PostMapping("/withdraw")
  public String withdraw(@RequestParam("accountId") String accountId,
                        @RequestParam("withdrawAmount") String withdrawAmount,
                        RedirectAttributes redirectAttributes,HttpSession session){
	  
    if(accountId.isEmpty() || withdrawAmount.isEmpty()){
      String errorMsg="Withdrawal Amount and Account Withdrawing From Cannot be Empty!!!";
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }
    
    double withdrawAmountValue=Double.parseDouble(withdrawAmount);
    
    if(withdrawAmountValue==0){
      String errorMsg="Withdrawal Amount Cannot be of 0 (Zero) "
                                        + "value, please enter a value greater than 0 (Zero)!!!";
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";  
    }
    
    int accountID=Integer.parseInt(accountId);
    User user=(User)session.getAttribute("user");
    
    LocalDateTime currentDateTime=LocalDateTime.now();
    double currentBalance=accountRepository.getAccountBalance(accountID,user.getUserId());
    
    if(currentBalance<withdrawAmountValue){
      String errorMsg="You Have insufficient Funds to perform this Withdrawal!!!";
      transactionRepository.logTransaction(accountID,"withdrawal",withdrawAmountValue,
        "online","failed","Insufficient Funds!!!",currentDateTime);
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }
        
    
    double newBalance=currentBalance-withdrawAmountValue;
    accountRepository.updateAccountBalance(newBalance,accountID);
    
    transactionRepository.logTransaction(accountID,"withdrawal",withdrawAmountValue,
      "online","success","Withdrawal Transaction Successful", currentDateTime);
    
    redirectAttributes.addFlashAttribute("success","Withdrawal Successful!!!");
    return "redirect:/app/dashboard";
  }
  
  @PostMapping("/payment")
  public String payment(@RequestParam("beneficiary") String beneficiary,
		    @RequestParam("accountNumber") String accountNumber,
                        @RequestParam("accountId") String accountId,
                        @RequestParam("reference") String reference,
                        @RequestParam("paymentAmount") String paymentAmount,
                        RedirectAttributes redirectAttributes,HttpSession session){
    if(beneficiary.isEmpty() || accountNumber.isEmpty() || 
    		accountId.isEmpty() || reference.isEmpty() || paymentAmount.isEmpty()){
      String errorMsg="Beneficiary, Account Number, Account Paying "
                                  + "From, Reference and Payment Amount Cannot be Empty!!!";
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }
    
    double paymentAmountValue=Double.parseDouble(paymentAmount);
    
    if(paymentAmountValue==0){
      String errorMsg="Payment Amount Cannot be of 0 (Zero) "
                                     + "value, please enter a value greater than 0 (Zero)!!!";
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }
    
    User user=(User)session.getAttribute("user");
    int accountID=Integer.parseInt(accountId);
    
    LocalDateTime currentDateTime=LocalDateTime.now();
    double currentBalance=accountRepository.getAccountBalance(accountID,user.getUserId());
    
    if(currentBalance<paymentAmountValue){ 
      paymentRepository.makePayment(accountID,beneficiary,
              accountNumber,paymentAmountValue,reference,"failed",
                     "Could not Processed Payment due to insufficient funds!!!",currentDateTime);
      
      transactionRepository.logTransaction(accountID,"payment",paymentAmountValue,
        "online","failed","Insufficient Funds!!!", currentDateTime);
      
      String errorMsg="You Have insufficient Funds to perform this payment!!!";
      redirectAttributes.addFlashAttribute("error",errorMsg);
      return "redirect:/app/dashboard";
    }
    
    paymentRepository.makePayment(accountID,beneficiary,accountNumber,paymentAmountValue,
      reference,"success","Payment Processed Successfully!!!",currentDateTime);
    
    double newBalance=currentBalance-paymentAmountValue;
    accountRepository.updateAccountBalance(newBalance, accountID);
    
    transactionRepository.logTransaction(accountID,"payment",paymentAmountValue,
                     "online","success","Payment Transaction Successful!!!",currentDateTime);
    
    redirectAttributes.addFlashAttribute("success",
                                        "Payment Processed Successfully!!!");
    return "redirect:/app/dashboard";
  }
}





