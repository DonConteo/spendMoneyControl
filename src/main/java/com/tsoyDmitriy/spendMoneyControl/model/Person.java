package com.tsoyDmitriy.spendMoneyControl.model;

import javax.persistence.*;

@Entity
@Table(name = "person_table")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long person_id;

    private String person_name;
    private String person_surname;

    public Long getPerson_id() {
        return person_id;
    }
    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }
    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_surname() {
        return person_surname;
    }
    public void setPerson_surname(String person_surname) {
        this.person_surname = person_surname;
    }
}
