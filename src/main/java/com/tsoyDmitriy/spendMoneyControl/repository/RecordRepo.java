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

//Получение всех записей пользователя
    @Modifying
    @Transactional
    @Query(value = "select * from RECORDS as r where r.USER_ID=:user_id", nativeQuery = true)
    List<Record> getRecordsForUser(@Param("user_id") long id);

//Получение записей пользователя за текущий месяц
    @Transactional
    @Query(value = "select * from RECORDS as r where r.USER_ID=:user_id and date_trunc('month', date) = date_trunc('month', now())", nativeQuery = true)
    List<Record> getRecordsForUserThisMonth(@Param("user_id") long id);

//Получение записей пользователя за прошлый месяц
    @Transactional
    @Query(value = "select * from RECORDS as r where r.USER_ID=:user_id and date_trunc('month', date) = date_trunc('month', current_date - interval '1' month)", nativeQuery = true)
    List<Record> getRecordsForUserLastMonth(@Param("user_id") long id);

//Получение суммы трат за все время
    @Transactional
    @Query(value = "select sum(amount) from records as a where a.user_id=:user_id", nativeQuery = true)
    double getSumAllTime(@Param("user_id") long id);

//Получение суммы трат за текущий месяц
    @Transactional
    @Query(value = "select sum(amount) from records as a where a.user_id=:user_id and date_trunc('month', date) = date_trunc('month', now())", nativeQuery = true)
    double getSumThisMonth(@Param("user_id") long id);

//Получение суммы трат за прошлый месяц
    @Transactional
    @Query(value = "select sum(amount) from records as a where a.user_id=:user_id and date_trunc('month', date) = date_trunc('month', current_date - interval '1' month)", nativeQuery = true)
    double getSumLastMonth(@Param("user_id") long id);

//Получение суммы трат за позапрошлый месяц
    @Transactional
    @Query(value = "select sum(amount) from records as a where a.user_id=:user_id and date_trunc('month', date) = date_trunc('month', current_date - interval '2' month)", nativeQuery = true)
    double getSumLastSecondMonth(@Param("user_id") long id);

//Получение суммы трат за три месяца назад
    @Transactional
    @Query(value = "select sum(amount) from records as a where a.user_id=:user_id and date_trunc('month', date) = date_trunc('month', current_date - interval '3' month)", nativeQuery = true)
    double getSumLastThirdMonth(@Param("user_id") long id);
}
