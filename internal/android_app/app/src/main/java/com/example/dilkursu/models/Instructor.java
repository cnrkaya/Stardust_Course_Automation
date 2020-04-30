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

    public boolean isAvailable(Timestamp timestamp){
        return false;
    }

}
