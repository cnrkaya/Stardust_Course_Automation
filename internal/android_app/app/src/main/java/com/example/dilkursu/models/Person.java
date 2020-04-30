package com.example.dilkursu.models;

import java.util.ArrayList;

public abstract class Person {
    private String fname;
    private String lname;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> homeNumbers;
    private String address;
    private String id;
    private String branchName;

    public Person(String fname, String lname, ArrayList<String> phoneNumbers, ArrayList<String> homeNumbers, String address, String id, String branchName) {
        this.fname = fname;
        this.lname = lname;
        this.phoneNumbers = phoneNumbers;
        this.homeNumbers = homeNumbers;
        this.address = address;
        this.id = id;
        this.branchName = branchName;
    }

    public Person PersonFactory(String fname, String lname, ArrayList<String> phoneNumbers, ArrayList<String> homeNumbers, String address, String id, String branchName){
        return null;
    }

    public Person(){

    }






}
