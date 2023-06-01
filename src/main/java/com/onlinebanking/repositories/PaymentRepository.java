package com.onlinebanking.repositories;

import java.time.LocalDateTime; 
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.onlinebanking.models.Payment;
import jakarta.transaction.Transactional;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Integer>{
  
  @Modifying
  @Transactional
  @Query(value="insert into _payments(_account_id,_beneficiary,_beneficiary_acc_no,"
              +"_amount,_reference_no,_status,_reason_code,_created_at) values(:ACCOUNT_ID,"
              +" :BENEFICIARY, :BENEFICIARY_ACC_NO, :AMOUNT, :REFERENCE_NO,"
              +":STATUS, :REASON_CODE, :CREATED_AT);",nativeQuery=true)
  void makePayment(@Param("ACCOUNT_ID") int accountId,
		@Param("BENEFICIARY") String beneficiary,
		@Param("BENEFICIARY_ACC_NO") String beneficiaryAccNo,
                    @Param("AMOUNT") double amount,
                    @Param("REFERENCE_NO") String referenceNo,
                    @Param("STATUS") String status,
                    @Param("REASON_CODE") String reasonCode,
                    @Param("CREATED_AT") LocalDateTime createdAt);
}
