package com.tsoyDmitriy.spendMoneyControl.service;

import com.tsoyDmitriy.spendMoneyControl.model.Person;
import com.tsoyDmitriy.spendMoneyControl.model.Record;
import com.tsoyDmitriy.spendMoneyControl.repository.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    RecordRepo recordRepo;

    public void saveRecord(String target, long amount, String comment, long person) {
        Record record = new Record(target, amount, comment, person);
        record.setRecord_target(target);
        record.setRecord_amount(amount);
        record.setRecord_comment(comment);

    }
}
