package com.example.dilkursu.models;

import java.sql.Timestamp;

public class Invoice {

    private int invoiceId;
    private Timestamp timestamp;
    private int nPayments;
    private float total;
    private Person person;

    public Invoice(Timestamp timestamp, int nPayments, float total, Person person) {
        this.timestamp = timestamp;
        this.nPayments = nPayments;
        this.total = total;
        this.person = person;
    }

    public Invoice invoiceFactory(){
        return null;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getnPayments() {
        return nPayments;
    }

    public float getTotal() {
        return total;
    }

    public Person getPerson() {
        return person;
    }
}
