package com.tsoyDmitriy.spendMoneyControl.repository;

import com.tsoyDmitriy.spendMoneyControl.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepo extends JpaRepository<Record, Long> {
}
