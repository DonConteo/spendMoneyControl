package com.tsoyDmitriy.spendMoneyControl.controller;

import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getall")
    public @ResponseBody Iterable<User> getAllPerson() {
        return userService.findAll();
    }



























//
//    @GetMapping("findbyid/{id}")
//    public @ResponseBody
//    Optional<Person> findById (@PathVariable(value = "id") long id) {
//        return personService.getPerson(id);
//    }

//    @GetMapping("add/{name}/{surname}")
//    public @ResponseBody List<Person> addNewPerson (@PathVariable(value = "name") String name,
//                                                    @PathVariable(value = "surname") String surname) {
//        personService.savePerson(name, surname);
//        return personService.findAll();
//    }

//    @GetMapping("update/{id}/{name}/{surname}")
//    public @ResponseBody Optional<Person> updatePerson (@PathVariable(value = "id") long id,
//                                                        @PathVariable(value = "name") String name,
//                                                        @PathVariable(value = "surname") String surname) {
//        personService.updatePerson(id, name, surname);
//        return personService.getPerson(id);
//    }
//
//    @GetMapping("delete/{id}")
//    public @ResponseBody List<Person> deletePerson (@PathVariable(value = "id") long id) {
//        personService.deletePerson(id);
//        return personService.findAll();
//    }
//
}
