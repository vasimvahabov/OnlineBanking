package com.onlinebanking.helpers;

import java.util.Random;

public class GenerateAccountNumber {
  public static int generateAccountumber(){
    Random rand=new Random();
    int bound=1000;
    int accountNumber=bound*rand.nextInt(bound);
    return accountNumber;
  }
}
