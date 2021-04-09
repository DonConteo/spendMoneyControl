package com.tsoyDmitriy.spendMoneyControl.model;

import javax.persistence.*;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String purpose;
    double amount;
    String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }
    public void setPerson(User user) {
        this.user = user;
    }

    public Record(String purpose, double amount, String comment, User user) {
        this.purpose = purpose;
        this.amount = amount;
        this.comment = comment;
        this.user = user;
    }

    public Record() {
    }
}
