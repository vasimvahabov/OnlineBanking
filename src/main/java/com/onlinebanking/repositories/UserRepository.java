package com.onlinebanking.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.onlinebanking.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
  
  @Query(value="select _email from _users where _email=:EMAIL",nativeQuery=true)
  String getUserEmail(@Param("EMAIL") String email);
	
  @Query(value="select _password from _users where _email=:EMAIL",nativeQuery=true)
  String getUserPassword(@Param("EMAIL") String email);
	
  @Query(value="select _verified from _users where _email=:EMAIL",nativeQuery=true)
  int isVerified(@Param("EMAIL") String email);
 	
  @Query(value="select * from _users where _email=:EMAIL",nativeQuery = true)
  User getUserDetails(@Param("EMAIL") String email);
	
  @Transactional
  @Modifying
  @Query(value = "insert into _users(_first_name,_last_name,_email,_password,_token,_code)"
        +"values(:FIRST_NAME, :LAST_NAME, :EMAIL, :PASSWORD, :TOKEN, :CODE)",nativeQuery =true)
  void registerUser(@Param("FIRST_NAME") String firstName,@Param("LAST_NAME") String lastName,
                  @Param("EMAIL") String email,@Param("PASSWORD") String password,
                  @Param("TOKEN") String token,@Param("CODE") int code);
	
  @Transactional
  @Modifying
  @Query(value = "update _users set _token=null,_code=null,_verified=1,_verified_at=now(),"
            +"_updated_at=now() where _token=:TOKEN and _code=:CODE",nativeQuery = true)
  void verifyAccount(@Param("TOKEN") String token,@Param("CODE") String code);
  
  @Query(value="select _token from _users where _token=:TOKEN",nativeQuery=true)
  String checkToken(@Param("TOKEN") String token);	
}
