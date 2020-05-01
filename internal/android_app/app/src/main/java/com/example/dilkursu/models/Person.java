package com.example.dilkursu.models;

import java.util.ArrayList;

public class Person {
    private String fname;
    private String lname;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> homeNumbers;
    private String address;
    private String id;
    private String branchName;
    private Branch branch;


    public Person(String fname, String lname, ArrayList<String> phoneNumbers, ArrayList<String> homeNumbers, String address, String id, String branchName) {
        this.fname = fname;
        this.lname = lname;
        this.phoneNumbers = phoneNumbers;
        this.homeNumbers = homeNumbers;
        this.address = address;
        this.id = id;
        this.branchName = branchName;
    }

    public Person PersonFactory(String fname, String lname, ArrayList<String> phoneNumbers, ArrayList<String> homeNumbers, String address, String id, String branchName) {
        return null;
    }

    public Person() {
        branch = new Branch();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList<String> getHomeNumbers() {
        return homeNumbers;
    }

    public void setHomeNumbers(ArrayList<String> homeNumbers) {
        this.homeNumbers = homeNumbers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
