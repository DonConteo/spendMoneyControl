package com.tsoyDmitriy.spendMoneyControl.controller;

import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.repository.RecordRepo;
import com.tsoyDmitriy.spendMoneyControl.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("records")
public class RecordController {

    @Autowired
    RecordRepo recordRepo;

    RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping()
    public String showAllRecords(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getUsername());
        model.addAttribute("records", recordService.getRecordsForUser(user.getId()));
        model.addAttribute("sumThisMonth", recordService.spendThisMonth(user.getId()));
        return "records";
    }

    @GetMapping("addRecord")
    public String addRecord() {
        return ("addRecord");
    }

    @PostMapping("addRecord")
    public String addRecord(@AuthenticationPrincipal User user,
                            @RequestParam String purpose,
                            @RequestParam double amount,
                            @RequestParam String comment) {
        recordService.saveRecord(purpose, amount, comment, user);
        return "redirect:/records";
    }

    @PostMapping("delete/{id}")
    public String deleteRecord(@AuthenticationPrincipal User user, @PathVariable(value = "id") long id, Model model) {
        recordService.deleteRecord(id);
        model.addAttribute("records", recordRepo.getRecordsForUser(user.getId()));
        return "redirect:/records";
    }
}
