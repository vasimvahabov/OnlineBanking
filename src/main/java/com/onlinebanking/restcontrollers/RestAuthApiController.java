//package com.onlinebanking.restcontrollers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.onlinebanking.repositories.UserRepository;
//
//@RestController
//@RequestMapping("/api/auth")
//public class RestAuthApiController {
//	
//  @Autowired
//  UserRepository userRepository;
//  
//  @GetMapping("/login")
//  public ResponseEntity validateUser(@PathVariable("email") String email){
//    String userEmail=userRepository.getUserEmail(email);
//    String userPass=null;
//    
//    if(userEmail!=null){
//      userPass=userRepository.getUserPassword(email);
//      return new ResponseEntity<>(userPass,HttpStatus.OK);
//    }
//    return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
//    
//  }
//}
