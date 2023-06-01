package com.onlinebanking.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.onlinebanking.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer>{
  
  @Query(value="select * from _accounts where _user_id=:USER_ID",nativeQuery=true)
  List<Account> getAccountsByUserId(@Param("USER_ID") int userId);
  
  @Query(value="select sum(_balance) from _accounts where _user_id=:USER_ID",nativeQuery=true)
  BigDecimal getTotalBalance(@Param("USER_ID") int userId);
  	
  @Query(value="select _balance from _accounts where "
                            + "_account_id=:ACCOUNT_ID and _user_id=:USER_ID",nativeQuery=true)
  double getAccountBalance(@Param("ACCOUNT_ID") int accountId,@Param("USER_ID") int userId);
	
  @Transactional
  @Modifying
  @Query(value="update _accounts set _balance=:NEW_BALANCE,"
          + "                 _updated_at=now() where _account_id=:ACCOUNT_ID",nativeQuery=true)
  void updateAccountBalance(@Param("NEW_BALANCE") double balance,
                             @Param("ACCOUNT_ID") int accountID);
	
  @Transactional
  @Modifying
  @Query(value="insert into _accounts(_user_id,_account_number,_account_name,_account_type) "
	+ "values(:USER_ID, :ACCOUNT_NUMBER, :ACCOUNT_NAME, :ACCOUNT_TYPE)",nativeQuery=true)
  void createAccount(@Param("USER_ID") int userId,
                     @Param("ACCOUNT_NUMBER") String accountNumber,
	           @Param("ACCOUNT_NAME") String accountName,
	           @Param("ACCOUNT_TYPE") String accountType);	
}
