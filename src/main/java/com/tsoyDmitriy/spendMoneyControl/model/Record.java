package com.tsoyDmitriy.spendMoneyControl.model;

import javax.persistence.*;

@Entity
@Table(name = "record_table")
public class Record {

    @Id
    private long id;

    String target;
    long amount;
    String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public Record(String target, long amount, String comment, Person person) {
        this.target = target;
        this.amount = amount;
        this.comment = comment;
        this.person = person;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
}
