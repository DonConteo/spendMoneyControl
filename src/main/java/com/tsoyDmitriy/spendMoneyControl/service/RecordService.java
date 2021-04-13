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
        List<Record> records = new ArrayList<>(recordRepo.getRecordsForUser(id));
        return records;
    }

    public double spendThisMonth(long id) {
        double spendThisMonth;
        try {
            spendThisMonth = recordRepo.getSumThisMonth(id);
        } catch (Exception e) {
            spendThisMonth = 0;
        }
        return spendThisMonth;
    }

    public double spendLastMonth(long id) {
        double spendLastMonth;
        try {
            spendLastMonth = recordRepo.getSumLastMonth(id);
        } catch (Exception e) {
            spendLastMonth = 0;
        }
        return spendLastMonth;
    }

    public double getPlannedSpends(long id) {
        double spendLastMonth;
        try {
            spendLastMonth = recordRepo.getSumLastMonth(id);
        } catch (Exception e) {
            spendLastMonth = 0;
        }

        double spendLastSecondMonth;
        try {
            spendLastSecondMonth = recordRepo.getSumLastSecondMonth(id);
        } catch (Exception e) {
            spendLastSecondMonth = 0;
        }

        double spendLastThirdMonth;
        try {
            spendLastThirdMonth = recordRepo.getSumLastThirdMonth(id);
        } catch (Exception e) {
            spendLastThirdMonth = 0;
        }

        double averageSum = spendLastMonth + spendLastSecondMonth + spendLastThirdMonth;

        if (spendLastThirdMonth == 0 && spendLastSecondMonth == 0) {
            return averageSum;
        }
        if (spendLastThirdMonth == 0) {
            return averageSum/2;
        }
        else return averageSum/3;
    }
}
