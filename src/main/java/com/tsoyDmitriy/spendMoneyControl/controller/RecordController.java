package com.tsoyDmitriy.spendMoneyControl.controller;

import com.tsoyDmitriy.spendMoneyControl.model.Person;
import com.tsoyDmitriy.spendMoneyControl.model.Record;
import com.tsoyDmitriy.spendMoneyControl.service.PersonService;
import com.tsoyDmitriy.spendMoneyControl.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("recordPage")
public class RecordController {

    RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("recordPage/add/{userid}/{target}/{amount}/{comment}")
    public @ResponseBody Record addRecord(@PathVariable("target") String target,
                     @PathVariable("amount") long amount,
                     @PathVariable("comment") String comment,
                     @PathVariable("userid") long id) {
        recordService.saveRecord(target, amount, comment, id);
        return recordService.findAll();
    }
}
