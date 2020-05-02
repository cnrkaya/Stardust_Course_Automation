package com.example.dilkursu.models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Instructor extends Person {

    private Timestamp startTimeStamp;
    private ArrayList<String> knownLanguages;
    private int[][] availableHours;


    public Instructor(String fname, String lname, ArrayList<String> phoneNumbers, ArrayList<String> homeNumbers, String address, String id, String branchName, Timestamp startTimeStamp, ArrayList<String> knownLanguages) {
        super(fname, lname, phoneNumbers, homeNumbers, address, id, branchName);
        this.startTimeStamp = startTimeStamp;
    }

    public Instructor() {
        super();
        startTimeStamp = new Timestamp(System.currentTimeMillis());
        knownLanguages = new ArrayList<>();
    }

    public boolean isAvailable(Timestamp timestamp) {
        return false;
    }

    public Timestamp getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(Timestamp startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public ArrayList<String> getKnownLanguages() {
        return knownLanguages;
    }

    public void setKnownLanguages(ArrayList<String> knownLanguages) {
        this.knownLanguages = knownLanguages;
    }

    public int[][] getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(int[][] availableHours) {
        this.availableHours = availableHours;
    }
}
