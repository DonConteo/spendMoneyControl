package com.tsoyDmitriy.spendMoneyControl.service;

import com.tsoyDmitriy.spendMoneyControl.model.Record;
import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.repository.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepo recordRepo;

    public void saveRecord(String purpose, double amount, String comment, User user) {
        Record record = new Record(purpose, amount, comment, user);
        record.setDate(new Date());
        recordRepo.save(record);
    }

    public void deleteRecord(long id) {
        recordRepo.delete(recordRepo.findById(id).orElseThrow());
    }

    public List<Record> getRecordsForUser(long id) {
        List<Record> list = new ArrayList<>(recordRepo.getRecordsForUser(id));
        return list;
    }

    public double spendThisMonth(long id) {
        List<Double> list = new ArrayList<>(recordRepo.getSumThisMonth(id));
        double sum = 0;
        for (double i : list) {
            sum += i;
        }
        return sum;
    }
}
