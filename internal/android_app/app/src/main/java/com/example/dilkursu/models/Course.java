package com.example.dilkursu.models;

import java.util.ArrayList;

public class Course {

    private int id;
    private String name;
    private String language;
    private int price;
    private ArrayList<Lesson> lessons;
    private ArrayList<Person> persons;

    public Course(String name, String language, int price) {
        this.name = name;
        this.language = language;
        this.price = price;
    }

    private Course(){

    }

    public static Course CourseFactory(int id, String name, String language, int price){
        Course course = new Course();
        course.id = id;
        course.name=name;
        course.price=price;
        course.language=language;

        return course;
    }


}
