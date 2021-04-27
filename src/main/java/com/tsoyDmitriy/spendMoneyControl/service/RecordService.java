package com.tsoyDmitriy.spendMoneyControl.service;

import com.tsoyDmitriy.spendMoneyControl.model.Record;
import com.tsoyDmitriy.spendMoneyControl.Dto.RecordDto;
import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.repository.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

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

    public List<Record> getRecordsForUserThisMonth(long id) {
        List<Record> records = new ArrayList<>(recordRepo.getRecordsForUserThisMonth(id));
        return records;
    }

    public List<Record> getRecordsForUserLastMonth(long id) {
        List<Record> records = new ArrayList<>(recordRepo.getRecordsForUserLastMonth(id));
        return records;
    }

    public double getSpendThisMonth(long id) {
        double spendThisMonth;
        try {
            spendThisMonth = recordRepo.getSumThisMonth(id);
        } catch (Exception e) {
            spendThisMonth = 0;
        }
        return spendThisMonth;
    }

    public double getSpendLastMonth(long id) {
        double spendLastMonth;
        try {
            spendLastMonth = recordRepo.getSumLastMonth(id);
        } catch (Exception e) {
            spendLastMonth = 0;
        }
        return spendLastMonth;
    }

    public double getSpendAllTime(long id) {
        double spendAllTime;
        try {
            spendAllTime = recordRepo.getSumAllTime(id);
        } catch (Exception e) {
            spendAllTime = 0;
        }
        return spendAllTime;
    }

    public double getPlannedSpends(long id) {
        double spendLastMonth = 0.0;
        double spendLastSecondMonth = 0.0;
        double spendLastThirdMonth = 0.0;
        double divider = 1L;

        try {
            spendLastMonth = recordRepo.getSumLastMonth(id);
            spendLastSecondMonth = recordRepo.getSumLastSecondMonth(id);
            spendLastThirdMonth = recordRepo.getSumLastThirdMonth(id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (spendLastThirdMonth != 0 && spendLastSecondMonth != 0){
            divider = 3L;
        } else if (spendLastThirdMonth == 0 && spendLastSecondMonth != 0){
            divider = 2L;
        } else if (spendLastThirdMonth == 0 && spendLastSecondMonth == 0){
            divider = 1L;
        }

        return (spendLastMonth + spendLastSecondMonth + spendLastThirdMonth) / divider;
    }

    public List<RecordDto> getRecordDtosThisMonth(long id) {
        List<Record> records = recordRepo.getRecordsForUserThisMonth(id);
        return getRecordDtos(records);
    }

    public List<RecordDto> getRecordDtosLastMonth(long id) {
        List<Record> records = recordRepo.getRecordsForUserLastMonth(id);
        return getRecordDtos(records);
    }

    public List<RecordDto> getRecordDtos(long id) {
        List<Record> records = recordRepo.getRecordsForUser(id);
        return getRecordDtos(records);
    }

    private List<RecordDto> getRecordDtos(List<Record> records) {
        Map<String, Double> spends = new HashMap<>();
        List<RecordDto> recordDtos = new ArrayList<>();
        for (Record x : records) {
            String purpose = x.getPurpose();
            double amount = 0;
            for (Record s : records) {
                if (s.getPurpose().equals(purpose)) {
                    amount += s.getAmount();
                }
            }
            spends.put(purpose, amount);
        }
        double sum = 0;
        for (Map.Entry<String, Double> entry : spends.entrySet()) {
            sum += entry.getValue();
        }
        for (Map.Entry<String, Double> entry : spends.entrySet()) {
            double percent = entry.getValue() * 100 / sum;
            DecimalFormat f = new DecimalFormat("##.0");
            String formate = f.format(percent);
            double finalValue = 0;
            try {
                finalValue = (Double)f.parse(formate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            RecordDto recordDto = new RecordDto(entry.getKey(), entry.getValue(), finalValue);
            recordDtos.add(recordDto);
        }
        return recordDtos;
    }
}