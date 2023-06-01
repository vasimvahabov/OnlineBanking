package com.onlinebanking.repositories;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.onlinebanking.models.Transaction;
import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Integer>{
  
  @Modifying
  @Transactional
  @Query(value="insert into _transactions(_account_id,_transaction_type,_amount,_source,"
              +"_status,_reason_code,_created_at) values(:ACCOUNT_ID, :TRANSACTION_TYPE,"
              +":AMOUNT, :SOURCE, :STATUS, :REASON_CODE, :CREATED_AT)",nativeQuery = true)
  void logTransaction(@Param("ACCOUNT_ID") int accountId,
                     @Param("TRANSACTION_TYPE") String transactionType,
                     @Param("AMOUNT") double amount,
                     @Param("SOURCE") String source,
                     @Param("STATUS") String status,
                     @Param("REASON_CODE") String reasonCode,
                     @Param("CREATED_AT") LocalDateTime createdAt);
}
