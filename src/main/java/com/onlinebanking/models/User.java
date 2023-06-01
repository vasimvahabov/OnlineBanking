package com.onlinebanking.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email; 
import jakarta.validation.constraints.NotEmpty; 
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="_users")
public class User {
  
  @Id
  @Column(name ="_user_id")
  private int userId;
	
  @Column(name="_first_name")
  @NotEmpty(message="The First Name field can't be empty!!!")
  @Size(min=3,message="The First name field must greater that 3 characters!!!")
  private String firstName;
	
  @Column(name="_last_name")
  @NotEmpty(message="The Last name field can't be empty!!!")
  @Size(min=3,message = "The Last name field must greater that 3 characters!!!")
  private String lastName;
	
  @Email
  @Column(name="_email")
  @NotEmpty(message ="The Email field can't be empty!!!")
  @Pattern(regexp ="([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+"
                                                        +"(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})",
  message = "Please Enter a Valid Email!!!")
  private String email;

  @Column(name="_password")
  @NotEmpty(message ="The Password field can't be empty!!!") 
  private String password;
	
  @Column(name="_token")
  private String token;
  
  @Column(name="_code")
  private Integer code;
  
  @Column(name="_verified")
  private int verified;
  
  @Column(name="_verified_at")
  private LocalDate verifiedAt;
  
  @Column(name="_created_at")
  private LocalDateTime createdAt;
  
  @Column(name="_updated_at")
  private LocalDateTime updatedAt;
	
  public int getUserId() {
    return userId;
  }
	
  public void setUserId(int userId) {
    this.userId = userId;
  }
	
  public String getFirstName() {
    return firstName;
  }
	
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
	
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
	
  public String getEmail() {
    return email;
  }
	
  public void setEmail(String email) {
    this.email = email;
  }
	
  public String getPassword() {
    return password;
  }
	
  public void setPassword(String password) {
    this.password = password;
  }
	
  public String getToken() {
    return token;
  }
	
  public void setToken(String token) {
    this.token = token;
  }
	
  public int getCode() {
    return code;
  }
	
  public void setCode(int code) {
    this.code = code;
  }
	
  public int getVerified() {
    return verified;
  }
	
  public void setVerified(int verified) {
    this.verified = verified;
  }
	
  public LocalDate getVerifiedAt() {
    return verifiedAt;
  }
	
  public void setVerifiedAt(LocalDate verifiedAt) {
    this.verifiedAt = verifiedAt;
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
