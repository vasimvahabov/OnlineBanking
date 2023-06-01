package com.onlinebanking.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="_payments")
public class Payment {
  @Id
  @Column(name ="_payment_id")
  private int paymentId;
  
  @Column(name="_account_id")
  private int accountId;
  
  @Column(name="_beneficiary")
  private String beneficiary;
  
  @Column(name="_beneficiary_acc_no")
  private String beneficiaryAccNo;
  
  @Column(name="_amount")
  private double amount;
  
  @Column(name="_reference_no")
  private String referenceNo;
  
  @Column(name="_status")
  private String status;
 
  @Column(name="_reason_code")
  private String reasonCode;
  
  @Column(name="_created_at")
  private LocalDateTime createdAt;

  public int getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(int paymentId) {
    this.paymentId = paymentId;	
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getBeneficiary() {
    return beneficiary;
  }

  public void setBeneficiary(String beneficiary) {
    this.beneficiary = beneficiary;
  }

  public String getBeneficiaryAccNo() {
    return beneficiaryAccNo;
  }

  public void setBeneficiaryAccNo(String beneficiaryAccNo) {
    this.beneficiaryAccNo = beneficiaryAccNo;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getReferenceNo() {
    return referenceNo;
  }

  public void setReferenceNo(String referenceNo) {
    this.referenceNo = referenceNo;
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
