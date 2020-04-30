package com.example.dilkursu.models;

import java.util.ArrayList;

public class Sales {

    private int invoiceId;
    private String executorId;
    private int courseId;
    private int total;
    private ArrayList<Payment> payments;

    public Sales SalesFactory(int invoiceId, String executorId, int courseId, int total, ArrayList<Payment> payments) {
        this.invoiceId = invoiceId;
        this.executorId = executorId;
        this.courseId = courseId;
        this.total = total;
        this.payments = payments;

        payments = new ArrayList<>();
        return null;
    }
}
