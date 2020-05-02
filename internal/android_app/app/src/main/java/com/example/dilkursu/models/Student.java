package com.example.dilkursu.models;

import android.util.Log;

import com.example.dilkursu.repository.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Student extends Person {

    private ArrayList<Sales> sales;
    private int groupNo;
    private String level;
    private String language;
    private Course course;

    public Student() {
        super();
        course = new Course();
    }

    public ArrayList<Sales> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sales> sales) {
        this.sales = sales;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
