package com.tsoyDmitriy.spendMoneyControl.service;

import com.tsoyDmitriy.spendMoneyControl.domain.Role;
import com.tsoyDmitriy.spendMoneyControl.model.Person;
import com.tsoyDmitriy.spendMoneyControl.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PersonService {

    private static final AtomicInteger PERSON_ID = new AtomicInteger();

    @Autowired
    PersonRepo personRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Optional<Person> getPerson(long id) {
        Optional<Person> person = personRepo.findById(id);
        return person;
    }

    public void savePerson(String name, String surname, String password){
        Person person = new Person();
        person.setId((long) PERSON_ID.incrementAndGet());
        person.setName(name);
        person.setSurname(surname);
        person.setPassword(passwordEncoder.encode(password));
        person.setRoles(Collections.singleton(Role.USER));
        personRepo.save(person);
    }

    public void deletePerson(long id) {
        personRepo.deleteById(id);
    }

    public void deleteAll(){
        personRepo.deleteAll();
    }
}
