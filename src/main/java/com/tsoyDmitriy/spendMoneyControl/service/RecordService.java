package com.tsoyDmitriy.spendMoneyControl.service;

import com.tsoyDmitriy.spendMoneyControl.model.Record;
import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.repository.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepo recordRepo;

    public void saveRecord(String purpose, double amount, String comment, User user) {
        Record record = new Record(purpose, amount, comment, user);
        recordRepo.save(record);
    }
}
