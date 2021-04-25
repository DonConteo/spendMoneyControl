package com.tsoyDmitriy.spendMoneyControl.controller;

import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.repository.RecordRepo;
import com.tsoyDmitriy.spendMoneyControl.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecordController {

    @Autowired
    RecordRepo recordRepo;

    RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

//Главная страница
    @GetMapping("records")
    public ResponseEntity<String> showAllRecords(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getUsername());
        model.addAttribute("records", recordService.getRecordsForUserThisMonth(user.getId()));
        model.addAttribute("sumThisMonth", recordService.getSpendThisMonth(user.getId()));
        model.addAttribute("sumLastMonth", recordService.getSpendLastMonth(user.getId()));
        model.addAttribute("plannedSpends", recordService.getPlannedSpends(user.getId()));
        model.addAttribute("spends", recordService.getRecordDtosThisMonth(user.getId()));
        return ResponseEntity.ok("records");
    }

//Удаление записи
    @PostMapping("records/delete/{id}")
    public String deleteRecord(@AuthenticationPrincipal User user, @PathVariable(value = "id") long id, Model model) {
        recordService.deleteRecord(id);
        model.addAttribute("records", recordRepo.getRecordsForUser(user.getId()));
        return "redirect:/records";
    }

//Добавление записи
    @GetMapping("records/addRecord")
    public String addRecord() {
        return "addRecord";
    }

    @PostMapping("records/addRecord")
    public String addRecord(@AuthenticationPrincipal User user,
                            @RequestParam String purpose,
                            @RequestParam double amount,
                            @RequestParam String comment) {
        recordService.saveRecord(purpose, amount, comment, user);
        return "redirect:/records";
    }

//Страница трат за прошлый месяц
    @GetMapping("spendsLastMonth")
    public String getSpendsForLastMonth(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getUsername());
        model.addAttribute("records", recordService.getRecordsForUserLastMonth(user.getId()));
        model.addAttribute("sumLastMonth", recordService.getSpendLastMonth(user.getId()));
        model.addAttribute("spends", recordService.getRecordDtosLastMonth(user.getId()));
        return "spendsLastMonth";
    }

//Страница трат за все время
    @GetMapping("spendsAllTime")
    public String getSpendsForAllTime(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getUsername());
        model.addAttribute("records", recordService.getRecordsForUser(user.getId()));
        model.addAttribute("sumAllTime", recordService.getSpendAllTime(user.getId()));
        model.addAttribute("spends", recordService.getRecordDtos(user.getId()));
        return "spendsAllTime";
    }
}
