package com.tsoyDmitriy.spendMoneyControl.controller;

import com.tsoyDmitriy.spendMoneyControl.model.Person;
import com.tsoyDmitriy.spendMoneyControl.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("person")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("getall")
    public @ResponseBody Iterable<Person> getAllPerson() {
        return personService.findAll();
    }

    @GetMapping("findbyid/{id}")
    public @ResponseBody
    Optional<Person> findById (@PathVariable(value = "id") long id) {
        return personService.getPerson(id);
    }

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

    @GetMapping("delete/{id}")
    public @ResponseBody List<Person> deletePerson (@PathVariable(value = "id") long id) {
        personService.deletePerson(id);
        return personService.findAll();
    }

    @GetMapping("deleteall")
    public @ResponseBody List<Person> deleteAllPerson() {
        personService.deleteAll();
        return personService.findAll();
    }
}
