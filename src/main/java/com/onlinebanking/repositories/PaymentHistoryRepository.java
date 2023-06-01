package com.onlinebanking.repositories;

import java.util.List; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import com.onlinebanking.models.PaymentHistory;

@Repository
public interface PaymentHistoryRepository extends CrudRepository<PaymentHistory,Integer>{
  @Query(value="select * from _v_payments where _user_id=:USER_ID",nativeQuery = true)
  List<PaymentHistory> getPaymentHistoriesByUserId(@Param("USER_ID") int userId);
}
