package com.tsoyDmitriy.spendMoneyControl.repository;

import com.tsoyDmitriy.spendMoneyControl.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RecordRepo extends JpaRepository<Record, Long> {

    @Modifying
    @Transactional
    @Query(value = "select * from RECORDS as r where r.USER_ID=:user_id", nativeQuery = true)
    List<Record> getRecordsForUser(@Param("user_id") long id);

    @Modifying
    @Transactional
    @Query(value = "select amount from records as a where a.user_id=:user_id and date_part('month', date) = date_part('month', now())", nativeQuery = true)
    List<Double> getSumThisMonth(@Param("user_id") long id);
}
