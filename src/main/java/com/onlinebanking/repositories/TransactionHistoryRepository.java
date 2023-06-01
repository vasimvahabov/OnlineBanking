package com.onlinebanking.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.onlinebanking.models.TransactionHistory;
import java.util.List;

@Repository
public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory,Integer>{
  @Query(value="select * from _v_transactions where _user_id=:USER_ID",nativeQuery = true)
  List <TransactionHistory> getTransactionsByUserId(@Param("USER_ID") int userId);
}
