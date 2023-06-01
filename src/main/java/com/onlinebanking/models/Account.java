package com.onlinebanking.models;

import java.math.BigDecimal; 
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="_accounts")
public class Account {

  @Id
  @Column(name="_account_id")
  private int accountId;
  
  @Column(name="_user_id")
  private int userId;
  
  @Column(name="_account_number")
  private String accountNumber;
  
  @Column(name="_account_name")
  private String accountName;
  
  @Column(name="_account_type")
  private String accountType;
  
  @Column(name="_balance")
  private BigDecimal balance;
  
  @Column(name="_created_at")
  private LocalDateTime createdAt;
  
  @Column(name="_updated_at")
  private LocalDateTime updatedAt;
  
  public int getAccountId() {
    return accountId;
  }
  
  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }
  
  public int getUserId() {
    return userId;
  }
  
  public void setUserId(int userId) {
    this.userId = userId;
  }
  
  public String getAccountNumber() {
    return accountNumber;
  }
  
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }
  
  public String getAccountName() {
    return accountName;
  }
  
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getAccountType() {
    return accountType;
  }
  
  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }
  
  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  } 
}
