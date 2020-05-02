package com.example.dilkursu.models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Classroom {

    private String name;
    private int capacity;
    private String branchName;
    private ArrayList<Lesson> lessons;

    public Classroom(String name, int capacity, String branchName) {
        this.name = name;
        this.capacity = capacity;
        this.branchName = branchName;
        this.lessons = new ArrayList<Lesson>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
}
