package com.tsoyDmitriy.spendMoneyControl.service;

import com.tsoyDmitriy.spendMoneyControl.model.Record;
import com.tsoyDmitriy.spendMoneyControl.model.RecordDto;
import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.repository.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RecordService {

    @Autowired
    RecordRepo recordRepo;

//Сохранение записи
    public void saveRecord(String purpose, double amount, String comment, User user) {
        Record record = new Record(purpose, amount, comment, user);
        record.setDate(new Date());
        recordRepo.save(record);
    }

//Удаление записи
    public void deleteRecord(long id) {
        recordRepo.delete(recordRepo.findById(id).orElseThrow());
    }

//Получение всех записей определенного пользователя
    public List<Record> getRecordsForUser(long id) {
        List<Record> records = new ArrayList<>(recordRepo.getRecordsForUser(id));
        return records;
    }

//Получение всех записей пользователя за текущий месяц
    public List<Record> getRecordsForUserThisMonth(long id) {
        List<Record> records = new ArrayList<>(recordRepo.getRecordsForUserThisMonth(id));
        return records;
    }

//Получение всех записей пользователя за прошлый месяц
    public List<Record> getRecordsForUserLastMonth(long id) {
        List<Record> records = new ArrayList<>(recordRepo.getRecordsForUserLastMonth(id));
        return records;
    }

//Получение суммы трат пользователя за текущий месяц
    public double getSpendThisMonth(long id) {
        double spendThisMonth;
        try {
            spendThisMonth = recordRepo.getSumThisMonth(id);
        } catch (Exception e) {
            spendThisMonth = 0;
        }
        return spendThisMonth;
    }

//Получение суммы трат пользователя за прошлый месяц
    public double getSpendLastMonth(long id) {
        double spendLastMonth;
        try {
            spendLastMonth = recordRepo.getSumLastMonth(id);
        } catch (Exception e) {
            spendLastMonth = 0;
        }
        return spendLastMonth;
    }

//Получение трат пользователя за все время
    public double getSpendAllTime(long id) {
        double spendAllTime;
        try {
            spendAllTime = recordRepo.getSumAllTime(id);
        } catch (Exception e) {
            spendAllTime = 0;
        }
        return spendAllTime;
    }

//Расчет предполагаемых трат на следующий месяц
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
            return averageSum / 2;
        } else return averageSum / 3;
    }

//Получение списка объектов для передачи в виде "категория-сумма-процент" за текущий месяц
    public List<RecordDto> getRecordDtosThisMonth(long id) {
        List<Record> records = recordRepo.getRecordsForUserThisMonth(id);
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
            DecimalFormat f = new DecimalFormat("##.00");
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

//Получение списка объектов для передачи в виде "категория-сумма-процент" за прошлый месяц
    public List<RecordDto> getRecordDtosLastMonth(long id) {
        List<Record> records = recordRepo.getRecordsForUserLastMonth(id);
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
            DecimalFormat f = new DecimalFormat("##.00");
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

//Получение списка объектов для передачи в виде "категория-сумма-процент" за все время
    public List<RecordDto> getRecordDtos(long id) {
        List<Record> records = recordRepo.getRecordsForUser(id);
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
            DecimalFormat f = new DecimalFormat("##.00");
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