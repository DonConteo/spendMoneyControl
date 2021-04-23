package com.tsoyDmitriy.spendMoneyControl.model;

public class RecordDto {

    private String purpose;
    private double amount;
    private double percent;

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

    public double getPercent() {
        return percent;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }

    public RecordDto(String purpose, double amount, double percent) {
        this.purpose = purpose;
        this.amount = amount;
        this.percent = percent;
    }
}
