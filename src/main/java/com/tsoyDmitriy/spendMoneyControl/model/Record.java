package com.tsoyDmitriy.spendMoneyControl.model;

import javax.persistence.*;

@Entity
@Table(name = "record_table")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long record_id;

    String record_target;
    long record_amount;
    String record_comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public Record(String target, long amount, String comment, Person person) {
        this.record_target = target;
        this.record_amount = amount;
        this.record_comment = comment;
        this.person = person;
    }

    public long getRecord_id() {
        return record_id;
    }
    public void setRecord_id(long record_id) {
        this.record_id = record_id;
    }

    public String getRecord_target() {
        return record_target;
    }
    public void setRecord_target(String record_target) {
        this.record_target = record_target;
    }

    public long getRecord_amount() {
        return record_amount;
    }
    public void setRecord_amount(long record_amount) {
        this.record_amount = record_amount;
    }

    public String getRecord_comment() {
        return record_comment;
    }
    public void setRecord_comment(String record_comment) {
        this.record_comment = record_comment;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
}
