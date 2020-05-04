package com.example.dilkursu.models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Instructor extends Person {

    private Timestamp startTimeStamp;
    private ArrayList<String> knownLanguages;
    private int[][] availableHours;

    private String pworking_hours;


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
        int[][] availableHours = new int[7][24];
        byte[] phours = Instructor.stringToBytesASCII(this.pworking_hours);

        int day;
        for(int i = 0; i < 21; i++){
            // Traverse each byte, 3x byte represents one day, in total 7 days
            day = i / 3;
            for(int j = 0; j < 8; j++){
                availableHours[day][ (i % 3)*8 + j] = phours[i] % 2;
                phours[i] >>>= 1;
            }
        }

        return availableHours;
    }

    public void setAvailableHours(int[][] availableHours) {
        this.availableHours = availableHours;
    }

    public String getPworking_hours() {
        return pworking_hours;
    }

    public void setPworking_hours(String pworking_hours) {
        this.pworking_hours = pworking_hours;
    }

    public static byte[] stringToBytesASCII(String str) {
        char[] buffer = str.toCharArray();
        byte[] b = new byte[buffer.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) buffer[i];
        }
        return b;
    }


}
