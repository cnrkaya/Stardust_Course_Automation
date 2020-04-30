package com.example.dilkursu.models;

import java.sql.Timestamp;

public class Payment {

    private int paymentId;
    private float total;
    private Timestamp timestamp;

    public Payment(float total, Timestamp timestamp) {
        this.total = total;
        this.timestamp = timestamp;
    }

    private Payment() {
    }

    public static Payment paymentFactory(int paymentId, float total, Timestamp timestamp) {
        // TODO:
        //Payment()


        return null;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public float getTotal() {
        return total;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
