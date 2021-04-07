package com.tsoyDmitriy.spendMoneyControl.service;

import com.tsoyDmitriy.spendMoneyControl.model.Person;
import com.tsoyDmitriy.spendMoneyControl.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepo personRepo;

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Optional<Person> getPerson(long id) {
        Optional<Person> person = personRepo.findById(id);
        return person;
    }

    public void savePerson(String name, String surname){
        Person person = new Person();
        person.setPerson_name(name);
        person.setPerson_surname(surname);
        personRepo.save(person);
    }

//    public void updatePerson(long id, String name, String surname) {
//        Optional<Person> person = personRepo.findById(id);
//        person.
//    }

    public void deletePerson(long id) {
        personRepo.deleteById(id);
    }

    public void deleteAll(){
        personRepo.deleteAll();
    }
}
