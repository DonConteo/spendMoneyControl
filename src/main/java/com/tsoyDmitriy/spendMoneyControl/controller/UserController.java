package com.tsoyDmitriy.spendMoneyControl.controller;

import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/user";
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
