package com.tsoyDmitriy.spendMoneyControl.controller;

import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.repository.RecordRepo;
import com.tsoyDmitriy.spendMoneyControl.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecordController {

    @Autowired
    RecordRepo recordRepo;

    RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("records")
    public String showAllRecords(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getUsername());
        model.addAttribute("records", recordRepo.findAll());
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
}










//    @GetMapping("recordPage/add/{userid}/{target}/{amount}/{comment}")
//    public @ResponseBody Record addRecord(@PathVariable("target") String target,
//                     @PathVariable("amount") long amount,
//                     @PathVariable("comment") String comment,
//                     @PathVariable("userid") long id) {
//        recordService.saveRecord(target, amount, comment, id);
//        return recordService.findAll();
//    }
