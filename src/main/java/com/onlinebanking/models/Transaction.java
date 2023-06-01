package com.onlinebanking.models;

import java.time.LocalDateTime; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="_transactions")
public class Transaction {
	
  @Id 
  @Column(name="_transaction_id")
  private int transactionId;
  
  @Column(name="_account_id")
  private int accountId;
  
  @Column(name="_transaction_type")
  private String transactionType;
  
  @Column(name="_amount")
  private double amount;
  
  @Column(name="_source")
  private String source;
  
  @Column(name="_status")
  private String status;
  
  @Column(name="_reason_code")
  private String reasonCode;
  
  @Column(name="_created_at")
  private LocalDateTime createdAt;

  public int getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(int transactionId) {
    this.transactionId = transactionId;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }	

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getReasonCode() {
    return reasonCode;
  }

  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }  
}
